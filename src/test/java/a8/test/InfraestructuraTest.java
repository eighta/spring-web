package a8.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import a8.conf.RepositorioConf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositorioConf.class })
public class InfraestructuraTest {

	@Test
	public void dummyMethod(){
		System.out.println("HEY....");
	}
	
	@Test
	@Ignore //XXX SOLO FUNCIONAL EN "HOME", MAS ADELANTE INVESTIGAR SOBRE: SOCKS
	public void connect2DB(){
		
		/*
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Loading class `com.mysql.jdbc.Driver'. This is deprecated. 
			//The new driver class is `com.mysql.cj.jdbc.Driver'. 
			//The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		*/
		
//		Connection conn = null;
//		try {
//		    conn =
//		       DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql5125772?" +
//		                                   "user=sql5125772&password=m8kvzb1Czk");
//
//		    // Do something with the Connection
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		
//		System.getProperties().put("http.proxyHost", "10.1.0.194");
//		System.getProperties().put("http.proxyPort", "3128");
//		System.setProperty("java.net.useSystemProxies", "true");
		
		
		Connection connection = null;
		try {	
			Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection(
					"jdbc:postgresql://pellefant-02.db.elephantsql.com:5432/lrhvftyn","lrhvftyn", "IA5OtW7om2hqvh_EFuuy-IhDAczg-FdF");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
