package com.APIResponse;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.testng.Assert;

import io.restassured.response.Response;

public class UserModuleResponseValidation extends CommonResponseValidation{
	
	
    public void validateResponseIsArray(Response response) {
        List<Object> users = response.jsonPath().getList("$");
        Assert.assertFalse(users.isEmpty(), "User Response is empty or not an array!");
    }
   
}


