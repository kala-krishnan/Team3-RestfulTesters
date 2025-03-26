package com.APIRequest;

import java.io.FileNotFoundException;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.context.TextContext;
import com.pojoclass.BatchPojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BatchRequest extends SpecificationClass{
	
	Response response;
	BatchPojo batchPojo = new BatchPojo();
	ScenarioContext context = ScenarioContext.getInstance();
	RequestSpecification reqSpec;
	public BatchRequest() throws FileNotFoundException
	{
		
	}
	public int getBatchStatusCode() {
		BatchPojo batchStatusCode=(BatchPojo) context.get("BatchPojo");
		return batchStatusCode.getStatusCode();
	}
	public String getBatchStatusLine() {
		BatchPojo batchStatusLine=(BatchPojo) context.get("BatchPojo");
		return batchStatusLine.getStatusText();
	}
	public String getIdBatch() {
		BatchPojo batchid=(BatchPojo) context.get("BatchPojo");
		return batchid.getId();
	}
	public String getNameBatch() {
		BatchPojo batchName=(BatchPojo) context.get("BatchPojo");
		return batchName.getName();
	}	
	public BatchPojo getNewBatchRequest() {
		BatchPojo batch=(BatchPojo) context.get("BatchPojo");
		return batch;
	}
	public void setNewBatchRequest(String requestType) throws Exception 
	{
		BatchPojo batch = TestDataLoader.loadTestDatafor_Post_Put(requestType, BatchPojo.class);
		context.set("BatchPojo", batch);
	}
	
//----------------------------------------  POST BATCH ----------------------------------------------
	
	public void PostNewBatchRequest(String Scenario)
	{	
		
		int PrgId =Integer.valueOf(getIdBatch());	
		if(PrgId == 0)  PrgId = TextContext.getProgramId();
				
		
		
		String EndPoint = APIResources.valueOf("APIAddBatch").getResources();			
		if (Scenario.equals("AddBatchInvalidEp")) 
			EndPoint = APIResources.valueOf("APIAddBatch").getResources() + "Invalid";
		
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);
		batch.setProgramId(PrgId);
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(batch).log().all()
				.post(EndPoint);       
		context.set("batchResponse", response); 
		if (response.getStatusCode()==201 && Scenario.equals("AddBatchValid"))
		{			
			// Setting BatchId for chaining 
			int BatchID = response.jsonPath().get("batchId");
			String BatchName = response.jsonPath().get("batchName");
			TextContext.setBatchId(BatchID); 
			TextContext.setBatchName(BatchName);
			
		}

	}
	
	public void NoAuthPostNewBatchRequest()
	{
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.body(batch).log().all()
				.post(APIResources.valueOf("APIAddBatch").getResources());       
		context.set("batchResponse", response); 	
	}
	
//--------------------------------------- GET ALL BATCH-------------------------------------------
	
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
	
	public void GetAllBatchSearchString(String Scenario)
	{
		response = RestAssured.given().spec(requestHeadersWithToken())
				.log().all()
				.queryParam("searchString", "sdet")
				.get(APIResources.valueOf("APIGetAllBatch").getResources());       
		context.set("batchResponse", response); 
	}
	
	public void NoAuthGetAllBatchRequest()
	{
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.get(APIResources.valueOf("APIGetAllBatch").getResources());       
		context.set("batchResponse", response); 
	}
	
//--------------------------------------- GET BATCH BY ID-------------------------------------------
	
	public void GetBatchByIDRequest(String Scenario)
	{
		int id =Integer.valueOf(getIdBatch());
		if(id == 0)  id = TextContext.getBatchId();

		String EndPoint = APIResources.valueOf("APIGetBatchByID").getResources();
		if (Scenario.equals("GetByBatchIDInValidEP")) 
			EndPoint = APIResources.valueOf("APIGetBatchByID").getResources() + "API";

		response = RestAssured.given().spec(requestHeadersWithToken())
				.log().all()
				.pathParam("batchId", id)
				.get(EndPoint);       
		context.set("batchResponse", response); 
	}
	
	public void NoAuthGetBatchByIDRequest()
	{
		int id = TextContext.getBatchId();	
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.pathParam("batchId", id)
				.get(APIResources.valueOf("APIGetBatchByID").getResources());       
		context.set("batchResponse", response); 
	}
	
//--------------------------------------- GET BATCH BY NAME-------------------------------------------
	
	public void GetBatchByNameRequest(String Scenario)
	{
		String name = getNameBatch();
		
		if(name.equals("0"))  name = TextContext.getBatchName();
		String EndPoint = APIResources.valueOf("APIGetBatchByName").getResources();
		if (Scenario.equals("GetByBatchNameInValidEP")) 
			EndPoint = APIResources.valueOf("APIGetBatchByName").getResources() + "API";

		response = RestAssured.given().spec(requestHeadersWithToken())
				.log().all()
				.pathParam("batchName", name)
				.get(EndPoint);       
		context.set("batchResponse", response); 
	}
	public void NoAuthGetBatchByNameRequest()
	{
		String name = getNameBatch();
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.pathParam("batchName", name)
				.get(APIResources.valueOf("APIGetBatchByName").getResources());       
		context.set("batchResponse", response); 
	}	
//--------------------------------------- GET BATCH BY PROGRAM ID------------------------------------------
	public void GetBatchByPrgmIDRequest(String Scenario)
	{
		int id =Integer.valueOf(getIdBatch());
		if(id == 0)  id = TextContext.getProgramId();
		
		String EndPoint = APIResources.valueOf("APIGetBatchByPrgmID").getResources();
		if (Scenario.equals("GetByProgramIDInValidEP")) 
			EndPoint = APIResources.valueOf("APIGetBatchByPrgmID").getResources() + "Invalid";

		response = RestAssured.given().spec(requestHeadersWithToken())
				.log().all()
				.pathParam("programId", id)
				.get(EndPoint);       
		context.set("batchResponse", response); 
	}
	public void NoAuthGetBatchByPrgmIDRequest()
	{
		int id = TextContext.getProgramId();
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.pathParam("programId", id)
				.get(APIResources.valueOf("APIGetBatchByPrgmID").getResources());       
		context.set("batchResponse", response); 
	}
	
//--------------------------------------- UPDATE BATCH BY ID -------------------------------------------	
	public void PutBatchRequest(String Scenario)
	{
		int PrgmID = TextContext.getProgramId();
		
		
		int id =Integer.valueOf(getIdBatch());
		if(id == 0)  id = TextContext.getBatchId();
		
		String EndPoint = APIResources.valueOf("APIUpdateBatchByID").getResources();
		if (Scenario.equals("PutBatchInvalidEp")) 
			EndPoint = APIResources.valueOf("APIUpdateBatchByID").getResources() + "Invalid";
		
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);
		batch.setProgramId(PrgmID);
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(batch).log().all()
				.pathParam("batchId", id)
				.put(EndPoint);       
		context.set("batchResponse", response); 
		if (response.getStatusCode()==200 && Scenario.equals("PutBatchValid"))
		{
			context.set("batchName", response.jsonPath().getString("batchName"));

		}
	}
	public void NoAuthPutBatchRequest()
	{
		int id = TextContext.getBatchId();
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.body(batch).log().all()
				.pathParam("batchId", id)
				.put(APIResources.valueOf("APIUpdateBatchByID").getResources());       
		context.set("batchResponse", response); 
	}
	
	//--------------------------------------- DELETE BATCH BY ID -----------------------------------------
	
	public void DeleteBatchRequest(String Scenario)
	{
		int id =Integer.valueOf(getIdBatch());
		

		if(id == 0)  id = TextContext.getBatchId();
		
		String EndPoint = APIResources.valueOf("APIDeleteBatchByID").getResources();
		if (Scenario.equals("DeleteBatchInvalidEP")) 
			EndPoint = APIResources.valueOf("APIDeleteBatchByID").getResources() + "Invalid";
		
		BatchPojo batch = context.get("BatchPojo", BatchPojo.class);
		response = RestAssured.given().spec(requestHeadersWithToken())
				.log().all()
				.pathParam("batchId", id)
				.delete(EndPoint);       
		context.set("batchResponse", response); 
	}
	
	public void NoAuthDeleteBatchRequest()
	{
		int id = TextContext.getBatchId();
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.pathParam("batchId", id)
				.delete(APIResources.valueOf("APIDeleteBatchByID").getResources());       
		context.set("batchResponse", response); 
	}
	
}
