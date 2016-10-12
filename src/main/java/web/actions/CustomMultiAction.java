package web.actions;

import org.springframework.stereotype.Component;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Component
public class CustomMultiAction extends MultiAction {
	
	public Event methodOne(RequestContext context){
		System.out.println("Method: Executing CustomMultiAction.methodOne(...)");
		
		 //context.getFlowScope().get("booking");
		return success();
	}

}
