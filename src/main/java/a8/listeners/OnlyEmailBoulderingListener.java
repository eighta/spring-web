package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import a8.events.SendEmail;
import a8.events.types.Bouldering;

//NOT WORK DOS NIVELES DE GENERIC
public class OnlyEmailBoulderingListener implements ApplicationListener<SendEmail<Bouldering>>{

	private static final Logger logger = LoggerFactory.getLogger(OnlyEmailBoulderingListener.class);
	
	@Override
	public void onApplicationEvent(SendEmail<Bouldering> correoBouldering) {

		logger.info("<<<<<<leyendo correo de [BOULDERING] (iface ApplicationListener): " + correoBouldering.getTo());
		
	}

}
