/**
 * This is the connection class that creates connection between the edges and the source node
 * @author Sujan
 * last updated- 02/23/2016
 */

import java.util.ArrayList;

public class Connection {

	Node sourceNode;
	ArrayList<Edge> connections;
	
	/**
	 * This is a constructor
	 * @param sn
	 */
	public Connection(Node sn)
	{
		sourceNode = sn;
		connections = new ArrayList<Edge>();

	}
	/**
	 * This method is used to addconnection from the given input
	 * @param desNode
	 * @param w
	 */
	public void addConnection(Node desNode, double w)
	{
		connections.add(new Edge(desNode, w));

	}
	/**
	 * This is a toString method that returns connections
	 */
	public String toString()
	{
		String info = sourceNode.toString();
		for(int x = 0; x < connections.size(); x++)
		{
			info += connections.get(x).toString() + " ";
		}
		info = info.substring(0, info.length());
		return info;

	}

}
