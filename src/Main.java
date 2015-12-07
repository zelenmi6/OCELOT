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

/*Note: When using your copy, be sure to change the reference library for sqlite jdbc library,
 * couldn't find a way to make that automatic within the project. Would have liked to have that
 *  implemented if we had more time. Granted we were using eclipse.
 */
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

















