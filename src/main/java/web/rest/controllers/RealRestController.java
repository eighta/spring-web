package web.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import a8.data.Person;

@RestController
@RequestMapping("/rest")
public class RealRestController {

	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public Person getMePerson(){
		return new Person("Milton", "Ochoa","1982-01-01");
	}
}
