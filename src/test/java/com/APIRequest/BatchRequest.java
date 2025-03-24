package com.APIRequest;

import java.io.FileNotFoundException;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.pojoclass.BatchPojo;
import com.pojoclass.UserPojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BatchRequest extends SpecificationClass{
	
	Response response;
	ScenarioContext context;
	
	public BatchRequest(ScenarioContext context) throws FileNotFoundException
	{
		super(context);
		this.context = context;
	}
	public int getBatchStatusCode() {
		BatchPojo batchStatusCode=(BatchPojo) context.get("BatchPojo");
		return batchStatusCode.getStatusCode();
	}
	public String getBatchStatusLine() {
		BatchPojo batchStatusLine=(BatchPojo) context.get("BatchPojo");
		return batchStatusLine.getStatusText();
	}
	public String getBatchContentType() {
		BatchPojo batchContentType=(BatchPojo) context.get("BatchPojo");
		return batchContentType.getContentType();
	}
	
	public void setNewBatchRequest(String requestType) throws Exception 
	{
		BatchPojo batch = TestDataLoader.loadTestDatafor_Post_Put(requestType, BatchPojo.class);
		System.out.println("user statuscode"+batch.getStatusCode());
		context.set("BatchPojo", batch);
	}
	
	
	public void PostNewBatchRequest()
	{
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(batch).log().all()
				.post(APIResources.valueOf("APIAddBatch").getResources());       
		context.set("batchResponse", response); 
		if (response.getStatusCode()==201)
		{
			context.set("batchId", response.jsonPath().get("batchId"));
			context.set("batchName", response.jsonPath().getString("batchName"));
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ "+context.get("batchName"));
		System.out.println("##################################################### "+context.get("batchId"));
		}
 
	}
	
	public void PostNewBatchInvalidEpRequest()
	{
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);	
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(batch).log().all()
				.post(APIResources.valueOf("APIAddBatch").getResources()+"Invaild");       
		context.set("batchResponse", response); 
	}

}
