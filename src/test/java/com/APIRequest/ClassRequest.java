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
	public int responseCode()
	{
		return responseCode;	
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
				.pathParam("batchId",paramForGetEndpoint)
				.get(APIResources.valueOf("APIGetAllClassesByBatchId").getResources());				
		return response;
	}

	/****************** GET without parameter Request *************************/

	public JsonNode setGetClassRequest(String requestType) throws Exception 
	{
		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
		return getTestData;
	}

	public Response sendGetClassReqWithOutBody(String reqType) {
		Endpoints = APIResources.valueOf("APIGetAllClasses").getResources();
		if(reqType.equals("GetAllClassesInvalidEndpoint"))
		{
			Endpoints = APIResources.valueOf("APIGetAllClasses").getResources()+"sss";
		}
		Response response = RestAssured.given().spec(requestHeadersWithToken())				
				.get(Endpoints);
		
		return response;
	}

/****** GET without parameter Request *************************/
public JsonNode getClassRequest(String requestType) throws Exception
{
	
	JsonNode getTestData_get = TestDataLoader.loadTestDatafor_Get(requestType);
	
	return getTestData_get;
}
public Response sendGetClassReqWithOutBody(String endpointValue,String status) {
	Response response =null;
	String endpoint = "API"+endpointValue;
	System.out.println(endpoint);
	if(!status.equalsIgnoreCase("405"))
	{}
	else
	{
		response = RestAssured.given().spec(requestHeadersWithToken())				
				.post(APIResources.valueOf(endpoint).getResources());
	}
	return response;
}
/**************************GetAllClassByClassId*************************/
public JsonNode setGetClassRequestBodyByClassId(String requestType,String parameterValue) throws Exception
{
JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
paramForGetEndpoint = getTestData.get(parameterValue).asText();
return getTestData;
}
public Response sendGetClassReqWithBodyByClassId(String requestType,String paramValue) {
	if (requestType.equals("GetClassByClassId")) {
		//paramForGetEndpoint=TextContext.getClassId().toString();
	}
	System.out.println(APIResources.valueOf("APIGetClassByID").getResources()+ paramForGetEndpoint);
	Response response = RestAssured.given().spec(requestHeadersWithToken())	
			.pathParam("classId",paramForGetEndpoint)
			.get(APIResources.valueOf("APIGetClassByID").getResources());				
	return response;
}
/**********************************GetAllClassByClassTopic**********************************/
public JsonNode setGetClassRequestBodyByClassTopic(String requestType,String parameterValue) throws Exception
{
JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
paramForGetEndpoint = getTestData.get(parameterValue).asText();


return getTestData;
}
public Response sendGetClassReqWithBodyByClassTopic(String requestType,String paramValue) {
	if (requestType.equals("GetClassByClassTopic")) {
		//paramForGetEndpoint=TextContext.getClassId().toString();
	}
	System.out.println(APIResources.valueOf("APIGetAllClassesByClassTopic").getResources()+ paramForGetEndpoint);
	Response response = RestAssured.given().spec(requestHeadersWithToken())	
			.pathParam("classTopic",paramForGetEndpoint)
			.get(APIResources.valueOf("APIGetAllClassesByClassTopic").getResources());				
	return response;
}
/**********************************GetClassRecordingByBatchID**********************************/
public JsonNode setGetClassRequestBodyByClassRecordingByBatchId(String requestType,String parameterValue) throws Exception
{
JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
System.out.println("classmodule"+getTestData.get(parameterValue).asText());
paramForGetEndpoint = getTestData.get(parameterValue).asText();
return getTestData;
}
public Response sendGetClassReqWithBodyByClassRecordingByBatchId(String requestType,String paramValue) {
	if (requestType.equals("GetClassByBatchId")) {
		//paramForGetEndpoint=TextContext.getClassId().toString();
	}
	System.out.println(""+paramValue);
	System.out.println(APIResources.valueOf("APIGetClassByBatchId").getResources()+ paramForGetEndpoint);
	Response response = RestAssured.given().spec(requestHeadersWithToken())	
			.pathParam("batchId",paramForGetEndpoint)
			.get(APIResources.valueOf("APIGetClassByBatchId").getResources());				
	return response;
}

/****************************************GetClassRecordingByClassID********************************/
public JsonNode setGetClassRequestBodyByClassRecordingByClassId(String requestType,String parameterValue) throws Exception
{
JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
System.out.println("classmodule"+getTestData.get(parameterValue).asText());
paramForGetEndpoint = getTestData.get(parameterValue).asText();
return getTestData;
}
public Response sendGetClassReqWithBodyByClassRecordingByClassId(String requestType,String paramValue) {
	if (requestType.equals("GetClassRecordingsByClassId")) {
		//paramForGetEndpoint=TextContext.getClassId().toString();
	}
	System.out.println(""+paramValue);
	System.out.println(APIResources.valueOf("APIGetClassRecordingsByClassId").getResources()+ paramForGetEndpoint);
	Response response = RestAssured.given().spec(requestHeadersWithToken())	
			.pathParam("classId",paramForGetEndpoint)
			.get(APIResources.valueOf("APIGetClassRecordingsByClassId").getResources());				
	return response;
}
/*************************************GetAllRecordings************************************/
public Response sendGetClassReqWithOutBodyClass(String reqType) {
	Endpoints = APIResources.valueOf("APIGetAllRecordings").getResources();
	if(reqType.equals("GetAllClassRecordingInvalidEndpoint"))
	{
		Endpoints = APIResources.valueOf("APIGetAllRecordings").getResources()+"sss";
	}
	System.out.println(Endpoints);
	Response response = RestAssured.given().spec(requestHeadersWithToken())				
			.get(Endpoints);
	
	return response;
}
/******************************GetAllClassesStaffId**********************/
public JsonNode setGetClassRequestBodyClassByStaffId(String requestType,String parameterValue) throws Exception
{
JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
System.out.println("classmodule"+getTestData.get(parameterValue).asText());
paramForGetEndpoint = getTestData.get(parameterValue).asText();
return getTestData;
}
public Response sendGetClassReqWithBodyClassByStaffId(String requestType,String paramValue) {
	if (requestType.equals("GetAllClassesByStaffId")) {
		//paramForGetEndpoint=TextContext.getClassId().toString();
	}
	System.out.println(""+paramValue);
	System.out.println(APIResources.valueOf("APIGetAllClassesByStaffId").getResources()+ paramForGetEndpoint);
	Response response = RestAssured.given().spec(requestHeadersWithToken())	
			.pathParam("staffId",paramForGetEndpoint)
			.get(APIResources.valueOf("APIGetAllClassesByStaffId").getResources());				
	return response;
}
	public JsonNode setGetClassidRequestBody(String requestType, String parameterValue)throws Exception {
		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
		paramForGetEndpoint = getTestData.get(parameterValue).asText();
		return getTestData;
	}
	public Response sendGetClassIdReqWithBody() {
		System.out.println(APIResources.valueOf("APIGetClassRecordingsByClassId").getResources()+ paramForGetEndpoint);
		Response response = RestAssured.given().spec(requestHeadersWithToken())				
				.get(APIResources.valueOf("APIGetClassRecordingsByClassId").getResources()+ paramForGetEndpoint);				
		return response;
	}
	
	/*********************DeleteClass********************/
	public JsonNode setGetClassRequestBodyDeleteClass(String requestType,String parameterValue) throws Exception
	{
	JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
	System.out.println("classmodule"+getTestData.get(parameterValue).asText());
	paramForGetEndpoint = getTestData.get(parameterValue).asText();
	return getTestData;
	}
	public Response sendGetClassReqWithBodyDeleteClass(String requestType,String paramValue) {
		if (requestType.equals("GetClassRecordingsByClassId")) {
			//paramForGetEndpoint=TextContext.getClassId().toString();
		}
		System.out.println(""+paramValue);
		System.out.println(APIResources.valueOf("APIDeleteClassByClassId").getResources()+ paramForGetEndpoint);
		Response response = RestAssured.given().spec(requestHeadersWithToken())	
				.pathParam("classId",paramForGetEndpoint)
				.delete(APIResources.valueOf("APIDeleteClassByClassId").getResources());				
		return response;
	}
	}


