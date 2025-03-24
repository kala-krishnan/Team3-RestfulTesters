package com.commonUtils;

import java.io.FileOutputStream;
import java.io.PrintStream;
import com.context.ScenarioContext;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.FileNotFoundException;
import com.context.TextContext;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class SpecificationClass {
	
	RequestSpecification reqSpec;
	String baseURL = ConfigReader.getProperty("baseURL");
	ScenarioContext context;
	String FilePath = ConfigReader.getProperty("LogFilePath");
	 public SpecificationClass(ScenarioContext context) throws FileNotFoundException {
	        this.context = context;
	        log =new PrintStream(new FileOutputStream(FilePath));
	    }
	 
		ResponseSpecification responseSpec;
		PrintStream log;
		public SpecificationClass() throws FileNotFoundException
		{
			log =new PrintStream(new FileOutputStream(FilePath));
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
			System.out.println("Token....."+context.get("LMStoken"));
			reqSpec = new RequestSpecBuilder()
					.addHeader("Authorization","Bearer "+context.get("LMStoken") ).setBaseUri(baseURL)
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

		public RequestSpecification requestHeadersWithTokenForJson()
		{
			System.out.println("Token......."+context.get("LMStoken"));
			reqSpec = new RequestSpecBuilder()
					.addHeader("Authorization","Bearer "+context.get("LMStoken") ).setBaseUri(baseURL).setContentType(ContentType.JSON).build();
			return reqSpec;
		}
		
}
