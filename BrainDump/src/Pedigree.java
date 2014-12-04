import java.util.List;



public interface Pedigree {
	
	public int getNumPedigreeChildren();
	
	public List getPedigreeChildren();
	 
	public PedigreeNode getPedigreeParent();
	
	public PedigreeRecord getPedigreeRecord();

}
