package assignment10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represents a Maximum Binary Heap, and as such implements a PriorityQueue interface written by this course's instructor.
 * A Binary Heap keeps a backing array to represent the tree, and each node added to the tree is at the next available space at the'
 * bottom of the tree (or the next available space in the array) and percolates up until the new node is less than each parent node.
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version: 07:13:23 CS-2420_001 SUM_2023
 */

public class BinaryMaxHeap <E> implements PriorityQueue<E> {
	
	// Fields
	E[] maxHeap;
	int backingArrayLength, size;
	private Comparator<? super E> cmp;
	
	
	/**
	 * @Constructor that instantiates the backing array and relevant fields, assumes natural ordering for comparator
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		
		maxHeap = (E[]) new Object[3];
		backingArrayLength = 3;
		size = 0;
	}
	
	
	
	/**
	 * @Constructor that instantiates the backing array and relevant fields, uses parameter for comparisons
	 * 
	 * @param cmp: Comparator to use
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap (Comparator<? super E> cmp) {
		
		maxHeap = (E[]) new Object[3];;
		backingArrayLength = 3;
		size = 0;
		this.cmp = cmp; // Uses given parameter
	}
	
	
	
	/**
	 * @Constructor that instantiates the backing array and relevant fields, assumes natural ordering for comparator.
	 * Uses a buildHeap helper method to add all of the items from the parameter into a heap in O(N) time
	 * 
	 * @param paramList: List of objects to be added
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap( List<? extends E> list ) {
		
		maxHeap = (E[]) new Object[3];;
		backingArrayLength = 3;
		size = 0;
			
		// Use helper method to buildHeap
		buildHeap(list); 
	}

	

	/**
	 * @Constructor that instantiates the backing array and relevant fields, uses parameter for comparisons. Uses a
	 * buildHeap helper method to add all of the items of the parameter in O(N) time.
	 * 
	 * @param paramList: List of objects to be added
	 * @param cmp: Comparator to use
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap( List<? extends E> list, Comparator<? super E> cmp ) {
		
		maxHeap = (E[]) new Object[3];
		backingArrayLength = 3;
		size = 0;
		this.cmp = cmp; // Uses given parameter
		
		// Use helper method to buildHeap
		buildHeap(list);
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
	
	
	
	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	public void add(E item) {
		
		// Catch case for empty array
		if (this.isEmpty()) {
			
			//System.out.println("Root created: " + item); // Test statement
			maxHeap[0] = item;
			size++; // Creates the root and exits
			return;
		}
		
		size++; // Track the size
		
		// Check if the backingArray needs to be resized
		if (size >= backingArrayLength) {
			this.resize();
		}
		
		// Add this element to the next available space
		maxHeap[ size-1 ] = item;
		//System.out.println( item + " added to index " + (backingArrayLength-size) ); // Test statement
		
		// Percolate up if needed
		percolateUp(size-1);
	}
	
	
	
	/**
	 * Builds a heap from a given list
	 * 
	 * @param list: List of generic objects to be added
	 */
	@SuppressWarnings("unchecked")
	public void	buildHeap (List<? extends E> list) {
		
		// Finds the size for the heap by adding a number of elements needed for each level (2^i) and stopping when the necessary number is reached.
		int count = 0;
		while(backingArrayLength < list.size()) {
			
			backingArrayLength += 2^count;
			count++;
		}
		
		// Make the array of adequate size
		maxHeap = (E[]) new Object[backingArrayLength];
		
		// Add each element from the list to the binary heap.
		for(int i = 0; i < list.size(); i++) {
			
			maxHeap[list.size()+1 - (i+2) ] = list.get(i);
			//System.out.println(maxHeap[list.size() - (i+2) ]); // Test statement
			size++;
		}
	}
	
	
	
	/**
	 * Helper method that changes the backing array to be an adequate length
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		
		// Test statement
		//System.out.println("Resizing at " + size + " for arrayLength of " + backingArrayLength);
		
		// Make a new empty array for the resized array.
		E[] newMaxHeap = (E[])new Object[ (size*2)+1 ];
		
		// Loop through the current backing array and copy its existing elements to the new one
		for(int i = 0; i < backingArrayLength; i++) {
			newMaxHeap[i] = maxHeap[i];
		} 
		
		// Adjust the size tracker and the maxHeap
		backingArrayLength = (size*2)+1;
		maxHeap = newMaxHeap;
		
	}
	
	
	
	/**
	 * Move the given index up the tree till the order is correct. Swap 
	 * the elements at each position when moving up the tree.
	 * 
	 * @param index: Index of the item in the backing array to be percolated.
	 */
	private void percolateUp(int index) {
		
		// Test statements
		//System.out.println("CHILD VALUE: " + maxHeap[index]);
		//System.out.println("PARENT VALUE: " + maxHeap[(index-1)/2]);
		
		// If this child node is greater than its parent (found at (i-1)/2 in a binary heap for the array):
		if (innerCompare(maxHeap[index], maxHeap[ (index-1)/2 ] ) > 0) {
			
			// Store the data in the child node
			E givenData = maxHeap[index]; 
			
			// Swap the two elements at the parent and child
			maxHeap[index] = maxHeap[ (index-1)/2 ];
			maxHeap[ (index-1)/2 ] = givenData;
			
			// Repeat this process at the new position of the added element
			percolateUp( (index-1)/2 );
			
		} else {
			
			//System.out.println(this.toArray());
			return; // Base case, if there's no percolation needed its done
		}
	}
	
	
	
	/**
	 * Moves the given element down from the top of the tree down till it's in
	 * its correct position. The element swaps it position with the lower child till
	 * the children underneath, if any, are both smaller.
	 * 
	 * @param index
	 */
	public void percolateDown (int index) {
		
		// Variables to hold the data of each position
		E givenData = maxHeap[index];
		
		// Trackers for left and right sides
		int lhsIndex = (2*index) + 1;
		int rhsIndex = (2*index) + 2;
		E lhsData; E rhsData;
		
		// Check if the left index exists:
		if(lhsIndex < backingArrayLength) {
			
			lhsData = maxHeap[lhsIndex];
			
			// Check if the right index is inside the array
			if (rhsIndex < backingArrayLength) {
				
				rhsData = maxHeap[rhsIndex];
				boolean lhsGreater;
				
				// Determines if the left or right is bigger
				if (innerCompare(lhsData, rhsData) > 0) {
					lhsGreater = true;
				} else {
					lhsGreater = false;
				}
			
				// If the left side needs to be swapped
				if (lhsGreater && innerCompare(givenData, lhsData) < 0) {
				
					// Swap positions and recurse
					maxHeap[index] = lhsData;
					maxHeap[lhsIndex] = givenData;
					percolateDown(lhsIndex);
					return;
				
				// If the right positions is greater
				} else if (!lhsGreater && innerCompare(givenData, rhsData) < 0) {
					
					// Swap positions and recurse
					maxHeap[index] = rhsData;
					maxHeap[rhsIndex] = givenData;
					percolateDown(rhsIndex);
				}
			
			// If the right index dosn't exist, check only the left:
			} else if (innerCompare(givenData, lhsData) < 0) {
				
				// Swap positions and recurse
				maxHeap[index] = lhsData;
				maxHeap[lhsIndex] = givenData;
				percolateDown(lhsIndex);
				return;
			}
			
		} 
		
		return; // If the left and the right do not exist, return.
				
		/*
		lhsData = maxHeap[lhsIndex];
		rhsData = maxHeap[rhsIndex];
		
		boolean lhsGreater;
		
		// Determines if the left or right is bigger
		if (innerCompare(lhsData, rhsData) > 0) {
			lhsGreater = true;
		} else {
			lhsGreater = false;
		}
		
		// If the left side is greater and needs to be swapped
		if (lhsGreater && innerCompare(givenData, lhsData) < 0) {
			
			// Swap positions and recurse
			maxHeap[index] = lhsData;
			maxHeap[lhsIndex] = givenData;
			percolateDown(lhsIndex);
			return;
			
		// If the right positions is greater
		} else if (!lhsGreater && innerCompare(givenData, rhsData) < 0) {
			
			// Swap positions and recurse
			maxHeap[index] = rhsData;
			maxHeap[rhsIndex] = givenData;
			percolateDown(rhsIndex);
		}
		*/
	}
	
	
	
	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException{
		
		// Catch case for empty heap
		if (this.isEmpty()) {
			throw new NoSuchElementException(); // No item to delete
		}
		
		return (E)maxHeap[0];
	}
	
	
	
	/**
	 * Returns and removes the maximum item this priority queue.
	 * O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extractMax () throws NoSuchElementException {
		
		// Catch case for empty array
		if (this.isEmpty()) {
			throw new NoSuchElementException(); // No item to delete
		}
		
		size--; // Track size
		// Store the max to be returned
		E maxValue = maxHeap[0];
		
		/*
		// Test statement
		System.out.println(maxValue);
		System.out.println(maxHeap[1]);
		System.out.println(maxHeap[2]);
		System.out.println(maxHeap[3]);
		System.out.println();
		*/
			
		// To find the leftmost leaf node, divide the total length in half and subtract 1 from it. The bottom level is the length of 
		//  all the nodes that came before it + 1, therefore the leftmost leaf will be at the total length without all of the nodes 
		//  at the bottom level.
		int leafIndex = (backingArrayLength/2)-1;
		
		// Loop through the bottom level until a null value is found or until its out of bounds
		//while(maxHeap[leafIndex] != null && leafIndex < backingArrayLength) {
		
		// While the tracker is still in bounds:
		while(leafIndex < backingArrayLength) {
			
			leafIndex++;
			
			// Break from the loop if the next value is null
			if (maxHeap[leafIndex+1] == null) {
				break;
			}
		}
		
		// Now that we have the rightmost leaf index, swap it with the root and delete the old max
		maxHeap[0] = maxHeap[leafIndex];
		maxHeap[leafIndex] = null;
		
		//System.out.println(maxHeap[0]); // Test statement
		
		// Percolate the new max down at the root to find the proper value.
		percolateDown(0);
		
		//System.out.println(maxHeap[0]); // Test statement
		//System.out.println();
		
		return maxValue; // Return the deleted root.
	}
	
	
	
	
	
	/**
	 * @return the number of items in this priority queue.
	 * O(1)
	 */
	public int size() { return size; }
	
	
	
	/**
	 * @return length of the backing array
	 * O(1)
	 */
	public int getArrayLength() { return backingArrayLength; }
	
	
	
	/**
	 * @return true if this priority queue is empty, false otherwise.
	 * O(1)
	 */
	public boolean isEmpty() {
		return size == 0; // Return if the size is greater than 0
	}
	
	
	
	/**
	 * Empties this priority queue of items.
	 * O(1)
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		
		// Essentially reset all the values back to their base
		maxHeap = (E[]) new Object[3];;
		backingArrayLength = 3;
		size = 0;
	}
	
	
	
	/** 
	 * Creates and returns an array of the items in this priority queue,
	 * in the same order they appear in the backing array.
	 * O(N)
	 * 
	 * (NOTE: This method is needed for grading purposes. The root item 
	 * must be stored at index 0 in the returned array, regardless of 
	 * whether it is in stored there in the backing array.) 
	 * 
	 * @return: An array containing each element in the heap without any null spaces.
	 */
	public Object[] toArray() {
		
		// Make an empty array to be added to and a tracker for its index
		Object[] returnVal = new Object[size];
		int returnValIndex = 0;
		
		// Add each non-null value in the backing array to this new one to skip the "gaps"
		for(E currentObject : maxHeap) {
			
			if(currentObject != null) {
				
				// Add it to the array and increment the tracking index
				returnVal[returnValIndex] = currentObject;
				returnValIndex++;
			}
		}
		
		return returnVal; // Return the completed array.
	}

}
