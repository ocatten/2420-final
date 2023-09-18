package assign03;

import java.util.Random; 

public class PriorityQueueTimer<Type> {
	public static void main(String args[]) {
		findMaxTest();
	}
	
	public static void findMaxTest() {
		Random randomNumberGenerator = new Random();
		
		// Do 10000 lookups and use the average running time
		int timesToLoop = 10000;

		// For each problem size n . . .
		for(int n = 1; n <= 20; n += 1) {
			
			SimplePriorityQueue<Integer> currQueue = new SimplePriorityQueue<Integer>();//The current queue
			
			



			long startTime, stopTime; //midpointTime,

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Randomly shuffle the array
			for(int i = 0; i < (n*timesToLoop); i++) {
				Integer randomIndex = randomNumberGenerator.nextInt(n);
				currQueue.insert(randomIndex);
			}

			currQueue.findMax();
			



			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = (stopTime) - startTime;
			averageTime /= 1000000;
			averageTime /= 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
}