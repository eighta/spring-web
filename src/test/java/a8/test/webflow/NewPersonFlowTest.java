package a8.test.webflow;

import org.springframework.context.annotation.Bean;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import a8.data.Person;
import a8.services.InterviewFactory;
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
		
		builderContext.registerBean("person4Grammy", new Person());
		builderContext.registerBean("interviewFactory", new InterviewFactory());
		builderContext.registerBean("theFlowListener", new WebFlowListener());
		
	}
	
//	private static final String ENTER_PERSON_INFO = "enterPersonInfo";
//	
	public void testNewPersonCreation() throws Exception {
		MockExternalContext context = new MockExternalContext();
		startFlow(context);
		assertCurrentStateEquals("enterMainPersonInfo");
		assertNotNull(getRequiredFlowAttribute("grammy"));
	}
	
	public void testTransition(){
		setCurrentState("enterLocationPersonInfo");
		MockExternalContext context = new MockExternalContext();
		context.setEventId("next");
		resumeFlow(context);
		assertCurrentStateEquals("enterKeyPersonInfo");
	}
	
	//LOS METODOS QUE SE EJECUTARAN
	//los nombres deben empezar por "test"
	public void testEndState(){
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
	
//	java.lang.IllegalStateException: No active FlowSession to access; this FlowExecution has ended
//	public void testFinishCreation(){
//		setCurrentState("reviewPersonInfo");
//		
//		MockExternalContext context = new MockExternalContext();
//		context.setEventId("confirm");
//		resumeFlow(context);
//		
//		assertCurrentStateEquals("end");
//	}

}
