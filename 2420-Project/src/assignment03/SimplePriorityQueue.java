package assignment03; //update comments with provided interface

/**
 * Represents a simple PriorityQueue with insert, find/delete max, determine if an item is present, 
 * return the number of elements, and insert all of the items from a collection.
 * 
 * @author: Everett Oglesby and Parker Catten
 * @version: CS-2420_020 FA-2023
 */

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>{
	
	// Fields
	private Comparator<? super E> cmp = null;
	private E[] backingArray;
	
	
	/**
	 * @Constructor that sets up relevant fields, assumes generic objects use comparable
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		
		cmp = null;
		backingArray = (E[]) new Object[1];
	}
	
	
	
	/**
	 * @Constructor that instantiates relevant fields, will sort the generic objects with parameter
	 * 
	 * @param cmp: Comparator used to sort generic objects
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		
		this.cmp = cmp;
		backingArray = (E[]) new Object[1];
	}

	
	
	/**
	 * Retrieves the max element without removing it
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	public E findMax() throws NoSuchElementException {
		
		// Catch case for empty array
		if(backingArray.length == 0) {
			throw new NoSuchElementException();
		}
		
		// Get the largest element at the end of the backing array
		return backingArray[size()-1];
	}

	
	
	/**
	 * Retrieves and removes the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@SuppressWarnings("unchecked")
	public E deleteMax() throws NoSuchElementException {
		
		// Catch case for an empty array
		if(backingArray.length == 0) {
			throw new NoSuchElementException();
		}
		
		// Find the largest element at the last index of the backing array
		E max = backingArray[backingArray.length-1];
		
		// Copy everything from the existing array into the new one except for the max
		E[] newArray = (E[]) new Object[backingArray.length-1];
		
		for(int i = 0; i < backingArray.length -1 ; i++) {
			newArray[i] = backingArray[i];
		}
		
		backingArray = newArray;
		return max; //Return the previous max element
	}
	
	
	
	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	public void insertAll(Collection<? extends E> coll) {
		
		for(E item : coll) { // Insert every item from the collection
			insert(item);
			//System.out.println(toString()); // Test statement
		}
	}
	
	

	/**
	 * Indicates whether this priority queue contains the specified element.
	 * 
	 * @param item - the element to be checked for containment in this priority queue
	 * @return true if the item is contained in this priority queue
	 */
	public boolean contains(E item) {
		
		// If the backing array is empty, add the given item at the first index
		if(backingArray.length == 0) {
			return false;
		}
		
		//If their is only one item in the array, return the item at the first index
		if(backingArray.length == 1) {
			if(backingArray[0].equals(item)) {
				return true;
			}
			return false;
		}
		
		//Find the index of the element
		int itemIndex = binarySearch(item);
		
		//If the item at the item index matches the give item, return true
		if(backingArray[itemIndex-1].equals(item)) {
			return true;
		}
		//If none of the elements match the given element, return false
		return false;
	}
	
	
	
	/**
	 * @return the number of elements in this priority queue
	 */
	public int size () {
		return backingArray.length;
	}

	
	
	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	public boolean isEmpty () {
		//If the length is greater than zero return true
		if(backingArray.length <= 0) {
			clear(); // for edge cases
			return true;
		}
		return false;
	}

	
	
	/**
	 * Removes all of the elements from this priority queue. The queue will be
	 * empty when this call returns.
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		backingArray = (E[]) new Object[1];
	}
	
	
	
	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@SuppressWarnings("unchecked")
	public void insert (E item) {
		
		// If the backing array is empty, add the given item at the first index
		if (backingArray.length == 2) {
			newArray[0] = item;
			backingArray = newArray;
			return;
		}
		
		// Create a new generic array one index longer then the current array
		E[] newArray = (E[]) new Object[backingArray.length + 1];
		// System.out.println("The length of the new array is " + newArray.length); // Test statement
		
		//Flag for if the item has been added to the new array
		int itemAdded = 0;
		
		//Finds the index of the item in the array
		int itemIndex = binarySearch(item);	
		
		//System.out.println(); //Return the index of the current item and the array length to the console
		//System.out.println("Item index = " + itemIndex + " and the array length is " + newArray.length);
		
		for(int i = 0; i < backingArray.length; i++) {
			//If the current index is equal to the item index, add the item
			//at the current index.
			if((i == itemIndex || itemIndex < 0) && (itemAdded == 0)) {//Also checks that the item hasn't been added yet
				newArray[i] = item;
				//Add one to the item added count to push every following object 
				//back one index
				itemAdded ++;
			}
			//Add the current index of the backing array plus one if the item
			//has been added
			newArray[i + itemAdded] = backingArray[i];
		}
		
		//If the item hasn't been added yet, add the item to the end of the list
		if(itemAdded == 0) {
			newArray[backingArray.length] = item;
		}

		//Set the new array equal the backing array
		backingArray = newArray;
	}
	
	
	
	/**
	 * Computes a binary search to find the index of a given item in
	 * the backing array.
	 * 
	 * @param item
	 * @throws NoSuchElementException if the array doesn't have that index
	 */
	public int binarySearch(E item) throws NoSuchElementException {
		
		// Sets up fields for the binary search
		int high = backingArray.length;
		int mid, low = 0;
		
		while(low < high) {
			
			// Evaluate the mid index each iteration
			mid = ((high - low) / 2) + low;
			
			System.out.println("Running"); // Test statement
			
			// If the item is greater than the midpoint:
			if (innerCompare(item, backingArray[mid]) > 0) {
				
				low = mid; // The upper half of this set is the target, make the mid the new low
				System.out.println("GREATER than mid"); // Test statement
				
			} else if (innerCompare(item, backingArray[mid]) < 0) {
				
				
				high = mid; // If its lower, the new high becomes the mid to search the lower half
				System.out.println("LOWER than mid"); // Test statement
				
			} else { // If it's equivalent, the index is found
				
				//System.out.println("MATCH FOUND"); // Test statement 
				return mid;
			}
		}
		
		// Return 0 if the high is never above the low (if the list is size 1)
		return mid;
	}
	
	
	
	/**
	 * Determines whether to use the comparator or comparable for
	 * the comparisons.
	 * 
	 * @param lhs: Left hand side
	 * @param rhs: Right hand side
	 * @return the compareTo value of the objects
	 */
	@SuppressWarnings("unchecked")
	public int innerCompare(E lhs, E rhs) {
		
		// Catch case for null left or right child objects
		if (lhs == null && rhs != null) {
			
			return -1; // Null is less than existing
			
		// Catch case for null right child
		} else if (rhs == null && lhs != null) {
			
			return 1; // Existing is greater than null
		
		} else if (rhs == null && lhs == null) { // If both are null:
			
			//System.out.println("TWO NULL COMPARISIONS"); // Test statement
			return 0; // Equivalent.
		}
		
		
		// If there is no comparator, assume that the parameters are comparables so use their parent's compareTo
		if(cmp == null) {
			
			return ( (Comparable<? super E>)(lhs) ).compareTo( rhs );
		}
		
		// If there is a comparator, use it
		return cmp.compare(lhs, rhs);
	}
	
	
	/*
	 * Converts a SimplePriorityQueue into a String
	 */
	public String toString() {

		//String to return
		String queueToString = "";
		
		//Loop through each index in the backing array and add the item to the the queueToString
		for(int i = 0; i < backingArray.length; i++) {
			String current = backingArray[i].toString();
			queueToString = queueToString + ", " + current;
		}
		return queueToString;
	}
}
