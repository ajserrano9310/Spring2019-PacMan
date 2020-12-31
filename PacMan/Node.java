package assignment10;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alejandro Rubio
 * @author Alejandro Serrano
 * 
 *         Node class that will hold each individual piece of the given puzzle
 *
 */
public class Node {
	/**
	 * Holds whether the value is space, 'S', 'X' or 'G'
	 */
	private char state;
	/**
	 * Holds true or false if the node has been visited
	 */
	private boolean visited;
	/**
	 * Holds the reference of the previous node
	 */
	private Node cameFrom;
	/**
	 * Holds the list of neighbors for this node
	 */
	public List<Node> neighbours;

	/**
	 * Constructor with parameter. Takes in the state of the value
	 * 
	 * @param state can be either space, 'S', 'G' or 'X'
	 */
	public Node(char state) {
		this.state = state;
		this.visited = false;
		this.cameFrom = null;
		this.neighbours = new ArrayList<Node>();
	}

	/**
	 * Gets the current state of the node
	 * 
	 * @return the state of the node
	 */
	public char getState() {
		return this.state;
	}

	/**
	 * Informs whether the node has been visited or not
	 * 
	 * @return true or false if the Node has been visited
	 */
	public boolean getVisited() {
		return this.visited;
	}

	/**
	 * Gets the previous Node
	 * 
	 * @return the previous Node from where this came from
	 */
	public Node getCameFrom() {
		return this.cameFrom;
	}

	/**
	 * Sets the state of the node
	 * 
	 * @param state new state for the node
	 */
	public void setState(char state) {
		this.state = state;
	}

	/**
	 * Sets true if the node has been visited
	 * 
	 * @param visited true or false
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Sets the previous node where this came from
	 * 
	 * @param cameFrom previous node
	 */
	public void setCameFrom(Node cameFrom) {
		this.cameFrom = cameFrom;
	}

	/**
	 * Adds the corresponding neighbor of the Node
	 * 
	 * @param neighbour node to be added to the list
	 */
	public void addNeighbour(Node neighbour) {
		this.neighbours.add(neighbour);
	}

	/**
	 * Checks to see if the neighbor is already in list
	 * 
	 * @param neighbour neighbor to be checked
	 * @return
	 */
	public boolean containsNeighbour(Node neighbour) {
		return this.neighbours.contains(neighbour);
	}
}