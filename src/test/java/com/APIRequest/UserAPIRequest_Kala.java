package com.APIRequest;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.APIResponse.UserModuleResponseValidation;
import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.context.TextContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojoclass.UserPojo;
import com.pojoclass.UserRole;
import com.pojoclass.UserRoleWrapperClass;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserAPIRequest_Kala extends SpecificationClass{
	int responseCode;
	Response response;
	RequestSpecification resquest;
	UserModuleResponseValidation resValidation;
	String requestType;
	JsonNode getTestData;
	String Endpoints;
	String userId;
	ScenarioContext context =ScenarioContext.getInstance();
	public UserAPIRequest_Kala() throws FileNotFoundException {
		//super(context);
		resValidation = new UserModuleResponseValidation();
		
	
	}

	public JsonNode setGetUserRequest(String requestType) throws Exception 
	{
		
	 getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
	resquest=given().spec(requestHeadersWithToken());
	this.requestType= requestType;
	return getTestData;
	}
	

	
	public void getAllUsers(String reqType) throws IOException
	{
		Endpoints = APIResources.valueOf("APIGetAllUser").getResources();
		
		if(reqType.equals("GetAllUserInvalidEndpoint"))
		{
			Endpoints = APIResources.valueOf("APIGetAllUser").getResources()+"sss";
		}
		response = resquest
				   .get(Endpoints)
				   .then()
				   .extract().response();
		
			/*
			 * Response Validation
			 */
		//String getAllUserSchemaPath = new String(Files.readAllBytes(Paths.get("src/test/resources/Schemas/getAllUserSchema.json")));
		String getAllUserSchemaPath = "Schemas/getAllUserSchema.json";
		System.out.println("***getAllUserSchemaPath***"+getAllUserSchemaPath);
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		resValidation.validateStatusLine(response,  getTestData.get("statusText").asText() );
		if( getTestData.get("statusCode").asInt()==200) {
		resValidation.validateContentType(response);
		resValidation.validateResponseIsArray(response);
		//resValidation.validateJsonSchema(response, getAllUserSchemaPath);
		}
		
		
		
		
		responseCode = response.getStatusCode();
				   System.out.println("responseCode "+responseCode);
		
	}
	public void getAllActiveUsers(String reqType)
	{
		Endpoints = APIResources.valueOf("APIGetActiveUser").getResources();
		if(reqType.equals("GetAllActiveUsersInvalidEndpoint"))
		{
			Endpoints = APIResources.valueOf("APIGetActiveUser").getResources()+"sss";
		}
		
		response = resquest
				   .get(Endpoints)
				   .then()
				   .extract().response();
			/*
			 * Response Validation
			 */
		String getAllUserSchemaPath = System.getProperty("user.dir")+"/src/test/resources/Schemas/getAllActiveUserSchema.json";
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		resValidation.validateStatusLine(response, getTestData.get("statusText").asText() );
		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
			resValidation.validateResponseIsArray(response);
		//	resValidation.validateJsonSchema(response, getAllUserSchemaPath);
			}
		responseCode = response.getStatusCode();
				   System.out.println("responseCode "+responseCode);
		
	}
	public void getAllFetchEMailUsers(String reqType)
	{
		Endpoints = APIResources.valueOf("APIGetAllUserEmail").getResources();
		if(reqType.equals("GetActiveEmailUsersInvalidEndpoint"))
		{
			Endpoints = APIResources.valueOf("APIGetAllUserEmail").getResources()+"sss";
		}
		
		response = resquest
				   .get(Endpoints)
				   .then()
				   .extract().response();
			/*
			 * Response Validation
			 */
		System.out.println("response :: "+response.getStatusLine());
		String getAllUserSchemaPath = System.getProperty("user.dir")+"/src/test/resources/Schemas/getAllFetchEmailUser.json";
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		resValidation.validateStatusLine(response,getTestData.get("statusText").asText() );
		
		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
			resValidation.validateResponseIsArray(response);
			//resValidation.validateJsonSchema(response, getAllUserSchemaPath);
			}
	
		
		
		responseCode = response.getStatusCode();
				   System.out.println("responseCode "+responseCode);
		
	}
	
	public void getAllRoles(String reqType)
	{
		Endpoints = APIResources.valueOf("APIGetAllRoles").getResources();
		if(reqType.equals("GetAllRolesInvalidEndpoint"))
		{
			Endpoints = APIResources.valueOf("APIGetAllRoles").getResources()+"sss";
		}
		response = resquest
				   .get(Endpoints)
				   .then()
				   .extract().response();
			/*
			 * Response Validation
			 */
		System.out.println("response :: "+response.getStatusLine());
		String getAllUserSchemaPath = System.getProperty("user.dir")+"/src/test/resources/Schemas/getAllRolesSchemas.json";
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
			resValidation.validateResponseIsArray(response);
		//	resValidation.validateJsonSchema(response, getAllUserSchemaPath);
			
			}
		
		resValidation.validateStatusLine(response, getTestData.get("statusText").asText() );
		
		responseCode = response.getStatusCode();
				   System.out.println("responseCode "+responseCode);
		
	}
	public void getAllUserwithRoles(String reqType)
	{
		Endpoints = APIResources.valueOf("APIGetAllUserRoles").getResources();
		if(reqType.equals("GetAllUserwithRolesInvalidEndpoint"))
		{
			Endpoints = APIResources.valueOf("APIGetAllUserRoles").getResources()+"sss";
		}
		response = resquest
				   .get(Endpoints)
				   .then()
				   .extract().response();
			/*
			 * Response Validation
			 */
		System.out.println("response :: "+response.getStatusLine());
		String getAllUserSchemaPath = System.getProperty("user.dir")+"/src/test/resources/Schemas/getallUserwithRolesSchema.json";
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
			resValidation.validateResponseIsArray(response);
		//	resValidation.validateJsonSchema(response, getAllUserSchemaPath);
			}
		
		resValidation.validateStatusLine(response, getTestData.get("statusText").asText() );
		
		
		responseCode = response.getStatusCode();
				   System.out.println("responseCode "+responseCode);
		
	}
	
	public void GetAllUserStatusCount(String reqType)
	{
		Endpoints = APIResources.valueOf("APIGetUserByStatus").getResources();
		if(reqType.equals("GetAllUserStatusCountInvalidEndpoint"))
		{
			Endpoints = APIResources.valueOf("APIGetUserByStatus").getResources()+"sss";
		}
		response = resquest
				   .get(Endpoints)
				   .then()
				   .extract().response();
			/*
			 * Response Validation
			 */
		System.out.println("response :: "+response.getStatusLine());
		String getAllUserSchemaPath = System.getProperty("user.dir")+"/src/test/resources/Schemas/getAllUserStatusCount.json";
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
			resValidation.validateResponseIsArray(response);
		//	resValidation.validateJsonSchema(response, getAllUserSchemaPath);
			}
		
		resValidation.validateStatusLine(response, getTestData.get("statusText").asText() );
		
		
		responseCode = response.getStatusCode();
				   System.out.println("responseCode "+responseCode);
		
	}
	
	public int responseCode()
	{
		return responseCode;	
	}
	public void GetUserByUserId(String reqType)
	{
		Endpoints = APIResources.valueOf("APIGetUserByID").getResources();
		TextContext.setUserId("U37");
		
		if(reqType.equals("GetUserByInvalidUserId") || reqType.equals("GetUserByInvalidSpecialCharacterUserId"))
		{
			TextContext.setUserId(getTestData.get("id").toString());
		}
		
		System.out.println("Full URL: " + Endpoints + "/" + userId); 
		response = resquest
		           .pathParam("userId", TextContext.getUserId())
				   .get(Endpoints)
				   .then()
				   .extract().response();
		/*
		 * Response Validation
		 */
//	String getAllUserSchemaPath = System.getProperty("user.dir")+"//src///test//resources//Schemas//getAllUserStatusCount.json";
	resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
	resValidation.validateResponseTime(response);
	if( getTestData.get("statusCode").asInt()==200) {
		resValidation.validateContentType(response);
//		resValidation.validateJsonSchema(response, getAllUserSchemaPath);
		}
	resValidation.validateStatusLine(response,getTestData.get("statusText").asText() );

	
	responseCode = response.getStatusCode();
	}
	
	public void updateUserRequest(String requestType) throws Exception 
	{
		UserRole user = TestDataLoader.loadTestDatafor_Post_Put(requestType, UserRole.class);
		context.set("UserRole", user);
		resquest=given().spec(requestHeadersWithToken());
		this.requestType= requestType;
	}
	public void updateRoleByUserId(String requestType) throws JsonProcessingException
	{
		Endpoints = APIResources.valueOf("APIUpdateUserByID").getResources();
		UserRoleWrapperClass userWrapper = new UserRoleWrapperClass();
		TextContext.setUserId("U2668");
		
		UserRole userrole = context.get("UserRole", UserRole.class);
		userWrapper.setUserRoleList(Arrays.asList(userrole));
		
		response =resquest.pathParam("userId", TextContext.getUserId())
				.body(userWrapper)
				.put(Endpoints);       
		context.set("userRoleResponse", response); 
		
		responseCode = response.getStatusCode();
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.asString());
		/*
		 * Response Validation
		 */
		resValidation.validateStatusCode(response,userrole.getStatusCode() );
		resValidation.validateResponseTime(response);
		if( responseCode==200) {
			resValidation.validateContentType(response);
			}
		resValidation.validateStatusLine(response,userrole.getStatusText() );
	}

	
	
}
