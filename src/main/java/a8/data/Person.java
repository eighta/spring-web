package a8.data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.msgpack.annotation.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import a8.utils.JsonDateSerializer;

@Message
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(Person.class);
	
	private static final String DATE_PATTERN= "yyyy-MM-dd";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

	private Integer id;
	
	@Pattern(regexp = "sophie", message="Debe ser {2}")
	private String firstName;
	
    private String secondName;
    
    private String lastName = "OcHoA";
    
    @DateTimeFormat(pattern = DATE_PATTERN)
    private Date dateOfBirth;
    
    private String gender;
    private String hospital;
    
    //ANOTHERS fields
    @Min(5)
    private Integer email;
    private String country;
    private String city;
    private String lang;
    private String password;
    private String confirmPassword;
    private String securityQuestion;
    private Boolean acceptConditions; 

    public Person(){
    	logger.info("Instanciando...");
    }
    
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

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	public Integer getEmail() {
		return email;
	}

	public void setEmail(Integer email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public Boolean getAcceptConditions() {
		return acceptConditions;
	}

	public void setAcceptConditions(Boolean acceptConditions) {
		this.acceptConditions = acceptConditions;
	}

}
