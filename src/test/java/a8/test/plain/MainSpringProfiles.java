package a8.test.plain;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import a8.beans.SimpleBean;
import a8.conf.BackendJavaConfig;

public class MainSpringProfiles {

	public static void main(String[] args) {
		  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		  
		  //configuracion de profiles:
		  //context.getEnvironment().setActiveProfiles("dev_1");
		  //or
		  System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev_1");
		  context.register(BackendJavaConfig.class);
		  context.refresh();
		  
		  testSpringScopes(context);
			
		  ((ConfigurableApplicationContext) context).close();
	}

	private static void testSpringScopes(AnnotationConfigApplicationContext applicationContext) {
		SimpleBean simpleBean = applicationContext.getBean("simpleBeanProfileOne",SimpleBean.class);
		System.out.println("simpleBean: " + simpleBean.getSimpleBeanName());
	}
	
	
}
