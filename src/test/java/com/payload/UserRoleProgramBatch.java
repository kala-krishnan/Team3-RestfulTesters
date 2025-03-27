package com.payload;

public class UserRoleProgramBatch {
	    private int batchId;
	    private String userRoleProgramBatchStatus;

	    // Getters and Setters
	    public int getBatchId() {
	        return batchId;
	    }

	    public void setBatchId(int batchId) {
	        this.batchId = batchId;
	    }

	    public String getUserRoleProgramBatchStatus() {
	        return userRoleProgramBatchStatus;
	    }

	    public void setUserRoleProgramBatchStatus(String userRoleProgramBatchStatus) {
	        this.userRoleProgramBatchStatus = userRoleProgramBatchStatus;
	    }

	    @Override
	    public String toString() {
	        return "UserRoleProgramBatch{" +
	                "batchId=" + batchId +
	                ", userRoleProgramBatchStatus='" + userRoleProgramBatchStatus + '\'' +
	                '}';
	    }
	}

