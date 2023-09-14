package assignment10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Everett Oglesby and Parker Catten
 * @version 07:17:23 CS-2420_001 SUM-2023
 */

public class FindKLargest < E extends Comparable<? super E> > { 
	
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		
		// Catch statement for if k is out of bounds
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException(k + " is out of bounds for the given list of size " + items.size());
		}
		
		// Create a binary heap from the items given
		BinaryMaxHeap<E> maxHeap = new BinaryMaxHeap<E>(items);
		//System.out.println(maxHeap.peek()); // Test statement
		//System.out.println();
		
		// List of items to return
		ArrayList<E> kLargestItems = new ArrayList<E>();
		
		// Extracts the max value K times to get a list of those items
		for(int i = 0; i < k; i++) {
			
			kLargestItems.add((E)maxHeap.extractMax());
			//System.out.println(kLargestItems); // Test statement
		}
		
		return kLargestItems; // Return completed list
	}
	
	

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		
		// Catch statement for if k is out of bounds
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException(k + " is out of bounds for the given list");
		}
		
		// Create a binary heap from the items given with the provided comparator
		BinaryMaxHeap<E> maxHeap = new BinaryMaxHeap<E>(items, cmp);
		
		// List of items to return
		ArrayList<E> KLargestItems = new ArrayList<E>();
		
		// Extracts the max value K times to get a list of those items
		for(int i = 0; i < k; i++) {
			
			KLargestItems.add ((E)maxHeap.extractMax() );
		}
		
		return KLargestItems; // Return completed list
	}

	
	
	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {

		// Catch statement for if k is out of bounds
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException(k + " is out of bounds for the given list");
		}
		
		// Items to return
		ArrayList<E> KLargestItems = new ArrayList<E>();
		
		// Sort with a null comparator
		items.sort(null);
		
		// Add each item to the list moving from the end
		for(int i = 0; i < k; i++) {
			//System.out.println("adding " + items.get(items.size()-1-i) + " to list");
			KLargestItems.add(items.get(items.size()-1-i));
		}
		
		return KLargestItems; // Return completed list
	}
	
	

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {

		// Catch statement for if k is out of bounds
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException(k + " is out of bounds for the given list");
		}
		
		// Items to return
		ArrayList<E> KLargestItems = new ArrayList<E>();
		
		// Sort with given comparator
		items.sort(cmp);
		
		// Add each item to the list moving from the end
		for(int i = 0; i < k; i++) {
			KLargestItems.add(items.get(items.size()-1-i));
		}
		
		return KLargestItems; // Return completed list
	}
}