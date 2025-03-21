package com.APIRequest;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.context.ScenarioContext;
import com.context.TextContext;
import com.payload.LoginPayload;
import com.pojoclass.LoginPojo;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

public class LoginRequest extends SpecificationClass{
	
	RequestSpecification req;
	Response response;
	//Request request;
	LoginPayload loginPayload = new LoginPayload();
	String token;
	int responseCode;
	ScenarioContext context;
//	TextContext testContext;
//	public LoginRequest(TextContext testContext)
//	{
//		this.testContext = testContext;
//	}
	public LoginRequest()
	{
		
	}
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
	
	
	

}
