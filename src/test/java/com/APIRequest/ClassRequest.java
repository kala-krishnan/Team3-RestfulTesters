package com.APIRequest;

import java.io.FileNotFoundException;

import com.APIResponse.UserModuleResponseValidation;
import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.context.TextContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.pojoclass.ClassPojo;
import com.pojoclass.UserPojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ClassRequest extends SpecificationClass {
	Response response;
	ScenarioContext context =ScenarioContext.getInstance();
	String paramForGetEndpoint;
	JsonNode getTestData;
	RequestSpecification resquest;
	String requestType;
	UserModuleResponseValidation resValidation;
	int responseCode;
	String Endpoints;		

	public ClassRequest() throws FileNotFoundException
	{
		resValidation = new UserModuleResponseValidation();
	}	

	public int geClassStatusCode() { // testdata status code
		ClassPojo classStatusCode=(ClassPojo) context.get("ClassPojo");
		return classStatusCode.getStatusCode();
	}

	/****************************** POST *********************/
	public void newClassRequest(String requestType) throws Exception 
	{
		ClassPojo ClassModule = TestDataLoader.loadTestDatafor_Post_Put(requestType, ClassPojo.class);
		context.set("ClassPojo", ClassModule);
	}

	public void PostNewClassRequest()
	{
		ClassPojo pojo = context.get("ClassPojo", ClassPojo.class);
		String chaining_userID = null;
		Integer chaining_batchId =null;
		
		//getting the chaining variable programid,batchid, userid
		String type = pojo.getType();
		if(type.equalsIgnoreCase("Valid"))
		{
		 chaining_userID = TextContext.getUserId(); 
		 chaining_batchId=TextContext.getBatchId();
		 if(chaining_userID!=null)
			{		
				pojo.setClassStaffId(chaining_userID);
				if(chaining_batchId!=null)
					pojo.setBatchId(chaining_batchId);
			}
		}

		System.out.println(pojo.toString());
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(pojo).log().all()
				.post(APIResources.valueOf("APIClassPost").getResources());       
		context.set("classResponse", response); 
		String classID  = response.jsonPath().getString("csId");
		TextContext.setUserId(classID); // Setting userid for chaining 

		System.out.println(response.prettyPrint());
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Headers: " + response.getHeaders());
		System.out.println("Response Body: " + response.getBody().asString());
		
	}

	/****************************** PUT *********************/
	public void updateClassRequest(String requestType) throws Exception 
	{
		ClassPojo ClassModule = TestDataLoader.loadTestDatafor_Post_Put(requestType, ClassPojo.class);
		context.set("ClassPojo", ClassModule);
	}

	public void UpdateSendClassRequest(String requestType)
	{
		//getting the chaining variable programid,batchid, userid
		String chaining_userID = TextContext.getUserId(); 
		Integer chaining_batchId=TextContext.getBatchId();

		ClassPojo pojo = context.get("ClassPojo", ClassPojo.class);
		if(chaining_userID!=null)
			pojo.setClassStaffId(chaining_userID);
		if(chaining_batchId==null)
			pojo.setBatchId(chaining_batchId);

		String endpoint_tmp = requestType;				
		String endpoint =endpoint_tmp.substring(3)	;

		System.out.println(pojo.toString());
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(pojo).log().all()
				.put(APIResources.valueOf(endpoint).getResources());      
		context.set("classResponse", response); 
		String classID  = response.jsonPath().getString("csId");
		TextContext.setUserId(classID); // Setting userid for chaining 

		System.out.println(response.prettyPrint());
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Headers: " + response.getHeaders());
		System.out.println("Response Body: " + response.getBody().asString());

		// getting the batchid for chaining
		int storedBatchID = TextContext.getBatchId();
		System.out.println("Printing chaining batch ID in user module" + storedBatchID );
	}

	/****************** GET with parameter Request  *************************/

	public JsonNode setGetClassRequestBody(String requestType,String parameterValue) throws Exception
	{
		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
		paramForGetEndpoint = getTestData.get(parameterValue).asText();
		return getTestData;
	}

	public Response sendGetClassReqWithBody() {
		System.out.println(APIResources.valueOf("APIGetAllClassesByBatchId").getResources()+ paramForGetEndpoint);
		Response response = RestAssured.given().spec(requestHeadersWithToken())				
				.get(APIResources.valueOf("APIGetAllClassesByBatchId").getResources()+ paramForGetEndpoint);				
		return response;
	}

	/****************** GET without parameter Request *************************/

	public JsonNode setGetClassRequest(String requestType) throws Exception 
	{
		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
		return getTestData;
	}

	public Response sendGetClassReqWithOutBody() {
		Response response = RestAssured.given().spec(requestHeadersWithToken())				
				.get(APIResources.valueOf("APIGetAllClasses").getResources());				
		return response;
	}

}
//
//	/****************** GET with parameter Request  *************************/
//
//	public JsonNode setGetClassRequestBody(String requestType,String parameterValue) throws Exception
//	{
//		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
//		paramForGetEndpoint = getTestData.get(parameterValue).asText();
//		return getTestData;
//	}
//
//	public Response sendGetStaffidReqWithBody() {
//		System.out.println(APIResources.valueOf("APIGetAllClassesByStaffId").getResources()+ paramForGetEndpoint);
//		Response response = RestAssured.given().spec(requestHeadersWithToken())				
//				.get(APIResources.valueOf("APIGetAllClassesByStaffId").getResources()+ paramForGetEndpoint);				
//		return response;
//	}
//	public JsonNode setGetClassidRequestBody(String requestType, String parameterValue)throws Exception {
//		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
//		paramForGetEndpoint = getTestData.get(parameterValue).asText();
//		return getTestData;
//	}
//	public Response sendGetClassIdReqWithBody() {
//		System.out.println(APIResources.valueOf("APIGetClassRecordingsByClassId").getResources()+ paramForGetEndpoint);
//		Response response = RestAssured.given().spec(requestHeadersWithToken())				
//				.get(APIResources.valueOf("APIGetClassRecordingsByClassId").getResources()+ paramForGetEndpoint);				
//		return response;
//	}
//
//
//
//
//
//	/****************** GET without parameter Request *************************/
//
//	public JsonNode setGetClassRequest(String requestType) throws Exception 
//	{
//		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
//		return getTestData;
//	}
//
//	public Response sendGetClassReqWithOutBody() {
//		Response response = RestAssured.given().spec(requestHeadersWithToken())				
//				.get(APIResources.valueOf("APIGetAllRecordings").getResources());				
//		return response;
//	}
//
//	public Response setNewGetClass(String requestType) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
//
//
//
//
//
