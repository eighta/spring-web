package web.rest.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import a8.business.PersonManager;
import a8.data.Person;
import a8.exceptions.NotFoundException;

@Controller
@RequestMapping("/rest-false")
public class FalseRestController {

	@Autowired
	private PersonManager personManager;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public @ResponseBody Person getMePerson(){
		return new Person("Javier", "Larios","1982-01-01");
	}
	
	//XXX OJO, si NO se coloca un @ResponseBody y el metodo retorna un objeto,
	//Spring intentara buscar una vista con el nombre: 'rest-false/me', que viene a ser:
	//la URL indicada por el usuario
	
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public String getPersonByIdReturningViewString(@PathVariable Long id){
//		
//		Person person = personManager.findById(id);
//		
//		if (person == null) {
//			throw new NotFoundException(Person.class, id.toString());
//		}
//		
//		return person.toString();
//	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Person getPersonById(
//			@RequestHeader Map allrequestHeaders,
//			@RequestHeader String accept,
			@PathVariable Long id
//			@RequestHeader(value="User-Agent") String userAgent
			)
			/*throws NotFoundException */{

//System.out.println("accept: " + accept);
		
		Person person = personManager.findById(id);
		
		if (person == null) {
			throw new NotFoundException(Person.class, id.toString());
		}
		
		return person;
	}

}