import static com.tutego.jrtf.Rtf.rtf;

import static com.tutego.jrtf.RtfHeader.font;
import static com.tutego.jrtf.RtfPara.*;
import static com.tutego.jrtf.RtfText.*;
import static com.tutego.jrtf.RtfUnit.CM;

import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rtf.RtfGenerator;
import sqlite.SQLiteJDBC;

import com.tutego.jrtf.*;

import static com.tutego.jrtf.RtfFields.tableOfContentsField;


public class Main {

	public static void main( String... args ) throws IOException
	  {
		//String reportDest;
		//Scanner input = new Scanner(System.in);
		//System.out.print("Enter Report Destination (use two slashes): ");
		//reportDest = input.next();
		//RtfGenerator rtg = new RtfGenerator(reportDest + "\\test.rtf");
	    
		SQLiteJDBC db = new SQLiteJDBC();
	 
		RtfGenerator rtg = new RtfGenerator("src\\ReportArchive\\test.rtf");
		String sec1 = db.listAllMeta();
		String sec2 = db.executeTest(1);
		String sec3 = db.executeTest(2);
		rtg.addSection(sec1, RtfHeaderStyle.HEADER_1, "First section", "Random footnote1");
		rtg.addSection(sec2, RtfHeaderStyle.HEADER_2, "Second section", "Random footnote2");
		rtg.addSection(sec3, RtfHeaderStyle.HEADER_3, "Third section", "Random footnote3");
		rtg.outputFile();
		
		
	  }
}

















