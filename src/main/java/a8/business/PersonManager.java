package a8.business;

import java.util.List;

import a8.data.Person;

public interface PersonManager {

	List<Person> findAll();

	Person findById(Long id);
}
