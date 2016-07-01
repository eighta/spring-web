package a8.test.plain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import a8.test.utils.CommonsUtils;
import a8.test.utils.DatabaseUtils;

public class DatabasePlainTest {

	private static Properties properties;
	
	private static CommonsUtils commonsUtils = CommonsUtils.getInstance();
	private static DatabaseUtils databaseUtils = DatabaseUtils.getInstance();

	@BeforeClass
	public static void runBeforeClass() {
		properties = commonsUtils.getProperties("database"+File.separator+"datasources.properties");
	}
	
	@Test
	public void propertiesLoaded(){
		assertNotNull(properties);
		
		String prop = properties.getProperty("postgresql_username");
		assertNotNull(prop);
	}

	// POSTGRESQL
	@Test
	public void connect2postgresql() {

		String db = "postgresql";
		String driverClassName = properties.getProperty(commonsUtils.stringJoinUsingCharDelimiter("_",db,"driverClassName"));
		String url = properties.getProperty(commonsUtils.stringJoinUsingCharDelimiter("_",db,"url"));
		String username = properties.getProperty(commonsUtils.stringJoinUsingCharDelimiter("_",db,"username"));
		String password = properties.getProperty(commonsUtils.stringJoinUsingCharDelimiter("_",db,"password"));

		Connection connection = databaseUtils.getConnection(driverClassName, url, username, password);
		assertNotNull(connection);
		runSuiteTest(connection);
	}

	// MYSQL
	@Test
	public void connect2mysql() {
		
		String db = "mysql";
		String driverClassName = properties.getProperty(commonsUtils.stringJoinUsingCharDelimiter("_",db,"driverClassName"));
		String url = properties.getProperty(commonsUtils.stringJoinUsingCharDelimiter("_",db,"url"));
		String username = properties.getProperty(commonsUtils.stringJoinUsingCharDelimiter("_",db,"username"));
		String password = properties.getProperty(commonsUtils.stringJoinUsingCharDelimiter("_",db,"password"));
		
		Connection connection = databaseUtils.getConnection(driverClassName, url, username, password);
		assertNotNull(connection);
		runSuiteTest(connection);
	}

	//common
	private void runSuiteTest(Connection connection) {
		assertTrue(databaseUtils.retrieveData(connection) > 0);
	}

}
