

public class PedigreeTree {

	private PedigreeNode root;
	
	public PedigreeTree(PedigreeNode root){
		this.root = root;
	}
	
	public int getNumLevels() {
		if (root != null) {
            return traverse(root, 0) + 1;
        } else
            return 0;
	}
	
	public PedigreeNode getRoot() {
        return root;
    }
	
	 public void setRoot(PedigreeNode root) {
	        this.root = root;
	    }

	 //Tabs each new level
	 private String getTabStr(int num) {
		 StringBuffer tabStrBuf = new StringBuffer();
		 for (int i = 0; i < num; i++) {
			 tabStrBuf.append("\t");
	     }

	        return tabStrBuf.toString();
	 }
	 //traverses each node and prints the Filename of the respective file for that pedigree node
	 public void traverseAndPrint(PedigreeNode node, int level){
		 System.out.println(getTabStr(level) + node.getPedigreeRecord().getFilename());
		 if (node.getNumPedigreeChildren() > 0){
			 for (int i = 0; i< node.getNumPedigreeChildren(); i++){
				 traverseAndPrint(node.getChildAt(i), level + 1);
			 }
		 }
		   
	 }
	 //iterates through the nodes using the number of children to determine the level
	  private int traverse(PedigreeNode root, int level) {
	        int maxLevel = level;
	        if (root.getNumPedigreeChildren() > 0) {
	            for (int i = 0; i < root.getNumPedigreeChildren(); i++) {
	                int lvl = traverse(root.getChildAt(i), level + 1);
	                if (lvl > maxLevel) {
	                    maxLevel = lvl;
	                }
	            }
	        }

	        return maxLevel;
	    }
	  //print the entire tree.
	    public void printPedigreeTree() {
	        PedigreeNode root = getRoot();
	        traverseAndPrint(root, 0);
	    }
	 
}	
