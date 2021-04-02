package dataload;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import datamodel.buildingblocks.LineBlock;


public class RawFileLineLoader {

	
	//@param filePath is the path of the file we want to load and store
	public List<LineBlock> load(String filePath) {
		
		List<LineBlock> lineBlocks = new ArrayList<LineBlock>();
		LineBlock lineBlock = new LineBlock();
		List<String> textList = new ArrayList<String>();
		String line;
		int firstEmptyLine = 1; //integer that helps us to handle continuous empty liens in the file
	
		
		//Read the file and add paragraphs in List<String> textList 
		//Separate paragraphs with one empty line
		
	    try {
	        File myObj = new File(filePath);
	        Scanner myReader = new Scanner(myObj);     
	        while (myReader.hasNextLine()) {
	        	
	        	line = myReader.nextLine();
	        	if(line.trim().isEmpty()) {
	        		if(firstEmptyLine == 1) {
	        			if(textList.size() > 0) {
		        			textList.add(line);
		        			firstEmptyLine = 0;
	        			}
	        		}
	        	}else {
	        		firstEmptyLine = 1;
	        		
	        		textList.add(line);	}
	        }
	        
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("File not found.");
	        e.printStackTrace();
	    }	   
	    int counter = 0;
	    int i;
	    String text;
	    
	    //Create a new Lineblock for each paragraph and store the position of the paragraph.
	    //Add all the lineblocks in List<LineBlock> lineBlocks
	    for(i = 0; i<textList.size(); i++) {
	    	text = textList.get(i);
	    	
	    	if(!text.trim().isEmpty()) {
	    		lineBlock.addLines(text);
	    		if(i == textList.size() - 1) {
	    			lineBlock.LineBlockAddpos(counter);
	    			lineBlocks.add(lineBlock);

	    		}
	    	}
	    	else if(text.trim().isEmpty()) {
	    		lineBlock.LineBlockAddpos(counter);
	    		lineBlocks.add(lineBlock);
	    		counter++;
	    		lineBlock = new LineBlock();
	    	}
	    	
	    }
	    return lineBlocks;		
	}
}
