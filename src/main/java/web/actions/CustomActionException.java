package web.actions;

import org.springframework.stereotype.Component;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import a8.exceptions.EightaException;

@Component
public class CustomActionException extends AbstractAction{

	@Override
	protected Event doExecute(RequestContext context) throws Exception {
		try {
			boolean condition = Boolean.TRUE;
			if(condition){throw new EightaException("RE-PAILAS");}
			return success();
		} catch (EightaException e) {
			return error(e); // Optionally pass exception
			//Available as $exception in event-context
		}
	}

}
