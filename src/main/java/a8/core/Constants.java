package a8.core;

import java.time.format.DateTimeFormatter;

public class Constants {

	public static final String STRING_DATE_FORMAT = "yyyy-MM-dd";
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(STRING_DATE_FORMAT);
	
	public static final String STRING_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(STRING_DATE_TIME_FORMAT);
}
