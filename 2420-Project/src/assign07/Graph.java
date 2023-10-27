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
	 * @return: the HashMap this graph uses
	 */
	public Map<Type, Vertex<Type>> getVertices() { 
		return vertices; 
	}
	
	
	
	/*
	 * Returns the vertex being searched for or null if no vertex exists
	 */
	public Vertex<Type> getVertex(String data){
		return vertices.get(data);
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" 
	 * to the vertex with name "name2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(Type name1, Type name2) {
		Vertex<Type> vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<Type>(name1);
			vertices.put(name1, vertex1);
		}

		Vertex<Type> vertex2;
		if(vertices.containsKey(name2.toString()))
			vertex2 = vertices.get(name2.toString());
		else {
			vertex2 = new Vertex<Type>(name2);
			vertices.put(name2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
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
	
}