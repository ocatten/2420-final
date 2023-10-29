package assign07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class stores the vertices and the edges between them, utilizing GraphUtility to run operations on them.
 * 
 * @author Parker Catten, & Everett Oglesby
 * @version 10:23:23
 */
public class Graph<Type> {
	
	// Fields
	private Map<Type, Vertex<Type>> vertices;

	
	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<Type, Vertex<Type>>();
	}
	
	
	
	/**
	 * @return: the map of vertices used for the graph
	 */
	public Map<Type, Vertex<Type>> getVertices() { 
		return vertices; 
	}
	
	
	
	/*
	 * Returns the vertex being searched for or null if no vertex exists
	 */
	public Vertex<Type> getVertex(Type data){
		return vertices.get(data);
	}

	/**
	 * Creates a directed edge from the srcData to the dstData and adds the
	 * edge to the local vertice's adjacency list.
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(Type srcData, Type dstData) {
		
		
		Vertex<Type> sourceVertex;//Represents the vertex at the source.
		Vertex<Type> dstVertex;//Represents the vertex at the destination.
		
		//Gets the Vertex of the srcData if it already exists in the graph.
		if(vertices.containsKey(srcData)) {
			sourceVertex = vertices.get(srcData);
		}
		else {//If it doesn't then we can create a new Vertex and add the vertex to the graph
			sourceVertex = new Vertex<Type>(srcData);
			vertices.put(srcData, sourceVertex);
		}

		//Repeats same process with the dstData to find or create the dstVertex.
		if(vertices.containsKey(dstData.toString()))
			dstVertex = vertices.get(dstData.toString());
		else {
			dstVertex = new Vertex<Type>(dstData);
			vertices.put(dstData, dstVertex);
		}

		//Add a directed edge from the source to the destination within the sourceVertex's adjacney list.
		sourceVertex.addEdge(dstVertex);
	}
	
	
	
	/**
	 * @return a DOT String for webgraphiz.com
	 */
	public String dotGraph () {
		
		StringBuilder DOT = new StringBuilder(); // StringBuilder object to return the DOT Graph
		
		// Add the beginning of the DOT graph
		DOT.append("graph { \n");
		
		// Get every vertex's edges and add those edges to the DOT object in order
		for(Entry<Type, Vertex<Type>> vertexEntry : vertices.entrySet()) {
			
			List<Edge<Type>> edges = vertexEntry.getValue().getAdjacent();
			
			// Loop through each vertex's adjacency list for the edges
			for(Edge<Type> edge : edges)  {
				
				// Add the source, the arrow, and the destination for DOT format with indentations
				DOT.append("   \"" + vertexEntry.getValue().getData() + "\" -> \"" + edge.getDestination().getData() + "\"\n");
			}
		}
		
		// Add the closing bracket
		DOT.append("}");
		
		return DOT.toString(); // Return the final String
	}
	
	
	
	/**
	 * Helper method that sets each vertex's visited value to false.
	 */
	public void unVisit () {
		
		for(Entry<Type, Vertex<Type>> vertexEntry : vertices.entrySet()) {
			
			vertexEntry.getValue().setVisited(false);
		}
	}
	
	
}