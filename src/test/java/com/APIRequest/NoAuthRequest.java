package com.APIRequest;

import java.io.FileNotFoundException;
import java.util.Arrays;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.context.ScenarioContext;
import com.context.TextContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pojoclass.UserPojo;
import com.pojoclass.UserRole;
import com.pojoclass.UserRoleWrapperClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NoAuthRequest extends SpecificationClass{
	
	public NoAuthRequest() throws FileNotFoundException {
		
	}
	ScenarioContext context =ScenarioContext.getInstance();
	Response response;
	RequestSpecification resquest;
	
	public void PostNewUserRequestNoAuth()
	{
		UserPojo user = context.get("UserPojo", UserPojo.class);
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.body(user)
				.post(APIResources.valueOf("APICreateUserWithRole").getResources());
		context.set("userResponse", response); 
		System.out.println("User response" +response.getStatusCode());
	}
//	public void PutUserRequestNoAuth()
//	{
//		UserPojo user = context.get("UserPojo", UserPojo.class);
//		response = RestAssured.given().spec(requestHeadersWithoutToken())
//				.body(user)
//				.put(APIResources.valueOf("APIUpdateUser").getResources());
//		context.set("userResponse", response); 
//		System.out.println("User response" +response.getStatusCode());
//	}
	public void getAllUserRequestNoAuth(String requestEndpoint)
	{
		response =RestAssured.given().spec(requestHeadersWithoutToken())
				   .get(APIResources.valueOf(requestEndpoint).getResources());
		context.set("userResponse", response); 
			
	}
	public void updateRoleByUserIdNoAuth(String requestEndpoint) 
	{
		UserRoleWrapperClass userWrapper = new UserRoleWrapperClass();
		TextContext.setUserId("U2668");
		
		UserRole userrole = context.get("UserRole", UserRole.class);
		userWrapper.setUserRoleList(Arrays.asList(userrole));
		
		response =RestAssured.given().spec(requestHeadersWithoutToken()).pathParam("userId", TextContext.getUserId())
				.body(userWrapper)
				.put(APIResources.valueOf(requestEndpoint).getResources());       
		context.set("userRoleResponse", response); 
	}
	public void getUserByUserIdNoAuth(String requestEndpoint)
	{
		TextContext.setUserId("U2668");
		response = RestAssured.given().spec(requestHeadersWithoutToken())
		           .pathParam("userId", TextContext.getUserId())
				   .get(APIResources.valueOf(requestEndpoint).getResources())
				   .then()
				   .extract().response();
		context.set("userRoleResponse", response); 
	}
}
