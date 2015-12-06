package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteJDBC {
	private Connection c = null;
    private PreparedStatement listAll;
    private PreparedStatement listById;
    private PreparedStatement listQueries;
    private final String dbName = "jdbc:sqlite:serval.db";

	public SQLiteJDBC() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dbName);
			System.out.println("Connection successful");
			
			listAll = c.prepareStatement("select * from meta");
			listById = c.prepareStatement("select * from meta "
					+ "where id = ?");
			listQueries = c.prepareStatement("select * from queries "
					+ "where meta_id = ?");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find the driver");
			e.printStackTrace();
		}
	}
	
	public String listAllMeta() {
		String string = ""; 
		try {
			ResultSet rs = listAll.executeQuery();
			while (rs.next()) {
				string = string + rs.getString(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getString(3) + "\t" + rs.getString(4) + "\t";
				System.out.println(string);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("listAllMeta----------------------------------");
		return string;
	}
	
	public ResultSet listMeta(int id) {
		try {
			listById.setInt(1, id);
			ResultSet rs = listById.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
									rs.getString(3) + "\t" + rs.getString(4) + "\t");
			}
			System.out.println("listMeta----------------------------------");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String executeTest(int id) {
		String string ="";
		ResultSet metaRs = listMeta(id);
		try {
			listQueries.setInt(1, id);
			ResultSet queriesRs = listQueries.executeQuery();
			while (queriesRs.next()) {
				string = string + queriesRs.getInt(1) + "\t" + queriesRs.getInt(2) + "\t" +
						queriesRs.getString(3) + "\t" + queriesRs.getString(4) + "\t" + queriesRs.getString(5);
				System.out.println(string);
			}
			System.out.println("executeTest----------------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}
}





