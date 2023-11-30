package assign10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class BinaryHeapTimer {

	
	public static void main(String[] args) {
		
		int timesToLoop = 100000;

		//Uncomment the line below to test the basic priority queue methods.
		//addTest(timesToLoop);
		//extractMaxTest(timesToLoop);
		//peekTest(timesToLoop*25);

		//Uncomment to test findKLargest heap vs. sort
		//findLargestHeap(timesToLoop*20);
		findLargestSort(timesToLoop*20);
		
	}
	
	
	
	public static void addTest(int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n ++) {
			
			BinaryMaxHeap<Integer> maxHeap = new BinaryMaxHeap<Integer>();
			

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
			for(Integer i = 0; i < loopTime;i++) {
				maxHeap.add(i);
			}
			
			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	public static void extractMaxTest(int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n ++) {
			
			BinaryMaxHeap<Integer> maxHeap = new BinaryMaxHeap<Integer>();
			

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			List<Integer> intArray = new ArrayList<Integer>();
			for(int i = 0; i < loopTime;i++) {
				intArray.add(rng.nextInt());
			}
			maxHeap.buildHeap(intArray);
			
			startTime = System.currentTimeMillis();
					
			for(Integer i = 0; i < loopTime;i++) {
				maxHeap.extractMax();
			}
			
			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	public static void peekTest(int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n ++) {
			
			BinaryMaxHeap<Integer> maxHeap = new BinaryMaxHeap<Integer>();
			

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			List<Integer> intArray = new ArrayList<Integer>();
			for(int i = 0; i < loopTime;i++) {
				intArray.add(rng.nextInt());
			}
			maxHeap.buildHeap(intArray);
			
			startTime = System.currentTimeMillis();
					
			maxHeap.peek();
			
			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	public static void findLargestHeap(int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n ++) {
			
			BinaryMaxHeap<Integer> maxHeap = new BinaryMaxHeap<Integer>();
			FindKLargest findLargest = new FindKLargest();

			Random rng = new Random(timesToLoop);
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
			
			List<Integer> intArray = new ArrayList<Integer>();
			for(int i = 0; i < timesToLoop;i++) {
				intArray.add(rng.nextInt());
			}
			
			
			startTime = System.currentTimeMillis();
					
			int curr = (int) Math.pow(2, n);
			List<Integer> kLargest = findLargest.findKLargestHeap(intArray, curr);
			
			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
public static void findLargestSort(int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n ++) {
			
			BinaryMaxHeap<Integer> maxHeap = new BinaryMaxHeap<Integer>();
			FindKLargest findLargest = new FindKLargest();

			Random rng = new Random(timesToLoop);
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
			
			List<Integer> intArray = new ArrayList<Integer>();
			for(int i = 0; i < timesToLoop;i++) {
				intArray.add(rng.nextInt());
			}
			
			
			startTime = System.currentTimeMillis();
			
			
			int curr = (int) Math.pow(2, n);		
			List<Integer> kLargest = findLargest.findKLargestSort(intArray, curr);
			
			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
}