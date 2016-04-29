/**
 * This is the Node class 
 * Purpose- To take in the nodes and pass them back
 * @author Sujan
 *
 */
public class Node {
	
	String label;
	/**
	 * This is the Node constructor 
	 * @param l
	 */
	
	public Node(String l)
	{
		label = l;
	}
	/**
	 * This is a toString method that returns the nodes
	 */
	public String toString()
	{
		return label;
	}

}