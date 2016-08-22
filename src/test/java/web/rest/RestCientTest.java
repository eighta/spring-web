package web.rest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import a8.data.Person;

public class RestCientTest {

	@Test
	public void probarRestTemplate(){
		
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
	
}
