package exporters;

import java.util.ArrayList;
import java.util.List;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.LineBlock;

public class MarkdownExporter {

	
	Markdown markdown;
	
	//Get the lineblocks of the @param document and create new outputFileName.md file
	public MarkdownExporter(Document document, String outputFileName) {
		
		List<LineBlock> lineBlocks = new ArrayList<LineBlock>(); 
		lineBlocks = document.getLineblocks();
		markdown = new Markdown(lineBlocks, outputFileName);
		markdown.MarkdownCreate();
	}

	public int export() {
		
		return markdown.getLineBlockSIZE();
	}


}
