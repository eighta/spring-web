package a8.business;

import java.util.ArrayList;
import java.util.List;

import a8.data.Person;

public class PersonManagerImpl implements PersonManager{

	@Override
	public List<Person> findAll() {
		
		List<Person> personList = new ArrayList<>();
		personList.add(new Person() );
		personList.add(new Person() );
		
		return personList;
	}

}
