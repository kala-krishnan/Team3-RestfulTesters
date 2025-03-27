package com.pojoclass;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPojo {

    private String userComments;
    private String userEduPg;
    private String userEduUg;
    private String userFirstName;
    private String userLastName;
    private String userLinkedinUrl;
    private String userLocation;
    private String userMiddleName;
    private String userPhoneNumber;
    private List<UserRole> userRoleMaps;
    private String userTimeZone;
    private String userVisaStatus;
    private UserLogin userLogin;
    
    //mandatory in all pojo
    
    @JsonIgnore 
    private int statusCode;
    @JsonIgnore 
    private String scenario;
    @JsonIgnore 
	private String statusText;
    
    public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

//	public String getAuthType() {
//		return type;
//	}
//
//	public void setAuthType(String authType) {
//		this.type = authType;
//	}

//	public String getContentType() {
//		return contentType;
//	}
//
//	public void setContentType(String contentType) {
//		this.contentType = contentType;
//	}
	
	@JsonIgnore 
    private String type;
	
//    @JsonIgnore 
//    private String contentType;

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

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getUserComments() {
        return userComments;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }

    public String getUserEduPg() {
        return userEduPg;
    }

    public void setUserEduPg(String userEduPg) {
        this.userEduPg = userEduPg;
    }

    public String getUserEduUg() {
        return userEduUg;
    }

    public void setUserEduUg(String userEduUg) {
        this.userEduUg = userEduUg;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserLinkedinUrl() {
        return userLinkedinUrl;
    }

    public void setUserLinkedinUrl(String userLinkedinUrl) {
        this.userLinkedinUrl = userLinkedinUrl;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public List<UserRole> getUserRoleMaps() {
        return userRoleMaps;
    }

    public void setUserRoleMaps(List<UserRole> userRoleMaps) {
        this.userRoleMaps = userRoleMaps;
    }

    public String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public String getUserVisaStatus() {
        return userVisaStatus;
    }

    public void setUserVisaStatus(String userVisaStatus) {
        this.userVisaStatus = userVisaStatus;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
    @Override
    public String toString() {
        return "UserPojo{" +
                "userComments='" + userComments + '\'' +
                ", userEduPg='" + userEduPg + '\'' +
                ", userEduUg='" + userEduUg + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userLinkedinUrl='" + userLinkedinUrl + '\'' +
                ", userLocation='" + userLocation + '\'' +
                ", userMiddleName='" + userMiddleName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userRoleMaps=" + userRoleMaps +
                ", userTimeZone='" + userTimeZone + '\'' +
                ", userVisaStatus='" + userVisaStatus + '\'' +
                ", userLogin=" + userLogin +
                ", statusCode='" + statusCode + '\'' +
                ", scenario='" + scenario + '\'' +
                '}';
    }
}
