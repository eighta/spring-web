package web.actions;

import org.springframework.stereotype.Component;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Component
public class CustomAction2 extends AbstractAction{

	@Override
	protected Event doExecute(RequestContext context) throws Exception {
		System.out.println("Method: Executing CustomAction2.doExecute(...)");
		return new Event(new Object(), "_plainAction");
		//return success();
	}
}
