package a8.test.webflow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.binding.collection.SharedMap;
import org.springframework.binding.collection.SharedMapDecorator;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.LocalSharedAttributeMap;
import org.springframework.webflow.core.collection.SharedAttributeMap;
import org.springframework.webflow.execution.ActionExecutor;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import a8.data.Person;
import a8.services.InterviewFactory;
import a8.validators.PersonValidator;
import web.listeners.WebFlowListener;

public class NewPersonFlowTest extends AbstractXmlFlowExecutionTests {

	/**
	 * Initialize for each test - setup a dining form to use.
	 */
	@Override
	protected void setUp(){ }
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createFileResource("src/main/webapp/WEB-INF/flows/persons/newPerson/newPerson-flow.xml");
	}
	
	@Override
	protected void configureFlowBuilderContext(
			MockFlowBuilderContext builderContext) {
		
//		builderContext.registerBean("person4Grammy", new Person());
		builderContext.registerBean("interviewFactory", new InterviewFactory());
		builderContext.registerBean("theFlowListener", new WebFlowListener());
		
		
		builderContext.registerBean("personValidator", new PersonValidator());
		builderContext.registerBean("newPerson", new Person() );
		
		
		
	}

	//Al ejecutar esta clase con JUnit, el motor indetificará 
	//aquellos metodos que empiecen por la palabra "test"
	//para luego ejecutarlos
	
	public void testStart(){
		
		startFlow(new MockExternalContext());
		assertCurrentStateEquals("enterMainPersonInfo");
	}
	
	public void testEnterLocationInfoProceed(){
		
		setCurrentState("enterMainPersonInfo");
		MockExternalContext context = new MockExternalContext();
		context.setEventId("next");
		
		resumeFlow(context);
		assertCurrentStateEquals("enterLocationPersonInfo");
		
	}
	
	public void testEndState(){
		
		startFlow(new MockExternalContext());
		
		MockExternalContext context = new MockExternalContext();
		context.setEventId("cancel");
		
		resumeFlow(context);
		assertFlowExecutionEnded();
		assertFlowExecutionOutcomeEquals("cancelled");
		//assertTrue(context.getExternalRedirectRequested());
	}
	
	
//	private static final String ENTER_PERSON_INFO = "enterPersonInfo";
//	
	public void _testNewPersonCreation() throws Exception {
		MockExternalContext context = new MockExternalContext();
		startFlow(context);
		assertCurrentStateEquals("enterMainPersonInfo");
		assertNotNull(getRequiredFlowAttribute("grammy"));
	}
	
	public void _testTransition(){
		setCurrentState("enterLocationPersonInfo");
		MockExternalContext context = new MockExternalContext();
		context.setEventId("next");
		resumeFlow(context);
		assertCurrentStateEquals("enterKeyPersonInfo");
	}
	
	public void _testEndState(){
		setCurrentState("reviewPersonInfo");
		//getFlowScope().put("diningForm", diningForm);
		MockExternalContext externalContext = new MockExternalContext();
		externalContext.setEventId("confirm");
		resumeFlow(externalContext);
		assertFlowExecutionEnded();
		assertFlowExecutionOutcomeEquals("end");
		assertTrue(externalContext.getExternalRedirectRequested());
		assertEquals("contextRelative:/s/tasks/webflow/persons/1",
				externalContext.getExternalRedirectUrl());
	}
}
