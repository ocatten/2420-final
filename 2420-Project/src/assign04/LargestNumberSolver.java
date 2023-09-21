package assign04;

/**
 * This class takes an array of integers and sorts them into a BigInteger number as the largest combination, the kth largest combination,
 * largest int, largest long, etc. Uses StringBuilder to create large numbers. Used Canvas page for some of the method contracts, used
 * both as a useful synopsis and on-hand rubric for designing methods. Credit to @ErinParker for writing segments of these contracts.
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 09:14:03 2420_020 FA-2023
 */

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver {
	
	// Fields
	private  Comparator<String> cmp = new Comparator<String>() {
		
		// For this compare method, we essentially want to sort each number alphabetically. Single digits
		//  go first for their respective "leading number", as second ones do, and so on
		
		@SuppressWarnings("removal")
		public int compare(String o1, String o2) { 
			
			// Convert both to character arrays of the strings
			char[] lhs = o1.toCharArray(), rhs = o2.toCharArray();
			
			// Loop through all the numbers until one ends or a greater value is found in any given position
			int index = 0;
			while (index < lhs.length && index < rhs.length) {
				
				// If a greater value is found:
				if(lhs[index] > rhs[index]) {
					
					return -1; // Greater than
				
				} else if (lhs[index] < rhs[index]) {
					
					return 1; // Less than
				
				} 
				
				index++; // Equal to
			}
			
			// If the two Strings are equal up to this point, return the shorter one.
			return new Integer(lhs.length).compareTo(new Integer (rhs.length))*-1; // Inverted for insertionSort
		}
	};
	
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * 
	 * @param arr: Array of generic objects that is sorted.
	 * @param cmp: Comparator object that's used to sort the generic objects
	 */
	public  <T> void insertionSort (T[] arr, Comparator<? super T> cmp) {
		
		// Catch case for an empty array or an array with 1 element
		if(arr.length <= 1) {
			return;
		}
		
		// Loop through the given list
		for (int i = 1; i < arr.length; i++) {
	        
			// Takes the current element to make comparisons with
			T inserted = arr[i];
			int j = i - 1; // Find index before this one
            
			// Checks the ith element from the insertion key to the beginning and swaps around
            while (j >= 0 && cmp.compare(arr[j], inserted) > 0) {
            	
            	// Swap elements around the inserted component
            	arr[j + 1] = arr[j];
            	j = j - 1;
	        }
            
            // Resets the loop for the next key
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
		
		// Use helper for sorting the Strings and getting the built list
		return stringsToBigInt( getSortedStrings(arr) );
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
		
		String[] newArr = getSortedStrings(arr); // Use helper method to find the largest combination in Strings
		
		// Now uses a while loop to keep looping under smaller versions until an int is found
		int permutation = 1;
		while (permutation <= factorial(newArr.length)) {
			
			// Create an array to store the current permutation
			String[] ithLargest = new String[newArr.length];
			
			// Check the ith version and take the leading number out first. We find this by determining that each number's
			//  position in an ith largest combination is determined by i. Each number will remain unchanged during the
			//  first interval of its position! (factorial). After exceeding that threshold, the second largest number 
			//  swaps with it, then the third, and so on. We'll search the possible combinations and remove elements as we
			//  clarify their position recursively
			
			int currentPermutation = permutation;
			
			int position = 1;
			while (position < ithLargest.length) {
				
				// Test statements
				//System.out.println(factorial(index++));
				//System.out.println(currentPermutation + " p");
				//System.out.println("newValue: " + newValue);
				
				if (currentPermutation == 0) {
					for(int j = position; j <= ithLargest.length; j++) {
						
						
						ithLargest[j-1] = newArr[j-1];
					}
					break;
					
				}
				
				String newValue = newArr[currentPermutation / factorial(position)];
				
				while (newValue == null) {
					
					System.out.println("null value found");
					position++;
					newValue = newArr[currentPermutation / factorial(position)];
				}
				
				newArr[currentPermutation / factorial(position)] = null;
				ithLargest[position-1] = newValue;
				
				currentPermutation %= factorial(position);
								
			}
			
			newArr = ithLargest.clone();
			
			// Test statements
			for (String s : newArr) {
				System.out.println(s);
			}
			
			if (stringsToBigInt(newArr).compareTo(new BigInteger("2147483647")) < 1) {
				return stringsToBigInt(newArr).intValue();
			}
			
			permutation++;
		}
		
		return 0;
	}
	
	
	
	/**
	 * Helper method that calculates a factorial
	 * 
	 * @param x: Number to take factorial of
	 * @return: Factorial of x
	 */
	public int factorial  (int x) {
		
		int xFactorial = 1;
		
		// Keep multiplying the result by the current iteration to get the factorial
		for (int i = 1; i <= x; i++) {     
		      xFactorial *= i; 
		}
		
		return xFactorial;
	}
		
		
		
	/**
	 * Helper method to avoid repeating logic from FindLargestNumber. 
	 * 
	 * @param arr: Array of Integers to combine
	 * @return: array of Strings
	 */
	public String[] getSortedStrings (Integer[] arr) throws OutOfRangeException {
		
		// Follows same process as largestNumber but uses a helper method to get smaller versions
		
		// First, convert each Integer to Strings for better length comparisons and charArrays
		String[] newArr = new String[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i].toString();
		}

		// Use the custom lambda expression to sort each array in the list
		insertionSort(newArr, cmp);
		
		return newArr;
	}
	
	
	
	/**
	 * Helper method to turn String arrays into BigIntegers while preserving order
	 * 
	 * @param arr: Array of Strings to be combined into a BigInteger
	 * @return: BigInteger of each concatenated element in the array
	 */
	public BigInteger stringsToBigInt (String[] arr) {
		
		// Build the String for each sorted array
		StringBuilder builder = new StringBuilder();
		
		for(String s : arr) {
			builder.append(s);
		}
		
		// Return the built String as a BigInteger if the String exists, otherwise return 0
		if(builder.toString().length() > 0) {
			return new BigInteger(builder.toString());
		} else {
			return new BigInteger("0");
		}
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
		// Creates a variable to track the final sum.
		BigInteger finalSum = BigInteger.valueOf(0);
		
		// Loops through the list and adding the largest number of each array to the final sum.
		for(Integer[] currList : list) {
			// Finds the current list then gets the largest number from it.
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
		
}