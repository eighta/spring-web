package web.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import a8.data.Person;
import web.converters.PersonMessageConverter;

public class RestClientTest {

	//XXX Using Hamcrest matcher framework
	//http://www.vogella.com/tutorials/Hamcrest/article.html
	
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
	public void EXCHANGE(){
		
		String url = "http://localhost:8080/spring-web/s/rest/me";
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		RequestEntity<?> requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(url) );
		
		//String
		ResponseEntity<Person> exchangeFromString = restTemplate.exchange(url,HttpMethod.GET, requestEntity, Person.class);
		Person personFromString = exchangeFromString.getBody();
		assertNotNull(personFromString);
		assertThat(personFromString.getFirstName(), is(equalTo("Milton")));
		
		//URI
		
		//RequestEntity
	}
	
	
	@Test
	@Ignore
	public void OPTIONS(){
		String url = "http://localhost:8080/spring-web/s/rest";
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		Set<HttpMethod> options = restTemplate.optionsForAllow(url);
		for(HttpMethod httpMethod: options){
			System.out.println(httpMethod);
		}
	}
	
	@Test
	@Ignore
	public void HEAD(){
		String url = "http://localhost:8080/spring-web/s/rest";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = restTemplate.headForHeaders(url);
		MediaType contentType = headers.getContentType();
		assertThat(contentType, is(equalTo(MediaType.TEXT_MARKDOWN)));
	}
	
	@Test
	@Ignore
	public void DELETE(){
		
		String url = "http://localhost:8080/spring-web/s/rest/{id}";
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		restTemplate.delete(url,"987");
	}
	
	@Test
	@Ignore
	public void PUT(){
		
		String url = "http://localhost:8080/spring-web/s/rest";
		
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		Person personTorequest = new Person();
		personTorequest.setId(102030);
		
		restTemplate.put(url, personTorequest);
	}
	
	@Test
	@Ignore
	public void POST(){
		
		String url = "http://localhost:8080/spring-web/s/rest";
		Integer expectedId = 55;
		URI expectedURI = URI.create(url+"/"+expectedId);
		
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		Person personTorequest = new Person();
		personTorequest.setFirstName("Otto");
		personTorequest.setLastName("Normalverbraucher");
		
		//POST 4 OBJECT
		Person personFromResponse = restTemplate.postForObject(url, personTorequest, Person.class);
		assertThat(personTorequest, is(not(equalTo(personFromResponse))));
		assertThat(personFromResponse.getId(), is(equalTo(expectedId)));
		
		//POST 4 ENTITY
		ResponseEntity<Person> postForEntity = restTemplate.postForEntity(url, personTorequest, Person.class);
		Person personFromEntity = postForEntity.getBody(); 
		assertThat(personTorequest, is(not(equalTo(personFromEntity))));
		assertThat(personFromEntity.getId(), is(equalTo(expectedId)));
		
		//POST 4 LOCATION
		URI postLocation = restTemplate.postForLocation(url, personTorequest);
		assertThat(postLocation, is(equalTo(expectedURI)));
	}
	
	@Test
	@Ignore
	public void GET(){
		
		String url = "http://localhost:8080/spring-web/s/rest/me";
		
		//RestTemplate restTemplate = new RestTemplate();
		//by default: Accept header to [application/xml, text/xml, application/json, application/*+xml, application/*+json]
		
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		//GET 4 OBJECT
		Person getForObject = restTemplate.getForObject(url,Person.class);
		assertEquals(getForObject.getFirstName(),"Milton");
		
		//GET 4 ENTITY
		ResponseEntity<Person> getForEntity = restTemplate.getForEntity(url, Person.class);
		assertEquals(getForEntity.getBody().getLastName(),"Ochoa");
	}

	private RestTemplate getRestTemplateWithConverters(){
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new PersonMessageConverter() );
		return new RestTemplate(messageConverters);
	}
	
	//===============
	
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
