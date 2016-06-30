package a8.test;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import a8.beans.SimpleBean;

public class Main {

	public static void main(String[] args) {

		testClassPathXmlApplicationContext();
	}
	
	public static void testClassPathXmlApplicationContext(){
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("a8/conf/backend-config.xml");
		
		//Properties properties = context.getBean(Properties.class);
		Properties properties = (Properties)context.getBean("dbProp");
		System.out.println(">>>"+properties.getProperty("mysql_dbProp.url"));
		
		//SimpleBean simpleBean = context.getBean(SimpleBean.class);
		//System.out.println(">>>"+ simpleBean);
	}

}
