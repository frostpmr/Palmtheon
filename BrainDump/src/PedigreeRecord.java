import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/*
 * The current implementation of Pedigree Record only accepts Files.
 */
import java.io.File;
import java.util.UUID;

/*
 * The current implementation of Pedigree Record only accepts File objects
 */
public class PedigreeRecord {

	private String uniqueID;
	
	private String pedigreeType = "File";
	
	private String filename;
	
	private String path;
	
//	private ArrayList<String> parentguids;

	public PedigreeRecord(File file) {
		this.uniqueID = UUID.randomUUID().toString();
		this.pedigreeType = pedigreeType;
		this.path = file.getPath();
		this.filename = file.getName();
		
	}
	
	public String getGuid() {
		return uniqueID;
	}

	public String getPedigreeType() {
		return pedigreeType;
	}

	public void setPedigreeType(String pedigreeType) {
		this.pedigreeType = pedigreeType;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
//	public ArrayList<String> getParentguids() {
//		return parentguids;
//	}
//	public void setParentguids(ArrayList<String> parentguids) {
//		this.parentguids = parentguids;
//	}
	
}