package com.APIResponse;

import org.testng.Assert;

import com.commonUtils.ConfigReader;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class CommonResponseValidation {
	
	 public void validateStatusCode(Response response, int expectedStatusCode) {
	        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code mismatch!");
	    }

	 public void validateStatusLine(Response response, String expectedMessage) {
	        String actualMessage = response.getStatusLine();
	        Assert.assertEquals(actualMessage, expectedMessage, "Status Line mismatch!");
	    } 

	    public void validateStatusMessage(Response response, String expectedMessage) {
	        String actualMessage = response.jsonPath().getString("message");
	        Assert.assertEquals(actualMessage, expectedMessage, "Status message mismatch!");
	    }

	    public void validateContentType(Response response) {
	    	String expectedMessage = ConfigReader.getProperty("contentType");
	        String actualContentType = response.getHeader("Content-Type");
	        Assert.assertEquals(actualContentType,  expectedMessage, "Content-Type mismatch!");
	    }

	    public void validateJsonSchema(Response response, String schemaFilePath) {
	        response.then().assertThat()
	                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFilePath));
	    }

	    public void validateResponseTime(Response response) {
	        Assert.assertTrue(response.getTime() < 5000, "Response time exceeds 5000ms!");
	    }

}
