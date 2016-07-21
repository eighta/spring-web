package a8.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import a8.conf.BackendJavaConfig;
import a8.data.Person;
import web.conf.MvcJavaConfig;
import web.controllers.TheController;

@RunWith(SpringJUnit4ClassRunner.class)

//The WebAppConfiguration annotation makes sure that the context in which the test methods in this
//class are run is WebApplicationContext.
@WebAppConfiguration //XXX COMO ESPECIFICAR QUIEN ES EL FRONT Y QUIEN ES EL ROOT?

@ContextConfiguration(classes={MvcJavaConfig.class,BackendJavaConfig.class})
public class AllControllerTest {

	@Autowired
	private TheController theController;
	
	@Before
	public void setUp(){
		//we are making sure the controller was initialized correctly
		assertNotNull(theController);
	}
	
	@Test
	public void list() {
		Model model = new BindingAwareModelMap();
		String view = theController.list(model);
		assertEquals("persons/list", view);
		assertNotNull(model.asMap().get("persons"));
		// test to see id the manager returned the expected result
		assertTrue(((List<Person>) model.asMap().get("persons")).size() == 2);
	}
	
}
