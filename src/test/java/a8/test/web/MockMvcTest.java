package a8.test.web;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import a8.conf.BackendJavaConfig;
import web.conf.MvcJavaConfig;

@RunWith(SpringJUnit4ClassRunner.class)

//The WebAppConfiguration annotation makes sure that the context in which the test methods in this
//class are run is WebApplicationContext.
@WebAppConfiguration //XXX COMO ESPECIFICAR QUIEN ES EL FRONT Y QUIEN ES EL ROOT?

@ContextConfiguration(classes={MvcJavaConfig.class,BackendJavaConfig.class})
public class MockMvcTest {

	@Autowired
	private WebApplicationContext wac;
	
//	using MockMvc, which is the main entry point for
//	server-side Spring MVC test support.
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		assertNotNull(wac);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	// test all aspects handling a request to "/tasks/ctrls/"
	public void tastsControllers() throws Exception {
		mockMvc.perform( get("/tasks/ctrls/") )
		
		// test if response status is 200
		.andExpect(status().isOk())
		
		// test if the attribute "hospital" was added to the model
		.andExpect(model().attributeExists("modelId"));
		
		//XXX probar el FORWARD
		//when using Tiles, we can test the forward of the request to
		//the template for the page
		//.andExpect(forwardedUrl("/WEB-INF/templates/layout.jsp"));
	}
	
	@Test
	//test a method returning "redirect:/persons/list"
	// (all persons born at the hospital with code =134181)
	public void testRedirect() throws Exception {
		
		mockMvc.perform(get("/tasks/ctrls/r"))
		
		// test if response status is 302
		.andExpect(status().isFound())
		
		//test if the "persons" attribute was added to the redirectAttributes model
		.andExpect(flash().attributeExists("anotherPerson"))
		
		// test if the redirect request is sent to the expected URL
		.andExpect(redirectedUrl("s"));
	}
	
}
