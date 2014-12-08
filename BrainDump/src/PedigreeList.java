import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PedigreeList {
	
	private  Map<File,String> pedigreeMap = new HashMap<File,String>();

	private  List<PedigreeRecord> pedigreeList = new ArrayList<PedigreeRecord>();
	
	private  List<PedigreeRecord> grandchildPedigreeList = new ArrayList<PedigreeRecord>();
	
	private  List<PedigreeRecord> tempGrandchildPedigreeList = new ArrayList<PedigreeRecord>();
	
	private  List<PedigreeRecord> childList = new ArrayList<PedigreeRecord>();

	public  List<PedigreeRecord> getChildList(){
		return childList;
	}
	public void setChildList(PedigreeRecord PedigreeRecord){
		childList.add(PedigreeRecord);			
	}
	
	public  List<PedigreeRecord> getGrandchildPedigreeList() {
		return grandchildPedigreeList;
	}

	public  void setGrandchildPedigreeList(PedigreeRecord grandchild) {
		grandchildPedigreeList.add(grandchild);
		
	}
	public  void setTempGrandchildPedigreeList(PedigreeRecord grandchild) {
		tempGrandchildPedigreeList.add(grandchild);
		
	}
	public List<PedigreeRecord> getPedigreeList() {
		return pedigreeList;
	}
	public List<PedigreeRecord> getTempGrandchildPedigreeList() {
		return tempGrandchildPedigreeList;
	}
	public  Map<File, String> getPedigreeMap() {
		return pedigreeMap;
	}

	public void setPedigreeMap(Map<File, String> pedigreeMap) {
		this.pedigreeMap = pedigreeMap;
	}
}
