package a8.services;

import java.util.Date;

import a8.utils.DateUtils;

public class InterviewFactory {

	public String createInterview(){
		return DateUtils.convertUtilDate2String(new Date() );
	}
	
	public String getCurrentDateTime(){
		return "F: "+DateUtils.convertUtilDate2String(new Date() );
	}
}
