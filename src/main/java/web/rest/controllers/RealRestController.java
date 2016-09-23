package web.rest.controllers;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.Proxy.Type;
import java.util.Map;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import a8.data.Person;
import a8.data.User;
import web.rest.exceptions.RestException;
import web.rest.exceptions.RestNoBodyException;

@RestController
@RequestMapping("/rest")
public class RealRestController {

	@RequestMapping(path="/async")
	public void doAsyncRequest() throws InterruptedException{
System.out.println("METHOD: doAsyncRequest(...) ");
		findUser("eighta");
System.out.println("METHOD: doAsyncRequest(...) END");
	}
	
	@Async
    public Future<User> findUser(String user) throws InterruptedException {
System.out.println("METHOD: findUser(..)");
        String url = String.format("https://api.github.com/users/%s", user);
        
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress("10.1.0.194", 3128));
	    requestFactory.setProxy(proxy);
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
System.out.println("METHOD: findUser(..) END");        
        return new AsyncResult<>(results);
    }
	
	@RequestMapping(path="/headers")
	public ResponseEntity<Void> getCustomHeader(
			HttpEntity<Void> requestEntity){
		
		//REQUEST
		HttpHeaders requestHeaders = requestEntity.getHeaders();
		String customHeaderValue = requestHeaders.getFirst("CUSTOM-HEADER-IN");
		
		//RESPONSE
		return ResponseEntity.ok().header("CUSTOM-HEADER-OUT", customHeaderValue + "Ochoa").build();
	}
	
	@RequestMapping(path="/json"
			, produces=MediaType.APPLICATION_JSON_VALUE
			)
	public Person getJsonPerson(){
System.out.println("getJsonPerson()");		
		return new Person(54,"Monsieur",MediaType.APPLICATION_JSON_VALUE,"2016-01-01");
	}
	
	@RequestMapping(path="/json"
			, produces=MediaType.TEXT_HTML_VALUE
			)
	public Person getPersonTextHtml(){ //<<< XXX DELETE METHOD
System.out.println("getPersonTextHtml()");
		return new Person(54,"Monsieur",MediaType.TEXT_HTML_VALUE,"2016-01-01");
	}
	
	@RequestMapping(method = RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE
			//,produces=MediaType.APPLICATION_JSON_VALUE //SINO SE ESPECIFICA POR DEFECTO ES: [text/html]
			)
	public Person postPersonJson(
			@RequestBody
			Person person,
			HttpServletResponse response){
		
		Integer settedId = 88;
		String newLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + settedId).build().toUriString();
		response.setHeader(HttpHeaders.LOCATION, newLocation);
		
		person.setSecondName("SECONDNAME-JSON");
		return person;
	}
	
	@RequestMapping(path="/body")
	public void getWithNoBody(){
		throw new RestNoBodyException();
	}
	
	@RequestMapping(path="/exception")
	public void getWithException(){
		throw new RestException();
	}
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@RequestMapping(path="/status/400")
	public void statusCode400(){}
	
	@ResponseStatus(code=HttpStatus.MULTIPLE_CHOICES)
	@RequestMapping(path="/status/300")
	public void statusCode300(){}
	
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	@RequestMapping(path="/status/204")
	public void statusCode204(){}
	
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	@RequestMapping(path="/status/202")
	public void statusCode202(){}
	
	@ResponseStatus(code=HttpStatus.CREATED)
	@RequestMapping(path="/status/201")
	public void statusCode201(){}
	
	//By Default return 200 (status code)
	@RequestMapping(path="/status/200")
	public void statusCode200(){}
	
	@RequestMapping(method = RequestMethod.HEAD)
	public ResponseEntity<?> headPerson(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_MARKDOWN);
		ResponseEntity<?> response = new ResponseEntity<>(null,headers,HttpStatus.PARTIAL_CONTENT);
		return response;
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	public void deletePerson(
			@PathVariable
			Integer id){
		System.out.println("HTTP DELETE: " + id);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void putPerson(
			@RequestBody
			Person person){
		System.out.println("HTTP PUT: " + person.getId());
	}
	
//	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
//	public void postPersonResponseUsingServlet(
//			@RequestBody
//			Person person,
//			HttpServletResponse response){
//		
//				
//		Integer settedId = 55;
//		String newLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + settedId).build().toUriString();
//		
////		person.setId(settedId);
////
//		
////		
////		HttpHeaders headers = new HttpHeaders();
////	    headers.setLocation(newLocation);
////		
////		ResponseEntity<Person> response = new ResponseEntity<Person>(person,headers,HttpStatus.CREATED);
////		return response;
//		
//		response.setHeader(HttpHeaders.LOCATION, newLocation);
//	}
//	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Person> postPerson(
			@RequestBody
			Person person,
			@RequestHeader
			Map<String,Object> allHeader){
		
		Integer settedId = 55;
		person.setId(settedId);

		URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + settedId).build().toUri();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(newLocation);
		
		ResponseEntity<Person> response = new ResponseEntity<Person>(person,headers,HttpStatus.CREATED);
		return response;
	}
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public Person getMePerson(){
		return new Person(1,"Milton", "Ochoa","1982-01-01");
	}
	
	@RequestMapping(value = "/family", method = RequestMethod.GET)
	public Person [] getFamily(){
		
		return new Person[] { 
				new Person(1,"Milton", "Ochoa","1982-01-01"),
				new Person(2,"Sophie", "Ochoa","2013-03-21")
				
		};
	}
	
}
