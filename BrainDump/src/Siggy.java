import jangada.SigFilePredictor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.cmu.minorthird.util.LineProcessingUtil;


public class Siggy {

	private static String directory;
	private String filePath;
	private static Map<File,String> signatureMap = new HashMap<File,String>();
	
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
	public Siggy(File file) throws Exception {
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
	
	private Map<File, String> signatureMap(Map<File,String> signature){
		signatureMap = signature;
		return signatureMap;
		
	}

	public  Map<File, String> getSignatureMap() {
		return signatureMap;
	}
	
	public void signatureExtractionDirectory() {
		SigFilePredictor sigpred = new SigFilePredictor();
		Map<File,String> sigMap = new HashMap<File,String>();
		try {

			File dir = new File(directory);
			File[] directoryListing = dir.listFiles();
			

			if (directoryListing != null) {

				for (File child : directoryListing) {

					if (!child.getName().contains("~")) {
						String filePath = child.getCanonicalPath();
					//	System.out.println(child.getName());

						String message = LineProcessingUtil.readFile(filePath);
						String msg = sigpred.getSignatureLines(message);

						
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

	}
	
	
	
	
}
