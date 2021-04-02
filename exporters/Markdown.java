package exporters;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datamodel.buildingblocks.LineBlock;

public class Markdown {

	private List<LineBlock> lineBlocks;
	private String outputFileName;
	private int lineblocksSIZE ;
	

	public Markdown(List<LineBlock> Lineblocks, String outPutFileName) {
		this.lineBlocks = Lineblocks;
		this.outputFileName = outPutFileName;
	}

	//Create a new Markdown file
	public void MarkdownCreate(){

		int i;
		List<String> lineBlockAsString = new ArrayList<String>();
		String newLine ="";
		this.lineblocksSIZE = 0;
		String Note ;
		String priority = "" ;
		
	    try {
	         FileWriter markdown = new FileWriter(outputFileName);
	 		 
	 		//Read all the Lineblocks and get the Format and Style from each one
	         outerloop:
	         for(LineBlock lineblock : lineBlocks) {	
	 			
	        	Note = "";
	 			lineBlockAsString = lineblock.getLines();
	 			

	 			switch (lineblock.getFormat()) {
	 			
	 				case BOLD:
	 					Note = "**";
	 					priority = "format";
	 					break;
	 					
	 				case ITALICS:
	 					Note = "_";
	 					priority = "format";

	 					break;
	 					
	 				default:
					break;
	 			}
	 			switch (lineblock.getStyle()) {
 				
	 				case OMITTED: 
	 					Note = "omit";
	 					priority = "style";

	 					break;	
	 				case H1:
	 					Note = "#";
	 					
	 					priority = "style";

	 					break;
	 				case H2:
	 					Note = "##";
	 					priority = "style";

	 					break;
	 				default:
	 					break;
			}
 			
	 			//Insert to the markdown output file the notes that correspond to each line,
	 			//based on the input Ruleset
	 			
	 			for(i = 0 ; i<lineBlockAsString.size(); i++) {
	 				
	 				if(Note.equals("omit")) continue outerloop; //omit rule
	 				if(priority.equals("format")) {
	 					newLine = Note+lineBlockAsString.get(i) +" "+ Note;
	 				}
	 				else if(priority.equals("style")) {
	 					newLine = Note+lineBlockAsString.get(i) + " ";
	 				}else {
	 					newLine = lineBlockAsString.get(i) ;
	 				}
	 				markdown.write(newLine);
	 				newLine = "";
	 			}
	 			markdown.write("\n");
	 			markdown.write("\n");
	 			this.lineblocksSIZE ++;

	         }
	         markdown.close();
	       } catch (IOException e) {
	         System.out.println("An error occurred.");
	         e.printStackTrace();
	       }
	
	}

	//return the number of lineblocks that exist in the output file
	public int getLineBlockSIZE() {
		
		return lineblocksSIZE;
	}
	

}
