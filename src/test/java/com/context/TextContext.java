package com.context;


public  class TextContext {

	private static final ScenarioContext context = ScenarioContext.getInstance();

	public static void setToken(String token) {
		context.set(ContextKeyEnum.LMSTOKEN.name(), token);
	}

	public static void setUserId(String userId) {
		context.set(ContextKeyEnum.LMS_USER_ID.name(), userId);
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

	public static int getProgramId() {
		return (int) context.get(ContextKeyEnum.LMS_PROGRAM_ID.name());
	}

	public static int getBatchId() {
		return (int) context.get(ContextKeyEnum.LMS_BATCH_ID.name());
	}

	public static int getClassId() {
		return (int) context.get(ContextKeyEnum.LMS_CLASS_ID.name());
	}



	/*public static ScenarioContext scenarioContext = new ScenarioContext();
		public static String setToken(){				
		String retrievedToken =scenarioContext.getContext("LMStoken");
		return retrievedToken;
		}*/
}

