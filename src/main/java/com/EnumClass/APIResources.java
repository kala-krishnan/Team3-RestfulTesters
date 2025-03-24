package com.EnumClass;

public enum APIResources {
	
	//User Login Endpoints
	APILoginPost("/login"),
	APILogoutGet("/logoutlms"),
	//Program Endpoints
	APIAddProgram("/saveprogram"),
	APIGetAllProgram("/allPrograms"),
	APIGetAllPrgmWithUser("/allProgramsWithUsers"),
	APIGetProgramByID("/programs/{programId}"),
	APIUpdateProgramByName("/program/{programName}"),
	APIUpdateProgramByID("/putprogram/{programId}"),
	APIDeleteProgramByID ("/deletebyprogid/{programId}"),
	APIDeleteProgramByName ("/deletebyprogname/{programName}"),
	
	//Batch Endpoints
	APIAddBatch("/batches"),
	APIGetAllBatch ("/batches"),
	APIGetBatchByID("/batches/batchId/"),
	APIGetBatchByName("/batches/batchName/"),
	APIGetBatchByPrgmID("/batches/program/"),
	APIUpdateBatchByID("/batches/"),
	APIDeleteBatchByID("/batches/"),
	
	//User Endpoints
	
	APIUpdateUser("/users/"),
	APIDeleteUserByID("/users/"),
	APIUpdateUserStatus("/users/userLogin/"),
	APIUpdateUserByRPBStatus("/users/roleProgramBatchStatus/"),
	APIUpdateUserByID("/users/roleId/"),
	APICreateUserWithRole("/users/roleStatus"),
	APIGetUserWithFilter("/v2/users"),
	APIGetAllUser("/users"),
	APIGetUserByID("/users/"),
	APIGetUserBatchID("/users/user/"),
	APIGetAllUserRoles("/users/roles"),
	APIGetUserByRole("/users/roles/"),
	APIGetByProgramID("/users/programs/"),
	APIGetByBatchID("/users/programBatch/"),
	APIGetUserByUserID("/users/details/"),
	APIGetUserByStatus("/users/byStatus"),
	APIGetActiveUser("/users/activeUsers"),
	APIGetAllRoles("/roles"),
	APIGetAllUserEmail("/fetch-emails"),
	
	//Class Endpoints
	APIClassPost("/CreateClassSchedule"),
	APIGetAllClasses("/allClasses"),
	APIGetAllParticularClass("/upcomingClasses/{studentID}"),
	APIGetClassByBatchId("/batchRecordings/{batchId}"),
	APIGetClassByID("/class/{classId}"),
	APIGetAllClassesByClassTopic("/classes/{classTopic}"),
	APIGetAllClassesByBatchId("/classesbyBatch/{batchId}"),
	APIGetAllClassesByStaffId("/classesByStaff/{staffId}"),
	APIGetAllRecordings("/classrecordings"), 
	APIGetClassRecordingsByClassId("/classRecordings/{classId}"),
	APIUpdateNewClass("/updateClass/{classId}"),
	APIUpdateClassRecordingPath("/updateClassrecording/{classId}"),
	APIDeleteClassByClassId("/deleteByClass/{classId}"),
	APIDownloadClassRecording("/download/{csId}");
	
	
	private String resources;
	APIResources(String resources){
	this.resources = resources;	
	}
	public String getResources()
	{
		return resources;
	}
	
}
