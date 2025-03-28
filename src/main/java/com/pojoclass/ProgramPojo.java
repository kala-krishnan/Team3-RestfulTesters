package com.pojoclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProgramPojo {
    private Integer programId;
    private String programName;
    private String programDescription;
    private String programStatus;

    @JsonIgnore
    private int statusCode;
    @JsonIgnore
    private String statusText;
    @JsonIgnore
    private String authType;
    @JsonIgnore
    private String scenario;

    public ProgramPojo() {
    }

    public ProgramPojo(String programName, String programDescription, 
                     String programStatus, Integer programId) {
        this.programId = programId;
        this.programName = programName;
        this.programDescription = programDescription;
        this.programStatus = programStatus;
    }

    // Corrected setProgramId method
    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public String getProgramStatus() {
        return programStatus;
    }

    public void setProgramStatus(String programStatus) {
        this.programStatus = programStatus;
    }

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

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    @Override
    public String toString() {
        return "ProgramPojo{" + 
               "programId=" + programId + 
               ", programName='" + programName + '\'' +
               ", programDescription='" + programDescription + '\'' + 
               ", programStatus='" + programStatus + '\'' +
               ", statusCode=" + statusCode + 
               ", statusText='" + statusText + '\'' + 
               ", authType='" + authType + '\'' +
               ", scenario='" + scenario + '\'' + 
               '}';
    }
}