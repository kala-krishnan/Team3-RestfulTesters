package com.context;


public  class TextContext {

	private static final ScenarioContext context = ScenarioContext.getInstance();

	public static void setToken(String token) {
		context.set(ContextKeyEnum.LMSTOKEN.name(), token);
	}

	public static void setUserId(String userId) {
		context.set(ContextKeyEnum.LMS_USER_ID.name(), userId);
	}

	public static void setRoleId(String role_id) {
		context.set(ContextKeyEnum.LMS_ROLE_ID.name(), role_id);
	}
	
	public static void setprogramName(String  programName) {
		context.set(ContextKeyEnum.LMS_PROGRAM_NAME.name(), programName);
	}
	
	public static void setBatchName(String batchName) {
		context.set(ContextKeyEnum.LMS_BATCH_NAME.name(), batchName);
	}
	public static void setProgramId(Integer  programId) {
		context.set(ContextKeyEnum.LMS_PROGRAM_ID.name(), programId);
	}
	
	public static void setBatchId(int batchId) {
		context.set(ContextKeyEnum.LMS_BATCH_ID.name(), batchId);
	}

	public static void setClassId(String classId) {
		context.set(ContextKeyEnum.LMS_CLASS_ID.name(), classId);
	}

	public static String getToken() {
		return (String) context.get(ContextKeyEnum.LMSTOKEN.name());
	}

	public static String getUserId() {
		return (String) context.get(ContextKeyEnum.LMS_USER_ID.name());
	}

	public static Integer getProgramId() {
		return (Integer) context.get(ContextKeyEnum.LMS_PROGRAM_ID.name());
	}

	public static Integer getBatchId() {
		return (Integer) context.get(ContextKeyEnum.LMS_BATCH_ID.name());
	}

	public static Integer getClassId() {
		return (Integer) context.get(ContextKeyEnum.LMS_CLASS_ID.name());
	}
	
	public static String getRoleId() {
		return (String) context.get(ContextKeyEnum.LMS_ROLE_ID.name());
	}
	
	public static String getProgramName() {
		return (String) context.get(ContextKeyEnum.LMS_PROGRAM_NAME.name());
	}
	
	public static String getBatchName() {
		return (String) context.get(ContextKeyEnum.LMS_BATCH_NAME.name());
	}
		
	}

