package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;


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
		//Throw exception if the number of sources doesn't match the number of destinations.
		if(sources.size() != destinations.size()){
			throw new IllegalArgumentException("The length of the list of the sources doesn't match the length of the list of destinations!");
		}
		
		//Creates a graph from the lists of sources and destinations.
		Graph<Type> graph = new Graph<Type>();
		
		//Creates the edges between sources[i] and destinations[i]
		for(int i = 0; i < destinations.size();i++) {
			graph.addEdge( sources.get(i), destinations.get(i) );
		}
		
		//Create an empty tracker for the start vertex and search the graph for the starting vertex
		Vertex<Type> startVertex = null;
		startVertex = graph.getVertex(srcData);
		
		
		//Throw exception if no source found
		if(startVertex == null) {
			throw new IllegalArgumentException("Source could not be found!");
		}
		
		//Calls depth first search to recursively iterate through the list to find if the srcData and the dstData are connected.
		boolean found = depthFirstSearch(startVertex, graph.getVertex(dstData));
		

		
		
		return found;
	}

	private static <Type> boolean depthFirstSearch(Vertex<Type> curr, Vertex<Type> dest) {
		
		//Search each edge of the graph, recursively traveling down to the end of the branch.
		for(Edge e: curr.getAdjacent()) {
			//Get the opposite vertex of the edge.
			Vertex oppositeVertex = e.getDestination();

			//Check if it has been visited yet.
			if(!oppositeVertex.getVisited()) {
				//If not, check if its data matches the destination data
				if(oppositeVertex.getData().equals(dest.getData())){
					return true;
				}
				//If there isn't a match then call depth first search on the opposite edge.
				return depthFirstSearch(oppositeVertex,dest);
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
		
		//Throw exception if the number of sources doesn't match the number of destinations.
		if(sources.size() != destinations.size()){
			throw new IllegalArgumentException("The length of the list of the sources doesn't match the length of the list of destinations!");
		}
		
		//Creates a graph from the lists of sources and destinations.
		Graph<Type> graph = new Graph<Type>();
		
		//Creates the edges between sources[i] and destinations[i]
		for(int i = 0; i < destinations.size();i++) {
			graph.addEdge( sources.get(i), destinations.get(i) );
		}
		
		
		//Create an empty tracker for the start vertex and search the graph for the starting vertex
		Vertex<Type> startVertex = null;
		startVertex = graph.getVertex(srcData);
		
		
		//Throw exception if no source found
		if(startVertex == null) {
			throw new IllegalArgumentException("Source could not be found!");
		}
		
		//Creates a queue to store the next vertices to sort through
		Queue<Vertex<Type> > queue = new LinkedList<Vertex<Type> >();
		
		//Add the first vertex to the queue.
		queue.add(startVertex);
		
		//List 
		
		//Loops through each vertex to find the shortest path
		while(!queue.isEmpty()) {
			//Starts its search at the next vertex of the queue
			Vertex<Type> currVertex = queue.remove();
			
			//Find each of the vertices that share edges with the current vertex
			for(Edge<Type> e: currVertex.getAdjacent()) {
				Vertex<Type> other = e.getDestination();
				
				
				if(!other.getVisited()) {//Assures that the same vertex isn't checked twice
					other.setVisited(true);
					
					//Increase the distance from start of the other vertex by 1.
					other.addDistance(1);
					
					//Sets the other's previous vertex to the current vertex and adds it next to the queue.
					other.setPrevious(currVertex);
					queue.add(other);
				}
			}
		}
		
		return null;
	}
	
	//Currently not being used, possibly later
//	private void breadthFirstSearch(Vertex<Type> curr) {
//		
//		
//		
//		
//	}
	
	/*
	 * Generates a sorted ordering of the graph created from the list of sources and destinations. The graph may have more than
	 * one valid ordering and since it only works with acyclic graphs, it will throw an IllegalArgumentException if the graph contains
	 * any cycles.
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		
		//Throw exception if the number of sources doesn't match the number of destinations.
		if(sources.size() != destinations.size()){
			throw new IllegalArgumentException("The length of the list of the sources doesn't match the length of the list of destinations!");
		}
	
		//Creates a graph from the lists of sources and destinations.
		Graph<Type> graph = new Graph<Type>();
		
		//Creates the edges between sources[i] and destinations[i]
		for(int i = 0; i < destinations.size();i++) {
			graph.addEdge( sources.get(i), destinations.get(i) );
		}
		
		//Creates a queue to store the next vertices to sort through
		Queue<Vertex<Type> > queue = new LinkedList<Vertex<Type> >();
		
		
		//Check each vertex in the graph and if its indegree is 0 then add it to the queue.
		for(Entry<Type, Vertex<Type>> vertexEntry : graph.getVertices().entrySet()) {
			if(vertexEntry.getValue().getIndegree() == 0) {
				queue.add(vertexEntry.getValue());
			}
		}
		
		//List tracking the sorted graph
		List<Type> sortedList = new ArrayList<Type>();
		
		
		//This will repeat until each vertex has been added in a sorted order.
		while(!queue.isEmpty()) {
			//Remove the current vertex from the queue and add its data to the sortedList.
			Vertex<Type> currVertex = queue.remove();
			
			//Prevents topological sort from attempting to sort Cyclic graphs.
			if(currVertex.getVisited()) {
				throw new IllegalArgumentException("Cyclic graph can not be sorted by topological sort!");
			}
			currVertex.setVisited(true);//Set the current vertex to visited
			
			sortedList.add(currVertex.getData());
			
			for(Edge<Type> e: currVertex.getAdjacent()) {
				Vertex<Type> other = e.getDestination();
				
				//Reduce the indegree by 1
				other.setIndegree(other.getIndegree()-1);
				
				//If the indegree is zero then add it next to the queue
				if(other.getIndegree() == 0) {
					queue.add(currVertex);
				}
			}
		}
		
		//Finally return the list representing the sorted graph.
		return sortedList;
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