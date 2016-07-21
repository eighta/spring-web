package a8.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import a8.utils.JsonDateSerializer;

public class Person {

	private static final String DATE_PATTERN= "yyyy-MM-dd";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
	
    public String firstName;
    public String lastName;
    @DateTimeFormat(pattern = DATE_PATTERN)
    private Date dateOfBirth;

    public Person(String firstName, String lastName, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        try {
			this.dateOfBirth = dateFormat.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //Specialized JSON annotation in order to describe how the date will be formatted in the JSON output
    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
