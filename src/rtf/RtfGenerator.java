package rtf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tutego.jrtf.Rtf;
import com.tutego.jrtf.RtfHeaderStyle;

import static com.tutego.jrtf.Rtf.rtf;
import static com.tutego.jrtf.RtfHeader.font;
import static com.tutego.jrtf.RtfPara.*;
import static com.tutego.jrtf.RtfText.*;
import static com.tutego.jrtf.RtfUnit.CM;

public class RtfGenerator {
	
	File out;
	Rtf rtfObject;
	
	public RtfGenerator(String fileName) {
		out = new File(fileName);
		rtfObject = Rtf.rtf();
	}
	
	public void addSection(String sectionText, RtfHeaderStyle headerType,
						   String headerText, String footnote) {
		rtfObject.headerStyles( RtfHeaderStyle.values() )
		.section(p( headerType, headerText ), p(sectionText));
	}
	
	public void outputFile() {
		try {
			rtfObject.out( new FileWriter( out ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
