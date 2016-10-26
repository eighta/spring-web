package web.actions;

import org.springframework.binding.message.MessageBuilder;
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
			
			throw e;
			
			/* WORKS OK
			context
			.getMessageContext()
			.addMessage(
					new MessageBuilder()
							.error()
							.source("creditCardNumber")
							.defaultText("The credit card number is not associated with any account")
							.build());
			
			
			return error(e); // Optionally pass exception
			*/
			
			//Available as $exception in event-context
		}
	}

}
