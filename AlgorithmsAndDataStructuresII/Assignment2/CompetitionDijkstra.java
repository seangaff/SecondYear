/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * edges that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class CompetitionDijkstra {
    String filename;
	int sA = 0;
	int sB = 0;
	int sC = 0;
	int vertices = 0;
	int edges = 0;
	boolean valid = true;
	ArrayList<String> graphString = null;
	double[][] graph = null;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */

    CompetitionDijkstra (String filename, int sA, int sB, int sC){
        this.filename = filename;
		this.sA = sA;
		this.sB = sB;
		this.sC = sC;
		this.vertices = 0;
		this.edges = 0;
		this.graphString = new ArrayList<String>();
		parseFile(fileScanner(filename));
    }

    private Scanner fileScanner(String fileName) {
		try {
			Scanner fileScan = new Scanner(new File(fileName));
			return fileScan;
		} 
        catch (Exception e) {
			valid = false;
			return null;
		}
	}

    private void parseFile(Scanner scannedFile) {
		if (valid) {
			graphString.clear();
			try {
				if (scannedFile.hasNextInt()) {
					this.vertices = scannedFile.nextInt();
				}
				if (scannedFile.hasNextInt()) {
					this.edges = scannedFile.nextInt();
					scannedFile.nextLine();
				}
				while (scannedFile.hasNextLine()) {
					graphString.add(scannedFile.nextLine());
				}
			} 
            catch (Exception e) {
			}
		}
	}

    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */

    public int timeRequiredforCompetition(){
        if (!valid) {
			return -1;
		}
		if (this.vertices == 0) {
			return -1;
		}
		if ((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50)) {
			return -1;
		}
		int slowest = 0;
		if (this.sA < this.sB && this.sA < this.sC)
			slowest = sA;
		else if (this.sB < this.sA && this.sB < this.sC)
			slowest = this.sB;
		else
			slowest = this.sC;
		double[][] dist = new double[this.vertices][this.vertices];
		for (int i = 0; i < this.vertices; i++) {
			for (int j = 0; j < this.vertices; j++) {
				dist[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		graph = createGraph();
		double maxDistance = dijkstra();
		if (maxDistance == -1)
			return -1;
		else
			return (int) ((Math.ceil((maxDistance * 1000) / slowest)));
    }

    private double[][] createGraph() {
		double[][] graph = new double[this.vertices][this.vertices];
		for (int i = 0; i < this.vertices; i++) {
			for (int j = 0; j < this.vertices; j++) {
				graph[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		for (int i = 0; i < this.vertices; i++) {
			graph[i][i] = 0;
		}
		for (int i = 0; (i < this.graphString.size()); i++) {
			Scanner lineReader = new Scanner(this.graphString.get(i));
			int edge = lineReader.nextInt();
			int connectingEdge = lineReader.nextInt();
			double distance = lineReader.nextDouble();

			graph[edge][connectingEdge] = distance;
			lineReader.close();
		}
		return graph;
	}

    private double dijkstra() {
		double currentMaxDist = 0;
		for (int i = 0; i < this.vertices; i++) {
			int queueSize = 1;
			double[] dist = new double[this.vertices];
			boolean[] marked = new boolean[this.vertices];
			boolean[] reached = new boolean[this.vertices];
			for (int j = 0; j < this.vertices; j++) {
				dist[j] = Double.POSITIVE_INFINITY;
				marked[j] = false;
				reached[j] = false;
			}
			dist[i] = 0;
			reached[i] = true;
			while (queueSize > 0) {
				int indexOfShortest = getShortestPath(dist, marked);
				for (int j = 0; j < vertices; j++) {
					if (((graph[indexOfShortest][j] + dist[indexOfShortest]) < dist[j]) && (!marked[j])) {
						dist[j] = (graph[indexOfShortest][j] + dist[indexOfShortest]);
						queueSize++;
						reached[j] = true;
					}
				}
				marked[indexOfShortest] = true;
				queueSize--;
			}
			double max = getHighestValue(dist);
			if (max == Double.POSITIVE_INFINITY) {
				return -1;
			}
			if (max > currentMaxDist) {
				currentMaxDist = max;
			}
		}
		return currentMaxDist;
	}

    private double getHighestValue(double[] dist) {
		double highest = 0;
		for (int i = 0; i < dist.length; i++)
			highest = (dist[i] > highest) ? dist[i] : highest;
		return highest;
	}

    private int getShortestPath(double[] dist, boolean[] marked) {
		int shortest = 0;
		for (int i = 1; i < dist.length; i++)
			if ((dist[i] < dist[shortest] && !marked[i]) || marked[shortest]) {
				shortest = i;
			}
		return shortest;
	}
}
