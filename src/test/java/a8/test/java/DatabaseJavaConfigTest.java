package a8.test.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.conf.DatabaseJavaConfig;
import a8.test.utils.DatabaseUtils;
import a8.test.utils.TestActiveProfilesResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseJavaConfig.class)
@ActiveProfiles(resolver = TestActiveProfilesResolver.class)
public class DatabaseJavaConfigTest {

	private static DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
	
	@Autowired()
	@Qualifier("postgresqlDS")
	DataSource postgresqlDS;
	
	@Test
	public void getPostgresqlDataSource() throws SQLException {
		Assume.assumeTrue(TestActiveProfilesResolver.isSophieHome());
		
		System.out.println(TestActiveProfilesResolver.isSophieHome());
		assertNotNull(postgresqlDS);
		runSuiteTest(postgresqlDS.getConnection());
	}

	// common
	private void runSuiteTest(Connection connection) {
		assertTrue(databaseUtils.retrieveData(connection) > 0);
	}
}
