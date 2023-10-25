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
	
	//Private variables to help with different sort algoritms
	
	private boolean visited = false; //Used by Depth-First Search
	
	private int distanceFromStart;//Used by Breadth-First Search, If not visited
	
	private Vertex<Type> previous;//Used by Breadth-First Search
	
	private int indegree;//Used by Topological Search
	
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
	
	/**
	 * @return whether the vertex has been visited or not
	 */
	public boolean getVisited() { return visited; }
	
	/**
	 * @return the previous vertex
	 */
	public Vertex<Type> getPrevious() { return previous; }
	
	/**
	 * Set the value of the previous vertex
	 * 
	 * @return the previous vertex
	 */
	public Vertex<Type> setPrevious(Vertex<Type> previousVertex) { 
		
			previous = previousVertex;
		
			return previous; 
		}
	
	
	
}
