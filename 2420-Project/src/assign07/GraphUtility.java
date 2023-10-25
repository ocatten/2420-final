package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import assignment07.Vertex;

/**
 * Contains several methods for solving problems on generic, directed, unweighted, sparse graphs.
 * 
 * @author Prof. Parker, Everett Oglesby and Parker Catten
 * @version October 19, 2023
 */
public class GraphUtility {

	/*
	 * Performs a depth-first search algorithm on a graph created from the list of sources and destinations to determine 
	 * if there is a path from the srcData to dstData in the graph. The Depth-First algorithm searches recursively to find
	 * a route, if any, by first iterating all the way down to the last level of a graph and back tracking and going down 
	 * different branches until a solution is found. Throws IllegalArgumentException if there is no vertex in the 
	 * graph with scrData or dstData.
	 * 
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		
		boolean found = depthFirstSearch(srcData,dstData);
		
		/*
		 * oreach vertex vi do
vi.distance_from_s ← ∞
s.distance_from_s ← 0
depthFirstSearch(s)
depthFirstSearch(vertex x):
foreach edge e from x do
vertex w ← e.destination
if w.distance_from_s = ∞ do
w.distance_from_s = x.distance_from_start + e.weight
w.previous = x
depthFirstSearch(w)

		 */
		
		
		return false;
	}

	private <Type> boolean depthFirstSearch(Type curr, Vertex<Type> dest) {
		for(Edge e: curr.getAdjacent()) {
			Vertex oppositeVertex = e.getDestination();

			if(!oppositeVertex.getVisited()) {
				if(oppositeVertex.getData().equals(dest.getData())){
					return true;
				}
				depthFirstSearch(oppositeVertex,dest);
			}
		}
		return false;
	}
	
	
	
	
	/*
	 * Finds the shortest path from the vertex scrData to the vertex dstData in a graph created from the sources and destinations by
	 * using a Breadth-First Search algorithm to iterate through the graph. Throws IllegalArgumentException if there is no vertex in the 
	 * graph with scrData or dstData.
	 * 
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		
		Vertex<Type> startVertex = null;
		
		//First find the vertex with srcData, if no vertex matches then throw Illegal argument exception
		for(Type source: sources) {
			//Check if the current source matches the source expected in the list.
			if(source.equals(srcData)) {
				startVertex = Vertex<Type>
			}
		}
		
		//Throw exception if no source found
		if(startVertex != null) {
			throw new IllegalArgumentException("Source could not be found!");
		}
		
		//Creates a queue to store the next vertices to sort through
		Queue<Vertex<Type> > queue = new LinkedList<Vertex<Type> >();
		
		//Add the first vertex to the queue.
		queue.add(startVertex);
		
		
		while(!queue.isEmpty()) {
			Vertex<Type> currVertex = queue.remove();
			for(Edge<Type> e: currVertex.getAdjacent()) {
				Vertex<Type> other = e.getDestination();
				other.d
			}
		}
		
		return null;
	}
	
	
	private void breadthFirstSearch(Vertex<Type> curr) {
		
		
		
		
	}
	
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		// FILL IN + ADD METHOD COMMENT
		return null;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
}