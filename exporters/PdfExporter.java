package exporters;

import java.util.ArrayList;
import java.util.List;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.LineBlock;


public class PdfExporter {

	List<LineBlock> lineBlocks ;
	Pdf pdf;
	
	//Get the lineblocks of the @param document and create new outputFileName.pdf file
	public PdfExporter(Document document, String outputFileName){
		
		List<LineBlock> lineBlocks = new ArrayList<LineBlock>(); 
		lineBlocks = document.getLineblocks();
		pdf = new Pdf(lineBlocks, outputFileName);
		pdf.PdfCreate();
		
	}
	public int export() {

		return  pdf.getLineBlockSIZE();
	}

}
