package com.payload;

import com.commonUtils.ConfigReader;
import com.pojoclass.LoginPojo;

public class LoginPayload {
	
	LoginPojo LPojo = new LoginPojo(); 
	
	public LoginPojo postLoginPayload()
	{
		LPojo.setUserLoginEmailId(ConfigReader.getProperty("userLoginEmailId"));
		LPojo.setPassword(ConfigReader.getProperty("password"));
		return LPojo;
	}
	
	

}
