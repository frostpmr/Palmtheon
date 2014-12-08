import java.io.File;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String dir = null;
		String outputfolder = null;
		Avro avroWriter = new Avro();
		SignatureExtractor peddy = new SignatureExtractor();
		for (int e = 0; e < args.length; e++) {
			dir = args[0];
			outputfolder = args[1];	
		}
		Siggy extract = new Siggy(new File(dir));
	
		extract.signatureExtractionDirectory();
		peddy.setParentPedigree(dir);
		peddy.setPedigreeMap(extract.getSignatureMap());
		peddy.sigMap2PedigreeList();
		peddy.outputList(outputfolder);
		peddy.getParentIds();
		
		avroWriter.avroWriter(peddy.getPedigreeList());
	
	}
	
	
}
