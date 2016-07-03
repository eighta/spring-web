package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class AllApplicationEventListener implements
	ApplicationListener<ApplicationEvent>{

	private static final Logger logger = LoggerFactory.getLogger(AllApplicationEventListener.class);
	
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		logger.info("event: " + applicationEvent.getClass());
		
		
	}

}
