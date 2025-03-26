package com.pojoclass;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClassPojo {
	private int csId;
	  private int batchId;
	  private int classNo;
	  private String classDate;
	  private String classTopic;
	  private String classStatus;
	  private String classStaffId;
	  private String classDescription;                                          
	  private String classComments;
	  private String classNotes;
	  private String classRecordingPath;
	  private String batchName;
	  private List<String> classScheduledDates; 
	  
		@JsonIgnore 
	    private int statusCode;
	    @JsonIgnore 
	    private String scenario;
	    @JsonIgnore 
		private String statusText;
	    @JsonIgnore 
	    private String type;
	    @JsonIgnore 
	    private String contentType;
	    public String getStatusText() {
			return statusText;
		}

		public void setStatusText(String statusText) {
			this.statusText = statusText;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		

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
	  public ClassPojo()
		{
			this.csId=csId;
			this.batchId=batchId;
			this.classNo=classNo;
			this.classDate=classDate;
			this.classTopic=classTopic;
			this.classStatus=classStatus;
			this.classStaffId=classStaffId;
			this.classDescription=classDescription;
			this.classComments=classComments;
			this.classNotes=classNotes;
			this.classRecordingPath=classRecordingPath;
			this.batchName=batchName;
			this.classScheduledDates=classScheduledDates;		
		}
	  public int getCsId() {
		return csId;
	}
	public void setCsId(int csId) {
		this.csId = csId;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public int getClassNo() {
		return classNo;
	}
	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}
	public String getClassDate() {
		return classDate;
	}
	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	public String getClassTopic() {
		return classTopic;
	}
	public void setClassTopic(String classTopic) {
		this.classTopic = classTopic;
	}
	public String getClassStatus() {
		return classStatus;
	}
	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus;
	}
	public String getClassStaffId() {
		return classStaffId;
	}
	public void setClassStaffId(String classStaffId) {
		this.classStaffId = classStaffId;
	}
	public String getClassDescription() {
		return classDescription;
	}
	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}
	public String getClassComments() {
		return classComments;
	}
	public void setClassComments(String classComments) {
		this.classComments = classComments;
	}
	public String getClassNotes() {
		return classNotes;
	}
	public void setClassNotes(String classNotes) {
		this.classNotes = classNotes;
	}
	public String getClassRecordingPath() {
		return classRecordingPath;
	}
	public void setClassRecordingPath(String classRecordingPath) {
		this.classRecordingPath = classRecordingPath;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
    public List<String> getClassScheduledDates() {
		return classScheduledDates;
	}

	public void setClassScheduledDates(List<String> classScheduledDates) {
		this.classScheduledDates = classScheduledDates;
	}
}
