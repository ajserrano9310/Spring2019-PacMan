package assignment10;

import java.util.LinkedList;
import java.util.Queue;
import components.simplewriter.SimpleWriter1L;

/**
 * 
 * @author Alejandro Rubio
 * @author Alejandro Serrano
 * 
 *         Graph class to implement solutions to Pac-Man Game
 *
 */
public class Graph {
	/**
	 * 2D Array that will represent the graph data structure
	 */
	private Node[][] matrix;

	/**
	 * Sets the size that the graph data structure will have
	 * 
	 * @param row the value of the row
	 * @param col the value of the col
	 */
	private void resize(int row, int col) {
		matrix = new Node[row][col];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				matrix[i][j] = null;
	}

	/**
	 * Constructor based on the row and cols from the puzzle
	 * 
	 * @param row amount of rows the graph will have
	 * @param col amount of columns the graph will have
	 */
	public Graph(int row, int col) {
		resize(row, col);
	}

	/**
	 * Sets the node at a x and y position inside the graph
	 * 
	 * @param label node to be placed in the matrix
	 * @param row   row where the value will be placed
	 * @param col   col where the value will be placed
	 */
	public void setNode(Node label, int row, int col) {
		this.matrix[row][col] = label;
	}

	/**
	 * Sets the neighbors of the node from N, S, E and W coordinates
	 * 
	 * @param row row where the node will be
	 * @param col col where the node will be
	 */
	public void setNeighbor(int row, int col) {
		// We go trough every node on the puzzle and set its neighbors
		// Neighbors can only be S G or Blank Space. If its X we ignore it.
		for (int x = 1; x < row; x++) {
			for (int y = 1; y < col; y++) {
				if (this.matrix[x][y].getState() != 'X') {
					if (this.matrix[x - 1][y].getState() != 'X') {
						this.matrix[x][y].addNeighbour(matrix[x - 1][y]);
					}
					if (this.matrix[x + 1][y].getState() != 'X') {
						this.matrix[x][y].addNeighbour(matrix[x + 1][y]);
					}
					if (this.matrix[x][y - 1].getState() != 'X') {
						this.matrix[x][y].addNeighbour(matrix[x][y - 1]);
					}
					if (this.matrix[x][y + 1].getState() != 'X') {
						this.matrix[x][y].addNeighbour(matrix[x][y + 1]);
					}
				}
			}
		}
	}

	/**
	 * Returns the node from and X and Y position from the graph Matrix
	 * 
	 * @param row where the Node will be
	 * @param col where the Node will be
	 * @return the node at the position given by row and col
	 */
	public Node getNode(int row, int col) {
		return this.matrix[row][col];
	}

	/**
	 * Traverses the graph visiting each node. Stops when finds the node with a 'G'
	 * state.
	 * 
	 * @param src the source node of the traversal, 'S' in this case
	 * @return returns the path cost of the traversal
	 */
	public int bfs(Node src) {
		Queue<Node> q = new LinkedList<>();
		q.add(src);
		src.setVisited(true);
		int count = 0;
		while (!q.isEmpty()) {
			Node v = q.remove();
			for (int i = 0; i < v.neighbours.size(); i++) {
				if (v.neighbours.get(i).getVisited() == false) {
					v.neighbours.get(i).setVisited(true);
					v.neighbours.get(i).setCameFrom(v);
					q.add(v.neighbours.get(i));
					// If we find a neighbor with the letter G we found the sortest path from S to G
					// We draw the path and stop the bfs algorithm by returning the number of nodes
					if (v.neighbours.get(i).getState() == 'G') {
						Node g = v.neighbours.get(i);
						count = this.drawPath(g);
						return count;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Helper method. Goes from G to S changing the spaces into dots to mark the
	 * shortest path to Goal. It's not used unless the last node contains 'G'
	 * 
	 * @param end the last visited node of the bfs method
	 * @return the count of the amount of changed spaces
	 */
	private int drawPath(Node end) {
		int count = 0;
		// Start from the last node found and go to the first one while changing the
		// character in each node to a dot
		while (end.getState() != 'S') {
			if (end.getState() == ' ') {
				end.setState('.');
				count++;
			}
			end = end.getCameFrom();
		}
		return count;
	}

	/**
	 * Creates and prints a new file that will contain the answer to the puzzle
	 * 
	 * @param file the file to be overwritten
	 */
	public void writeFile(String file) {
		String str = "";
		int rows = this.matrix.length;
		int col = this.matrix[0].length;
		str = str + rows + " " + col + "\n";
		// Get the state (character) of every position and add it to the output string
		for (int a = 0; a < rows; a++) {
			for (int b = 0; b < col; b++) {
				str = str + matrix[a][b].getState();
			}
			str = str + "\n";
		}
		// Write the string to the file with SimpleWriter
		SimpleWriter1L a = new SimpleWriter1L(file);
		a.print(str);
		a.close();
	}
}