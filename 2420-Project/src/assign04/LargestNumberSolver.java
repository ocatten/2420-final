package assign04;

import java.math.BigInteger;

/**
 * This class takes an array of integers and sorts them into a BigInteger number as the largest combination, the kth largest combination,
 * largest int, largest long, etc. Uses StringBuilder to create large numbers. Used Canvas page for some of the method contracts, used
 * both as a useful synopsis and on-hand rubric for designing methods. Credit to @ErinParker for writing segments of these contracts.
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 09:14:03 2420_020 FA-2023
 */

import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver {
	
	// Fields
	private  Comparator<Double> cmpDouble = new Comparator<Double>() {
		public int compare(Double o1, Double o2) { return o2.compareTo(o1); }
	};
	private  Comparator<Integer> cmpInteger = new Comparator<Integer>() {
		public int compare(Integer o1, Integer o2) { return o2.compareTo(o1); }
	}; 
	
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * 
	 * @param arr: Array of generic objects that is sorted.
	 * @param cmp: Comparator object that's used to sort the generic objects
	 */
	public  <T> void insertionSort (T[] arr, Comparator<? super T> cmp) {
		
		//If empty array or array with length of 1 given, then do nothing and
		//print out warning message.
		if(arr.length == 0) {
			System.out.println("Error. Empty array given");
			return;
		}
		
		if(arr.length == 1) {
			System.out.println("Array with length of 1 given. No sorting"
					+ " can be done.");
			return;
		}
		
		// Loop through every element of the array in the parameter
		for (int i = 1; i < arr.length; i++) {
	        
			// Takes the ith element and uses it as an insertion key to make comparisons with
			T inserted = arr[i];
			// j is essentially a position before the insertion key to make comparisons with
			int j = i - 1;
            
			// Loops through every item from the insertion key downward, checks to see if the jth element is greater than the key
            while (j >= 0 && cmp.compare(arr[j], inserted) > 0) {
            	
            	// If the key is less than the jth element, the element after it is the new value.
            	// After it's looped once, it's only checking if the jth element is still above 0
            	arr[j + 1] = arr[j];
            	j = j - 1;
	        }
            
            // This makes sure elements aren't overwritten by the while loop if they're in order.
            //  It resets the loop for the next key.
            arr[j + 1] = inserted;
	     }
	}
	
	
	
	/**
	 * This method returns the largest number that can be formed by arranging the integers of the given array, in any order. 
	 * If the array is empty, the largest number that can be formed is 0. This method must not alter the given array and will 
	 * call the insertionSort method along with a custom lambda.
	 * 
	 * @param arr: Basic array of Integer objects to be combined
	 * @return largestNumber: Largest possible combination of the elements in the parameter as a BigInteger object
	 */
	public BigInteger findLargestNumber (Integer[] arr) {
		
		Integer[] largestNumArr = findLargestNum(arr);
		
		String largestNum = "";//String to store the largest number.
		
		for(int i = 0; i < largestNumArr.length; i++) {
			largestNum += largestNumArr[i];
		}
		
		// Return the built String into a BigInteger.
		return new BigInteger(largestNum);
	}
	
	
	
	/**
	 * This method returns the largest int value that can be formed by arranging the integers of the given array, in any order.
	 * This method must not alter the given array.
	 * 
	 * @param arr: Basic array of Integer objects to be combined
	 * @return: Largest possible int combination created from elements in the parameter's array
	 * @throws OutOfRangeException: Thrown if the smallest combination within the array is out of range for an integer
	 */
	public int findLargestInt (Integer[] arr) throws OutOfRangeException {
		
		// This method will follow the same logic as findLargestNumber, but will tweak the final product until it can be an int
		
		// First, we need to sort by the first number each element in the array possesses.
		Double[] newArr = new Double[arr.length];
		
		// Take the empty double array and simplify each integer into scientific notation.
		for (int i = 0; i < arr.length; i++) {
			
			Double castedInt = arr[i].doubleValue() + .1; // Convert int to double and add tracker to the end
			while (castedInt > 10) { // While double isn't in scientific notation, keep dividing by 10.				
				castedInt /= 10;
			}
			
			// Add this element to the new array.
			newArr[i] = castedInt;
		}
		
		// Sort the new array of doubles.
		insertionSort(newArr, cmp);
		
		
		// Make the BigInteger String out of the array
		StringBuilder bigNumber = new StringBuilder();
		Integer[] intArr = new Integer[newArr.length];
		
		// Convert doubles back into bigIntegers and remove tracker placed earlier
		for (int i = 0; i < newArr.length; i++) {
			
			// While the current number hasn't been taken out of scientific notation (denoted by .1 tail)
			while (newArr[i] % 1 != .1) {
				newArr[i] *= 10;
			}
			
			// Truncate tail from the end once scientific notation is undone and add it to the array of integers
			bigNumber.append( newArr[i].intValue() );
			intArr[i] = newArr[i].intValue();
			
		}
		
		// Check the length of the final product to see if its in range
		if (bigNumber.toString().length() > 10) {
			throw new OutOfRangeException("integer");
		}
		
		int totalPermutations = 1;
		// Find the maximum number of combination for the array, length!
		for(int i = newArr.length; i > 0; i--) {
			totalPermutations *= i;
		}
		
		// Make 
		
		// If it is in range, keep making the kth largest value different to get smaller and smaller
		int k = 0; // Counter for number of iterations
		while (k < totalPermutations) {
			Integer arr
			if(new BigInteger(bigNumber.toString()).compareTo(new BigInteger("2147483647")) > 0) {
				
				  
			}
			
			k++;
		}
		return 0;
	}
	
	
	
	/**
	 * Helper method that converts an array into a String for BigInteger
	 * 
	 * @param arr: Array to be combined into a single string
	 * @return: Built String for array
	 */
	public String buildArrayString (Integer[] arr) {
		
		StringBuilder bigNumber = new StringBuilder();
		
		for (int i = 0; i < arr.length; i++) {	
			bigNumber.append(arr[i]);
		}
		
		return bigNumber.toString(); // Return built String
	}
	
	
	
	/**
	 * This method returns the largest long value that can be formed by arranging the integers of the given array, in any order.
	 * This method must not alter the given array.
	 * 
	 * @param arr: Basic array of Integer objects to be combined
	 * @return: Largest possible long combination created from elements in the parameter's array
	 * @throws OutOfRangeException: Thrown if the smallest combination within the array is out of range for a long
	 */
	public  long findLargestLong (Integer[] arr) throws OutOfRangeException {
		
		return 0;
	}
	
	
	
	/**
	 * This method sums the largest numbers that can be formed by each array in the given list.  This method must not alter
	 * the given list.
	 * 
	 * @param list: List of basic arrays to make the biggest numbers out of
	 * @return: BigInteger sum of each array's BigInteger
	 */
	public  BigInteger sum (List<Integer[]> list) {
		//Creates a variable to track the final sum.
		BigInteger finalSum = BigInteger.valueOf(0);
		
		//Loops through the list and adding the largest number of each array to the final sum.
		for(Integer i = 0; i < list.size(); i++) {
			//Finds the current list then gets the largest number from it.
			Integer[] currList = list.get(i);
			BigInteger current = findLargestNumber(currList);
			 finalSum = finalSum.add(current);
		}
		
		return finalSum;
	}
	
	
	
	/**
	 * This method determines the kth largest number that can be formed by each array in the given list.  E.g., if k=0 
	 * returns the largest overall, if k=list.size()-1 returns the smallest overall.  This method returns the original 
	 * array that represents the kth largest number, not the kth largest number itself.
	 * 
	 * @param list: List of basic arrays of Integers to be compared
	 * @param k: Which rank of the largest number will be found and returned
	 * @throws IllegalArgumentException: Thrown if k is not a valid position in the list
	 */
	public  Integer[] findKthLargest (List<Integer[]> list, int k) throws IllegalArgumentException {
		return null;
		
	}
	
	
	
	/**
	 * This method generates list of integer arrays from an input file, such that each line corresponds to one array of integers 
	 * separated by blank spaces
	 * 
	 * @param filename: Name of the file (inluding .txt) to be read
	 * @return: List of integer arrays from an input file, or an empty list if the file does not exist.
	 */
	public  List<Integer[]> readFile (String filename) {
		return null;
	}
	
	
	/*
	 * Helper method which takes in an array and returns the a new array in the form 
	 * to find the BigInteger.
	 */
	public Integer[] findLargestNum(Integer[] arr) {

		//If the list is empty, then return an array with a single value of zero.
		if(arr.length == 0) {
			Integer[] tempArr = new Integer[1];
			tempArr[0] = 0;
			return tempArr;
		}
		

		
		//Create a new array with each number converted to a double, so the character of each Integer
		//is followed by a ".". For example, 425 would be 4.25 and 9584 would be 9.584. If the Integer
		//is just a single character such as 9 or 4, just add it to the intArray and don't resize it.
		Double[] doubleArr = new Double[arr.length];//The double array to hold each Integer.
		
		Integer[] integerArr = new Integer[arr.length];
		
		int intSize = 0; int doubleSize = 0;//Trackers for the Integer and Double arrays.
		
		for(int i = 0; i < arr.length; i++) {
			boolean resized = false; //Flag to check if the current variable has been resized.
			double curr = arr[i];
			//Adds a tail to the end of doubles that will be later be deleted
			//Used for finding the end of a double, without 100 would be the same as 1.
			curr += 0.1;
			//Resizes the current item
			while(curr >= 10) {
				curr /= 10;
				resized = true;
			}
			//Once the integer has been resized to x.xx form, add it to the doubleArr if it has been resized.
			if(resized) {
				doubleArr[i] = curr;
				doubleSize++;
			}
			else {
				integerArr[i] = arr[i];
				intSize++;
			}
		}
		//Set the int and double resized arrays
		Integer[] intResizedArr = new Integer[intSize];
		Double[] doubleResizedArr = new Double[intSize];
		
		//Next sort both the Integer and Double arrays. Only sort the arrays if they have .
		if(intSize > 1) {
			for(int i = 0; i < intSize; i++) {
				intResizedArr[i] = integerArr[i];
			}
			
			insertionSort(intResizedArr,cmpInteger);
		}
		else {
			intResizedArr[0] = 0;//If the integer array is empty, set the first index equal to zero.
		}
		if(doubleSize > 1) {
			for(int i = 0; i < intSize; i++) {
				doubleResizedArr[i] = doubleArr[i];
			}
			
			insertionSort(doubleResizedArr,cmpDouble);
		}
		else {
			doubleResizedArr[0] = 0.0;//If the double array is empty, then set the first index equal to zero.
		}

	
		//Form the new array in the largest number form by adding together each of the lists.
		Integer[] largestNumArray = new Integer[arr.length];
		int j = 0;
		int k = 0;
		for(int i = 0; i < arr.length; i++) {
			if(doubleResizedArr[j] > intResizedArr[k]+1) {
				//Convert the current double back to an int.
				double currDouble = doubleResizedArr[j];
				while(currDouble % 1 != 0) {
					currDouble *= 10;
				}
				currDouble /= 10; //This will take the tail off the current number.
				largestNumArray[i] = (int) currDouble; //Add the next double in the doubleArr to the largestNumArray in int form.
				j++; //Update the index of the double array.
			}
			else {
				largestNumArray[i] = intResizedArr[k]; //Add the next int in the intArr to the largestNumberArray.
				k++; //Update the index of the int array.
			}
		}
		
		
		
		return largestNumArray;
	}
}
