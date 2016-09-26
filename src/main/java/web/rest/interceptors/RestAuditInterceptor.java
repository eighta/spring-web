package web.rest.interceptors;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import a8.data.Person;
import web.converters.PersonMessageConverter;

@ControllerAdvice(basePackages="web.rest.controllers") //<<<el basePackages indica que Controller se interceptaran 
public class RestAuditInterceptor implements ResponseBodyAdvice<Person>{

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return PersonMessageConverter.class.isAssignableFrom(converterType);
	}

	@Override
	public Person beforeBodyWrite(Person body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		body.setSecondName("Javier");
		return body;
	}

}
