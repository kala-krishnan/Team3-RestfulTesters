package com.APIResponse;

import org.testng.Assert;
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

	    // Logging Response Data
	    System.out.println("*********** RESPONSE DATA ***********");
	    System.out.println("Batch Name: " + jsonPath.getString("batchName"));
	    System.out.println("Batch Description: " + jsonPath.getString("batchDescription"));
	    System.out.println("Batch Status: " + jsonPath.getString("batchStatus"));
	    System.out.println("Batch No of Classes: " + jsonPath.getString("batchNoOfClasses"));

	    // Logging Request Data
	    System.out.println("*********** REQUEST DATA ***********");
	    System.out.println("Stored BatchPojo: " + batch);
	    System.out.println("Batch Name: " + batch.getBatchName());
	    System.out.println("Batch Description: " + batch.getBatchDescription());
	    System.out.println("Batch Status: " + batch.getBatchStatus());
	    System.out.println("Batch No of Classes: " + batch.getBatchNoOfClasses());
	    
	    // Comparing Request Data with Response Data
	    softAssert.assertEquals(batch.getBatchName(), jsonPath.getString("batchName"));
	    softAssert.assertEquals(batch.getBatchDescription(), jsonPath.getString("batchDescription"));
	    softAssert.assertEquals(batch.getBatchStatus(), jsonPath.getString("batchStatus"));
	    softAssert.assertEquals(batch.getBatchNoOfClasses(), jsonPath.getString("batchNoOfClasses"));

	}
	}
	