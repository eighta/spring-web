package a8.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.msgpack.annotation.Message;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import a8.utils.JsonDateSerializer;

@Message
public class Person {

	private static final String DATE_PATTERN= "yyyy-MM-dd";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

	private Integer id;
	
	private String firstName;
	
    private String secondName;
    
    private String lastName;
    
    @DateTimeFormat(pattern = DATE_PATTERN)
    private Date dateOfBirth;
    
    private String gender;
    private String hospital;

    public Person(){}
    
    public Person(Integer id, String firstName, String lastName, String dob) {
    	this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        try {
			this.dateOfBirth = dateFormat.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    //Specialized JSON annotation in order to describe how the date will be formatted in the JSON output
    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

	public static final SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public static final void setDateFormat(SimpleDateFormat dateFormat) {
		Person.dateFormat = dateFormat;
	}

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final String getSecondName() {
		return secondName;
	}

	public final void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final String getGender() {
		return gender;
	}

	public final void setGender(String gender) {
		this.gender = gender;
	}

	public final String getHospital() {
		return hospital;
	}

	public final void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public static final String getDatePattern() {
		return DATE_PATTERN;
	}

	public final void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


}
