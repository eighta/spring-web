package a8.test.plain;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.qos.logback.classic.Level;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		retrieveResourceFromClasspath();
		testClassPathXmlApplicationContext();
		retrieveOsName();
		testLogLevel();
	}
	
	private static void retrieveResourceFromClasspath() {
		
		InputStream resourceAsStream = Class.class.getResourceAsStream("/database/DDL.sql");
		// delegate it to ClassLoader.getSystemResourceAsStream(java.lang.String) method
		
		logger.info("resourceAsStream: " + resourceAsStream);
	}

	private static void testLogLevel() {
		
System.out.println("PRINTING ALL LEVELS");
		logger.trace("1-TRACE MSG");
		logger.debug("2-DEBUG MSG");
		logger.info("3-INFO MSG");
		logger.warn("4-WARNING MSG");
		logger.error("5-ERROR MSG");
System.out.println("END LEVELS");

		//TRACE < DEBUG < INFO <  WARN < ERROR
		ch.qos.logback.classic.Logger realLogger = (ch.qos.logback.classic.Logger) logger;
		
		realLogger.setLevel(Level.WARN);
		realLogger.warn("WARNING MESSAGE");
		realLogger.info	("no imprime INFO por tener effective level WARN (por ser INFO < WARN)");
		realLogger.error("SI imprime ERROR por tener effective level WARN (por ser WARN < ERROR)");
		
		//Appenders
		
		//adding a appender
		//realLogger.addAppender(...);
		
		//loggin conditional
		if(logger.isDebugEnabled()) {
			System.out.println("DEBUG ENABLED");
			logger.debug("Message 4 debug LEVEL");
		}else{
			System.out.println("DEBUG DISABLED");
		}
		
	}

	public static void retrieveOsName(){
		String osName = System.getProperty("os.name");
		logger.info(osName);
	}
	
	public static void testClassPathXmlApplicationContext(){
		
		ConfigurableApplicationContext  context =
				new ClassPathXmlApplicationContext("conf/backend-config.xml");
		
		//Properties properties = context.getBean(Properties.class);
		Properties properties = (Properties)context.getBean("othersProperties");
		
		logger.info(properties.getProperty("apple.slogan"));
		
		//SimpleBean simpleBean = context.getBean(SimpleBean.class);
		//System.out.println(">>>"+ simpleBean);
		
		context.close();
	}
}
