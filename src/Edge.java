/**
 * This is the Edge class which holds the edges(Attached Nodes and distance)
 * @author Sujan
 * Last update: 02/23/2016
 *
 */
public class Edge {

	Node desNode;
	double weight;

	/**
	 * This is a constructor
	 * @param n
	 * @param w
	 */
	public Edge(Node n, double w)
	{
		desNode = n;
		weight = w;
	}

	/**
	 * This is a toString method
	 * returns- destination Node and the weights
	 */
	public String toString()
	{
		return desNode.toString() +": " + weight;
	}
	/**
	 * This is a getter for getting Destination Node
	 * @return desNode
	 */
	public Node getDesNode() {
		return desNode;
	}
	/**
	 * This is a setter for setting the Destination Node
	 * @param desNode
	 */
	public void setDesNode(Node desNode) {
		this.desNode = desNode;
	}
	/**
	 * This is a getter for getting the Weights
	 * @return getWeight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * This is a setter for setting the weights
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}


}
