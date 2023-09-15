package assign03;

import java.util.Random;

import assign02.CS2420ClassGeneric;

public class PriorityQueueTimer<Type> {
	public static void main(String args[]) {
		findMaxTest();
	}
	
	public static void findMaxTest() {
		Random randomNumberGenerator = new Random();
		
		// Do 10000 lookups and use the average running time
		int timesToLoop = 100000;

		// For each problem size n . . .
		for(int n = 1; n <= 20; n += 1) {
			
			SimplePriorityQueue<Integer> currQueue = new SimplePriorityQueue<Integer>();//The current queue
			
			// Randomly shuffle the array
			for(int i = 0; i < (n*timesToLoop); i++) {
				Integer randomIndex = randomNumberGenerator.nextInt(n);
				currQueue.insert(randomIndex);
			}



			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			currQueue.findMax();
			

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}
}