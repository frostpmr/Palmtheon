
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;




public class PedigreeNode implements Pedigree {

	private List children;
	
	private PedigreeNode parent;
    
	private PedigreeRecord prod;
	
	//Constructor to set Parent Node w/o Children and File
	public PedigreeNode(PedigreeNode parent) {
	    this(parent, null);
	  }
	//Constructor to set Parent w/ File
	public PedigreeNode(PedigreeNode parent, PedigreeRecord prod) {
	    this(parent, new Vector(), prod);
	  }
	//Construct to set Parent node with List of Children and File
	public PedigreeNode(PedigreeNode parent,List children,PedigreeRecord prod){
		   this.parent = parent;
		   if (this.parent != null) {
			      this.parent.addChild(this);
			    }
		   this.children = children;
		   this.prod = prod;
	}
	
	public List getPedigreeChildren() {
		return children;
	}
	
	public void addChild(Object child) {
		this.children.add(child);
	}


	public void setParent(PedigreeNode parent) {
		this.parent = parent;
	}
	
	public PedigreeNode getPedigreeParent(){
		return parent;
	}
	
	public int getNumPedigreeChildren(){
		if (this.children == null)
			return 0;
		return children.size();
	}
	//returns the File at that node
	public PedigreeRecord getPedigreeRecord(){
		return this.prod;
	}
	
	public PedigreeNode getChildAt(int idx){
		return (PedigreeNode) this.children.get(idx);
	}
	
	}

