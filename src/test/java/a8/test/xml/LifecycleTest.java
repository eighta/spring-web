package a8.test.xml;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifecycleTest {

	private static final Logger logger = LoggerFactory.getLogger(LifecycleTest.class);
	
	@Test
	public void testAllApplicationContextLifeCycleEvents(){
	
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext(new String[] {"classpath:conf/lifecycle-config.xml"});
		
		logger.info("context.start(...)");
		context.start();
		
		logger.info("context.close(...)");
		context.close();
		
	}
	
}
