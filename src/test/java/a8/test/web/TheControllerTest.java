package a8.test.web;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import a8.business.PersonManager;
import web.controllers.TheController;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TheControllerTest {

	@Mock
	private PersonManager managerMock;
	
	private TheController theController;
	
	@Before
	public void setUp(){
		theController = new TheController(managerMock);
	}
	
	@Test
	public void list() {
		// setting up a mock manager that returns an "empty" list when
		// findAll() is called
		when(managerMock.findAll()).thenReturn(new ArrayList<>());
		
		//a model object is "manually" constructed to pass as argument
		// to the controller method
		Model model = new BindingAwareModelMap();
		String view = theController.list(model);
		assertEquals("persons/list", view);
		//this tests if the handler method has added
		//the "persons" attribute to the provided model.
		assertNotNull(model.asMap().get("persons"));
	}
	
}
