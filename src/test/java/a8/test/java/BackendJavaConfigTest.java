package a8.test.java;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.beans.SimpleBean;
import a8.conf.BackendJavaConfig;

@ActiveProfiles("dev_1")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BackendJavaConfig.class)
public class BackendJavaConfigTest {
	
	//private static final Logger logger = LoggerFactory.getLogger(BackendJavaConfigTest.class);

	@Autowired 
	private ApplicationContext applicationContext;
	
	@Autowired
	private Environment environment;
	
	@Test
	public void unknownSimpleBeanName(){
		SimpleBean simpleBean = applicationContext.getBean("getWhatIsMyBeanName",SimpleBean.class);
		assertNotNull(simpleBean);
	}
	
	@Test
	public void getDaughterNameFromProperties(){
		SimpleBean simpleBean = applicationContext.getBean("daughterName",SimpleBean.class);
		String simpleBeanName = simpleBean.getSimpleBeanName();
		assertEquals("Sophie",simpleBeanName);
	}
	
	@Test
	public void retrieveResourceFromApplicationContext(){
		Resource resource = applicationContext.getResource("logback.xml");
		assertNotNull(resource);
		
		/*
		Depending on the context class used, the resource loaded can have one of the following types:
			If ctx is a ClassPathXmlApplicationContext instance, the resource type is
			ClassPathResource
			If ctx is a FileSystemXmlApplicationContext instance, the resource type is
			FileSystemResource
			If ctx is a WebApplicationContext instance, the resource type is
			ServletContextResource
		*/
	}
	
	@Test
	public void scopeBeans(){
		
		String[] activeProfiles = environment.getActiveProfiles();
		System.out.println("activeProfiles: " + activeProfiles[0]);
		
		SimpleBean simpleBeanProfileOne = applicationContext.getBean("simpleBeanProfileOne",SimpleBean.class);
		assertNotNull(simpleBeanProfileOne);
		
		//esperaba que el objeto fuera null, pero en cambio sale:
		//org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'simpleBeanProfileTwo' is defined
		//SimpleBean simpleBeanProfileTwp = applicationContext.getBean("simpleBeanProfileTwo",SimpleBean.class);
		//assertNull(simpleBeanProfileTwp);
		
	}
	
	//@Test
	public void printActiveProfiles(){
		String[] activeProfiles = environment.getActiveProfiles();
		System.out.println("activeProfiles: " + activeProfiles[0]);
	}
	
}
