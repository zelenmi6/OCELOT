import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import rtf.RtfGenerator;
import sqlite.SQLiteJDBC;
import ReportArchive.Report;

import com.tutego.jrtf.RtfHeaderStyle;

import dbConnections.DbManager;
import dbConnections.DbManagerBuilder;


public class Main {

	public static void main( String... args ) throws IOException
	  {
		//String reportDest;
		//Scanner input = new Scanner(System.in);
		//System.out.print("Enter Report Destination (use two slashes): ");
		//reportDest = input.next();
		//RtfGenerator rtg = new RtfGenerator(reportDest + "\\test.rtf");
	    
//		SQLiteJDBC db = new SQLiteJDBC();
//	 
//		RtfGenerator rtg = new RtfGenerator("src\\ReportArchive\\test.rtf");
//		String sec1 = db.listAllMeta();
//		String sec2 = db.executeTest(1);
//		String sec3 = db.executeTest(2);
//		rtg.addSection(sec1, RtfHeaderStyle.HEADER_1, "First section", "Random footnote1");
//		rtg.addSection(sec2, RtfHeaderStyle.HEADER_2, "Second section", "Random footnote2");
//		rtg.addSection(sec3, RtfHeaderStyle.HEADER_3, "Third section", "Random footnote3");
//		rtg.outputFile();
		
//		try {
//			DbManager manager = new DbManagerBuilder(DbManager.DbType.SQLite, "socialNetwork.db").buildDbManager();
//			ResultSet rs = manager.executeQuery("Select * from students");
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int columnsNumber = rsmd.getColumnCount();
//			while (rs.next()) {
//			    for (int i = 1; i <= columnsNumber; i++) {
//			        if (i > 1) System.out.print(",  ");
//			        String columnValue = rs.getString(i);
//			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
//			    }
//			    System.out.println("");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		DbManager manager = null;
		try {
			manager = new DbManagerBuilder(DbManager.DbType.SQLite, "socialNetwork.db").buildDbManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (manager != null) {
			Report report = new Report(manager, 1);
		}
		
	  }
}

















