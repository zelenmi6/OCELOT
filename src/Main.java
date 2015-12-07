import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.List;

import rtf.RtfGenerator;
import sqlite.SQLiteJDBC;
import ReportArchive.Report;

import com.tutego.jrtf.RtfHeaderStyle;
import com.tutego.jrtf.RtfTextPara.TabKind;
import com.tutego.jrtf.RtfTextPara.TabLead;
import com.tutego.jrtf.RtfUnit;

import dbConnections.DbManager;
import dbConnections.DbManager.DbType;
import dbConnections.DbManagerBuilder;


public class Main {

	public static void main( String... args ) throws IOException
	  {
		/*By Jacob:
		String reportDest;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Report Destination (use two slashes): ");
		reportDest = input.next();
		RtfGenerator rtg = new RtfGenerator(reportDest + "\\test.rtf");
		*/
		
		//By Karen
		List<Integer> queries= Arrays.asList(1, 2, 3);
		RtfGenerator rtg = new RtfGenerator("src\\ReportArchive\\test.rtf");
		
		try {
			DbManager manager = new DbManagerBuilder(DbManager.DbType.SQLite, "socialNetwork.db").buildDbManager();
			ResultSet rs = manager.executeQuery("Select * from students");
			
			//Add to the rtf file
			rtg.addHeader("Report Title", queries, DbType.SQLite);
			rtg.AddQuery(1, "Select * from students", rs, "Some description", "Some conclusion");
			rtg.outputFile();
			
			/*By Milan:
			if (manager != null) {
				Report report = new Report(manager, 1);
			}*/
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		
	  }
}

















