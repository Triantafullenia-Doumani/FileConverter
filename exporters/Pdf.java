package exporters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import datamodel.buildingblocks.LineBlock;

public class Pdf{

	private List<LineBlock> lineBlocks;
	private String outputFileName;
	private int lineblocksSIZE ;
	
	public Pdf(List<LineBlock> Lineblocks, String outPutFileName) {
		this.lineBlocks = Lineblocks;
		this.outputFileName = outPutFileName;
	}
	
	//Create a new Pdf file with itext5

	public void PdfCreate()  {
		
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
			List<String> lineBlockAsString = new ArrayList<String>();
			int FORMAT = 0;
			int SIZE = 13;
			this.lineblocksSIZE = 0;
			document.open();
			//Read all the Lineblocks and get the Format and Style from each one

			for(LineBlock lineblock : lineBlocks) {
				Phrase phrase = new Phrase();
				
				FORMAT = 0;
				SIZE = 13;
				
				switch (lineblock.getFormat()) {
				
					case BOLD:
						FORMAT = Font.BOLD;
						break;
						
					case ITALICS:
						FORMAT = Font.ITALIC;
						break;
						
					default:
						FORMAT = Font.NORMAL;
						break;
				}
				switch (lineblock.getStyle()) {
					
					case OMITTED: 
						FORMAT = Font.UNDEFINED;
						break;	
					case H1:
						SIZE = 16;
						FORMAT = Font.BOLD;
						break;
					case H2:
						SIZE = 16;				
						break;
					default:
						break;
				}

				if(FORMAT ==  Font.UNDEFINED) //omit rule
					continue;
				
				//write inside the outputFileName.pdf based on the input Ruleset
				this.lineblocksSIZE ++;
				Font font = new Font(FontFamily.TIMES_ROMAN, SIZE, FORMAT);
				lineBlockAsString = lineblock.getLines();
				for(String line : lineBlockAsString) {
					Chunk chunk = new Chunk(line, font);				
					phrase.add(chunk);			
				}	
				
				Paragraph p = new Paragraph();
				p.add(phrase);
				document.add(p);
				document.add(Chunk.NEWLINE);
			}
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		document.close();
	}

	//return the number of lineblocks that exist in the output file
	public int getLineBlockSIZE() {
		return lineblocksSIZE;
	}

}
