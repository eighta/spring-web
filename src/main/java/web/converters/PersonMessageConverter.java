package web.converters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.google.common.base.Charsets;

import a8.data.Person;
import a8.utils.CsvUtils;

public class PersonMessageConverter extends AbstractHttpMessageConverter<Person> {

	private String [] columnMapping =  new String[] {"firstName","lastName"};
	
	public PersonMessageConverter(){
		super(MediaType.TEXT_HTML);
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		//https://www.javacodegeeks.com/2013/07/spring-mvc-requestbody-and-responsebody-demystified.html
		
		InputStream bodyInputStream = inputMessage.getBody();
		
		//Charsets.UTF_8 <<< GOOGLE
		String theString = IOUtils.toString(bodyInputStream, StandardCharsets.UTF_8.name());
		return CsvUtils.convertCsv2Bean(theString, Person.class, columnMapping);
	}

	@Override
	protected void writeInternal(Person person, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
	
		/* Convert to CSV
		String pattern = "Person (FirstName: \"{0}\", LastName: \"{1}\", DateOfBirth: \"{2}\")";
		Object [] arguments = {person.getFirstName(),person.getLastName(),person.getDateOfBirth() }; 
		String out = MessageFormat.format(pattern, arguments);
		*/
		
		//String csv = CsvUtils.convertBean2Csv(person); XXX ACTIVAR
		String csv = "Javier,Larios,2016-01-01";
		byte [] csvByteArray = csv.getBytes();
		
		OutputStream bodyOutputStream = outputMessage.getBody();
		bodyOutputStream.write(csvByteArray);
		bodyOutputStream.flush();
		bodyOutputStream.close();
		
		
	}

}
