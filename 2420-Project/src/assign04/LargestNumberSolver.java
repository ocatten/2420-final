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
	
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * 
	 * @param cmp: Comparator object that's used to sort the generic objects
	 */
	public static <T> void insertionSort (T[] arr, Comparator<? super T> cmp) {
		
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
		return null;
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
		
		return 0;
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
