package dbConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Milan Zelenka
 * This class manages connections with the target database. Use DbManagerBuilder class
 * to build DbManager. 
 */
public class DbManager {
	
	public enum DbType {
		SQLite,
		MySQL,
		Oracle
	}
	
	private final String JDBC_SQLITE_DRIVER = "org.sqlite.JDBC";
	private final String JDBC_MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private String usedDriver;
	
	private StringBuilder urlBuilder = new StringBuilder();
	
//	private final String DB_URL = "jdbc:mysql://db4free.net:3307/ocelot";
	private final String DB_URL = "jdbc:sqlite:socialNetwork.db";
	
	private Connection c = null;
	
	protected DbManager(DbType dbType, String URL, String port, String username, 
			String password, String dbName) throws Exception {
		
		urlBuilder.append("jdbc:");
		if (dbType == DbType.SQLite) {
			usedDriver = JDBC_SQLITE_DRIVER;
			urlBuilder.append("sqlite:");
		} else if (dbType == DbType.MySQL) {
			usedDriver = JDBC_MYSQL_DRIVER;
			urlBuilder.append("mysql:");
		} else {
			throw new Exception("Database not supported");
		}
		
		urlBuilder.append(URL);
		
		if (port != "") {
			urlBuilder.append(":");
			urlBuilder.append(port);
		}
		
		if (dbName != null) {
			urlBuilder.append("/");
			urlBuilder.append(dbName);
		}
		
		// Initialize driver
		Class.forName(usedDriver).newInstance();
		
		// Get connection
		if (username == null) {
			c = DriverManager.getConnection(urlBuilder.toString());
		} else {
			c = DriverManager.getConnection(urlBuilder.toString(), username, password);
		}
	}
	
	public ResultSet executeQuery(String query) {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}





