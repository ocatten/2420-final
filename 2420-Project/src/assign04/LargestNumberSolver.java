	package assign04;

import java.io.File;

/**
 * This class takes an array of integers and sorts them into a BigInteger number as the largest combination, the kth largest combination,
 * largest int, largest long, etc. Uses StringBuilder to create large numbers. Used Canvas page for some of the method contracts, used
 * both as a useful synopsis and on-hand rubric for designing methods. Credit to @ErinParker for writing segments of these contracts.
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 09:14:03 2420_020 FA-2023
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class LargestNumberSolver {
	
	// Fields
	private static Comparator<String> cmp = new Comparator<String>() {
		
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
	
	//Comparator for sorting BigIntegers, sorts highest to lowest.
		private static Comparator<BigInteger> cmpBigInt = new Comparator<BigInteger>() {
			public int compare(BigInteger o1, BigInteger o2) {return o2.compareTo(o1);}
		};
	
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * 
	 * @param arr: Array of generic objects that is sorted.
	 * @param cmp: Comparator object that's used to sort the generic objects
	 */
	public static <T> void insertionSort (T[] arr, Comparator<? super T> cmp) {
		
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
	public static BigInteger findLargestNumber (Integer[] arr) {
		
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
	public static int findLargestInt (Integer[] arr) throws OutOfRangeException {
		
		String[] newArr = getSortedStrings(arr); // Use helper method to find the largest combination in Strings
		
		// Now uses a while loop to keep looping under smaller versions until a viable int is found
		int permutation = 1;
		while (permutation <= factorial(newArr.length)) {
			
			String[] newArrTemp = newArr.clone(); // Make a duplicate to delete from
			
			// Create an array to store the current permutation
			String[] ithLargest = new String[newArrTemp.length];
			
			// Check the ith version and take the leading number out first. We find this by determining that each number's
			//  position in an ith largest combination is determined by i. Each number will remain unchanged during the
			//  first interval of its position! (factorial). After exceeding that threshold, the second largest number 
			//  swaps with it, then the third, and so on. We'll search the possible combinations and remove elements as we
			//  clarify their position recursively
			
			int currentPermutation = permutation; // Store currentPermutation
			
			for (int i = 1; i <= ithLargest.length; i++) {
				
				// Find the next value in the new BigInteger from the original array
				int newValueIndex = currentPermutation / factorial(i);
				
				// Similar to a table, this uses null as a "tombstone" and loops through until a remaining element is found 
				while (newArrTemp[newValueIndex] == null) {
					newValueIndex++;
				}
				
				// Test statements
				// System.out.println(newArr[newValueIndex]);
				
				// Cut the value from the original set of values and add it to the new one.
				ithLargest[i-1] = newArrTemp[newValueIndex];
				newArrTemp[newValueIndex] = null;
				
				// Take the remaining number of possibilities and remove all of the current position's values, finding what's left over
				currentPermutation %= factorial(ithLargest.length+1 - i);
			}
			
			// Test statements
			//for (String s : ithLargest) {
				//System.out.println(s);
			//}
			
			if (stringsToBigInt(ithLargest).compareTo(new BigInteger("2147483647")) < 1) {
				return stringsToBigInt(ithLargest).intValue();
			}
			
			permutation++;
		}
		
		throw new OutOfRangeException(stringsToBigInt(newArr).toString()); // If no solution is found in the permutations, throw the exception
	}
	
	
	
	/**
	 * Helper method that calculates a factorial
	 * 
	 * @param x: Number to take factorial of
	 * @return: Factorial of x
	 */
	public static int factorial  (int x) {
		
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
	public static String[] getSortedStrings (Integer[] arr) throws OutOfRangeException {
		
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
	public static BigInteger stringsToBigInt (String[] arr) {
		
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
	public static String buildArrayString (Integer[] arr) {
		
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
		
		String[] newArr = getSortedStrings(arr); // Use helper method to find the largest combination in Strings
		
		// Now uses a while loop to keep looping under smaller versions until a viable long is found
		Long permutation = (long) 1;
		while (permutation <= factorial(newArr.length)) {
			
			String[] newArrTemp = newArr.clone(); // Make a duplicate to delete from
			
			// Create an array to store the current permutation
			String[] ithLargest = new String[newArrTemp.length];
			
			// Check the ith version and take the leading number out first. We find this by determining that each number's
			//  position in an ith largest combination is determined by i. Each number will remain unchanged during the
			//  first interval of its position! (factorial). After exceeding that threshold, the second largest number 
			//  swaps with it, then the third, and so on. We'll search the possible combinations and remove elements as we
			//  clarify their position recursively
			
			Long currentPermutation = permutation; // Store currentPermutation
			
			for (int i = 1; i <= ithLargest.length; i++) {
				
				// Find the next value in the new BigInteger from the original array
				int newValueIndex = currentPermutation.intValue() / factorial(i);
				
				// Similar to a table, this uses null as a "tombstone" and loops through until a remaining element is found 
				while (newArrTemp[newValueIndex] == null) {
					newValueIndex++;
				}
				
				// Test statements
				// System.out.println(newArr[newValueIndex]);
				
				// Cut the value from the original set of values and add it to the new one.
				ithLargest[i-1] = newArrTemp[newValueIndex];
				newArrTemp[newValueIndex] = null;
				
				// Take the remaining number of possibilities and remove all of the current position's values, finding what's left over
				currentPermutation %= factorial(ithLargest.length+1 - i);
			}
			
			// Test statements
			//for (String s : ithLargest) {
				//System.out.println(s);
			//}
			
			if (stringsToBigInt(ithLargest).compareTo(new BigInteger("9223372036854775807")) < 1) {
				return stringsToBigInt(ithLargest).longValue();
			}
			
			permutation++;
		}
		
		throw new OutOfRangeException(stringsToBigInt(newArr).toString()); // If no solution is found in the permutations, throw the exception
	}
	
	
	
	/**
	 * This method sums the largest numbers that can be formed by each array in the given list.  This method must not alter
	 * the given list.
	 * 
	 * @param list: List of basic arrays to make the biggest numbers out of
	 * @return: BigInteger sum of each array's BigInteger
	 */
	public static BigInteger sum (List<Integer[]> list) {
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
	public static Integer[] findKthLargest (List<Integer[]> list, int k) throws IllegalArgumentException {
		
		//Check if k is a valid position in the list
		if(k < 0 || k >= list.size()){
			throw new IllegalArgumentException("k out of bounds for list size");
		}
		BigInteger[] integerList = new BigInteger[list.size()];
		
		//Loop through the list of Integer arrays and add each BigInteger to integerList.
		for(int i = 0; i < list.size();i++) {
			BigInteger curr = findLargestNumber(list.get(i));
			integerList[i] = curr;
		}
		
		//Create a copy to find the original index of each BigInteger after sorting.
		BigInteger[] copy = new BigInteger[integerList.length];
		for(int i = 0; i < copy.length;i++) {
			copy[i] = integerList[i];
		}
		
		//Then sort the original list
		Arrays.sort(integerList);
		
		BigInteger currentInt = integerList[k];
		
		//Sorts through the list until it finds the position at which the currentInt started as.
		int index = 0;
		for(int i = 0; i < list.size(); i++) {
			if(copy[i] == currentInt) {
				index = i;
				break;
			}
		}
		
		return list.get(index);
		
	}
	
	
	
	/**
	 * This method generates list of integer arrays from an input file, such that each line corresponds to one array of integers 
	 * separated by blank spaces
	 * 
	 * @param filename: Name of the file (inluding .txt) to be read
	 * @return: List of integer arrays from an input file, or an empty list if the file does not exist.
	 */
	public static List<Integer[]> readFile (String filename) {
		//Instantiates the Arraylist to hold the Integer arrays.
		ArrayList<Integer[]> fileList = new ArrayList<Integer[]>();
		
		//File file = new File(filename); //Converts the given filename into a file.
		
		//Creates a scanner that reads the given file.
		try(Scanner in = new Scanner(filename)){
			while(in.hasNextLine()) {
				//For each line the scanner, split the line into strings of individual items
				//and create an Integer array with its length being the number of items.
				String line = in.nextLine();
				String[] items = line.split(" ");
				Integer[] currArr = new Integer[items.length];
				
				for(int i = 0; i < currArr.length; i++) {
					currArr[i] = Integer.parseInt(items[i]);
				}
				fileList.add(currArr);
			}
		}
		
		catch(Exception e) {
			System.out.println("File can't be read " + filename);
		}
		
		return fileList;
	}
		
}