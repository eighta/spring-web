package web.converters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import a8.data.Person;
import a8.utils.CsvUtils;

public class SeveralPersonMessageConverter extends AbstractHttpMessageConverter<Person[]> {

	public SeveralPersonMessageConverter(){
		super(MediaType.TEXT_HTML);
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return Person[].class.equals(clazz);
	}

	@Override
	protected Person[] readInternal(Class<? extends Person[]> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
		List<Person> persons = new ArrayList<>();
		
		InputStream bodyInputStream = inputMessage.getBody();
		String theString = IOUtils.toString(bodyInputStream, StandardCharsets.UTF_8.name());
		bodyInputStream.close();
		
		String lines[] = theString.split(System.getProperty("line.separator"));
		for(String line:lines){
			Person p = CsvUtils.convertCsv2Bean(line, Person.class, PersonMessageConverter.columnMapping);
			persons.add(p);
		}
		
		//http://stackoverflow.com/questions/4042434/converting-arrayliststring-to-string-in-java
		return persons.toArray(new Person[0]);
		
	}

	@Override
	protected void writeInternal(Person[] variousPersons, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		OutputStream bodyOutputStream = outputMessage.getBody();
		for(Person person:variousPersons){
			String csvString = CsvUtils.convertBean2Csv(person,Person.class,PersonMessageConverter.columnMapping);
			byte [] csvByteArray = csvString.getBytes();
			bodyOutputStream.write(csvByteArray);
		}
		bodyOutputStream.flush();
		bodyOutputStream.close();
		
	}

}
