package com.APIRequest;

import java.io.FileNotFoundException;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.pojoclass.BatchPojo;
import com.pojoclass.LoginPojo;
import com.pojoclass.LogoutPojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LogoutRequest  extends SpecificationClass{

	Response response;
	ScenarioContext context =ScenarioContext.getInstance();
	
	public LogoutRequest() throws FileNotFoundException {
		
	}
	
	public int getStatusCode() {
		LogoutPojo logout = context.get("LogoutPojo", LogoutPojo.class);
		return logout.getStatusCode();
	}
	public String getStatusLine() {
		LogoutPojo logout = context.get("LogoutPojo", LogoutPojo.class);
		return logout.getStatusText();
	}
	
	public void setGetLogoutRequest(String requestType) throws Exception 
	{
		LogoutPojo logout = TestDataLoader.loadTestDatafor_Post_Put(requestType, LogoutPojo.class);
		context.set("LogoutPojo", logout);
	}
	
	public void GetLogoutRequest(String Scenario)
	{
		String EndPoint = APIResources.valueOf("APILogoutGet").getResources();
		if (Scenario.equals("LogoutInvalidEP")) 
			EndPoint = APIResources.valueOf("APILogoutGet").getResources() + "Invalid";

		response = RestAssured.given().spec(requestHeadersWithToken())
				.log().all()
				.get(EndPoint);       
		context.set("logoutResponse", response); 
	}
	
	public void NoAuthGetLogoutRequest()
	{
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.get(APIResources.valueOf("APILogoutGet").getResources());       
		context.set("logoutResponse", response); 
	}
	
}
