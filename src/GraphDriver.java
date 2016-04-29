/**
 * This class contains the main method
 * Purpose: In this class JFrame is created, files are loaded, and BFS/DFS search is performed
 * @author Sujan
 * Last updated: 02/24/2016
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class GraphDriver extends JFrame {

	private String startingVillage;
	private String destinationVillage;
	private Connection mConnections;
	private ArrayList <Connection> adjList;
	private Graph graph;
	private Node parentNode;
	private int index = 0;
	private ArrayList <Node> visitedNodes;
	private ArrayList <Node> frontier;

	private String title = "Search-Assignment";
	private int height = 400;
	private int width = 400;
	private JMenuBar bar;
	private JMenuBar menuBar;

	private JMenu action;
	private JMenu search;

	private JMenuItem loadFileA;
	private JMenuItem loadFileB;
	private JMenuItem exit;

	private JMenuItem runDepthFirstOnTwoVillages;
	private JMenuItem runBreadthFirstOnTwoVillages;
	private JMenuItem distanceMatrix;

	private String village;
	private String holdFrontier;
	private String info = "";


	/**
	 * This is the constructor of this class
	 */
	public GraphDriver()
	{
		JOptionPane.showMessageDialog(null, "This program will ask user first to choose the village to search,"+
				"\n Ask the user to \"Enter Starting Village\" "+
				"\n Ask the user to \"Enter DestinationVillage\""+
				"\n Then Ask the user to choose desired search method (BFS or DFS)"
				+ "\n Gives the user option to display the distance matrix ", " Welcome", 1);
		this.setTitle(title);
		this.setSize(height, width);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		JMenuBar menuBar = buildMenuBar();
		this.setJMenuBar(menuBar);
		this.setVisible(true);

	}

	/**
	 * Method to create MenuBar
	 */
	public JMenuBar buildMenuBar()
	{
		menuBar = new JMenuBar();

		action = buildActionMenu();
		search = buildSearchMenu();

		action.setEnabled(true);
		action.setEnabled(true);

		menuBar.add(action);
		menuBar.add(search);
		return menuBar;
	}

	/**
	 * Method to create the action menu
	 */
	public JMenu buildActionMenu()
	{
		action = new JMenu("Action");
		action.setEnabled(true);
		action.setForeground(Color.blue);

		loadFileA = new JMenuItem("Load VILLAGE_MAP");
		loadFileA.setEnabled(true);
		loadFileA.setForeground(Color.blue);

		loadFileB = new JMenuItem("Load VILLAGE_MAP_2");
		loadFileB.setEnabled(true);
		loadFileB.setForeground(Color.blue);

		exit = new JMenuItem("Exit");
		exit.setEnabled(true);
		exit.setForeground(Color.blue);


		//Adding action listeners to all the options
		MyListener listener =  new MyListener();
		loadFileA.addActionListener(listener);
		loadFileB.addActionListener(listener);
		exit.addActionListener(listener);

		action.add(loadFileA);
		action.add(loadFileB);
		action.add(exit);

		return action;

	}

	/**
	 * Method to create the search menu
	 * @return
	 */
	public JMenu buildSearchMenu()
	{
		search = new JMenu("Search");
		search.setEnabled(false);
		search.setForeground(Color.blue);

		runDepthFirstOnTwoVillages = new JMenuItem("Run-Depth-First-On-Two-Villages");
		runDepthFirstOnTwoVillages.setEnabled(true);
		runDepthFirstOnTwoVillages.setForeground(Color.blue);

		runBreadthFirstOnTwoVillages = new JMenuItem("Run-Breadth-First-On-Two-Villages");
		runBreadthFirstOnTwoVillages.setEnabled(true);
		runBreadthFirstOnTwoVillages.setForeground(Color.blue);

		distanceMatrix = new JMenuItem ("Disply-Distance-matrix-from-Graph");
		distanceMatrix.setEnabled(true);
		distanceMatrix.setForeground(Color.BLUE);

		//Adding action listener to all the options
		
		MyListener listener = new MyListener();
		runDepthFirstOnTwoVillages.addActionListener(listener);
		runBreadthFirstOnTwoVillages.addActionListener(listener);
		distanceMatrix.addActionListener(listener);

		search.add(runDepthFirstOnTwoVillages);
		search.add(runBreadthFirstOnTwoVillages);
		search.add(distanceMatrix);

		return search;
	}

	/**
	 * This is a action listener type class that provides different actions to all the listeners
	 */
	public class MyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()== exit)
			{
				System.exit(0);
			}
			if(e.getSource()== loadFileA)
			{
				graph = new Graph();
				graph.loadFromFile("VILLAGE_MAP.csv");
				adjList = graph.getAdjacencyList();
				search.setEnabled(true);
				village = "VILLAGE_MAP";
				startingVillage = JOptionPane.showInputDialog(null,"Enter  the Starting Village Node:");
				destinationVillage = JOptionPane.showInputDialog(null, "Enter the Ending Village Node:");

			}
			if(e.getSource()== loadFileB)
			{
				graph = new Graph();
				graph.loadFromFile("VILLAGE_MAP_2.csv");
				adjList = graph.getAdjacencyList();
				village = "VILLAGE_MAP_2";
				search.setEnabled(true);

				startingVillage = JOptionPane.showInputDialog(null,"Enter  the Starting Village Node:");
				destinationVillage = JOptionPane.showInputDialog(null, "Enter the Ending Village Node:");

			}
			if(e.getSource()== runDepthFirstOnTwoVillages)
			{
				DFS(startingVillage, destinationVillage, adjList);
			}
			if(e.getSource()== runBreadthFirstOnTwoVillages)
			{
				BFS(startingVillage, destinationVillage, adjList);
			}
			if(e.getSource()== distanceMatrix)
			{
				JOptionPane.showMessageDialog(null, village+":\n" + graph.toString()+"\n\n");
			}
		}
	}
	/**
	 * This is the main method of the class
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Call to the constructor

		new GraphDriver();
	}
	/**
	 * This method performs the depth first search
	 * @param startingVillage
	 * @param destinationVillage
	 * @param adjList
	 */
	public void DFS(String startingVillage, String destinationVillage, ArrayList <Connection>adjList)
	{

		Node rootNode = new Node(startingVillage);
		Node goalNode =  new Node(destinationVillage);

		if(this.startingVillage.equals(destinationVillage))
		{
			JOptionPane.showMessageDialog(null, "Goal Node Found at 0 depth\n"+ startingVillage);
		}
		frontier = new ArrayList<Node>();
		frontier.add(rootNode);
		visitedNodes = new ArrayList<Node>();

		//Adding the root node to the frontier

		while(!frontier.isEmpty())
		{
			holdFrontier = "Frontier: "+frontier+"\n";
			Node  current = frontier.remove(frontier.size()-1);
			if(current.label.equals(goalNode.label ))
			{
				info +=("Goal node \""+goalNode.label+"\" found");
				JOptionPane.showMessageDialog(null, info);
				break;
			}
			else
			{
				//Checking if the current node has already been explored or not!
				if(!isInList(current.label))
				{
					visitedNodes.add(current);
					info +=("Current: "+current+"\n");
					info += holdFrontier;
					info +=("Visited Nodes:" + visitedNodes+"\n");
					for( int i = 0; i< adjList.size(); i++)
					{
						if(adjList.get(i).sourceNode.label.equals(current.label))
						{
							for( int j = 0 ; j< adjList.get(i).connections.size(); j++)
							{
								if(!isInList(adjList.get(i).connections.get(j).desNode.label))
								{
									if(!isInListFrontier(adjList.get(i).connections.get(j).desNode.label))
									{
										frontier.add(adjList.get(i).connections.get(j).desNode);
									}
								}
							}
							break;
						}
					}	
				}
			}
		}
	}
	/**
	 * This method performs the breadth first search
	 * @param startingVillage
	 * @param destinationVillage
	 * @param adjList
	 */
	public void BFS(String startingVillage, String destinationVillage, ArrayList <Connection>adjList)
	{

		Node rootNode = new Node(startingVillage);
		Node goalNode =  new Node(destinationVillage);

		if(this.startingVillage.equals(destinationVillage))
		{
			JOptionPane.showMessageDialog(null, "Goal Node Found at 0 depth\n"+ startingVillage);
		}
		frontier = new ArrayList<Node>();
		frontier.add(rootNode);
		visitedNodes = new ArrayList<Node>();

		//Adding the root node to the stack
		frontier.add(rootNode);

		while(!frontier.isEmpty())
		{
			holdFrontier = "Frontier \n"+frontier+"\n";
			Node  current = frontier.remove(0);

			if(current.label.equals(goalNode.label ))
			{
				info +=("Goal node \""+goalNode.label+"\" found");
				JOptionPane.showMessageDialog(null, info);
				break;

			}
			else
			{
				//Checking if the current node has already been explored or not!
				if(!isInList(current.label))
				{
					visitedNodes.add(current);
					info +=("Current: "+current+"\n");
					info += holdFrontier;
					info +=("Visited Nodes\n" + visitedNodes+"\n");
					for( int i = 0; i< adjList.size(); i++)
					{
						if(adjList.get(i).sourceNode.label.equals(current.label))
						{
							for( int j = 0 ; j< adjList.get(i).connections.size(); j++)
							{
								if(!isInList(adjList.get(i).connections.get(j).desNode.label))
								{
									if(!isInListFrontier(adjList.get(i).connections.get(j).desNode.label))
									{
										frontier.add(adjList.get(i).connections.get(j).desNode);
									}
								}

							}
							break;

						}
					}	
				}
			}
		}
	}
	/**
	 * Checks if the input node is in the list of nodes
	 * @param label
	 * @return boolean value
	 */
	public boolean isInList(String label)
	{
		for(int x = 0; x < visitedNodes.size(); x++)
		{
			Node tempNode = visitedNodes.get(x);
			if(tempNode.label.equals(label))
			{
				return true;
			}
		}

		return false;

	}

	/**
	 * Checks if the input node is in the list of Frontier nodes
	 * @param label
	 * @return boolean value
	 */
	public boolean isInListFrontier(String label)
	{
		for(int x = 0; x < frontier.size(); x++)
		{
			Node tempNode = frontier.get(x);
			if(tempNode.label.equals(label))
			{
				return true;
			}
		}

		return false;

	}

}
