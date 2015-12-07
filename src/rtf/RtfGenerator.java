package rtf;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.tutego.jrtf.Rtf;
import com.tutego.jrtf.RtfHeaderStyle;
import static com.tutego.jrtf.Rtf.rtf;
import static com.tutego.jrtf.RtfDocfmt.*;
import static com.tutego.jrtf.RtfHeader.*;
import static com.tutego.jrtf.RtfHeader.color;
import static com.tutego.jrtf.RtfHeader.font;
import static com.tutego.jrtf.RtfInfo.*;
import static com.tutego.jrtf.RtfFields.*;
import static com.tutego.jrtf.RtfPara.*;
import static com.tutego.jrtf.RtfSectionFormatAndHeaderFooter.*;
import static com.tutego.jrtf.RtfText.*;
import static com.tutego.jrtf.RtfText.color;
import static com.tutego.jrtf.RtfText.font;
import static com.tutego.jrtf.RtfUnit.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import dbConnections.DbManager;

public class RtfGenerator {
	
	File out;
	Rtf rtfObject;
	
	/**
	* This method is used to generate a empty 
	* rtf file that will be used as the report
	* @param fileName This is the name of the 
	* rtf file (including it's path), 
	* e.g. "src\\ReportArchive\\test.rtf"
	*/
	public RtfGenerator(String fileName) {
		out = new File(fileName);
		rtfObject = Rtf.rtf();
		
	}
	
	/**
	* This method is used to add a header, including 
	* the title, database type and date, to the report.
	* @param title This is the title of the report
	* @param queries This is a list of the id of the queries executed
	* @param dbType This is the type of the target database
	* (enum in our program)
	*/
	public void addHeader(String title, List<Integer> queries, DbManager.DbType dbType){
		//color: default - black, 1 - red, 2 - green, 3 - blue
		//font: default - SimSun, 1 - Times New Roman, 2 - Arial, 3 - Courier
		//fontSize: default - 24 (12 in editor), title - 72 (36), header - 36 (18)
		//bold: title, header
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		
		rtfObject
		.header(
				color( 0xff, 0, 0 ).at( 1 ),
			    color( 0, 0xff, 0 ).at( 2 ),
			    color( 0, 0, 0xff ).at( 3 ),
			    font( "Times New Roman" ).at( 1 ),
			    font( "Arial" ).at( 2 ),
			    font( "Courier" ).at( 3 )
			    )
			  .section(
					  p( fontSize(72, bold( font( 1, "Report Title" ))) ).alignCentered(),
			          p( fontSize(24, bold( "Database Type: ")),dbType ).alignLeft(),
			          p( fontSize(24, bold( "Report Date: ")), dateFormat.format(date) ).alignLeft()
			  );
		
	}
	
	/**
	* This method is used to each query module to 
	* the report.
	* @param Id of the query
	* @param query  whole query as a string
	* @param rs  result of the query 
	* @param description  description of the query
	* @param conclusion  conclusion of the query
	*/
	public void AddQuery(Integer id, String query, ResultSet rs,
						   String description, String conclusion) {
		String resultString ="";
		try{
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		// add table header
		for (int i = 1; i <= columnsNumber; i++) {
			String columnValue = rs.getString(i);
			resultString = resultString +rsmd.getColumnName(i)+"\t";
			//if (i == 4) resultString = resultString + "\t\t";
			}
		resultString = resultString + "\n";
		while (rs.next()) {
			// add cells
		    for (int i = 1; i <= columnsNumber; i++) {
		    	String columnValue = rs.getString(i);
		    	resultString = resultString + columnValue;
		        if (i > 1 && i< columnsNumber) {
		        	Integer space = 11 - columnValue.length();
		        	System.out.println("i: "+i+" space: "+space);
		        	for (int j=0; j<space; j++){
		        		resultString = resultString+ " ";
		        	}
		        }else{
		        	resultString = resultString+ "\t\t ";
		        }
		    }
		    resultString = resultString + "\n";
		}
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rtfObject
		.header(
				color( 0xff, 0, 0 ).at( 1 ),
			    color( 0, 0xff, 0 ).at( 2 ),
			    color( 0, 0, 0xff ).at( 3 ),
			    font( "Times New Roman" ).at( 1 ),
			    font( "Arial" ).at( 2 ),
			    font( "Courier" ).at( 3 )
			    )
			  .section(
					  p( fontSize(36, color(3, bold( font( 2, "PART "+id+ " :")))) ),
					  p( fontSize(24, bold( "Introduction: ")) ),
					  p( fontSize(24, description ) ),
					  p( fontSize(24, bold( "Result: ")) ),
					  p( fontSize(24, resultString ) ),
					  p( fontSize(24, bold( "Conclusion: ")) ),
					  p( fontSize(24, conclusion ) )
					  );
		
	}
	
	/**
	* This method is used to write 
	* all the content to the rtf file
	*/
	public void outputFile() {
		try {
			rtfObject.out( new FileWriter( out ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
