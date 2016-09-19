package web.converters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import a8.data.Person;
import a8.utils.CsvUtils;
import a8.utils.MessagePackUtils;
import a8.utils.YamlUtils;

public class PersonMessageConverter extends AbstractHttpMessageConverter<Person> {

	public static final String [] columnMapping =  new String[] {"id","firstName","lastName"};
	
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
		byte[] bytes = IOUtils.toByteArray(bodyInputStream);

		return YamlUtils.convertToBean(bytes,Person.class);
		
//MessagePack GOOD!!!		
//		return MessagePackUtils.convertToBean(bytes,Person.class);

//CSV Sucks		
//		//Charsets.UTF_8 <<< GOOGLE
//		String theString = IOUtils.toString(bodyInputStream, StandardCharsets.UTF_8.name());
//		bodyInputStream.close();
//		return CsvUtils.convertCsv2Bean(theString, Person.class, columnMapping);
	}

	@Override
	protected void writeInternal(Person person, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

//CSV Sucks			
//		String csvString = CsvUtils.convertBean2Csv(person,Person.class,columnMapping);
//		byte [] csvByteArray = csvString.getBytes();
		
//MessagePack GOOD!!!		
//		byte[] raw = MessagePackUtils.convertFromBean( person, Person.class);
		
		
		byte[] raw = YamlUtils.convertFromBean(person);
		
		OutputStream bodyOutputStream = outputMessage.getBody();
		bodyOutputStream.write(raw);
		bodyOutputStream.flush();
		bodyOutputStream.close();
	}

}
