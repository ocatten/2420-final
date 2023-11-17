package assign04;

import java.util.ArrayList;  
import java.util.List;
import java.util.Random;

/*
 * This class uses copy and pasted code. NO SUBMIT!
 */
public class LargestNumberSolverTimer {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
 	
		// Do 10000 lookups and use the average running time
		int timesToLoop = 5000;
		
		// For each problem size n . . .
		for(int n = 1; n <= 20; n ++) {
			
			// Starts with a new number solver that's empty.
			LargestNumberSolver numberSolver = new LargestNumberSolver();
			
			Random rng = new Random();
			
			
			List<Integer[]> integerArrayList = new ArrayList<Integer[]>();

			
			for(int i = 0; i < n*timesToLoop; i++) {
				Integer[] currArray = new Integer[10];
				for (int j = 0; j < 10; j++) {
					currArray[j] = rng.nextInt(9)+1;
				}
				integerArrayList.add(currArray);
			}
			int kNum = rng.nextInt(integerArrayList.size());


			@SuppressWarnings("unused")
			Random randomNumberGenerator = new Random();

			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}

			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			//Uncomment this out to test our insertion sort
			@SuppressWarnings("unused")
			Integer[] largestArray = numberSolver.findKthLargest(integerArrayList, kNum);

			
			

			

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
