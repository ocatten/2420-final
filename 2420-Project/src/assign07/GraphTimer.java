package assign07;

import java.util.ArrayList;
import java.util.Random;

public class GraphTimer<Type> {
	
	ArrayList<Vertex<Integer>> sourceVertices = new ArrayList<Vertex<Integer>>();
	ArrayList<Vertex<Integer>> destinationVertices = new ArrayList<Vertex<Integer>>();
	
	public static void main(String[] args) {
		

	}

	/*
	 * Creates graph with its size being the given vertexCount
	 */
	public void createGraph(int vertexCount) {
		
		Random rng = new Random();

		
		//Creates the vertices for the current graph
		for(int i = 0; i < vertexCount;i++) {
			Integer data = i;
			Vertex<Integer> newVertex = new Vertex<Integer>(data);
			
			sourceVertices.add(newVertex);
		}
		
		//Creates mix for the vertices
		for(int i = 0; i < vertexCount;i++) {
			Integer data = i;
			Vertex<Integer> newVertex = new Vertex<Integer>(data);
			
			sourceVertices.add(newVertex);
		}
		
		//Creates edges
		for(int i = 0;i < (vertexCount*2);i++) {
			Integer index = rng.nextInt(vertexCount);
			destinationVertices.add(null)
		}
	}
}
