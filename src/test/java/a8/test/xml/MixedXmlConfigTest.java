package a8.test.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.beans.AnnotatedBean;
import a8.beans.InitializationBeanStyle1;
import a8.beans.InitializationBeanStyle2;
import a8.beans.InitializationBeanStyle3;
import a8.beans.RequiredBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/mixed-config.xml")
public class MixedXmlConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(MixedXmlConfigTest.class);
	
	@Autowired 
	private ApplicationContext applicationContext;
	
	@Test
	public void InitializationBeanStyle3(){
		InitializationBeanStyle3 initializationBeanStyle3 = applicationContext.getBean(InitializationBeanStyle3.class);
		assertNotNull(initializationBeanStyle3);
		assertEquals("InitializationBeanStyle3",initializationBeanStyle3.getInnerBeanName());
	}
	
	@Test
	public void InitializationBeanStyle2(){
		InitializationBeanStyle2 initializationBeanStyle2 = applicationContext.getBean(InitializationBeanStyle2.class);
		assertNotNull(initializationBeanStyle2);
		assertEquals("InitializationBeanStyle2",initializationBeanStyle2.getInnerBeanName());
	}
	
	@Test
	public void InitializationBeanStyle1(){
		InitializationBeanStyle1 initializationBeanStyle1 = applicationContext.getBean(InitializationBeanStyle1.class);
		assertNotNull(initializationBeanStyle1);
		assertEquals("InitializationBeanStyle1",initializationBeanStyle1.getInnerBeanName());
	}
	
	@Test
	public void requiredBean(){
		RequiredBean requiredBean = applicationContext.getBean(RequiredBean.class);
		assertNotNull(requiredBean);
	}
	
	@Test
	public void printBeanNames(){
		logger.info("===BEANS NAMES===");
		for(String beanName:applicationContext.getBeanDefinitionNames()){
			logger.info(beanName);
		}
		logger.info("===END BEANS NAMES===");
	}
	
	@Test
	public void getAnnotatedBean(){
		AnnotatedBean annotatedBean = applicationContext.getBean(AnnotatedBean.class);
		assertNotNull(annotatedBean);
		assertNotNull(annotatedBean.getSimpleBean());
	}
}
