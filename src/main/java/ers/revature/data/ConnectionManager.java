package ers.revature.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	private static String url =System.getenv("DB_URL");
	private static String user ="ERS";
	private static String password="PASSWORD123";
	
	public static Connection getConnection(){
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			return conn;
		}catch (Exception e) {
			throw new IllegalStateException("Database is not working bro.");
		}	
	}
}
