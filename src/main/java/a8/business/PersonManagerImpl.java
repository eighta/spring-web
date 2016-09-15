package a8.business;

import java.util.ArrayList;
import java.util.List;

import a8.data.Person;

public class PersonManagerImpl implements PersonManager{

	List<Person> personList = new ArrayList<>();
	{
		personList.add(new Person(1,"Javier","Ochoa","1982-09-29") );
		personList.add(new Person(2,"Sophie","Ochoa","2013-03-21") );
	}
	
	@Override
	public List<Person> findAll() {
		return personList;
	}

	@Override
	public Person findById(Long id) {
		return personList.get(0);
	}

}
