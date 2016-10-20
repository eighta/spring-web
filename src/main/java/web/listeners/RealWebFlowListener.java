package web.listeners;

import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.execution.EnterStateVetoException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.execution.FlowExecutionListener;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.View;

public class RealWebFlowListener implements FlowExecutionListener{

	@Override
	public void requestSubmitted(RequestContext context) {
System.out.println("requestSubmitted");
	}

	@Override
	public void requestProcessed(RequestContext context) {
System.out.println("requestProcessed");
	}

	@Override
	public void sessionCreating(RequestContext context, FlowDefinition definition) {
System.out.println("sessionCreating");
	}

	@Override
	public void sessionStarting(RequestContext context, FlowSession session, MutableAttributeMap<?> input) {
System.out.println("sessionStarting");
	}

	@Override
	public void sessionStarted(RequestContext context, FlowSession session) {
System.out.println("sessionStarted");
	}

	@Override
	public void eventSignaled(RequestContext context, Event event) {
System.out.println("eventSignaled");
	}

	@Override
	public void transitionExecuting(RequestContext context, TransitionDefinition transition) {
System.out.println("transitionExecuting");
	}

	@Override
	public void stateEntering(RequestContext context, StateDefinition state) throws EnterStateVetoException {
System.out.println("stateEntering");
	}

	@Override
	public void stateEntered(RequestContext context, StateDefinition previousState, StateDefinition state) {
System.out.println("stateEntered");
	}

	@Override
	public void viewRendering(RequestContext context, View view, StateDefinition viewState) {
System.out.println("viewRendering");
	}

	@Override
	public void viewRendered(RequestContext context, View view, StateDefinition viewState) {
System.out.println("viewRendered");
	}

	@Override
	public void paused(RequestContext context) {
System.out.println("paused");
	}

	@Override
	public void resuming(RequestContext context) {
System.out.println("resuming");
	}

	@Override
	public void sessionEnding(RequestContext context, FlowSession session, String outcome,
			MutableAttributeMap<?> output) {
System.out.println("sessionEnding");
	}

	@Override
	public void sessionEnded(RequestContext context, FlowSession session, String outcome, AttributeMap<?> output) {
System.out.println("sessionEnded");
	}

	@Override
	public void exceptionThrown(RequestContext context, FlowExecutionException exception) {
System.out.println("exceptionThrown");		
	}

}
