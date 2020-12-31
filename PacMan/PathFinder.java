package assignment10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Alejandro Rubio
 * @author Alejandro Serrano
 * 
 *         PathFinder class will solve the given Pac-Man puzzle
 *
 */
public class PathFinder {
	/**
	 * Takes in an input file which contains the maze to be solved The file will be
	 * put in a graph data structure Finally creating an output file with the
	 * solution of the puzzle Returns the path cost of the solution
	 * 
	 * @param inputFileName  puzzle to be solved
	 * @param outputFileName file that will contain solved puzzle
	 * @return path cost of the solution
	 */
	public static int solveMaze(String inputFileName, String outputFileName) {
		// Scanner to read in the given puzzle txt
		File file = new File(inputFileName);
		Scanner getWords = null;
		try {
			getWords = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// We get the necessary dimensions to create
		// the graph with the correct rows and columns
		int h = getWords.nextInt();
		int w = getWords.nextInt();
		int startX = 0;
		int startY = 0;
		getWords.nextLine();
		String line = "";
		Graph graph = new Graph(h, w);
		// We create the nodes with each value of the txt
		// We insert them at each position of the adjMatrix
		for (int i = 0; i < h; i++) {
			line = getWords.nextLine();
			for (int j = 0; j < w; j++) {
				Node node = new Node(line.charAt(j));
				graph.setNode(node, i, j);
				if (line.charAt(j) == 'S') {
					startX = i;
					startY = j;
				}
			}
		}
		graph.setNeighbor(h, w);
		// We calculate the the cost for the path
		int pathCost = graph.bfs(graph.getNode(startX, startY));
		graph.writeFile(outputFileName);
		if (pathCost == 0) {
			return -1;
		}
		return pathCost + 1;
	}
}