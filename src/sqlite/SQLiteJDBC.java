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
	
	public void listAllMeta() {
		try {
			ResultSet rs = listAll.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t"
									+ rs.getString(3) + "\t" + rs.getString(4) + "\t");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------------------------------");
	}
	
	public ResultSet listMeta(int id) {
		try {
			listById.setInt(1, id);
			ResultSet rs = listById.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
									rs.getString(3) + "\t" + rs.getString(4) + "\t");
			}
			System.out.println("----------------------------------");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void executeTest(int id) {
		ResultSet metaRs = listMeta(id);
		try {
			listQueries.setInt(1, id);
			ResultSet queriesRs = listQueries.executeQuery();
			while (queriesRs.next()) {
				System.out.println(queriesRs.getInt(1) + "\t" + queriesRs.getInt(2) + "\t" +
						queriesRs.getString(3) + "\t" + queriesRs.getString(4) + "\t" + queriesRs.getString(5));
			}
			System.out.println("----------------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}





