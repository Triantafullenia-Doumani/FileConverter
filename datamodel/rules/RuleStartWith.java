package datamodel.rules;

import java.util.ArrayList;
import java.util.List;

import datamodel.buildingblocks.LineBlock;

public class RuleStartWith extends AbstractRule {

	
	private String prefix;

	
	public RuleStartWith(String prefixString) {
		this.prefix = prefixString;
	}

	@Override
	public boolean isValid(LineBlock lineblock) {
		
		List<String> lines = new ArrayList<>();
		lines = lineblock.getLines();
		String firstLine = lines.get(0);
		String [] firstLineArray = firstLine.split(prefix);
		

		if(firstLineArray.length >= 1) {
			if(firstLineArray[0].equals("")) {
				return true ;
			}
		}else if(firstLineArray.length == 0) return true;
		return false;
	
	}

	@Override
	public String toString() {
		return "Rule that checks if the paragraph starts with specific prefix.";
	}

}