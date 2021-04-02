package datamodel.buildingblocks;

import java.util.ArrayList;
import java.util.List;

public class LineBlock {

	
	private List<String> lines = null;
	private StyleEnum style;
	private FormatEnum format;
	private int pos;
	private int numWords;
	
	public LineBlock() {
		lines = new ArrayList<String>();
	}
	public void addLines(String line){
		lines.add(line);
	}
	
	//return a List<String> with all the lines of specific lineblock
	public List<String> getLines() {
		return lines;
	}

	public void LineBlockAddpos(int position){
		this.pos = position;
	}
	
	//return the position of the lineblock inside the file.
	public int getLineBlockPos() {
		return pos;
	}
	
	public void setStyle(StyleEnum determineHeadingStatus) {
		this.style = determineHeadingStatus;	
	}

	public void setFormat(FormatEnum determineFormatStatus) {
		this.format = determineFormatStatus;
	}
	public StyleEnum getStyle() {
		return style;
	}
	public FormatEnum getFormat() {
		return format;
	}
	
	//@return true if the lineblock starts with @param prefix
	public boolean startsWith(String prefix) {
		
		String firstLine = lines.get(0);  
		String [] firstLineArray = firstLine.split(prefix); 
		
		if(firstLineArray.length >= 1) {
			if(firstLineArray[0].equals("")) {
				return true ;
			}
		}else if(firstLineArray.length == 0) return true;
		return false;
		
	}

	//replace the first element of the paragraph(prefix) with @param string
	public String replaceFirst(String prefix, String string) {
		
		String firstLine = lines.get(0);
		String newFirstLine = "";
		String [] firstLineArray = firstLine.split(prefix);
		firstLineArray[0] = "";
		newFirstLine = String.join("", firstLineArray);
		return newFirstLine;
	}

	//return the number of words that exist in the file
	public int getNumWords() {
		
		int count = 0;
		for(String line: lines) {
			count += WordsCountPerLine(line);
		}
		this.numWords = count;
		return numWords;
	}

	public String getStatsAsString() {
		
		return "Lines: "+lines.size()+"      Words: "+ getNumWords();
	}
	
	//return the number of words that exist in @param string
	public int WordsCountPerLine(String string){  
		
	  int count=0;  

      char ch[]= new char[string.length()];     
      for(int i=0;i<string.length();i++)  
      {  
          ch[i]= string.charAt(i);  
          if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )  
              count++;  
      }  
      return count;  
  	}  

}
