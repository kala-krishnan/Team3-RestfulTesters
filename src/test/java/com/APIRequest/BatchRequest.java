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
	public int getIdBatch() {
		BatchPojo batchid=(BatchPojo) context.get("BatchPojo");
		return batchid.getId();
	}
	
	public void setNewBatchRequest(String requestType) throws Exception 
	{
		BatchPojo batch = TestDataLoader.loadTestDatafor_Post_Put(requestType, BatchPojo.class);
		context.set("BatchPojo", batch);
	}
	
	
	public void PostNewBatchRequest(String Scenario)
	{
		String EndPoint = APIResources.valueOf("APIAddBatch").getResources();
		if (Scenario.equals("AddBatchInvalidEp")) 
			EndPoint = APIResources.valueOf("APIAddBatch").getResources() + "Invalid";
		
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(batch).log().all()
				.post(EndPoint);       
		context.set("batchResponse", response); 
		if (response.getStatusCode()==201 && Scenario.equals("AddBatchValid"))
		{
			context.set("batchId", response.jsonPath().get("batchId"));
			context.set("batchName", response.jsonPath().getString("batchName"));
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ "+context.get("batchName"));
			System.out.println("##################################################### "+context.get("batchId"));
		}

	}
	
	public void GetAllBatchRequest(String Scenario)
	{
		String EndPoint = APIResources.valueOf("APIGetAllBatch").getResources();
		if (Scenario.equals("GetAllBatchInValidEP")) 
			EndPoint = APIResources.valueOf("APIGetAllBatch").getResources() + "Invalid";

		response = RestAssured.given().spec(requestHeadersWithToken())
				.log().all()
				.get(EndPoint);       
		context.set("batchResponse", response); 
	}
	
	public void GetBatchByIDRequest(String Scenario)
	{
		String EndPoint = APIResources.valueOf("APIGetBatchByID").getResources()+getIdBatch();
		if (Scenario.equals("GetByBatchIDInValidEP")) 
			EndPoint = APIResources.valueOf("APIGetBatchByID").getResources() + "Invalid"+getIdBatch();

		response = RestAssured.given().spec(requestHeadersWithToken())
				.log().all()
				.get(EndPoint);       
		context.set("batchResponse", response); 
	}
	
}
