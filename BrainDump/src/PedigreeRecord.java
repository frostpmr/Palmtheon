import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

/*
 * The current implementation of Pedigree Record only accepts File objects
 */
public class PedigreeRecord {

	private String uniqueID;
	
	private String pedigreeType = "File";
	
	private String filename;
	
	private String path;
	
	private ArrayList<String> parentguids;
	
	private String eventtimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	private String md5Hash;
	
	private String sha512;
	
	private String cltnld = "11111111";	

	private String eventName = "Signature Block Extraction";

	private String eventVerison = "Beta 0.1.0";
	
	private String eventRunId = "0000000000";
	
	public PedigreeRecord(){
		
	}
	public PedigreeRecord(File file) {
		this.uniqueID = UUID.randomUUID().toString();
		this.pedigreeType = pedigreeType;
		this.path = file.getPath();
		this.filename = file.getName();
		this.md5Hash = DigestUtils.md5Hex(this.path);
		this.sha512 = DigestUtils.sha512Hex(this.path);
	
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
	public ArrayList<String> getParentguids() {
		return parentguids;
	}
	
	public void setParentguids(ArrayList<String> parentguids) {
		this.parentguids = parentguids;
	}	
	public void addParent(String parent){
		parentguids.add(parent);
	}
	
	public String getEventtimeStamp() {
		return eventtimeStamp;
	}

	public void setEventtimeStamp(String eventtimeStamp) {
		this.eventtimeStamp = eventtimeStamp;
	}
	
	
	public String getMd5Hash() {
		return md5Hash;
	}

	public void setMd5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
	}
	
	public String getSha512() {
		return sha512;
	}

	public void setSha512(String sha512) {
		this.sha512 = sha512;
	}
	
	public String getCltnld() {
		return cltnld;
	}

	public void setCltnld(String cltnld) {
		this.cltnld = cltnld;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventVerison() {
		return eventVerison;
	}

	public void setEventVerison(String eventVerison) {
		this.eventVerison = eventVerison;
	}

	public String getEventRunId() {
		return eventRunId;
	}

	public void setEventRunId(String eventRunId) {
		this.eventRunId = eventRunId;
	}
}