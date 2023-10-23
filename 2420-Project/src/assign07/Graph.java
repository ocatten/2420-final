package assign07;

import java.util.ArrayList;
import java.util.List;

import assignment07.Edge;
import assignment07.Vertex;

/**
 * This class stores the vertices and the edges between them, utilizing GraphUtility to run operations on them.
 * 
 * @author Parker Catten, & Everett Oglesby
 * @version 10:23:23
 */
public class Graph<Type> {
	
	// Fields
	ArrayList<Vertex<Type>> backingList;
	
	
	/**
	 * @Constructor to set up fields
	 */
	public Graph () {
		backingList = new ArrayList<Vertex<Type>>();
	}
	
	
	
	/**
	 * @return a DOT String for webgraphiz.com
	 */
	public String dotGraph () {
		
		StringBuilder DOT = new StringBuilder(); // StringBuilder object to return the DOT Graph
		
		// Add the beginning of the DOT graph
		DOT.append("graph { \n");
		
		// Get every vertex's edges and add those edges to the DOT object in order
		for(Vertex<Type> vertex : backingList) {
			
			List<Edge<Type>> edges = vertex.getAdjacent();
			
			// Loop through each vertex's adjacency list for the edges
			for(Edge<Type> edge : edges)  {
				
				// Add the source, the arrow, and the destination for DOT format with indentations
				DOT.append("   \"" + vertex.getData() + "\" -> \"" + edge.getDestination().getData() + "\"\n");
			}
		}
		
		// Add the closing bracket
		DOT.append("}");
		
		return DOT.toString(); // Return the final String
	}
	
}