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
		String reportDest;
		Scanner input = new Scanner(System.in);
//	    File out = new File( "out-toc.rtf" );
//
//	    rtf().headerStyles( RtfHeaderStyle.values() )
//	         .section( p( "Table of content\n" ),
//	                   p( tableOfContentsField() ) )
//	        .section( p( RtfHeaderStyle.HEADER_1, "Style - Header 1" ),
//	                  p( RtfHeaderStyle.HEADER_2, "Style - Header 2" ),
//	                  p( RtfHeaderStyle.HEADER_3, "Style - Header 3" ),
//	                  p( RtfHeaderStyle.HEADER_1, "Style - Header 1" ),
//	                  p( RtfHeaderStyle.HEADER_2, "Style - Header 2" ) )
//	    	.out( new FileWriter( out ) );
//	    try
//	    {
//	      Desktop.getDesktop().open( out );
//	    }
//	    catch ( IOException e ) { e.printStackTrace(); }
//	  }
		System.out.print("Enter Report Destination (use two slashes): ");
		reportDest = input.next();
		RtfGenerator rtg = new RtfGenerator(reportDest + "\\test.rtf");
//		String sec1 = "aaaaaaaaaaaaaa \t aaaaaaaaaaaaaaaa \n aaaaaaaaaaaaaa";
//		String sec2 = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
//		String sec3 = "cccccccccccccccccccccccccccccccccccccccccccc";
//		rtg.addSection(sec1, RtfHeaderStyle.HEADER_1, "First section", "Random footnote1");
//		rtg.addSection(sec2, RtfHeaderStyle.HEADER_2, "Second section", "Random footnote2");
//		rtg.addSection(sec3, RtfHeaderStyle.HEADER_3, "Third section", "Random footnote3");
		rtg.outputFile();
		
		SQLiteJDBC db = new SQLiteJDBC();
//		db.listAllMeta();
//		db.listMeta(1);
		db.executeTest(1);
	  }
}

















