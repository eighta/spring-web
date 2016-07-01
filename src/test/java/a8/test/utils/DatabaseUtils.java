package a8.test.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtils {

	//BEGIN: SINGLETON
	private static DatabaseUtils INSTANCE = null;
	private DatabaseUtils(){}
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new DatabaseUtils();
        }
    }
    public static DatabaseUtils getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    //END: SINGLETON
	
	public Connection getConnection(String driverClassName, String url, String username, String password) {

		try {

			return DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
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
	
}
