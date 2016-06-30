package a8.test;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/a8/conf/database-config.xml")
public class DatabaseXmlConfigTest {

	@Autowired 
	private ApplicationContext applicationContext;
	
	@Test
	public void getEmbeddedDataSource(){
		DataSource embeddedDS = applicationContext.getBean("embeddedDS", DataSource.class);
		assertNotNull(embeddedDS);
	}
	
}
