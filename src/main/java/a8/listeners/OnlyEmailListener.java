package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import a8.events.SendEmail;

public class OnlyEmailListener implements ApplicationListener<SendEmail<?>>{

	private static final Logger logger = LoggerFactory.getLogger(OnlyEmailListener.class);

	@Override
	public void onApplicationEvent(SendEmail<?> correo) {
		logger.info("<<<<<<leyendo correo (iface ApplicationListener): " + correo);
		
	}

}
