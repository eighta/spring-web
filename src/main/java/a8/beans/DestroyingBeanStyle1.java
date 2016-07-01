package a8.beans;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DestroyingBeanStyle1 {

	private static final Logger logger = LoggerFactory.getLogger(DestroyingBeanStyle1.class);
	
	@PreDestroy
	public void cleanup(){
		logger.info("PreDestroy...");
	}
}
