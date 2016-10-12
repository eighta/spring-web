package web.actions;

import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Component
public class CustomAction1 implements Action{

	@Override
	public Event execute(RequestContext context) throws Exception {
		System.out.println("Method: Executing CustomAction1.execute(...)");
		return new Event(new Object(), "_plainAction");
	}

}
