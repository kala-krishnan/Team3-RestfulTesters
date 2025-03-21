package com.commonUtils;

import com.context.TextContext;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecificationClass {
	
	RequestSpecification reqSpec;
	String baseURL = ConfigReader.getProperty("baseURL");
	
	public RequestSpecification requestHeadersWithoutToken()
	{
		reqSpec = new RequestSpecBuilder().setBaseUri(baseURL).setContentType(ContentType.JSON).build();
		return reqSpec;
	}
	
	public RequestSpecification requestHeadersWithToken()
	{
		reqSpec = new RequestSpecBuilder()
				.addHeader("Authorization","Bearer "+TextContext.scenarioContext.getContext("authtoken") ).setBaseUri(baseURL).setContentType(ContentType.JSON).build();
		return reqSpec;
	}
	
	

}
