package web.flows;

import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;

import a8.exceptions.EightaException;

public class CustomFlowExecutionExceptionHandler
	
//implements FlowExecutionExceptionHandler{
//	@Override
//	public boolean canHandle(FlowExecutionException exception) {
//		return Boolean.TRUE;
//	}
//	@Override
//	public void handle(FlowExecutionException exception, RequestControlContext context) {
//		System.out.println("===Handling Exception===");
//		//exception.printStackTrace();
//		
//		Throwable cause = exception.getCause();
//		cause.printStackTrace();
//	}

extends TransitionExecutingFlowExecutionExceptionHandler{
	
	public CustomFlowExecutionExceptionHandler(){
		super.add(EightaException.class, "estallada");
	}

}
