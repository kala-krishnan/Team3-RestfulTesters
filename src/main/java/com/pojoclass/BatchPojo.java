package com.pojoclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BatchPojo {
	
	private String batchDescription, batchName, batchNoOfClasses, batchStatus, programId;

    public String getBatchDescription() {
		return batchDescription;
	}
	public void setBatchDescription(String batchDescription) {
		this.batchDescription = batchDescription;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public String getBatchNoOfClasses() {
		return batchNoOfClasses;
	}
	public void setBatchNoOfClasses(String batchNoOfClasses) {
		this.batchNoOfClasses = batchNoOfClasses;
	}
	public String getBatchStatus() {
		return batchStatus;
	}
	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	@JsonIgnore 
    private int statusCode;
    @JsonIgnore 
    private String statusText, contentType, scenario;

	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

}
