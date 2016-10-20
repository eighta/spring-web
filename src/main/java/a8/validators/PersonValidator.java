package a8.validators;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

import a8.data.Person;

/*XXX TODO NO FUNCIONA*/
//Separate validator bean "discovered" by convention
//-Bean name: [model-object-name]Validator
//-Method name validate[view-name]
@Component("personValidator")
public class PersonValidator {

	//XXX OJO, SI FALLA POR BeanValidator, no llega aca
	public boolean check(Person person, MessageContext messageContext){
		System.out.println("4. Validando...... " + messageContext.hasErrorMessages());
		//XXX asi se agrega un mensaje
		//MessageResolver messageResolver = new DefaultMessageResolver(person,null,Severity.ERROR,null,"My Default Message");
		//messageContext.addMessage(messageResolver);
		
		if(false){
			messageContext.addMessage(
					new MessageBuilder().
					error().
					source("firstName").
					code("firstName.pailas").
					defaultText("mENSAJE Por Defecto")
					.build());
			
			return false;
		}
		
		return true;
	}
	
	/*XXX TODO NO FUNCIONA*/
	public void validate(ValidationContext ctx) {
		MessageContext messageContext = ctx.getMessageContext();
    	System.out.println("3. Validando...... " + messageContext.hasErrorMessages());
	}
	
	public void validateEnterMainPersonInfo(ValidationContext ctx) {
		MessageContext messageContext = ctx.getMessageContext();
    	System.out.println("2. Validando...... " + messageContext.hasErrorMessages());
	}
}
