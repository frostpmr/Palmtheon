
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class PedigreeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> guids = new ArrayList<String>();
	
		PedigreeRecord parentfile = new PedigreeRecord(new File("/home/frostpmr/Downloads/small_demo_266.tgz"));
		PedigreeRecord childfile = new PedigreeRecord(new File("/home/frostpmr/Downloads/test.txt"));
		PedigreeRecord grandchild = new PedigreeRecord(new File("/home/frostpmr/Downloads/test2.txt"));
		List<PedigreeNode> kids = new ArrayList<PedigreeNode>();

		PedigreeNode parentnode = null;
		PedigreeNode childnode = null;
		PedigreeNode grandchildnode = null;
		
		childnode = new PedigreeNode(childnode,childfile);
		grandchildnode = new PedigreeNode(grandchildnode,grandchild);
		
		parentnode = new PedigreeNode(parentnode,kids,parentfile);
		
//		guids.add(parentnode.getGuid().toString());
//		guids.add(childnode.getGuid().toString());
//		grandchildnode.setParentguids(guids);
		childnode.addChild(grandchildnode);
		kids.add(childnode);
		
//		System.out.println(parentnode.getGuid());
//		System.out.println(grandchildnode.getParentguids());

		
		PedigreeTree tree = new PedigreeTree(parentnode);
//		
		tree.printPedigreeTree();
		List<PedigreeNode> idk = childnode.getPedigreeChildren();
		
		System.out.println(childnode.getPedigreeParent());
				
//		extractList = siggy.signatureExtractionDirectory("/home/frostpmr/Downloads/small_demo/");
//		write.useByfferedFileWriter(extractList, "/home/frostpmr/BrainDump/filename.txt");
//		
//		
		
	}

}
