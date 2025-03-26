package com.APIResponse;

import org.testng.asserts.SoftAssert;

import com.context.ScenarioContext;
import com.pojoclass.BatchPojo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BatchModuleDataValidation {
	
	ScenarioContext context = ScenarioContext.getInstance();
	SoftAssert softAssert = new SoftAssert();
	
	public void DataValidation(Response response) {
	    JsonPath jsonPath = response.jsonPath();
	    BatchPojo batch = context.get("BatchPojo", BatchPojo.class);

	    if (batch == null) {
	        System.out.println("Error: BatchPojo not found in ScenarioContext.");
	        return;
	    }
	    
	    // Comparing Request Data with Response Data
	    softAssert.assertEquals(batch.getBatchName(), jsonPath.getString("batchName"));
	    softAssert.assertEquals(batch.getBatchDescription(), jsonPath.getString("batchDescription"));
	    softAssert.assertEquals(batch.getBatchStatus(), jsonPath.getString("batchStatus"));
	    softAssert.assertEquals(batch.getBatchNoOfClasses(), jsonPath.getString("batchNoOfClasses"));

	}

	public void GetAllDataValidation(Response response) {

		String responseBody = response.getBody().asString();
		softAssert.assertTrue(responseBody.contains("sdet"), "Search string 'sdet' is not present in the response body.");
		
	}
	
	}
	