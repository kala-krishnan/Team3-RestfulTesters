package com.pojoclass;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class LoginPojo {
	
	private String userLoginEmailId;
	private String password;
	
	 //mandatory in all pojo
    @JsonIgnore 
    private int statusCode;
    public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	
	@JsonIgnore 
    private String scenario;
    @JsonIgnore 
	private String statusText;
	
	public LoginPojo()
	{
		this.userLoginEmailId = userLoginEmailId;
		this.password = password;
	}
	
	public String getUserLoginEmailId() {
		return userLoginEmailId;
	}
	public void setUserLoginEmailId(String userLoginEmailId) {
		this.userLoginEmailId = userLoginEmailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
