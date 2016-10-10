package web.converters.http.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import a8.data.Person;

public class HtmlFormPersonMessageConverter extends AbstractHttpMessageConverter<Person> {

	public HtmlFormPersonMessageConverter(){
		super(MediaType.APPLICATION_FORM_URLENCODED);
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
		InputStream bodyInputStream = inputMessage.getBody();
		
		StringWriter writer = new StringWriter();
		IOUtils.copy(bodyInputStream, writer, StandardCharsets.UTF_8);
		String urlStringEncoded = writer.toString();

		List<NameValuePair> parse = URLEncodedUtils.parse(urlStringEncoded, StandardCharsets.UTF_8);
		
		Person person = new Person();
		
		for(NameValuePair nameValuePair:parse){
			String name = nameValuePair.getName();
			String value = nameValuePair.getValue();
			
			//Beanutils
			try {BeanUtils.setProperty(person, name, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();}
		}
		
		return person;
	}

	@Override
	protected void writeInternal(Person person, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
	
		
		//USING JACKSON
		//ObjectMapper mapper = new ObjectMapper();
		//Map<String,Object> props = mapper.convertValue(person, Map.class);
		
		Map<String, String> objectAsMap = null;
		try {
			objectAsMap = BeanUtils.describe(person);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		// Java8
		objectAsMap.values().removeIf(Objects::isNull);	
		
		//XXX Usar lambda 
		//objectAsMap.keySet().removeIf( isClass() );
		objectAsMap.remove("class");
		
		
		List<NameValuePair> parameters = new ArrayList<>();
		for(Entry<String,String> entry:objectAsMap.entrySet()){
			parameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue()) );
		}
		
		String stringEncoded = URLEncodedUtils.format(parameters, StandardCharsets.UTF_8);
		byte[] raw = stringEncoded.getBytes();
		
		OutputStream bodyOutputStream = outputMessage.getBody();
		bodyOutputStream.write(raw);
		bodyOutputStream.flush();
		bodyOutputStream.close();
	}
	
}
