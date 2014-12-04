import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import edu.cmu.minorthird.text.CharAnnotation;
import edu.cmu.minorthird.util.LineProcessingUtil;
import jangada.*;

public class SignatureExtractor  {

	private File file;
	private static String directory;
	private String filePath;
	static PedigreeNode wtf;
	ArrayList<String> signatureExtractList = new ArrayList<String>();
	static ArrayList<PedigreeNode> kids = new ArrayList<PedigreeNode>();
	static JSONObject obj = new JSONObject();
	private static Map<File,String> pedigreeMap = new HashMap<File,String>();
	private static List<PedigreeRecord> pedigreeList = new ArrayList<PedigreeRecord>();
	private static List<PedigreeRecord> grandchildPedigreeList = new ArrayList<PedigreeRecord>();
	private static List<PedigreeRecord> tempgrandchildPedigreeList = new ArrayList<PedigreeRecord>();
	private static List<PedigreeRecord> childPedigreeList = new ArrayList<PedigreeRecord>();

	public SignatureExtractor(File file) throws Exception {
		if (file.isDirectory()) {
			this.directory = file.getAbsolutePath();
			System.out.println("Signature extraction on directory: "
					+ directory);
		} else {
			this.filePath = file.getCanonicalPath();
			System.out.println("Signature extraction on specfic file: "
					+ filePath);
		}
	}

	// Count the number of spaces in a signature
	private int signatureSpaceCount(String signature) {
		int i = 0, spaceCount = 0;
		while (i < signature.length()) {
			if (signature.charAt(i) == ' ') {
				spaceCount++;
			}
			i++;
		}
		return spaceCount;
	}
	private static int countWords(String str) {
		if (str == null || str.isEmpty())
			return 0;

		int count = 0;
		for (int e = 0; e < str.length(); e++) {
			if (str.charAt(e) != ' ') {
				count++;
				while (str.charAt(e) != ' ' && e < str.length() - 1) {
					e++;
				}
			}
		}
		return count;
	}

	public ArrayList<String> signatureExtractionFile() {

		SigFilePredictor sigpred = new SigFilePredictor();
		try {
			String message = LineProcessingUtil.readFile(filePath);
			String msg = sigpred.getSignatureLines(message);

			if (countWords(msg) < 10) {
				System.out.println(msg);
				signatureExtractList.add(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return signatureExtractList;
	}

	public ArrayList<String> signatureExtractionDirectory() {
		SigFilePredictor sigpred = new SigFilePredictor();
		Map<File,String> sigMap = new HashMap<File,String>();
		try {

			File dir = new File(directory);
			File[] directoryListing = dir.listFiles();
			

			if (directoryListing != null) {

				for (File child : directoryListing) {

					if (!child.getName().contains("~")) {
						String filePath = child.getCanonicalPath();
			//			System.out.println(child.getName());

						String message = LineProcessingUtil.readFile(filePath);
						String msg = sigpred.getSignatureLines(message);

						signatureExtractList.add(msg);
						sigMap.put(child.getCanonicalFile(), msg);

					}
				signatureMap(sigMap);
				}
			} else {
				// Handle the case where dir is not really a directory.
				// Checking dir.isDirectory() above would not be sufficient
				// to avoid race conditions with another process that deletes
				// directories.
			}
			//

		} catch (Exception e) {
			e.printStackTrace();
		}

		return signatureExtractList;
	}

	public Map<File, String> signatureMap(Map<File,String> signature){
		pedigreeMap = signature;
		return pedigreeMap;
		
	}
	
	private static PedigreeRecord getParentPedigree(){
		PedigreeRecord parent = new PedigreeRecord(new File(directory));
		ArrayList<String> parentguids = new ArrayList<String>();
		parentguids.add("0");
		parent.setParentguids(parentguids);
		parent.setPedigreeType("Folder");
		pedigreeList.add(parent);
		return parent;
		
	}
	
	public static List<PedigreeRecord> sigMap2PedigreeList(Map<File,String> signatureMap){
		
		PedigreeRecord parentPedigree = getParentPedigree();
		for (Map.Entry<File, String> entry : signatureMap.entrySet()){
			ArrayList<String> parentguids = new ArrayList<String>();
			PedigreeRecord child = new PedigreeRecord(entry.getKey());	
			parentguids.add(parentPedigree.getGuid());
			child.setParentguids(parentguids);
			childPedigreeList.add(child);
		}
		
		return childPedigreeList;
		
	}
	
	public static List<PedigreeRecord> grandChildList(Map<File,String> signatureMap,String outputfolder){
		
		FileProcessor writer = new FileProcessor();	
		for (Map.Entry<File, String> entry : signatureMap.entrySet()){	
			String name = entry.getKey().getName();
			String output = outputfolder + name +".signature";
		
			writer.useByfferedFileWriter(entry.getValue(),outputfolder + name+".signature");
			
			PedigreeRecord grandchild = new PedigreeRecord(new File(output));
			grandchildPedigreeList.add(grandchild);
			
		}
		
		
		
		return grandchildPedigreeList;
		
	}
	
	public static List<PedigreeRecord> pedigreeList(){
		
		pedigreeList.addAll(childPedigreeList);
		pedigreeList.addAll(tempgrandchildPedigreeList);
		
		return pedigreeList;
		
	}

	public static void getParentIds(){
	

		for (PedigreeRecord childRecord : childPedigreeList){
		//	System.out.println(childRecord.getGuid());
			String child_name = null;
			String grand_child_name = null;
			for (PedigreeRecord grandChildRecord : grandchildPedigreeList){
				 ArrayList<String> parentguids = new ArrayList<String>(); 
				 PedigreeRecord tempGrand2 = new PedigreeRecord(new File(grandChildRecord.getPath()));
				 child_name = childRecord.getFilename();
				 grand_child_name = grandChildRecord.getFilename();
				if(grand_child_name.contains(child_name)){
					parentguids.add(childRecord.getGuid());
					parentguids.addAll(childRecord.getParentguids());
					grandChildRecord.setParentguids(parentguids);		
				 tempGrand2.setParentguids(parentguids);
				 tempgrandchildPedigreeList.add(tempGrand2);
				}
				
				
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		String dir = null;
		String outputfolder = null;

		ArrayList<String> filenames = new ArrayList<String>();
		ArrayList<String> extractedSignatures = new ArrayList<String>();
		ArrayList<String> parentguids = new ArrayList<String>();
		FileProcessor writer = new FileProcessor();
		Avro avroWriter = new Avro();

		for (int e = 0; e < args.length; e++) {
			dir = args[0];
			outputfolder = args[1];
		}
		PedigreeRecord parentfile = new PedigreeRecord(new File(dir));
		PedigreeNode parentnode = null;
		parentguids.add(parentfile.getGuid());
		SignatureExtractor extract = new SignatureExtractor(new File(dir));

		extractedSignatures = extract.signatureExtractionDirectory();

		File direct = new File(dir);
		File[] directoryListing = direct.listFiles();

		for (File child : directoryListing) {
			if (!child.getName().contains("~")) {
				filenames.add(child.getName());

				PedigreeRecord childfile = new PedigreeRecord(new File(
						child.getCanonicalPath()));
				PedigreeNode tempnode = null;
				tempnode = new PedigreeNode(tempnode, childfile);
//				
				wtf = tempnode;

				kids.add(wtf);
			}
		}

//		for (int i = 0; i < filenames.size(); i++) {
//			obj.put(filenames.get(i), extractedSignatures.get(i));
//		}
		
		
		PedigreeRecord grandchild = new PedigreeRecord(new File(outputfolder));
		PedigreeNode grandchildnode = null;
		grandchildnode = new PedigreeNode(grandchildnode, grandchild);
		PedigreeRecord greatgrandchildfile = new PedigreeRecord(new File(
				"/home/frostpmr/Downloads/test2.txt"));
		PedigreeNode greatgrandchildnode = null;
		greatgrandchildnode = new PedigreeNode(greatgrandchildnode,
				greatgrandchildfile);
		wtf.addChild(grandchildnode);
		
		grandchildnode.addChild(greatgrandchildnode);
		parentnode = new PedigreeNode(parentnode, kids, parentfile);

		PedigreeTree tree = new PedigreeTree(parentnode);
		
	//	tree.printPedigreeTree();
		sigMap2PedigreeList(pedigreeMap);
		
		grandChildList(pedigreeMap,outputfolder);
		getParentIds();
		
	

		avroWriter.avroWriter(pedigreeList());
		
		//writer.jsonWriter(obj,outputfolder);
	}

}
