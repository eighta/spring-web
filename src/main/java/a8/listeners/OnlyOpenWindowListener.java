package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import a8.events.OpenWindow;

public class OnlyOpenWindowListener implements ApplicationListener<OpenWindow>{

	private static final Logger logger = LoggerFactory.getLogger(OnlyOpenWindowListener.class);
	
	@Override
	public void onApplicationEvent(OpenWindow openWindow) {
		logger.info("abriendo la ventana... (iface ApplicationListener): " + openWindow);
		
	}
	
}
