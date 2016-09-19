package web.converters;

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
import a8.utils.JsonUtils;

public class JsonPersonMessageConverter extends AbstractHttpMessageConverter<Person> {

	public JsonPersonMessageConverter(){
		super(MediaType.APPLICATION_JSON);
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		InputStream bodyInputStream = inputMessage.getBody();
		byte[] bytes = IOUtils.toByteArray(bodyInputStream);

		return JsonUtils.convertToBean(bytes,Person.class);
	}

	@Override
	protected void writeInternal(Person person, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		byte[] raw = JsonUtils.convertFromBean(person);
		
		OutputStream bodyOutputStream = outputMessage.getBody();
		bodyOutputStream.write(raw);
		bodyOutputStream.flush();
		bodyOutputStream.close();
	}

}
