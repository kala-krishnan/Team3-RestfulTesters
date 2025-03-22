package com.commonUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.context.TextContext;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationClass {
	
	RequestSpecification reqSpec;
	ResponseSpecification responseSpec;
	String baseURL = ConfigReader.getProperty("baseURL");
	PrintStream log;
	public SpecificationClass() throws FileNotFoundException
	{
		log =new PrintStream(new FileOutputStream("RestAssuredLog.txt"));
	}
	public RequestSpecification requestHeadersWithoutToken()
	{
		reqSpec = new RequestSpecBuilder().setBaseUri(baseURL)
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return reqSpec;
	}
	
	public RequestSpecification requestHeadersWithToken()
	{
		reqSpec = new RequestSpecBuilder()
				.addHeader("Authorization","Bearer "+TextContext.scenarioContext.getContext("authtoken") ).setBaseUri(baseURL)
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return reqSpec;
	}
	
	public ResponseSpecification responseSpecBuilder()
	{
		responseSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return responseSpec;
	}

}
