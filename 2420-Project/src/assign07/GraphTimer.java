package assign07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import assign07.GraphUtility;

public class GraphTimer<Type> {
	

	public static void main(String[] args) {
		int vertexCount = 200000;
		
		dfsTest(vertexCount);
	}

	

	
	
	/*
	 * Tests depth first search
	 */
	public static void dfsTest(int vertexCount) {
		ArrayList<Integer> sourceData = new ArrayList<Integer>();
		ArrayList<Integer> destinationData = new ArrayList<Integer>();
		
		
		for(int i = 1;i <= 20;i++) {
			
			//Clear the source and destination data
			sourceData.clear();
			destinationData.clear();
			
			createGraphdfs((vertexCount*i),sourceData,destinationData);
			
			Integer first = 0;
			Integer last = (sourceData.size()/2)-1;
			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			// Now, run the test

			
			startTime = System.currentTimeMillis();

			GraphUtility.areConnected(sourceData, destinationData, first, last);//Runs Test

			double stopTime = System.currentTimeMillis();
			

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = stopTime - startTime;
			//Convert time to seconds
			averageTime = averageTime / 1000;
			System.out.println(i + "\t" + averageTime);
			
			

		}
	}
	
	
	
	/*
	 * Tests depth first search
	 */
	public static void bfsTest(int vertexCount) {
		ArrayList<Integer> sourceData = new ArrayList<Integer>();
		ArrayList<Integer> destinationData = new ArrayList<Integer>();
		
		
		for(int i = 1;i <= 20;i++) {
			
			//Clear the source and destination data
			sourceData.clear();
			destinationData.clear();
			
			createGraphbfs((vertexCount*i),sourceData,destinationData);
			
			Integer first = 0;
			Integer last = (sourceData.size()/2)-1;
			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			// Now, run the test

			
			startTime = System.currentTimeMillis();

			GraphUtility.shortestPath(sourceData, destinationData, first, last);//Runs Test

			double stopTime = System.currentTimeMillis();
			

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = stopTime - startTime;
			//Convert time to seconds
			averageTime = averageTime / 1000;
			System.out.println(i + "\t" + averageTime);
			
			

		}
	}
	
	/*
	 * Tests topological search
	 */
	public static void topoTest(int vertexCount) {
		ArrayList<Integer> sourceData = new ArrayList<Integer>();
		ArrayList<Integer> destinationData = new ArrayList<Integer>();
		
		
		for(int i = 1;i <= 20;i++) {
			
			//Clear the source and destination data
			sourceData.clear();
			destinationData.clear();
			
			createGraphTopo((vertexCount*i),sourceData,destinationData);
			
			Integer first = 0;
			Integer last = (sourceData.size()/2)-1;
			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			// Now, run the test

			
			startTime = System.currentTimeMillis();

			GraphUtility.sort(sourceData, destinationData);//Runs Test

			double stopTime = System.currentTimeMillis();
			

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = stopTime - startTime;
			//Convert time to seconds
			averageTime = averageTime / 1000;
			System.out.println(i + "\t" + averageTime);
			
			

		}
	}
	
	/*
	 * Creates graph with its size being the given vertexCount
	 */
	public static void createGraphdfs(int vertexCount, ArrayList<Integer> sourceData, ArrayList<Integer> destinationData) {
		
		Random rng = new Random();

		
		//Creates the vertices for the current graph
		for(int i = 0; i < vertexCount;i++) {
			Integer data = i;
			
			sourceData.add(data);
		}
		
		//Assures all edges are connected
		ArrayList<Integer> permutedList = new ArrayList<Integer>();
		
		//Adds Integers from 1 to size in ascending order
		for(int i = 1; i < vertexCount+1; i++) {
			permutedList.add(i);
		}
		
		//Collections.shuffle shuffles the list from ascending order to a random order.
		Collections.shuffle(permutedList);
		
		
		//Creates mix for the vertices
		for(int i = 0; i < vertexCount;i++) {
			Integer data = i;
			
			sourceData.add(data);
		}
		
		//Creates Edges
		
		for(int i = 0;i < (vertexCount*2);i++) {
			rng.nextInt(vertexCount);
		}
	}
	
	
	/*
	 * Creates graph with its size being the given vertexCount
	 */
	public static void createGraphbfs(int vertexCount, ArrayList<Integer> sourceData, ArrayList<Integer> destinationData) {
		
		Random rng = new Random();

		
		//Creates the vertices for the current graph
		for(int i = 0; i < vertexCount;i++) {
			Integer data = i;
			
			sourceData.add(data);
		}
		
		//Assures all edges are connected
		ArrayList<Integer> permutedList = new ArrayList<Integer>();
		
		//Adds Integers from 1 to size in ascending order
		for(int i = 1; i < vertexCount+1; i++) {
			permutedList.add(i);
		}
		
		//Collections.shuffle shuffles the list from ascending order to a random order.
		Collections.shuffle(permutedList);
		
		
		//Creates mix for the vertices
		for(int i = 0; i < vertexCount;i++) {
			Integer data = i;
			
			sourceData.add(data);
		}
		
		//Creates edges
		
		
		Collections.shuffle(permutedList);

		for(int i = 0;i < (vertexCount);i++) {
			Integer index;
			
			if(i < vertexCount - 1) {
				index = i+1;
			}
			else {
				index = rng.nextInt(vertexCount);
			}
			destinationData.add(index);
		}
		
		Collections.shuffle(permutedList);
		
		for(int i = 0;i < (vertexCount);i++) {
			Integer index = permutedList.get(i);
			destinationData.add(index);
		}
	}
	
	
	
	/*
	 * Creates graph with its size being the given vertexCount
	 */
	public static void createGraphTopo(int vertexCount, ArrayList<Integer> sourceData, ArrayList<Integer> destinationData) {
		
		Random rng = new Random();

		
		//Creates the vertices for the current graph
		for(int i = 0; i < vertexCount;i++) {
			Integer data = i;
			
			sourceData.add(data);
		}
		
		//Assures all edges are connected
		ArrayList<Integer> permutedList = new ArrayList<Integer>();
		
		//Adds Integers from 1 to size in ascending order
		for(int i = 1; i < vertexCount+1; i++) {
			permutedList.add(i);
		}
		
		//Collections.shuffle shuffles the list from ascending order to a random order.
		Collections.shuffle(permutedList);
		
		
		//Creates mix for the vertices
		for(int i = 0; i < vertexCount;i++) {
			Integer data = i;
			
			sourceData.add(data);
		}
		
		//Creates edges
		
		
		Collections.shuffle(permutedList);

		for(int i = 0;i < (vertexCount*2);i++) {
			Integer index;
			int lowerBounds;
			lowerBounds = sourceData.get(i);
			
			index = rng.nextInt(lowerBounds,vertexCount);
			destinationData.add(index);
		}
	}
}
