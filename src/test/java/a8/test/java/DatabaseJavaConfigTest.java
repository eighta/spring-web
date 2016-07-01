package a8.test.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.conf.DatabaseJavaConfig;
import a8.test.utils.DatabaseUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseJavaConfig.class)
public class DatabaseJavaConfigTest {

	private static DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
	
	@Autowired()
	@Qualifier("postgresqlDS")
	DataSource postgresqlDS;
	
	@Test
	// @Ignore //XXX SOLO FUNCIONAL EN "HOME", SE DEBE UTILIZAR MECANISMO DE
	// PROFILES
	public void getPostgresqlDataSource() throws SQLException {
		assertNotNull(postgresqlDS);
		runSuiteTest(postgresqlDS.getConnection());
	}

	// common
	private void runSuiteTest(Connection connection) {
		assertTrue(databaseUtils.retrieveData(connection) > 0);
	}
}
