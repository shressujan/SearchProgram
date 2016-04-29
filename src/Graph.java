/**
 * Purpose: This class creates an Adjacency list connecting the sourceNodes with the edges
 * This class parses the file and store the sourceNode, destinationNode and the distances in arraylist
 * Creates a conncetion between the sourceNode, destinationNode and the distance
 * @author Sujan
 * Last Updated : 02/24/2016
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

	ArrayList<Node> nodes;
	ArrayList<Connection> adjacencyList;
	/**
	 * This is the constructor for this class
	 */
	public Graph()
	{
		nodes = new ArrayList<Node>();
		adjacencyList = new ArrayList<Connection>();
	}
	/**
	 * This method loads parses the file that is passed as an input
	 * Create an arraylist of sourceNodes
	 * Attaches it to adjList
	 * create and arraylist of connections
	 * attaches it to adjList
	 * @param fileName
	 */
	public void loadFromFile(String fileName)
	{
		File file = new File(fileName);

		try
		{
			Scanner fileScan = new Scanner(file);
			int numberNodes = fileScan.nextInt();

			while(fileScan.hasNext())
			{
				String sourceName = fileScan.next();
				String desName = fileScan.next();
				double weight = fileScan.nextDouble();

				//check list of nodes in graph to see if source is there

				if(isInList(sourceName))
				{
					int index = getIndex(sourceName);
					Node desNode = new Node(desName);
					adjacencyList.get(index).addConnection(desNode, weight);


				}
				else
				{
					Node sourceNode = new Node(sourceName);
					nodes.add(sourceNode);
					Connection nConnection = new Connection(sourceNode);
					Node desNode = new Node(desName);
					nConnection.addConnection(desNode, weight);
					adjacencyList.add(nConnection);
				}

				if(isInList(desName))
				{
					int index2 = getIndex(desName);
					Node desNodeFlip = new Node(sourceName);
					adjacencyList.get(index2).addConnection(desNodeFlip, weight);
				}
				else
				{				

					Node sourceNodeFlip = new Node(desName);
					nodes.add(sourceNodeFlip);
					Connection nConnectionFlip = new Connection(sourceNodeFlip);
					Node desNodeFlip = new Node(sourceName);
					nConnectionFlip.addConnection(desNodeFlip, weight);
					adjacencyList.add(nConnectionFlip);


				}
				//System.out.println(this.toString() + "\n");
			}

		}

		catch(IOException e)
		{
			System.err.printf("exception thrown from laod from file:" + e.toString());
		}
	}
	/**
	 * This method checks whether the given node is in the node list or not
	 * @param label
	 * @return
	 */
	public boolean isInList(String label)
	{
		for(int x = 0; x < nodes.size(); x++)
		{
			Node tempNode = nodes.get(x);
			if(tempNode.label.equals(label))
			{
				return true;
			}
		}

		return false;

	}

	/**
	 * This method gets the index of the input nodes from the node list
	 * @param label
	 * @return
	 */
	public int getIndex(String label)
	{
		for(int x = 0; x < nodes.size(); x++)
		{
			Node tempNode = nodes.get(x);
			if(tempNode.label.equals(label))
			{
				return x;
			}
		}

		return -1;
		//search list of nodes for label and return index
	}
	/**
	 * This is a toString method that print out every connections
	 */
	public String toString()
	{
		String info = "Graph:\n";
		for(int x = 0; x < nodes.size(); x++)
		{
			info += adjacencyList.get(x).toString() + "\n";

		}
		return info;

	}
	/**
	 * This method returns the adjacencyList
	 * @return adjacencyList
	 */
	public ArrayList<Connection> getAdjacencyList() {
		return adjacencyList;
	}
}
