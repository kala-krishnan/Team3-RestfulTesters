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
	
	public void setNewBatchRequest(String requestType) throws Exception 
	{
		BatchPojo batch = TestDataLoader.loadTestDatafor_Post_Put(requestType, BatchPojo.class);
		System.out.println("user statuscode"+batch.getStatusCode());
		context.set("BatchPojo", batch);
	}
	
	public void PostNewBatchRequest()
	{
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);
		System.out.println(batch.toString());
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(batch).log().all()
				.post(APIResources.valueOf("APIAddBatch").getResources());       
		context.set("batchResponse", response); 
//		System.out.println(response.prettyPrint());
//		System.out.println("Status Code: " + response.getStatusCode());
//		System.out.println("Response Headers: " + response.getHeaders());
//		System.out.println("Response Body: " + response.getBody().asString());
 
	}


}
