package com.APIRequest;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payload.LoginPayload;
import com.pojoclass.LoginPojo;
import com.pojoclass.UserPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserRequest extends SpecificationClass{
	Response response;
	ScenarioContext context;
	String paramForGetEndpoint;

	public UserRequest(ScenarioContext context) throws FileNotFoundException
	{
		super(context);
		this.context = context;
	}	
	
	public int getUserStatusCode() {
		UserPojo userStatusCode=(UserPojo) context.get("UserPojo");
		return userStatusCode.getStatusCode();
	}

	/**************** POST Request ********************/
	
	public void setNewUserRequest(String requestType) throws Exception 
	{
		UserPojo user = TestDataLoader.loadTestDatafor_Post_Put(requestType, UserPojo.class);
		System.out.println("user sttaucode"+user.getStatusCode());
		context.set("UserPojo", user);
	}

	public void PostNewUserRequest()
	{
		UserPojo user = context.get("UserPojo", UserPojo.class);
		System.out.println(user.toString());
		response = RestAssured.given().spec(requestHeadersWithTokenForJson())
				.body(user).log().all()
				.post(APIResources.valueOf("APICreateUserWithRole").getResources());       
		context.set("userResponse", response); 
		System.out.println(response.prettyPrint());
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Headers: " + response.getHeaders());
		System.out.println("Response Body: " + response.getBody().asString());
		
		//String userID = response.jsonPath().getString("userId"); 
		//context.set("userId", userID); 
	}

	
	/****************** GET without parameter Request *************************/
	
	public JsonNode setGetUserRequest(String requestType) throws Exception 
	{
	JsonNode getTestData = TestDataLoader.loadTestDatafor_Post_Put(requestType);
	return getTestData;
	}
	
	public Response sendGetUserReqWithOutBody() {
		Response response = RestAssured.given().spec(requestHeadersWithTokenForJson())				
				.get(APIResources.valueOf("APIGetUserWithFilter").getResources());				
		return response;
	}
	
	/****************** GET with parameter Request  *************************/
	
	public JsonNode setGetUserRequestBody(String requestType,String parameterValue) throws Exception
	{
	JsonNode getTestData = TestDataLoader.loadTestDatafor_Post_Put(requestType);
	paramForGetEndpoint = getTestData.get(parameterValue).asText();
	return getTestData;
	}
	
	public Response sendGetUserReqWithBody() {
		Response response = RestAssured.given().spec(requestHeadersWithTokenForJson())				
				.get(APIResources.valueOf("APIGetUserByRole").getResources()+ paramForGetEndpoint);				
		return response;
	}
}
