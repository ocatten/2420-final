package assign10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Prof. Parker, Everett Oglesby and Parker Catten
 * @version November 27, 2023
 */
public class FindKLargest {
	
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
		
		//Check that K is not less than 0 or more than the number of items.
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException("There are " + items.size() + " number of items in the list, so " + k 
					+ " is out of the range for the list!");
		}
		

		//Create a max heap to sort the items in the given list, items.
		BinaryMaxHeap<E> maxHeap = new BinaryMaxHeap<E>(items);
		
		List<E> kLargest = new ArrayList<E>();//ArrayList to how the k number of largest items.
		
		//While k is greater then zero, add the max item of the heap next to the kLargest and increment k down one.
		while(k > 0) {
			kLargest.add(maxHeap.extractMax());
			k--;
		}
		
		return kLargest;
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
		
		//Check that K is not less than 0 or more than the number of items.
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException("There are " + items.size() + " number of items in the list, so " + k 
					+ " is out of the range for the list!");
		}
		
		//Create a max heap with the given list of items and the given comparator.
		BinaryMaxHeap<E> maxHeap = new BinaryMaxHeap<E>(items,cmp);
		
		List<E> kLargest = new ArrayList<E>();//ArrayList to how the k number of largest items.
		
		//While k is greater then zero, add the max item of the heap next to the kLargest and increment k down one.
		while(k > 0) {
			kLargest.add(maxHeap.extractMax());
			k--;
		}
		return kLargest;
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
		
		//Check that K is not less than 0 or more than the number of items.
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException("There are " + items.size() + " number of items in the list, so " + k 
					+ " is out of the range for the list!");
		}
				
		//Sorts the items via java's sort routine using Comparable since no Comparator is given.
		items.sort(null);
				
		List<E> kLargest = new ArrayList<E>();//ArrayList to how the k number of largest items.
			
		//Since items is now sorted, add the first k number of elements from items to kLargest.
		for(int i = 0; i < k; i++) {
			kLargest.add(items.get(i));
		}
		
		return kLargest;
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
		//Check that K is not less than 0 or more than the number of items.
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException("There are " + items.size() + " number of items in the list, so " + k 
					+ " is out of the range for the list!");
		}
				
		//Sorts the items via java's sort routine using the given Comparator, cmp.
		items.sort(cmp);
				
		List<E> kLargest = new ArrayList<E>();//ArrayList to how the k number of largest items.
			
		//Since items is now sorted, add the first k number of elements from items to kLargest.
		for(int i = 0; i < k; i++) {
			kLargest.add(items.get(i));
		}
		
		return kLargest;
	}
}