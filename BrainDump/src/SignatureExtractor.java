import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cmu.minorthird.text.CharAnnotation;
import edu.cmu.minorthird.util.LineProcessingUtil;
import jangada.*;

public class SignatureExtractor  {

	static PedigreeNode wtf;
	static ArrayList<PedigreeNode> kids = new ArrayList<PedigreeNode>();
	private static Map<File,String> signatureMap = new HashMap<File,String>();
	private static List<PedigreeRecord> pedigreeList = new ArrayList<PedigreeRecord>();
	private static PedigreeList pedList = new PedigreeList();
	private static PedigreeRecord parent = new PedigreeRecord();
	private Map<File, String> signatureMap(Map<File,String> signature){
		signatureMap = signature;
		return signatureMap;
		
	}

	public  Map<File, String> getSignatureMap() {
		return signatureMap;
	}
//
	public void setPedigreeMap(Map<File, String> pedigreeMap) {
		this.signatureMap = pedigreeMap;
	}
	
	public void setParentPedigree(String directory){
		PedigreeRecord parentuhh = new PedigreeRecord(new File(directory));
		this.parent = parentuhh;
		ArrayList<String> parentguids = new ArrayList<String>();
		parentguids.add("0");
		parent.setParentguids(parentguids);
		parent.setPedigreeType("Folder");
		pedigreeList.add(parent);
		
		
	}
	public static PedigreeRecord getParentPedigree(){
		return parent;
	}
	public static void sigMap2PedigreeList(){
		
		PedigreeRecord parentPedigree = getParentPedigree();
		for (Map.Entry<File, String> entry : signatureMap.entrySet()){
			ArrayList<String> parentguids = new ArrayList<String>();
			PedigreeRecord child = new PedigreeRecord(entry.getKey());	
			parentguids.add(parentPedigree.getGuid());
			child.setParentguids(parentguids);
//			childPedigreeList.add(child);
			pedList.setChildList(child);
		}
	}
	
	public static void outputList(String outputfolder){
		
		FileProcessor writer = new FileProcessor();	
		for (Map.Entry<File, String> entry : signatureMap.entrySet()){	
			String name = entry.getKey().getName();
			String output = outputfolder + name +".signature";
		
			writer.useByfferedFileWriter(entry.getValue(),outputfolder + name+".signature");
			
			PedigreeRecord grandchild = new PedigreeRecord(new File(output));
			pedList.setGrandchildPedigreeList(grandchild);	
		}		
	}
	
	public static List<PedigreeRecord> getPedigreeList(){	
		pedigreeList.addAll(pedList.getChildList());
		pedigreeList.addAll(pedList.getTempGrandchildPedigreeList());
		
		return pedigreeList;
		
	}

	public static void getParentIds(){
	
		for (PedigreeRecord childRecord : pedList.getChildList()){
			for (PedigreeRecord grandChildRecord : pedList.getGrandchildPedigreeList()){
				 ArrayList<String> parentguids = new ArrayList<String>(); 
				 PedigreeRecord tempGrand2 = new PedigreeRecord(new File(grandChildRecord.getPath()));
				if(grandChildRecord.getFilename().contains(childRecord.getFilename())){
					parentguids.add(childRecord.getGuid());
					parentguids.addAll(childRecord.getParentguids());
					grandChildRecord.setParentguids(parentguids);		
					tempGrand2.setParentguids(parentguids);
					pedList.setTempGrandchildPedigreeList(tempGrand2);
				}	
			}
		}
		
	}

//	public static void main(String[] args) throws Exception {
//		
//		String dir = null;
//		String outputfolder = null;
//		ArrayList<String> filenames = new ArrayList<String>();
//		ArrayList<String> extractedSignatures = new ArrayList<String>();
//		ArrayList<String> parentguids = new ArrayList<String>();
//		FileProcessor writer = new FileProcessor();
//		Avro avroWriter = new Avro();
//
//		for (int e = 0; e < args.length; e++) {
//			dir = args[0];
//			outputfolder = args[1];
//			
//		}
//		PedigreeRecord parentfile = new PedigreeRecord(new File(dir));
//		PedigreeNode parentnode = null;
//		parentguids.add(parentfile.getGuid());
////		SignatureExtractor extract = new SignatureExtractor(new File(dir));
////
////		extractedSignatures = extract.signatureExtractionDirectory();
//
//		File direct = new File(dir);
//		File[] directoryListing = direct.listFiles();
//
//		for (File child : directoryListing) {
//			if (!child.getName().contains("~")) {
//				filenames.add(child.getName());
//
//				PedigreeRecord childfile = new PedigreeRecord(new File(
//						child.getCanonicalPath()));
//				PedigreeNode tempnode = null;
//				tempnode = new PedigreeNode(tempnode, childfile);
////				
//				wtf = tempnode;
//
//				kids.add(wtf);
//			}
//		}
//		
//		PedigreeRecord grandchild = new PedigreeRecord(new File(outputfolder));
//		PedigreeNode grandchildnode = null;
//		grandchildnode = new PedigreeNode(grandchildnode, grandchild);
//		PedigreeRecord greatgrandchildfile = new PedigreeRecord(new File(
//				"/home/frostpmr/Downloads/test2.txt"));
//		PedigreeNode greatgrandchildnode = null;
//		greatgrandchildnode = new PedigreeNode(greatgrandchildnode,
//				greatgrandchildfile);
//		wtf.addChild(grandchildnode);
//		
//		grandchildnode.addChild(greatgrandchildnode);
//		parentnode = new PedigreeNode(parentnode, kids, parentfile);
//
//		PedigreeTree tree = new PedigreeTree(parentnode);
//		
//	//	tree.printPedigreeTree();
//		
//		sigMap2PedigreeList();
//		
//		grandChildList(outputfolder);
//		getParentIds();
//		
//		//pedigreeList();
//
//		avroWriter.avroWriter(getPedigreeList());
//		
//		//writer.jsonWriter(obj,outputfolder);
//		
//		//Modoulalize
//		
//	}

}
