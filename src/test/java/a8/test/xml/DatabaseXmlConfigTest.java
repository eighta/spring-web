package a8.test.xml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.test.utils.DatabaseUtils;
import a8.test.utils.TestActiveProfilesResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/database-config.xml")
@ActiveProfiles(resolver = TestActiveProfilesResolver.class)
public class DatabaseXmlConfigTest {

	private static DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
	private boolean sophieHome = Boolean.FALSE;

	@Autowired
	private Environment environment;
	
	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init(){
		//is Sophie Home ?
		List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
		sophieHome = activeProfiles.contains("sophieHome");
	}
	
	private boolean isSophieHome() {
		return sophieHome;
	}

	@Test
	public void getEmbeddedDataSource() throws SQLException {
		DataSource embeddedDS = applicationContext.getBean("embeddedDS", DataSource.class);
		assertNotNull(embeddedDS);
		runSuiteTest(embeddedDS.getConnection());
	}
	@Test
	public void getEmbeddedDataSource_2() throws SQLException {
		DataSource embeddedDS = applicationContext.getBean("embeddedDS_2", DataSource.class);
		assertNotNull(embeddedDS);
		runSuiteTest(embeddedDS.getConnection());
	}
	
	@Test
	public void getPostgresqlDataSource() throws SQLException {
		
		Assume.assumeTrue(isSophieHome());
		
		DataSource postgresqlDS = applicationContext.getBean("postgresqlDS", DataSource.class);
		assertNotNull(postgresqlDS);
		runSuiteTest(postgresqlDS.getConnection());
	}
	@Test
	public void getMySqlDataSource() throws SQLException {
		
		Assume.assumeTrue(isSophieHome());
		
		DataSource mysqlDS = applicationContext.getBean("mysqlDS", DataSource.class);
		assertNotNull(mysqlDS);
	}
	@Test
	public void getMySqlDataSourceFromSpringDriverManager() throws SQLException {
		
		Assume.assumeTrue(isSophieHome());
		
		DataSource mysqlDS = applicationContext.getBean("mysqlDS_springDriverManager", DataSource.class);
		assertNotNull(mysqlDS);
	}
	//common
	private void runSuiteTest(Connection connection) {
		assertTrue(databaseUtils.retrieveData(connection) > 0);
	}
}
