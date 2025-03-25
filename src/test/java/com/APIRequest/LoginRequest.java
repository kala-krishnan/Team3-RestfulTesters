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
	ScenarioContext context =ScenarioContext.getInstance();

	LoginPayload loginPayload = new LoginPayload();
	String token;
	
	public LoginRequest() throws FileNotFoundException
	{
		
	}
	// Valid Login Scenario: Data from Config File
	public void PostLoginRequest()
	{
		
		LoginPojo logPojo = loginPayload.postLoginPayload();
		response = given().spec(requestHeadersWithoutToken())
					.body(logPojo).log().all()
					.when()
					.post(APIResources.valueOf("APILoginPost").getResources());
		context.set("loginResponse", response); 
					 token = response.jsonPath().getString("token"); 
					 context.set("LMStoken", token);   
	}

	public void setLoginRequest(String requestType) throws Exception 
	{
		LoginPojo login = TestDataLoader.loadTestDatafor_Post_Put(requestType, LoginPojo.class);
		context.set("LoginPojo", login);
	}
	
	// Login Scenario Invalid: Json File
	public void postLoginRequestFromJson(String Scenario)
	{
		String EndPoint = APIResources.valueOf("APILoginPost").getResources();	
		if (Scenario.equals("LoginInvalidEndpoint")) 
			EndPoint = APIResources.valueOf("APILoginPost").getResources() + "Invalid";
		
		LoginPojo login = context.get("LoginPojo", LoginPojo.class);		
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.body(login).log().all()
				.post(EndPoint);
		context.set("loginResponse", response);
		
	}

	public int getStatusCode() {
		LoginPojo login = context.get("LoginPojo", LoginPojo.class);
		return login.getStatusCode();
	}
	public String getStatusLine() {
		LoginPojo login = context.get("LoginPojo", LoginPojo.class);
		return login.getStatusText();
	}
}
