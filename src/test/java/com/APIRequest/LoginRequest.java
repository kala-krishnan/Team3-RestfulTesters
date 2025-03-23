package com.APIRequest;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.context.TextContext;
import com.payload.LoginPayload;
import com.pojoclass.LoginPojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;

public class LoginRequest extends SpecificationClass{

	Response response;
	ScenarioContext context;
	
	/****** for config file ****************/
	RequestSpecification req;
	LoginPayload loginPayload = new LoginPayload();
	String token;
	int responseCode;
	
	
	public LoginRequest(ScenarioContext context) throws FileNotFoundException
	{
		super(context);
		this.context = context;
	}
	//for config file
	public void PostLoginRequest()
	{
		
		LoginPojo logPojo = loginPayload.postLoginPayload();
		response = given().spec(requestHeadersWithoutToken())
					.body(logPojo).log().all()
					.when()
					.post(APIResources.valueOf("APILoginPost").getResources());
					 token = response.jsonPath().getString("token"); 
					 System.out.println("TOken.." +token);
					 TextContext.scenarioContext.setContext("authtoken", token);
					
		   responseCode = response.getStatusCode();
		 System.out.println("getContext " +TextContext.scenarioContext.getContext("authtoken"));
		  
	}

	public void setLoginRequest(String requestType) throws Exception 
	{
		LoginPojo login = TestDataLoader.loadTestDatafor_Post_Put(requestType, LoginPojo.class);
		context.set("LoginPojo", login);
	}
	
	// for json file
	public void postLoginRequestFromJson()
	{
		LoginPojo login = context.get("LoginPojo", LoginPojo.class);		
		System.out.println(login.toString());
		response = RestAssured.given().spec(requestHeadersWithTokenForJson())
				.body(login).log().all()
				.post(APIResources.valueOf("APILoginPost").getResources());    
		String token = response.jsonPath().getString("token"); 
		context.set("LMStoken", token); 
		context.set("loginResponse", response);
	}

	public int getStatusCode() {
		LoginPojo login = context.get("LoginPojo", LoginPojo.class);

		return login.getStatusCode();
	}

}
