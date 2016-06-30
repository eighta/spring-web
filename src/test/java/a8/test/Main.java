package a8.test;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {

		testClassPathXmlApplicationContext();
		retrieveOsName();
		retrieveResourceFromApplicationContext();
	}
	
	private static void retrieveResourceFromApplicationContext() {

		
	}

	public static void retrieveOsName(){
		String osName = System.getProperty("os.name");
		logger.info(osName);
	}
	
	public static void testClassPathXmlApplicationContext(){
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("conf/backend-config.xml");
		
		//Properties properties = context.getBean(Properties.class);
		Properties properties = (Properties)context.getBean("othersProperties");
		
		logger.info(properties.getProperty("apple.slogan"));
		
		//SimpleBean simpleBean = context.getBean(SimpleBean.class);
		//System.out.println(">>>"+ simpleBean);
	}

}
