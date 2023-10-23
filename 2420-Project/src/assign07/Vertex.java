package assign07;

import java.util.LinkedList; 
import java.util.List;

/**
 * This class represents a point of data in the graph, aka a node or a vertex.
 * 
 * @author Parker Catten, & Everett Oglesby
 * @version 10:23:23
 */
public class Vertex<Type> {

	// Fields
	private Type data; // Data stored in the vertex, matches the Graph's generic data type
	private LinkedList<Edge<Type> > adj; // Adjacency list

	
	/**
	 * @Constructor that creates a new Vertex object with data
	 * 
	 * @param data: Value stored in the Vertex
	 */
	public Vertex(Type data) {
		
		this.data = data;
		this.adj = new LinkedList<Edge<Type> >();
	}
	
	
	
	/**
	 * Adds a directed edge to another vertex
	 * 
	 * @param destination: Vertex object that the new edge points to
	 */
	public void addEdge(Vertex<Type> destination) {
		
		adj.add(new Edge<Type>(this, destination)); // Update adjacency list with new edge
	}
	
	
	
	/**
	 * @return the data value for the vertex
	 */
	public Type getData() { return data; }
	
	
	
	/**
	 * @return the adjacency list
	 */
	public List< Edge<Type> > getAdjacent() { return adj; }
	
}
