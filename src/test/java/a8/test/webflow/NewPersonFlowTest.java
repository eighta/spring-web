package a8.test.webflow;

import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

public class NewPersonFlowTest extends AbstractXmlFlowExecutionTests {

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createFileResource("src/main/webapp/WEB-INF/flows/persons/newPerson/newPerson-flow.xml");
	}
	
//	private static final String ENTER_PERSON_INFO = "enterPersonInfo";
//	
	public void testNewPersonCreation() throws Exception {
		MockExternalContext context = new MockExternalContext();
		startFlow(context);
		assertCurrentStateEquals("enterMainPersonInfo");
	}
	
	public void testTransition(){
		setCurrentState("enterLocationPersonInfo");
		MockExternalContext context = new MockExternalContext();
		context.setEventId("next");
		resumeFlow(context);
		assertCurrentStateEquals("enterKeyPersonInfo");
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
