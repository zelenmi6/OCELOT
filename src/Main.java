import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.List;

import rtf.RtfGenerator;
import sqlite.SQLiteJDBC;
import ui.UI;
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
		
		UI sdf = new UI();
		sdf.open();
		
	  }
}

















