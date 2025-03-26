package com.APIResponse;

import java.util.Arrays;
import java.util.List;


import org.testng.Assert;

import io.restassured.response.Response;

public class UserModuleResponseValidation extends CommonResponseValidation{
	
	String[] validRoleIds = {"R01","R02","R03"};
    public void validateResponseIsArray(Response response) {
        List<Object> users = response.jsonPath().getList("$");
        Assert.assertFalse(users.isEmpty(), "User Response is empty or not an array!");
    }
    public void validateRoleId(Response response) {
    	String roleId = response.jsonPath().getString("roleId");
    	 boolean isValid = Arrays.asList(validRoleIds).contains(roleId);
    	   Assert.assertFalse(isValid, "Invalid roleId: " + roleId + ". Expected one of: " + Arrays.toString(validRoleIds));
       
    }
}


