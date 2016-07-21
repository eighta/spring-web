package a8.business;

import java.util.ArrayList;
import java.util.List;

import a8.data.Person;

public class PersonManagerImpl implements PersonManager{

	@Override
	public List<Person> findAll() {
		
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("Javier","Ochoa","1982-09-29") );
		personList.add(new Person("Sophie","Ochoa","2013-03-21") );
		
		return personList;
	}

}
