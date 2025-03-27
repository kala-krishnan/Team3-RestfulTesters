package com.payload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPayloadPut {
	
    private int programId;
    private String roleId;
    private String userId;
    private List<UserRoleProgramBatch> userRoleProgramBatches;

    @JsonIgnore 
    private String scenario;
    @JsonIgnore 
    private String type;
    @JsonIgnore 
    private int statusCode;
    @JsonIgnore 
    private String statusText;
    // Getters and Setters
    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserRoleProgramBatch> getUserRoleProgramBatches() {
        return userRoleProgramBatches;
    }

    public void setUserRoleProgramBatches(List<UserRoleProgramBatch> userRoleProgramBatches) {
        this.userRoleProgramBatches = userRoleProgramBatches;
    }

    @Override
    public String toString() {
        return "UpdateUserByRPBStatus{" +
                "scenario='" + scenario + '\'' +
                ", type='" + type + '\'' +
                ", statusCode=" + statusCode +
                ", statusText='" + statusText + '\'' +
                ", programId=" + programId +
                ", roleId='" + roleId + '\'' +
                ", userId='" + userId + '\'' +
                ", userRoleProgramBatches=" + userRoleProgramBatches +
                '}';
    }
}
