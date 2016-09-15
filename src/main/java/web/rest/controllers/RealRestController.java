package web.rest.controllers;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import a8.data.Person;

@RestController
@RequestMapping("/rest")
public class RealRestController {

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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Person> postPerson(Person person){
		
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
}
