package web.flows;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.mvc.servlet.FlowHandler;

public class MyCustomFlowHandler implements FlowHandler {
//extends AbstractFlowHandler{ BETTER
	
	@Override
	public String getFlowId() {
		return "advanced/eightaCustomFlow";
	}

	@Override
	public MutableAttributeMap<Object> createExecutionInputMap(HttpServletRequest request) {
System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<createExecutionInputMap");
		return null;
	}

	@Override
	public String handleExecutionOutcome(FlowExecutionOutcome outcome, HttpServletRequest request,
			HttpServletResponse response) {
System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<handleExecutionOutcome");
		return null;
	}

	@Override
	public String handleException(FlowException e, HttpServletRequest request, HttpServletResponse response) {
System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<handleException");
		return null;
	}
}
