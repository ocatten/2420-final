	package assign05;

import java.util.ArrayList;
import java.util.Random;


public class ArrayListSorterTimer {
	
	
	
	public static void main(String[] args) {
 
		quicksortTests();
	}
	
	public static void mergesortTests() {
		ArrayListSorter arraySorter = new ArrayListSorter();
		arraySorter.setInsertionThreshold(64);
		
		int loopAmount = 100000;
		
		ArrayList<Integer> currArr = new ArrayList<Integer>();
		
		// For each problem size n . . .
		for(int n = 1; n <= 20; n ++) {
			
			currArr = arraySorter.generateAscending(n*loopAmount);
			
			Random rng = new Random();
			
			
			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}

			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			arraySorter.mergesort(currArr);

			
			

			

			double stopTime = System.currentTimeMillis();
			

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = stopTime - startTime;
			//Convert time to seconds
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	public static void quicksortTests() {
		ArrayListSorter arraySorter = new ArrayListSorter();
		
		
		int loopAmount = 1000;
		
		ArrayList<Integer> currArr = new ArrayList<Integer>();
		
		// For each problem size n . . .
		for(int n = 1; n <= 20; n ++) {
			
			currArr = arraySorter.generatePermuted(n*loopAmount);
			
			Random rng = new Random();
			
			
			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}

			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			arraySorter.quicksort(currArr);

			
			

			

			double stopTime = System.currentTimeMillis();
			

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = stopTime - startTime;
			//Convert time to seconds
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	
}

