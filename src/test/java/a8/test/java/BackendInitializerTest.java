package a8.test.java;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.beans.SingletonBean;
import a8.conf.BackendInitializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = BackendInitializer.class)
public class BackendInitializerTest {
	
	@Autowired 
	private ApplicationContext applicationContext;
	
	@Test
	public void getSingletonBean(){
		SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);
		assertNotNull(singletonBean);
	}
}
