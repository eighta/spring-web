package web.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import a8.data.Person;
import web.converters.PersonMessageConverter;

public class RestClientTest {
	
	/*
	RestTemplate restTemplate = new RestTemplate();
	
	Si no se especifican argumentos en el constructor, Por defecto las peticiones (REQUEST)
	que se realicen se especificara:
	Accept header to [application/xml, text/xml, application/json, application/*+xml, application/*+json]
	
	Si se especifican en el constructor un List<HttpMessageConverter<?>> messageConverters, 
	en las peticiones que se realicen se incluiran en el Accept header, todos los MediaType definidos en los
	messageConverters
	
	*/
	@Test
	public void probarRestTemplate(){
		
		String url = "http://localhost:8080/spring-web/s/rest/me";
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new PersonMessageConverter() );
		
		RestTemplate restTemplate = new RestTemplate(messageConverters);
		//RestTemplate restTemplate = new RestTemplate();
		//by default: Accept header to [application/xml, text/xml, application/json, application/*+xml, application/*+json]
		
		//restTemplate.getForObject(...)
		Person getForObject = restTemplate.getForObject(url,Person.class);
		String firstName = getForObject.getFirstName();
		System.out.println(firstName);
		
		//restTemplate.getForEntity(...)
//		ResponseEntity<Person> getForEntity = restTemplate.getForEntity(url, Person.class);
	}

	@Ignore
	@Test
	public void probarNoRestTemplate(){
		
		//http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
		RestTemplate restTemplate = new RestTemplate();
		
		//XXX TODO ACTIVAR (PROBAR) TODOS LOS LLAMADOS
		
		//GET
		String url4uniqueObject = "http://localhost:8080/spring-web/s/rest-false/1";
//		Person person = restTemplate.getForObject("http://localhost:8080/spring-web/s/rest-false/1", Person.class);
//		assertNotNull(person);
		ResponseEntity<Person> responseEntityPerson = restTemplate.getForEntity(url4uniqueObject, Person.class);
//		assertNotNull(responseEntityPerson);
		
		//POST
//		restTemplate.postForObject(url, request, responseType, uriVariables);
//		restTemplate.postForEntity(url, request, responseType);
//		restTemplate.postForLocation(url, request);
		
		//PUT
//		restTemplate.put(url, request, urlVariables);
		
		//DELETE
//		restTemplate.delete(url);
		
		//HEAD
//		restTemplate.headForHeaders(url);
		
		//OPTIONS
//		restTemplate.optionsForAllow(url);
		
		//OTHERS (any type of REST calls)
//		restTemplate.exchange(requestEntity, responseType);
//		restTemplate.execute(url, method, requestCallback, responseExtractor);
		
		/*
		//using URI Template
		String url = "http://localhost:8080/mvc-rest/rest-person/id/{id}"; PARAMETER AS TEMPLATE
		Person person = restTemplate.getForObject(url, Person.class, "1");
		// using URI
		String url = "http://localhost:8080/mvc-rest/rest-personid/1";
		Person person = restTemplate.getForObject(url, Person.class);
		*/
		
	}
	
	@Ignore
	@Test
	public void probarExecuteMethodRequestCallback(){
		
		/*
		 String url ="http://localhost:8080/mvc-rest/rest-person/id/{id}";
Person person = restTemplate.execute(url, HttpMethod.GET,
new RequestCallback() {
@Override
public void doWithRequest(ClientHttpRequest request)
throws IOException {
HttpHeaders headers = request.getHeaders();
headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
System.out.println("Request headers = " + headers);
}
}, new HttpMessageConverterExtractor<Person>(Person.class,
restTemplate.getMessageConverters())
, new HashMap<String, Object>() {{
put("id", "1");
}});
		 
		 
		 */
	}
	
	@Ignore
	@Test
	public void probarAsyncRestTemplate(){
		
	}
	
}
