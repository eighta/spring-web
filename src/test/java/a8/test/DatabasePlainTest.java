package a8.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Runner;

public class DatabasePlainTest {

	private static Properties properties;
	private List<Connection> connections = new ArrayList<>();

	@BeforeClass
	public static void runBeforeClass() {
		properties = getProperties("database"+File.separator+"datasources.properties");
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
		String driverClassName = properties.getProperty(stringJoinUsingCharDelimiter("_",db,"driverClassName"));
		String url = properties.getProperty(stringJoinUsingCharDelimiter("_",db,"url"));
		String username = properties.getProperty(stringJoinUsingCharDelimiter("_",db,"username"));
		String password = properties.getProperty(stringJoinUsingCharDelimiter("_",db,"password"));

		Connection connection = getConnection(driverClassName, url, username, password);
		assertNotNull(connection);
		connections.add(connection);
	}

	// MYSQL
	@Test
	public void connect2mysql() {
		
		String db = "mysql";
		String driverClassName = properties.getProperty(stringJoinUsingCharDelimiter("_",db,"driverClassName"));
		String url = properties.getProperty(stringJoinUsingCharDelimiter("_",db,"url"));
		String username = properties.getProperty(stringJoinUsingCharDelimiter("_",db,"username"));
		String password = properties.getProperty(stringJoinUsingCharDelimiter("_",db,"password"));
		
		Connection connection = getConnection(driverClassName, url, username, password);
		assertNotNull(connection);
		connections.add(connection);
	}

	// COMMON TEST
	@Test
	public void verifyData() {

		for (Connection connection : connections) {
			assertTrue(retrieveData(connection) > 0);
		}
	}

	// UTIL
	public static String stringJoinUsingCharDelimiter(CharSequence  delimiter, String ... strings){
		
		StringJoiner sjr = new StringJoiner(delimiter);
		for(String str:strings){
			sjr.add(str);
		}
		
		return sjr.toString();
	}
	
	public static Properties getProperties(String path){

		try {
			Properties _properties = new Properties();
			_properties.load(Runner.class.getClassLoader().getResourceAsStream(path));
			return _properties;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} 
	}
	
	public int retrieveData(Connection connection) {

		Integer registros = 0;

		try {
			String query = "select count(1) as registros from books";

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				registros = rs.getInt("registros");

			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return registros;
	}

	public Connection getConnection(String driverClassName, String url, String username, String password) {

		try {

			return DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
