package a8.test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.beans.ComplexBean;
import a8.beans.SimpleBean;
import a8.beans.SimplePropertiesBean;
import a8.beans.SingletonBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/conf/backend-config.xml")
public class BackendXmlConfigTest {

	@Autowired 
	private ApplicationContext applicationContext;
	
	@Test
	public void getAppleSlogan(){
		SimpleBean simpleBean = applicationContext.getBean("specificSimpleBean",SimpleBean.class);
		String simpleBeanName = simpleBean.getSimpleBeanName();
		assertEquals("Think Different",simpleBeanName);
	}
	
	@Test
	public void getDaughterNameFromProperties(){
		SimpleBean simpleBean = applicationContext.getBean("daughterName",SimpleBean.class);
		String simpleBeanName = simpleBean.getSimpleBeanName();
		assertEquals("Sophie",simpleBeanName);
	}
	
	@Test
	public void getSimplePropertiesBeanUsing_C_and_P_namespace(){
		SimplePropertiesBean simplePropertiesBean = applicationContext.getBean("simplePropertiesBean_new",SimplePropertiesBean.class);
		assertNotNull(simplePropertiesBean);
		assertEquals("TAG c",simplePropertiesBean.getConstructorProperty());
		assertNotNull(simplePropertiesBean.getReferenceProperty());
	}
	
	@Test
	public void getSimplePropertiesBean(){
		SimplePropertiesBean simplePropertiesBean = applicationContext.getBean("simplePropertiesBean_old",SimplePropertiesBean.class);
		assertNotNull(simplePropertiesBean);
		assertEquals("TAG constructor-arg",simplePropertiesBean.getConstructorProperty());
		assertNotNull(simplePropertiesBean.getReferenceProperty());
	}
	
	@Test
	public void getSingletonBean(){
		SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);
		assertNotNull(singletonBean);
	}
	
	@Test
	public void getSimpleBeanFromFactoryBean(){
		SimpleBean simpleBean = applicationContext.getBean("factoryBean",SimpleBean.class);
		assertNotNull(simpleBean);
	}
	
	@Test
	public void getComplexBeanUsingFactoryBean(){
		ComplexBean complexBean = applicationContext.getBean(ComplexBean.class);
		assertNotNull(complexBean);
	}
	
}
