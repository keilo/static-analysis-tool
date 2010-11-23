import java.util.LinkedList;
import java.util.ListIterator;

// Tree node

 /// bah cba

public class Node {

	private Node parent;
	private LinkedList<Node> successors;
	//private successorsIterator;
	
	public void setParent(Node newParent)
	{
		this.parent = newParent;
	}
	
	public Node getParent()
	{
		return this.parent;
	}
	
	public void addSuccessor(Node node)
	{
		this.successors.add(node);
	}
	
	public Node getNextSuccessor()
	{
		return null;
	}
	
	
	
	// Add more methods required by TreeParser
	
}
