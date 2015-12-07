package ReportArchive;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sqlite.SQLiteJDBC;
import dbConnections.DbManager;

/**
 * This class automatically executes a test specified in its constructor
 * @author Milan Zelenka
 *
 */
public class Report {
	
	/**
	 * 
	 * @param dbManager reference to the DbManager that is being used
	 * @param reportId Id of the reports that is going to be run
	 */
	public Report(DbManager dbManager, int reportId) {
		
		
		SQLiteJDBC reportDb = new SQLiteJDBC();
		ResultSet reportInfo = reportDb.getReport(reportId);
		
		String title = getTitle(reportInfo);
		List<Integer> queryNumbers = getQueryNumbers(reportInfo);
		// Add header to the RTF
		runQueries(queryNumbers, reportDb, dbManager/*, RTFDocument*/);
	}
	
	private String getTitle(ResultSet reportInfo) {
		String title = null;
		try {
			title = reportInfo.getString(2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return title;
	}
	
	private List<Integer> getQueryNumbers(ResultSet reportInfo) {
		List<Integer> queryNumbers = new ArrayList<>();
		try {
			String queries = reportInfo.getString(3);
			String [] tokens = queries.split(",");
			for (String token : tokens) {
				queryNumbers.add(Integer.parseInt(token.trim()));
			}
			System.out.println(queryNumbers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return queryNumbers;
	}
	
	private void runQueries(List<Integer> queryNumbers, SQLiteJDBC reportDb,
			DbManager dbManager /*, RTFDocument*/) {
		for (Integer queryId : queryNumbers) {
			// Get query information and its meta
			ResultSet queryRs = reportDb.getQuery(queryId);
			int metaId;
			try {
				metaId = queryRs.getInt(2);
			} catch (SQLException e) {
				metaId = -1;
				e.printStackTrace();
				System.exit(0);
			}
			ResultSet metaRs = reportDb.getMeta(metaId);
			
			String title, intro, conclusion, queryString;
			try {
				title = metaRs.getString(2);
				intro = metaRs.getString(3);
				conclusion = metaRs.getString(4);
				queryString = queryRs.getString(3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				queryString = null;
				title = null;
				conclusion = null;
				intro = null;
				System.exit(0);
			}
			//  End of get query information and its meta
			
			// Run query against the target database
			ResultSet queryResultRs = dbManager.executeQuery(queryString);
			// RTF.AddQuery(queryId, queryString, queryResultRs, intro, conclusion)
		}
	}
	
}







