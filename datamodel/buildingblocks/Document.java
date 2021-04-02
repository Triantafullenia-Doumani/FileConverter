package datamodel.buildingblocks;

import java.util.List;

public class Document {

	public enum DocumentRawType {
		RAW , ANNOTATED

	}

	private DocumentRawType Type ;
	private List<LineBlock> lineBlocks;
	

	public Document(String pFilePath, DocumentRawType docType) {
		
		this.Type = docType;
		if(Type == DocumentRawType.RAW) {
			
		}
		else if (Type == DocumentRawType.ANNOTATED) {
			
		}
	}
	
	public List<LineBlock> getLineblocks() {
	    return lineBlocks;
	}
	public void addLineBlocks(List<LineBlock> LineBlocks) {
		this.lineBlocks = LineBlocks;
	}
	public DocumentRawType getInputFileType() {
		return Type;
	}


}
