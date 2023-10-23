package assign06;

import java.util.Random;

public class StackTimer {
	
	public static void main(String[] args) {
		
		//The amount of times to loop the program
		int timesToLoop = 1000000;
		
		//Set this for which test to do. Look on the if statements to see what they're timing
		//The timers are comparing our code VS a premade code (The array stack code)
		
		//Which test to do, change the int to do a different test
		//I wrote all of the tests as seperate methods
		int test = 1;

		LinkedListStack<Integer> timer = new LinkedListStack<Integer>();
		for(Integer n = 0; n < timesToLoop ; n++) {
			timer.push(n);
		}
		
		ArrayStack<Integer> timerArrayStack = new ArrayStack<Integer>();
		for(Integer n = 0; n < timesToLoop ; n++) {
			timer.push(n);
		}
		
		
		//Test Push in the LinkedListStack
		if(test == 0) {
			pushTest(timesToLoop);
		}
		
		//Test Pop in the LinkedListStack
		if(test == 1) {
			popTest(timer);
		}
		
		//Test Peek in the LinkedListStack
		if(test == 2) {
			peekTest(timer);
		}
		
		//Test Push in the given ArrayStack
		if(test == 3) {
			pushTestArrayStack(timesToLoop);
		}
		
		//Test Pop in the given ArrayStack
		if(test == 4) {
			popTestArrayStack(timerArrayStack);
		}
		
		//Test Peek in the given ArrayStack
		if(test == 5) {
			peekTestArrayStack(timerArrayStack);
		}
	}
	
	public static void pushTest(int timesToLoop) {

		
		// For each problem size n . . .
		for(int n = 1; n < 21; n++) {
			
			// Create new stack
			LinkedListStack<Integer> stackTimer = new LinkedListStack<Integer>();
			
			Random rng = new Random();
			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			//System.out.println("Starting next loop");
			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				stackTimer.push(rng.nextInt());
			}

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
	
	public static void popTest(LinkedListStack<Integer> timer) {
		//The amount of times to loop the program
		int timesToLoop = 1000000;
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n++) {
			
			// Create new stack
			//LinkedListStack<Integer> tempTimer = new LinkedListStack<Integer>();
			//tempTimer = timer;
			
			
			//Random rng = new Random();

			
			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				timer.push(i);
			}
			

			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			//System.out.println("Starting next loop");
			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				//Integer temp = (Integer) tempTimer.pop();
				//System.out.print(temp);
			}

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

	public static void peekTest(LinkedListStack<Integer> timer) {
		//The amount of times to loop the program
		int timesToLoop = 1000000;
		
		
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n++) {
			
			// Create new stack
			LinkedListStack<Integer> tempTimer = timer;
			
			
			//Random rng = new Random();
			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			//System.out.println("Starting next loop");
			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				tempTimer.peek();
			}

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
	
	public static void pushTestArrayStack(int timesToLoop) {

		
		// For each problem size n . . .
		for(int n = 1; n < 21; n++) {
			
			// Create new stack
			ArrayStack<Integer> stackTimer = new ArrayStack<Integer>();
			
			Random rng = new Random();

			

			

			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			//System.out.println("Starting next loop");
			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				stackTimer.push(rng.nextInt());
			}

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
	
	public static void popTestArrayStack(ArrayStack<Integer> timer) {
		//The amount of times to loop the program
		int timesToLoop = 1000000;
		
		
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n++) {
			
			// Create new stack
			ArrayStack<Integer> tempTimer = timer;
			
			
			//Random rng = new Random();

			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				timer.push(i);
			}

			

			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			//System.out.println("Starting next loop");
			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				tempTimer.pop();
			}

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
	
	public static void peekTestArrayStack(ArrayStack<Integer> timer) {
		//The amount of times to loop the program
		int timesToLoop = 1000000;
		
		
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n++) {
			
			// Create new stack
			ArrayStack<Integer> tempTimer = timer;
			
			
			//Random rng = new Random();

			
			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				timer.push(i);
			}
			

			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			//System.out.println("Starting next loop");
			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			//Create the list 
			for(Integer i = 0; i < n*timesToLoop; i++) {
				tempTimer.peek();
			}

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
