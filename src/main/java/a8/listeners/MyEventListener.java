package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import a8.events.SendEmail;
import a8.events.types.SendMailTypes;

//XXX IMPLEMENTAR QUE LOS TIPOS DE MAILS SEAN GENERICS DE: SendMailTypes
public class MyEventListener 
	implements ApplicationListener<SendEmail<? extends SendMailTypes>>
	//implements ApplicationListener<SendEmail<? extends SendMailTypes>>	PELIGROSO
	//ApplicationListener<OpenWindow>
	//ApplicationListener<CloseTheDoor>
	
	//implements ApplicationListener<SendEmail<Bouldering>>,
	//ApplicationListener<SendEmail<Sport>>,
	//ApplicationListener<SendEmail<Traditional>>

{
	private static final Logger logger = LoggerFactory.getLogger(MyEventListener.class);

	@Override
	public void onApplicationEvent(SendEmail<? extends SendMailTypes> correoNoseParaQuien) {
		logger.info("<<< onApplicationEvent(...) [correoNoseParaQuien]: " + correoNoseParaQuien);
		
	}

}
