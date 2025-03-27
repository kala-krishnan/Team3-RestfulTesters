package com.APIRequest;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.APIResponse.UserModuleResponseValidation;
import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.context.TextContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.payload.UserPayloadPut;
import com.payload.UserRoleProgramBatch;
import com.pojoclass.BatchPojo;
import com.pojoclass.UserPojo;
import com.pojoclass.UserRole;
import com.pojoclass.UserRoleWrapperClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserRequest extends SpecificationClass{
	Response response;
	ScenarioContext context =ScenarioContext.getInstance();
	String paramForGetEndpoint;
	Integer paramForGetIntEndpoint;
	JsonNode getTestData;
	RequestSpecification resquest;
	String requestType;
	UserModuleResponseValidation resValidation;
	int responseCode;
	String Endpoints;
	JsonNode getTestData_get ;
	public UserRequest() throws FileNotFoundException
	{
		resValidation = new UserModuleResponseValidation();
	}	

	public int getUserStatusCode() {
		UserPojo userStatusCode=(UserPojo) context.get("UserPojo");
		return userStatusCode.getStatusCode();
	}

	/**************** POST Request ********************/

	public void newUserRequest(String requestType) throws Exception 
	{
		UserPojo user = TestDataLoader.loadTestDatafor_Post_Put(requestType, UserPojo.class);
		context.set("UserPojo", user);
	}
	

	public void PostNewUserRequest()
	{
		UserPojo user = context.get("UserPojo", UserPojo.class);
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(user).log().all()
				.post(APIResources.valueOf("APICreateUserWithRole").getResources());       
		context.set("userResponse", response); 
		
		String userID  = response.jsonPath().getString("user.userId");
		TextContext.setUserId(userID); // Setting userid for chaining 
		String roleID = response.jsonPath().getString("roles[0].roleId");
		TextContext.setRoleId(roleID);
		int respCode=response.getStatusCode();
		if(respCode==200)
		{
		context.set("createUservalid", response);
		}
		
	}
	public void PostNewUserRequestInvalid()
	{
		UserPojo user = context.get("UserPojo", UserPojo.class);
		System.out.println(user.toString());
		Response response_invalid = RestAssured.given().spec(requestHeadersWithToken())
				.body(user).log().all()
				.post(APIResources.valueOf("APICreateUserWithRole").getResources());       
		context.set("createUserinvalid", response_invalid);
	}

	/****************** GET without parameter Request *************************/

	public JsonNode getUserRequest(String requestType,String status) throws Exception 
	{		
		getTestData_get = TestDataLoader.loadTestDatafor_Get(requestType);

		return getTestData_get;
	}

	public Response sendGetUserReqWithOutBody(String endpointValue,String status) {
		Response response =null;
		String endpoint = "API"+endpointValue;
		if(!status.equalsIgnoreCase("405"))
		{
			
			response = RestAssured.given().spec(requestHeadersWithToken())				
					.get(APIResources.valueOf(endpoint).getResources());	
		}
		else
		{
			response = RestAssured.given().spec(requestHeadersWithToken())				
					.post(APIResources.valueOf(endpoint).getResources());
		}
		return response;
	}

	/****************** GET with parameter Request  *************************/
	/************* requestType is set in Enum class-APIResource.java and feature File ***/ 

	public JsonNode setGetUserRequestBody(String requestType,String parameterValue) throws Exception
	{
		TextContext.setRoleId("R02");
		getTestData_get = TestDataLoader.loadTestDatafor_Get(requestType);
	    String type = getTestData_get.get("type").asText();

	    if (type.equalsIgnoreCase("valid")) { 
	        switch (parameterValue.toLowerCase()) {
	            case "roleid":
	                paramForGetEndpoint = TextContext.getRoleId();
	                break;
	            case "userid":
	                paramForGetEndpoint = TextContext.getUserId();
	                System.out.println("paramForGetEndpoint"+paramForGetEndpoint);
	                break;
	            case "programid":
	                paramForGetEndpoint = (TextContext.getProgramId() != null) 
	                        ? String.valueOf(TextContext.getProgramId()) 
	                        : "0";  
	                break;
	            case "batchid":
	            	paramForGetEndpoint = (TextContext.getBatchId() != null) 
	                        ? String.valueOf(TextContext.getBatchId()) 
	                        : "0";  
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid parameter value: " + parameterValue);
	        }
	    } else {
	    	System.out.println(parameterValue);
	    	paramForGetEndpoint = getTestData_get.get(parameterValue).asText();
	    }	
		return getTestData_get;
	}

	public JsonNode setGetUserReqBody(String requestType,String parameterValue) throws Exception
	{
		getTestData_get = TestDataLoader.loadTestDatafor_Get(requestType);
	    String type = getTestData_get.get("type").asText();

	    if (type.equalsIgnoreCase("valid")) { 
	        switch (parameterValue.toLowerCase()) {
	            case "programid":
	            	paramForGetIntEndpoint = (TextContext.getProgramId() != null) 
	                        ? (TextContext.getProgramId()) 
	                        : 0;  
	                break;
	            case "batchid":
	            	paramForGetIntEndpoint = (TextContext.getBatchId() != null) 
	                        ? TextContext.getBatchId() 
	                        : 0;  
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid parameter value: " + parameterValue);
	        }
	    } else {
	    	paramForGetIntEndpoint = getTestData_get.get(parameterValue).asInt();
	    }	
		return getTestData_get;
	}
	public Response sendGetUserReqWithBody(String endpointValue,String param) {
		String endpoint = "API"+endpointValue;
		Response response = RestAssured.given().spec(requestHeadersWithToken())
				.pathParam(param, paramForGetEndpoint)
				.log().all()					
				.get(APIResources.valueOf(endpoint).getResources());	
		return response;
	}
	public Response sendGetUserReqWithIntBody(String endpointValue,String param) {
		String endpoint = "API"+endpointValue;
		Response response = RestAssured.given().spec(requestHeadersWithToken())
				.pathParam(param, paramForGetIntEndpoint)
				.log().all()				
				.get(APIResources.valueOf(endpoint).getResources());	
		return response;
	}

	//**************************************************Kala Request******************************************

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

		String getAllUserSchemaPath = "Schemas/getAllUserSchema.json";
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		resValidation.validateStatusLine(response,  getTestData.get("statusText").asText() );
		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
			resValidation.validateResponseIsArray(response);
		}




		responseCode = response.getStatusCode();
		System.out.println("responseCode "+responseCode);
		resValidation.assertAll();

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
		String getAllUserSchemaPath = System.getProperty("user.dir")+"/src/test/resources/Schemas/getAllActiveUserSchema.json";
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		resValidation.validateStatusLine(response, getTestData.get("statusText").asText() );
		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
			resValidation.validateResponseIsArray(response);
		}
		responseCode = response.getStatusCode();
		resValidation.assertAll();
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
		String getAllUserSchemaPath = System.getProperty("user.dir")+"/src/test/resources/Schemas/getAllFetchEmailUser.json";
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		resValidation.validateStatusLine(response,getTestData.get("statusText").asText() );

		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
			resValidation.validateResponseIsArray(response);
			resValidation.validateJsonSchema(response, getAllUserSchemaPath);
		}



		responseCode = response.getStatusCode();
		System.out.println("responseCode "+responseCode);
		resValidation.assertAll();
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
				resValidation.validateJsonSchema(response, getAllUserSchemaPath);

		}

		resValidation.validateStatusLine(response, getTestData.get("statusText").asText() );

		responseCode = response.getStatusCode();
		
		resValidation.assertAll();
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
		resValidation.assertAll();

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
		resValidation.assertAll();

	}

	public int responseCode()
	{
		return responseCode;	
	}
	public void GetUserByUserId(String reqType)
	{
		Endpoints = APIResources.valueOf("APIGetUserByID").getResources();
		//TextContext.setUserId("U1767");
		if (requestType.equals("GetUserByUserIDInvalidEndpoint")) 
			Endpoints = APIResources.valueOf("APIGetUserByID").getResources() + "Invalid";
		if(reqType.equals("GetUserByInvalidUserId") || reqType.equals("GetUserByInvalidSpecialCharacterUserId"))
		{
			TextContext.setUserId(getTestData.get("id").toString());
		}

		response = resquest
				.pathParam("userId", TextContext.getUserId())
				.get(Endpoints)
				.then()
				.extract().response();
		/*
		 * Response Validation
		 */
		resValidation.validateStatusCode(response, getTestData.get("statusCode").asInt());
		resValidation.validateResponseTime(response);
		if( getTestData.get("statusCode").asInt()==200) {
			resValidation.validateContentType(response);
		}
		resValidation.validateStatusLine(response,getTestData.get("statusText").asText() );


		responseCode = response.getStatusCode();
		resValidation.assertAll();
	}
	public int getUserRoleStatusCode() {
		UserRole sCode=(UserRole) context.get("UserRole");
		return sCode.getStatusCode();
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
		UserRole userrole = context.get("UserRole", UserRole.class);
		userrole.setRoleId(TextContext.getRoleId());
		
		userWrapper.setUserRoleList(Arrays.asList(userrole));

		response =resquest.pathParam("userId", TextContext.getUserId())
				.body(userWrapper)
				.put(Endpoints);       
		context.set("userRoleResponse", response); 

		responseCode = response.getStatusCode();
		resValidation.validateStatusCode(response,userrole.getStatusCode() );
		resValidation.validateResponseTime(response);
		if( responseCode==200) {
			resValidation.validateContentType(response);
		}
		resValidation.validateStatusLine(response,userrole.getStatusText() );
		resValidation.assertAll();
	}
	
	///////////***************** update program batch user **************/
	
	public void updateUserProgramBatchRequest(String requestType) throws Exception 
	{
		//TextContext.setBatchId(9488);
		//TextContext.setUserId("U212");
		//TextContext.setProgramId(16925);
		String roleid = TextContext.getRoleId();
		UserPayloadPut userPut = TestDataLoader.loadTestDatafor_Post_Put(requestType, UserPayloadPut.class);
		userPut.setUserId(TextContext.getUserId());
		userPut.setProgramId(TextContext.getProgramId());
		userPut.setRoleId("R02");
		
		UserRoleProgramBatch newBatch = new UserRoleProgramBatch();
		newBatch.setBatchId(TextContext.getBatchId());
		newBatch.setUserRoleProgramBatchStatus("Active");
		
		List<UserRoleProgramBatch> batchList = new ArrayList<>();
		batchList.add(newBatch);
		
		userPut.setUserRoleProgramBatches(batchList);
		context.set("userPut", userPut);
		
		resquest=given().spec(requestHeadersWithToken());
		this.requestType= requestType;
	}
	public void updateProgramBatchuserRequest(String endpoint)
	{
		UserPayloadPut user = context.get("userPut", UserPayloadPut.class);
		System.out.println(user.toString());
		response = RestAssured.given().spec(requestHeadersWithToken())
				.body(user).log().all()
				.put(APIResources.valueOf("APIUpdateUserByRPBStatus").getResources()+TextContext.getUserId());     
		context.set("userPutAllResponse", response); 	


	}
	public void DeleteBatchRequest(String Scenario)
	{
		 if(Scenario.equals("DeleteUserInvalidID") || Scenario.equals("DeleteAlreadyRemovedUser"))
			{
				TextContext.setUserId(getTestData.get("id").toString());
			}
		
		String EndPoint = APIResources.valueOf("APIDeleteUserByID").getResources();
		System.out.println("*************************************"+EndPoint);
		response =  given().spec(requestHeadersWithToken())
				.pathParam("userId",TextContext.getUserId()) //TextContext.getUserId() update it later
				.delete(EndPoint);       
		context.set("batchResponse", response); 
		responseCode = response.getStatusCode();
	}
		
}