package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteJDBC {
	private Connection c = null;
    private PreparedStatement listAllMeta;
    private PreparedStatement listMetaById;
    private PreparedStatement listQueriesByMeta;
    private PreparedStatement listReportById;
    private PreparedStatement listQueryById;
    private final String dbName = "jdbc:sqlite:serval.db";

	public SQLiteJDBC() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dbName);
			System.out.println("Connection successful");
			
			listAllMeta = c.prepareStatement("select * from meta");
			listMetaById = c.prepareStatement("select * from meta "
					+ "where id = ?");
			listQueriesByMeta = c.prepareStatement("select * from queries "
					+ "where meta_id = ?");
			listReportById = c.prepareStatement("select * from reports "
					+ "where id = ?");
			listQueryById = c.prepareStatement("select * from queries "
					+ "where id = ?");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find the driver");
			e.printStackTrace();
		}
	}
	
	public ResultSet getQuery(int id) {
		try {
			listQueryById.setInt(1, id);
			ResultSet rs = listQueryById.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("There has been an error in the getQuery function");
		return null;
	}
	
	public String listAllMeta() {
		String string = ""; 
		try {
			ResultSet rs = listAllMeta.executeQuery();
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
	
	public ResultSet getMeta(int id) {
		try {
			listMetaById.setInt(1, id);
			ResultSet rs = listMetaById.executeQuery();
//			while (rs.next()) {
//				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
//									rs.getString(3) + "\t" + rs.getString(4) + "\t");
//			}
//			System.out.println("listMeta----------------------------------");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getReport(int id) {
		try {
			listReportById.setInt(1, id);
			ResultSet rs = listReportById.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("There has been an error in getReport function");
		return null;
	}
	
	public String executeTest(int id) {
		String string ="";
		ResultSet metaRs = getMeta(id);
		try {
			listQueriesByMeta.setInt(1, id);
			ResultSet queriesRs = listQueriesByMeta.executeQuery();
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





