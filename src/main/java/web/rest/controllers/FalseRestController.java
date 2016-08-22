package web.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Person getPersonById(@PathVariable Long id)
			/*throws NotFoundException */{
		
		Person person = personManager.findById(id);
		
		if (person == null) {
			throw new NotFoundException(Person.class, id.toString());
		}
		
		return person;
	}

}