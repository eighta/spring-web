package a8.awares;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class MyApplicationContextAware implements ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(MyApplicationContextAware.class);
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		logger.info("MyApplicationContextAware.setApplicationContext(...)");
		
	}

}
