package a8.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DestroyingBeanStyle2 {

	private static final Logger logger = LoggerFactory.getLogger(DestroyingBeanStyle2.class);
	
	//@Bean’s destroyMethod attribute
	//destroy-method attribute on a <bean/> XML definition
	public void plainCleanMethod(){
		logger.info("PreDestroy...");
	}
}
