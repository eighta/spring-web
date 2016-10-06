package a8.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import a8.core.Constants;

public class DateUtils {

	//DATE+TIME
	public static synchronized String convertUtilDate2String(java.util.Date date){
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		return date!=null?Constants.DATE_TIME_FORMATTER.format(ldt):null;
	}
	
	//--ONLY DATES
	public static synchronized String convertSqlDate2String(java.sql.Date date){
		return date!=null?Constants.DATE_FORMATTER.format(date.toLocalDate()):null;
	}
	
	public static synchronized String convertLocalDate2String(LocalDate localDate){
		return Constants.DATE_FORMATTER.format(localDate);
	}
	
	public static synchronized String convertLocalDate2String(LocalDate localDate,String format){
		return DateTimeFormatter.ofPattern(format).format(localDate);
	}
	
	public static synchronized LocalDate convertString2LocalDate(String date){
		return LocalDate.parse(date, Constants.DATE_FORMATTER);
	}
	
	public static synchronized java.sql.Date convertLocalDate2SqlDate(LocalDate date){
		return java.sql.Date.valueOf(date);
	}
	
	public static synchronized boolean isDateformatValid(String date){
		try{
			
			Constants.DATE_FORMATTER.parse(date);
			return Boolean.TRUE;
		}catch(DateTimeParseException e){
			return Boolean.FALSE;
		}
	}
}
