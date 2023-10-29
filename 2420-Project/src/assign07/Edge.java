package assign07;

/**
 * This class represents a connection (or "Edge") between two Vertices on a graph
 * 
 * @author Parker Catten, & Everett Oglesby
 * @version 10:23:23
 */

public class Edge<Type> {
	
	// Fields
	private Vertex<Type> src; // Vertex this edge is from
	private Vertex<Type> dst; // Vertex this edge points to

	
	/**
	 * @Constructor that makes the edge pointing to the destination
	 * 
	 * @param src: The source this edge comes from
	 * @param dst: The destination this edge points to
	 */
	public Edge(Vertex<Type> vertex, assign07.Vertex<Type> destination) {
		this.src = vertex;
		this.dst = destination;
	}
	
	
	
	/**
	 * @return the source vertex
	 */
	public Vertex<Type> getSource() { return src; }

	
	
	/**
	 * @return the destination vertex
	 */
	public Vertex<Type> getDestination() { return dst; }
	
}
