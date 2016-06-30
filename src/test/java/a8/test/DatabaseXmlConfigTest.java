package a8.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/a8/conf/database-config.xml")
@ActiveProfiles("sophieHome")
public class DatabaseXmlConfigTest {

	@Autowired 
	private ApplicationContext applicationContext;
	
	@Test
	public void getEmbeddedDataSource(){
		DataSource embeddedDS = applicationContext.getBean("embeddedDS", DataSource.class);
		assertNotNull(embeddedDS);
	}
	
	@Test
	@Ignore //XXX SOLO FUNCIONAL EN "HOME", SE DEBE UTILIZAR MECANISMO DE PROFILES
	public void getPostgresqlDataSource() throws SQLException{
		
		DataSource postgresqlDS = applicationContext.getBean("postgresqlDS", DataSource.class);
		assertNotNull(postgresqlDS);
		
		Connection connection = postgresqlDS.getConnection();
		
	}
	
	@Test
	@Ignore //XXX SOLO FUNCIONAL EN "HOME", SE DEBE UTILIZAR MECANISMO DE PROFILES
	public void getMySqlDataSource(){}
	
}
