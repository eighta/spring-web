package a8.core;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import a8.data.Person;

public class JustBusiness {

	@RolesAllowed("USER")
	public Person getPersonWithinRolesAllowed(){
		return new Person(1,"@","RolesAllowed","2000-01-01");
	}
	
	
	@Secured("hasRole('USER')")
	public Person getPersonSecured(){
		return new Person(1,"@","Secure","2000-01-01");
	}
	
	@PreAuthorize("hasRole('USER')")
	public Person getPersonPreAuthorize(){
		return new Person(1,"Pre","Authorize","2000-01-01");
	}
	
	public Person getMyDaughter(){
		return new Person(1,"Sophie","Ochoa","2016-01-01");
	}

	public Person getMySelf() {
		return new Person(2,"Javier","Larios","2000-01-01");
	}
}
