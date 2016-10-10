package web.converters.http.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import a8.data.Person;
import a8.utils.MessagePackUtils;

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

		InputStream bodyInputStream = inputMessage.getBody();
		byte[] bytes = IOUtils.toByteArray(bodyInputStream);
		return MessagePackUtils.convertToBean(bytes,Person[].class);
		
//CSV Sucks				
//		List<Person> persons = new ArrayList<>();
//		
//		InputStream bodyInputStream = inputMessage.getBody();
//		
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bodyInputStream, StandardCharsets.UTF_8.name()));
//		try{
//			String line=null;
//			while( (line=bufferedReader.readLine()) != null ){
//				System.out.println(line);
//			}
//			}catch(Exception e){e.printStackTrace();}
//		
//		String theString = IOUtils.toString(bodyInputStream, StandardCharsets.UTF_8.name());
//		bodyInputStream.close();
//		
//		String lines[] = theString.split(System.getProperty("line.separator"));
//		for(String line:lines){
//			Person p = CsvUtils.convertCsv2Bean(line, Person.class, PersonMessageConverter.columnMapping);
//			persons.add(p);
//		}
//		
//		//http://stackoverflow.com/questions/4042434/converting-arrayliststring-to-string-in-java
//		return persons.toArray(new Person[0]);
		
		
	}

	@Override
	protected void writeInternal(Person[] variousPersons, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		byte[] raw = MessagePackUtils.convertFromBean(variousPersons, Person[].class);
		
		OutputStream bodyOutputStream = outputMessage.getBody();
		bodyOutputStream.write(raw);
		bodyOutputStream.flush();
		bodyOutputStream.close();
		
//		for(Person person:variousPersons){
////CSV Sucks					
////			String csvString = CsvUtils.convertBean2Csv(person,Person.class,PersonMessageConverter.columnMapping);
////			byte [] csvByteArray = csvString.getBytes();
//			
//			MessagePackUtils.convertFromBean(person, clazz)
//			
//			bodyOutputStream.write(csvByteArray);
//		}
		
		
	}

}
