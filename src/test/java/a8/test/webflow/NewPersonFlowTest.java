package a8.test.webflow;

import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

public class NewPersonFlowTest extends AbstractXmlFlowExecutionTests {

	private static final String ENTER_PERSON_INFO = "enterPersonInfo";
	
	public void testStart() throws Exception {
		startFlow(new MockExternalContext());
		assertCurrentStateEquals(ENTER_PERSON_INFO);
	}

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createFileResource("src/main/webapp/WEB-INF/flows/persons/newPerson/newPerson-flow.xml");
	}

}
