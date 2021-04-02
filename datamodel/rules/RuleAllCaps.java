package datamodel.rules;

import java.util.List;

import datamodel.buildingblocks.LineBlock;

public class RuleAllCaps extends AbstractRule {
	
	public boolean isValid(LineBlock paragraph) {		
		int i;
		int uppercase = 0;
		List<String> lines  = paragraph.getLines();
		for(i=0; i<lines.size(); i++) {
			if(isStringUpperCase(lines.get(i))) {
				uppercase = 1;
			}else {
				uppercase = 0; 
			} 
		}
		if(uppercase == 1 ) return true;
		else return false;
	}
    public boolean isStringUpperCase(String str){
        
        //convert String to char array
        char[] charArray = str.toCharArray();
        
        for(int i=0; i < charArray.length; i++){
            
        	if(Character.isLetter(charArray[i])) {
                //if any character is not in upper case, return false
                if( !Character.isUpperCase( charArray[i] ))
                    return false;
        	}
        }
        return true;
    }
	@Override
	public String toString() {
				return "Rule that checks if all the letters of the paragraph are uppercase.";
	}

}
