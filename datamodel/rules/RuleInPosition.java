package datamodel.rules;

import java.util.List;

import datamodel.buildingblocks.LineBlock;

public class RuleInPosition extends AbstractRule {

	
	private List<Integer> positions;


	public RuleInPosition(List<Integer> pPositions) {
		positions = pPositions;
	}
	@Override
	public boolean isValid(LineBlock paragraph) {
		
		int pos = paragraph.getLineBlockPos();
		if (positions.contains(pos)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Rule that get specific paragraphs to activate the rule.";
	}

}
