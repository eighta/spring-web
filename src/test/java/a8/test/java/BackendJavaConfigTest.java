package a8.test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.beans.SimpleBean;
import a8.conf.BackendJavaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BackendJavaConfig.class)
public class BackendJavaConfigTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BackendJavaConfigTest.class);

	@Autowired 
	private ApplicationContext applicationContext;
	
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
			• If ctx is a ClassPathXmlApplicationContext instance, the resource type is
			ClassPathResource
			• If ctx is a FileSystemXmlApplicationContext instance, the resource type is
			FileSystemResource
			• If ctx is a WebApplicationContext instance, the resource type is
			ServletContextResource
		*/
	}
	
}
