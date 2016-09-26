package web.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import a8.data.Person;
import a8.data.User;
import a8.utils.JsonUtils;
import a8.utils.YamlUtils;
import web.converters.HtmlFormPersonMessageConverter;
import web.converters.JsonPersonMessageConverter;
import web.converters.PersonMessageConverter;
import web.converters.SeveralPersonMessageConverter;
class MyRequestCallBack implements RequestCallback{

	//from javadoc:
	//http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/client/RequestCallback.html
	//Callback interface for code that operates on a ClientHttpRequest. Allows to manipulate the request headers, and write to the request body.
	
	@Override
	public void doWithRequest(ClientHttpRequest request) throws IOException {
		System.out.println("doWithRequest...");
	}
}
class MyRequestCallBack4postForLocationWithServletResponse implements RequestCallback{

	//RequestCallback
	//Callback interface for code that operates on a ClientHttpRequest. Allows to manipulate the request headers, and write to the request body.
	
	@Override
	public void doWithRequest(ClientHttpRequest request) throws IOException {
		
		System.out.println("ClientHttpRequest...");
		
		//HEADERS
		HttpHeaders headers = request.getHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//BODY
		Person person = new Person();
		person.setFirstName("Alonso");
		person.setLastName("Punk");
		byte[] raw = JsonUtils.convertFromBean(person);
		
		OutputStream bodyOutputStream = request.getBody();
		bodyOutputStream.write(raw);
		bodyOutputStream.flush();
//		JAVADOC: Does not need to care about closing the request or about handling errors: this will all be handled by the RestTemplate.
//		bodyOutputStream.close(); 
		
	}
}

class MyResponseExtractor implements ResponseExtractor<Person>{

	@Override
	public Person extractData(ClientHttpResponse response) throws IOException {
		HttpHeaders headers = response.getHeaders();
		MediaType contentType = headers.getContentType();
		System.out.println(contentType);
		
		InputStream bodyInputStream = response.getBody();
		byte[] bytes = IOUtils.toByteArray(bodyInputStream);
		return YamlUtils.convertToBean(bytes,Person.class);
		
		//OLD: Csv Sucks
//		String theString = IOUtils.toString(bodyInputStream, StandardCharsets.UTF_8.name());
//		bodyInputStream.close();
//		return CsvUtils.convertCsv2Bean(theString, Person.class, PersonMessageConverter.columnMapping);
	}}

class AsyncCallback 
//implements ListenableFuture<Person>{ (Many METHODS to implement)
implements ListenableFutureCallback<ResponseEntity<Person>>{

	@Override
	public void onSuccess(ResponseEntity<Person> result) {
System.out.println("onSuccess(...)");
	}

	@Override
	public void onFailure(Throwable ex) {
System.out.println("onFailure(...): " + ex.getMessage());
	}
}

class MyResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
System.out.println("hasError(...)");
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
System.out.println("handleError(...)");
	}
	
}


public class RestClientTest {

	
	private boolean everythingOk = Boolean.TRUE;
	
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
	public void probandoInterceptores(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest/me";
		
		//RestTemplate restTemplate = new RestTemplate();
		//by default: Accept header to [application/xml, text/xml, application/json, application/*+xml, application/*+json]
		
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		//GET 4 OBJECT
		Person getForObject = restTemplate.getForObject(url,Person.class);
		assertEquals(getForObject.getFirstName(),"Milton");
		assertThat(getForObject.getSecondName(), is(equalTo("Javier")));
		
		
//		//GET 4 ENTITY
//		ResponseEntity<Person> getForEntity = restTemplate.getForEntity(url, Person.class);
//		assertEquals(getForEntity.getBody().getLastName(),"Ochoa");
//		
//		//>>>SeveralPerson
//		url = "http://localhost:8080/spring-web/s/rest/family";
//		Person[] persons = restTemplate.getForObject(url,Person[].class);
//		assertNotNull(persons);
//		assertThat(persons.length, is(equalTo(2)));
		
	}
	
	
	@Test
	@Ignore
	public void servlet(){
		
		String url = "http://localhost:8080/spring-web/servlet";
		URI uri = URI.create(url);
		
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<Void> requestEntity = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_JSON)
				.build();
		ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity,Void.class);
	}
	
	@Test
	@Ignore
	public void githubApi(){
		
		String url = "https://api.github.com/users/eighta";
		
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress("10.1.0.194", 3128));
	    requestFactory.setProxy(proxy);
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(url))
				.accept(MediaType.APPLICATION_JSON)
				.build(); 
		
		//User user = restTemplate.getForObject(url, User.class);
		ResponseEntity<User> responseEntity = restTemplate.exchange(requestEntity, User.class);
		
		HttpHeaders responseHeaders = responseEntity.getHeaders();
		MediaType responseContentType = responseHeaders.getContentType();
		assertThat(responseContentType.includes(MediaType.APPLICATION_JSON) , is(equalTo(Boolean.TRUE)));
		
		User user = responseEntity.getBody();
		assertThat(user.getName() , is(equalTo("Javier Larios")));
	}
	
	@Test
	public void testAsyncAnnotation(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest/async";
		RestTemplate restTemplate = new RestTemplate(); //<<< DEFAULT
		
		ResponseEntity<Void> responseEntity = restTemplate.getForEntity(URI.create(url), Void.class);
	}
	
	@Test
	public void customHeader(){
		assumeTrue(everythingOk);

		String url = "http://localhost:8080/spring-web/s/rest/headers";
		String customHeaderValueIn = "Sophie";
		
		RestTemplate restTemplate = new RestTemplate(); //<<< DEFAULT
		
		
		RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(url) )
											.header("CUSTOM-HEADER-IN", customHeaderValueIn).build();
		
		ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);
		
		String customHeaderValueOut = responseEntity.getHeaders().getFirst("CUSTOM-HEADER-OUT");
		assertThat(customHeaderValueOut, is(equalTo(customHeaderValueIn+"Ochoa")));
		
	}
	
	@Test
	public void postForLocationWithServletResponse(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest";
		URI uri = URI.create(url);
		
		RestTemplate restTemplate = new RestTemplate(); //<<< DEFAULT
		restTemplate.setMessageConverters(
				new ArrayList<HttpMessageConverter<?>>(){
					{
						add(new JsonPersonMessageConverter());
						
					}
				}
			);
		
		//Entity (to Request)
		Person personTorequest = new Person();
		personTorequest.setFirstName("Alonso");
		personTorequest.setLastName("Punk");
		
		//Headers (to Request)
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList( new MediaType[] { MediaType.APPLICATION_JSON} ));
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		//Request Entity
		RequestEntity<Person> requestEntity = new RequestEntity<>(personTorequest, requestHeaders,HttpMethod.POST, uri, Person.class);
		
//		RequestEntity<Person> requestEntity2 = RequestEntity.post(uri)
//													.accept(MediaType.APPLICATION_JSON)
//													.contentType(MediaType.APPLICATION_JSON)
		
		//Response Entity
		ResponseEntity<Person> responseEntity = restTemplate.exchange(requestEntity, Person.class);
		
		//Entity (from Response)
		Person personFromResponse = responseEntity.getBody();
		assertNotNull(personFromResponse);
		
		//Headers (from Response)
		HttpHeaders responseHeaders = responseEntity.getHeaders();
		URI location = responseHeaders.getLocation();
		
		Integer expectedId = 88;
		URI expectedURI = URI.create(url+"/"+expectedId);
		assertThat(location, is(equalTo(expectedURI)));
	}
	
	@Test
	public void executeLambda(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest/json";
		
		//Java8 - lambda
		RestTemplate restTemplate = new RestTemplate(); //<<< DEFAULT
		restTemplate.setMessageConverters(
				new ArrayList<HttpMessageConverter<?>>(){
					{
						add(new JsonPersonMessageConverter());
						
					}
				}
			);
		
		Person person = restTemplate.execute(url, HttpMethod.GET, 
				
				//lambda junto con headers
				request -> {
					HttpHeaders headerss = request.getHeaders();
					headerss.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
				},
				
				new HttpMessageConverterExtractor<Person>(
						Person.class,
						restTemplate.getMessageConverters() )
				 );
		assertNotNull(person);
		assertThat(person.getLastName(), is(equalTo(MediaType.APPLICATION_JSON_VALUE)));
		
	}
	
	@Test
	public void getJson(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest/json";
		URI uri = URI.create(url);
		
		RestTemplate restTemplate = new RestTemplate();
		
		//on-the-fly
		//http://stackoverflow.com/questions/852822/java-arraylist-and-hashmap-on-the-fly
		restTemplate.setMessageConverters(
					new ArrayList<HttpMessageConverter<?>>(){
						{
							add(new JsonPersonMessageConverter());
							
						}
					}
				);
		
		/*		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList( new MediaType[] { MediaType.APPLICATION_JSON} ));
		
		RequestEntity<Void> requestEntity = new RequestEntity<>(requestHeaders, HttpMethod.GET, uri);
or
*/		
		RequestEntity<Void> requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
		
		ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity,Void.class);
		
		HttpHeaders responseHeaders = responseEntity.getHeaders();
		MediaType responseContentType = responseHeaders.getContentType();
		
		assertThat(responseContentType, is(equalTo(MediaType.APPLICATION_JSON)));
	}
	
	@Test
	public void testConsumeAttribute(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest";
		URI uri = URI.create(url);
		
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		Person personTorequest = new Person();
		personTorequest.setFirstName("Otto");
		personTorequest.setLastName("Normalverbraucher");
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList( new MediaType[] { MediaType.APPLICATION_JSON} ));
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		RequestEntity<Person> requestEntity = new RequestEntity<>(personTorequest, requestHeaders,HttpMethod.POST, uri, Person.class);
		
		ResponseEntity<Person> responseEntity = restTemplate.exchange(requestEntity, Person.class);
		HttpHeaders responseHeaders = responseEntity.getHeaders();
		MediaType responseContentType = responseHeaders.getContentType();
		assertThat(responseContentType, is(equalTo(MediaType.APPLICATION_JSON)));

		Person personFromResponse = responseEntity.getBody();
		assertThat(personTorequest, is(not(equalTo(personFromResponse))));
		assertThat(personFromResponse.getSecondName(), is(equalTo("SECONDNAME-JSON")));
		
	}
	
	@Test
	public void testIncludeBodyInControllerAdvice(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest/body";
		
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		ResponseEntity<Person> getForEntity = restTemplate.getForEntity(url,Person.class);
		
		HttpStatus statusCode = getForEntity.getStatusCode();
		Person person = getForEntity.getBody();
		
		assertThat(statusCode, is(equalTo(HttpStatus.PERMANENT_REDIRECT)));
		assertThat(person, is(not(nullValue())));
		assertThat(person.getFirstName(), is(equalTo("Ivan")));
	}
	
	@Test
	public void restException(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest/exception";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new MyResponseErrorHandler() );
		
		ResponseEntity<?> getForEntity = restTemplate.getForEntity(url, null);
		HttpStatus statusCode = getForEntity.getStatusCode();
		assertThat(statusCode, is(equalTo(HttpStatus.NOT_IMPLEMENTED)));
	}
	
	@Test
	public void statusCodeTest(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest/status/{status}";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new MyResponseErrorHandler() );
		
		//GET 4 ENTITY
		//200
		ResponseEntity<?> getForEntity = restTemplate.getForEntity(url, null, "200");
		HttpStatus statusCode = getForEntity.getStatusCode();
		assertThat(statusCode, is(equalTo(HttpStatus.OK)));
		
		//201
		getForEntity = restTemplate.getForEntity(url, null, "201");
		statusCode = getForEntity.getStatusCode();
		assertThat(statusCode, is(equalTo(HttpStatus.CREATED)));
		
		//202
		getForEntity = restTemplate.getForEntity(url, null, "202");
		statusCode = getForEntity.getStatusCode();
		assertThat(statusCode, is(equalTo(HttpStatus.ACCEPTED)));
		
		//204
		getForEntity = restTemplate.getForEntity(url, null, "204");
		statusCode = getForEntity.getStatusCode();
		assertThat(statusCode, is(equalTo(HttpStatus.NO_CONTENT)));
		
		//300
		getForEntity = restTemplate.getForEntity(url, null, "300");
		statusCode = getForEntity.getStatusCode();
		assertThat(statusCode, is(equalTo(HttpStatus.MULTIPLE_CHOICES)));
		
		//400
		getForEntity = restTemplate.getForEntity(url, null, "400");
		statusCode = getForEntity.getStatusCode();
		assertThat(statusCode, is(equalTo(HttpStatus.BAD_REQUEST)));
		
		//404
		getForEntity = restTemplate.getForEntity(url+"/UNKNOWN", null,"404");
		statusCode = getForEntity.getStatusCode();
		assertThat(statusCode, is(equalTo(HttpStatus.NOT_FOUND)));
	}
	
	@Test
	public void asyncRestTemplateCallback() throws InterruptedException, ExecutionException{
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest";
		URI uri = URI.create(url);
		
		Person requestPerson = new Person();
		requestPerson.setFirstName("Javier");
		requestPerson.setLastName("Larios");
		
		AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
		asyncRestTemplate.setMessageConverters(this.getMessageConverters());
		
		RequestEntity<Person> requestEntity = 
				new RequestEntity<>(requestPerson,/*headers IGNORED*/null,/*HttpMethod IGNORED*/null,uri);
		
		ListenableFuture<ResponseEntity<Person>> futurePerson = asyncRestTemplate.exchange(uri,HttpMethod.POST,requestEntity, Person.class);
		futurePerson.addCallback(new AsyncCallback() );
		
		ResponseEntity<Person> responseEntity = futurePerson.get();
		Person personFromResponse = responseEntity.getBody();
		
		assertThat(personFromResponse.getId(), is(equalTo(55)));
	}
	
	@Test
	public void asyncRestTemplatePOST() throws InterruptedException, ExecutionException{
		assumeTrue(everythingOk);
		
		//return Future<T> wrappers (or ListenableFuture<F>
		//that extends Future<T> when a callback method is needed
		
		String url = "http://localhost:8080/spring-web/s/rest";
		URI uri = URI.create(url);
		
		Person requestPerson = new Person();
		requestPerson.setFirstName("Javier");
		requestPerson.setLastName("Larios");
		
		AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
		asyncRestTemplate.setMessageConverters(this.getMessageConverters());
		
		//RequestEntity
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList( new MediaType[] { MediaType.APPLICATION_JSON }  ));
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		RequestEntity<Person> requestEntity = new RequestEntity<>(requestPerson,/*headers IGNORED*/null,/*HttpMethod IGNORED*/null,uri);
		
		Future<ResponseEntity<Person>> asyncExchange = 
				asyncRestTemplate.exchange(uri,HttpMethod.POST,requestEntity, Person.class);
		
		////other computation ...
		
		//AJAX: ACA SE BLOQUEA HASTA QUE EL SERVIDOR EFECTIVAMENTE RESPONDA
		ResponseEntity<Person> responseEntity = asyncExchange.get();
		
		Person responsePerson = responseEntity.getBody();
		
		assertThat(responsePerson, is(not(equalTo(requestPerson))));
		assertThat(responsePerson.getId(), is(equalTo(55)));
	}
	
	@Test
	public void restTemplatePlusApacheHttpClient(){
		assumeTrue(everythingOk);
	
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

	@Test
	//EXECUTE solo retorna un Pojo (un entity, T), en cambio el EXCHANGE devuelve ResponseEntity<T>
	public void EXECUTE(){
		assumeTrue(everythingOk);
		
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
	//EXCHANGE es mas poderoso que EXECUTE
	public void EXCHANGE(){
		assumeTrue(everythingOk);
		
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
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest";
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		Set<HttpMethod> options = restTemplate.optionsForAllow(url);
		for(HttpMethod httpMethod: options){
			System.out.println(httpMethod);
		}
	}
	
	@Test
	public void HEAD(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = restTemplate.headForHeaders(url);
		MediaType contentType = headers.getContentType();
		assertThat(contentType, is(equalTo(MediaType.TEXT_MARKDOWN)));
	}
	
	@Test
	public void DELETE(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest/{id}";
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		restTemplate.delete(url,"987");
	}
	
	@Test
	public void PUT(){
		assumeTrue(everythingOk);
		
		String url = "http://localhost:8080/spring-web/s/rest";
		
		RestTemplate restTemplate = this.getRestTemplateWithConverters();
		
		Person personTorequest = new Person();
		personTorequest.setId(102030);
		
		restTemplate.put(url, personTorequest);
	}
	
	@Test
	public void POST(){
		assumeTrue(everythingOk);
		
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
		assumeTrue(everythingOk);
		
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
		messageConverters.add(new HtmlFormPersonMessageConverter() );
		messageConverters.add(new JsonPersonMessageConverter() );
		return messageConverters;
	}
}
