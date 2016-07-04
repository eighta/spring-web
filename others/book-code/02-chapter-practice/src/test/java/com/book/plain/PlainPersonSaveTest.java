package com.book.plain;


import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import com.book.base.Person;
import com.book.base.PersonManager;
import com.book.base.PersonRepository;
import com.book.spring.components.PersonManagerImpl;

/**
 * Created by iuliana.cosmina on 1/11/15.
 * Description: Test class exemplifying how to save a person with pure Java.
 */
public class PlainPersonSaveTest {

    @Test
    public void savePerson() {
        /* Start of setup*/
        // FIXED_TODO 1. Write necessary code to set up all needed in order to save a person
    	PersonRepository repo = new PlainPersonRepository();
    	DataSource dataSource = new BasicDataSource();
    	repo.setDataSource(dataSource);
    	PersonManager personManager = new PersonManagerImpl(repo);
        /* End of setup */
        
        Person person = new Person("John", "Smith", "1980-04-03");
        // FIXED_TODO 2. Call method to save the person instance and store the return value into the result variable
        int result = 0;
        result = personManager.save(person);
        
        
        assertEquals(1, result);
    }

}
