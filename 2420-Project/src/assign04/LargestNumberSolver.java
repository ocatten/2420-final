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
	static Comparator<Double> cmp = new Comparator<Double>() {
		public int compare(Double o1, Double o2) { return o1.compareTo(o2); }
	};
	
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * 
	 * @param arr: Array of generic objects that is sorted.
	 * @param cmp: Comparator object that's used to sort the generic objects
	 */
	public static <T> void insertionSort (T[] arr, Comparator<? super T> cmp) {
		
		// Loop through the given array
		for (int i = 0; i < arr.length; ++i) {
			
			int j = i + 1; // j is the next element in the set
			
		    while (j < arr.length  && cmp.compare(arr[j], arr[i]) > 0) {
		           
		    	int current = i, next = j; // Take current point at the array to iterate down with
		    	T inserted = arr[i];
		    	
		    	// Swap the current and the next(inserted) positions and decrement both until each element is sorted from i & j
		        arr[current] = arr[next];
		        arr[next] = inserted;
		        
		        // If there's still a valid index to move down to, decrement trackers.
		        if (current > 0) {
		        	current--; next--;
		        }
			}
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
	public static BigInteger findLargestNumber (Integer[] arr) {
		
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
		
		// Convert doubles back into bigIntegers and remove tracker placed earlier
		for (int i = 0; i < newArr.length; i++) {
			
			// While the current number hasn't been taken out of scientific notation (denoted by .1 tail)
			while (newArr[i] % 1 != .1) {
				newArr[i] *= 10;
			}
			
			// Truncate tail from the end once scientific notation is undone
			bigNumber.append( newArr[i].intValue() );
		}
		
		// Return the built String into a BigInteger.
		return new BigInteger(bigNumber.toString());
	}
	
	
	
	/**
	 * This method returns the largest int value that can be formed by arranging the integers of the given array, in any order.
	 * This method must not alter the given array.
	 * 
	 * @param arr: Basic array of Integer objects to be combined
	 * @return: Largest possible int combination created from elements in the parameter's array
	 * @throws OutOfRangeException: Thrown if the smallest combination within the array is out of range for an integer
	 */
	public static int findLargestInt (Integer[] arr) throws OutOfRangeException {
		
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
	public static long findLargestLong (Integer[] arr) throws OutOfRangeException {
		
		return 0;
	}
	
	
	
	/**
	 * This method sums the largest numbers that can be formed by each array in the given list.  This method must not alter
	 * the given list.
	 * 
	 * @param list: List of basic arrays to make the biggest numbers out of
	 * @return: BigInteger sum of each array's BigInteger
	 */
	public static BigInteger sum (List<Integer[]> list) {
		return null;
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
	public static Integer[] findKthLargest (List<Integer[]> list, int k) throws IllegalArgumentException {
		
		return null;
		
	}
	
	
	
	/**
	 * This method generates list of integer arrays from an input file, such that each line corresponds to one array of integers 
	 * separated by blank spaces
	 * 
	 * @param filename: Name of the file (inluding .txt) to be read
	 * @return: List of integer arrays from an input file, or an empty list if the file does not exist.
	 */
	public static List<Integer[]> readFile (String filename) {
		return null;
	}
	
}
