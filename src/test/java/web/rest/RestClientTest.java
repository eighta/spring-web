package web.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import a8.data.Person;
import a8.utils.CsvUtils;
import web.converters.PersonMessageConverter;
import web.converters.SeveralPersonMessageConverter;

class MyRequestCallBack implements RequestCallback{

	@Override
	public void doWithRequest(ClientHttpRequest request) throws IOException {
		System.out.println("doWithRequest...");
		
		//from javadoc:
		//http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/client/RequestCallback.html
		//
		//Callback interface for code that operates on a ClientHttpRequest. Allows to manipulate the request headers, and write to the request body.
	}
}

class MyResponseExtractor implements ResponseExtractor<Person>{

	@Override
	public Person extractData(ClientHttpResponse response) throws IOException {
		InputStream bodyInputStream = response.getBody();
		String theString = IOUtils.toString(bodyInputStream, StandardCharsets.UTF_8.name());
		bodyInputStream.close();
		
		return CsvUtils.convertCsv2Bean(theString, Person.class, PersonMessageConverter.columnMapping);
	}}

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
	public void restTemplatePlusApacheHttpClient(){
	
		/*
		highly recommended for production applications when authentication 
		and HTTP connection pooling are usually needed
		*/
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory 
			= new HttpComponentsClientHttpRequestFactory();
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
		restTemplate.setMessageConverters(this.getMessageConverters());
		
		String url = "http://localhost:8080/spring-web/s/rest/family";
		Person[] persons = restTemplate.getForObject(url,Person[].class);
		assertNotNull(persons);
		assertThat(persons.length, is(equalTo(2)));
		assertThat(persons[0].getFirstName(), is(equalTo("Milton")));
		assertThat(persons[1].getFirstName(), is(equalTo("Sophie")));
		
	}
	
	@Ignore
	@Test
	public void asyncRestTemplate(){
		//XXX CREAR UNA PRUEBA ASYNCRONA!
	}

	@Ignore
	public void EXECUTE(){
		
		String url = "http://localhost:8080/spring-web/s/rest/me";
		URI uri = URI.create(url);
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		Person person = restTemplate.execute(uri, HttpMethod.GET, new MyRequestCallBack(), new MyResponseExtractor() );
		assertNotNull(person);
		assertThat(person.getFirstName(), is(equalTo("Milton")));
		assertThat(person.getLastName(), is(equalTo("Ochoa")));
		
		//ANOTHER TEST!!!
		restTemplate = new RestTemplate(); //<<< DEFAULT
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.add(new PersonMessageConverter() );
		
		Person person2 = restTemplate.execute(uri, HttpMethod.GET, new MyRequestCallBack(), 
				new HttpMessageConverterExtractor<Person>(
						Person.class,
						messageConverters )
				 );
		assertNotNull(person2);
		assertThat(person2.getFirstName(), is(equalTo("Milton")));
		assertThat(person2.getLastName(), is(equalTo("Ochoa")));
	}
	
	@Test
	public void EXCHANGE(){
		
		String url = "http://localhost:8080/spring-web/s/rest/me";
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		//String
		ResponseEntity<Person> exchangeFromString = restTemplate.exchange(url,HttpMethod.GET, /*requestEntity*/null, Person.class);
		Person personFromString = exchangeFromString.getBody();
		assertNotNull(personFromString);
		assertThat(personFromString.getFirstName(), is(equalTo("Milton")));
		
		//URI
		URI uri = URI.create(url);
		ResponseEntity<Person> exchangeFromURI = restTemplate.exchange(uri, HttpMethod.GET, /*requestEntity*/null, Person.class);
		Person personFromURI = exchangeFromURI.getBody();
		assertNotNull(personFromURI);
		assertThat(personFromURI.getLastName(), is(equalTo("Ochoa")));
		
		//RequestEntity
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList( new MediaType[] { MediaType.APPLICATION_JSON }  ));
		headers.setContentType(MediaType.APPLICATION_ATOM_XML);
		
		RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url) );
		
		ResponseEntity<Person> exchangeFromRequestEntity = restTemplate.exchange(requestEntity, Person.class);
		Person personFromRequestEntity = exchangeFromRequestEntity.getBody();
		assertNotNull(personFromRequestEntity);
		assertThat(personFromRequestEntity.getId(), is(equalTo(1)));
	}
	
	@Test
	public void OPTIONS(){
		String url = "http://localhost:8080/spring-web/s/rest";
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		Set<HttpMethod> options = restTemplate.optionsForAllow(url);
		for(HttpMethod httpMethod: options){
			System.out.println(httpMethod);
		}
	}
	
	@Test
	public void HEAD(){
		String url = "http://localhost:8080/spring-web/s/rest";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = restTemplate.headForHeaders(url);
		MediaType contentType = headers.getContentType();
		assertThat(contentType, is(equalTo(MediaType.TEXT_MARKDOWN)));
	}
	
	@Test
	public void DELETE(){
		
		String url = "http://localhost:8080/spring-web/s/rest/{id}";
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		restTemplate.delete(url,"987");
	}
	
	@Test
	public void PUT(){
		
		String url = "http://localhost:8080/spring-web/s/rest";
		
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		Person personTorequest = new Person();
		personTorequest.setId(102030);
		
		restTemplate.put(url, personTorequest);
	}
	
	@Test
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
		
		//POST 4 OBJECT (within HttpEntity)
		HttpEntity<Person> personRequest = new HttpEntity<>(personTorequest);
		Person personWithinEntityFromResponse = restTemplate.postForObject(url, personRequest, Person.class);
		assertThat(personWithinEntityFromResponse.getId(), is(equalTo(expectedId)));
		
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
		
		//>>>SeveralPerson
		url = "http://localhost:8080/spring-web/s/rest/family";
		Person[] persons = restTemplate.getForObject(url,Person[].class);
		assertNotNull(persons);
		assertThat(persons.length, is(equalTo(2)));
		
	}

	private RestTemplate getRestTemplateWithConverters(){
		return new RestTemplate(getMessageConverters() );
	}
	
	private List<HttpMessageConverter<?>> getMessageConverters(){
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new PersonMessageConverter() );
		messageConverters.add(new SeveralPersonMessageConverter() );
		return messageConverters;
	}
}
