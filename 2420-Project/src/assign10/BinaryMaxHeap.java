package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This method is used to create and interact with a binary max heap. It implements the PriorityQueue interface
 * and is able to uses applicable methods such as add, clear, remove, etc. The binary max backingArray is represented 
 * as a tree with each node being larger than the nodes below it. When a node is added to the tree, it is added
 * to the next available space and is percolated up until all the nodes directly above it are larger and all the 
 * nodes below are smaller.
 * 
 * @author: Everett Oglesby & Parker Catten
 * @version: 11:28:23 FA-23 2420_020
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E>{
	
	// Fields
	E[] backingArray; // Stores the heap as a basic array.
	int length = 0; // Stores the length of the backing array.
	int size = 0; // Tracker for the number of items in the binary heap.
	private Comparator<? super E> cmp = null; // Empty comparator to be replaced if a comparator is given.

	
	/**
	 * @constructor to instantiate relevant fields, assumes natural ordering for comparator object
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap () {
		 
		backingArray = (E[]) new Object[3];
		length = 3;
		size = 0;
	}
	
	
	
	/**
	 * @constructor that uses given constructor for ordering while setting up relevant fields
	 * @param cmp: Constructor to use
	 */
	public BinaryMaxHeap (Comparator<? super E> cmp) {
		
		this();
		this.cmp = cmp; // Call the basic constructor and instantiate the comparator obj
	}
	
	
	
	/**
	 * @constructor that builds a heap out of the list in the parameter while setting up relevant fields
	 * @param elements: Elements that will build the new heap
	 */
	public BinaryMaxHeap (List<? extends E> elements) {
		
		this();
		buildHeap(elements); // Uses helper method to create a new heap in O(N)
	}
	
	
	
	/**
	 * @constructor that builds the heap out of the list in the parameter with using the given comparator while setting up relevant fields
	 * 				and uses the given constructor for future comparisons
	 * @param elements: Elements that will build the new heap
	 * @param cmp: Constructor to use
	 */
	public BinaryMaxHeap (List<? extends E> elements, Comparator<? super E> cmp) {
		
		this();
		buildHeap(elements);  // Uses helper method to create a new heap in O(N)
		this.cmp = cmp; // Call the basic constructor and instantiate the comparator obj
	}
	
	
	
	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item: Item to add
	 */
	public void add (E item) {
		
		// Catch case for an empty heap
		if (this.isEmpty()) {
					
			backingArray[0] = item;
			size++; // Update the size tracker and exit the method
			return;
		}
		else {
			size++; // Update size to check if the array needs resizing
			
			// Catch case if the backing array needs resizing
			if (size >= length) {
				this.resize();
			}
					
			// Find next available space and add new item to it
			backingArray[ size-1 ] = item;
			
			percolateUp(size-1); // Percolate up to get the correct space
		}
	}
	
	
	
	/**
	 * Helper method to adjust the backing array as needed
	 */
	@SuppressWarnings("unchecked")
	private void resize () {
		
		// Make a new array to store the values
		E[] newBackingArray = (E[])new Object[ (size*2)+1 ];
		
		// Add each element from the previous array to the new one
		for(int i = 0; i < length; i++) {
			newBackingArray[i] = backingArray[i];
		} 
		
		// Replace the old array and update size tracker
		length = (size*2)+1;
		backingArray = newBackingArray;
	}
	
	

	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek () throws NoSuchElementException {
		
		
		
		// Empty heap catch case
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
				
		return backingArray[0];
	}

	
	
	/**
	 * Returns and removes the maximum item this priority queue.
	 * O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extractMax () throws NoSuchElementException {

		// Empty heap catch case
		if (size == 0) {
			throw new NoSuchElementException();
		}
		
		size--; // Decrease size tracker
		E oldMax = backingArray[0]; // Store the deleted max
		
		
		
		//Swap the positions of the end leaf and the max item.
		backingArray[0] = backingArray[size];
		backingArray[size] =  backingArray[0];
		
		//Percolate the new item at the max down to find the new max and bring the item back to the bottom level.
		percolateDown(0); 
		
		
		//Set the last left index to null
		backingArray[size] = null;
		
		return oldMax; // Return the extracted value
	}
	
	

	/**
	 * @return the number of items in this priority queue. O(1)
	 */
	public int size () {
		return size;
	}

	
	
	/**
	 * @return true if this priority queue is empty, false otherwise. O(1)
	 */
	public boolean isEmpty () {
		return (size == 0);
	}
	
	

	/**
	 * Empties this priority queue of items. O(1)
	 */
	@SuppressWarnings("unchecked")
	public void clear () {

		// Reset all fields
		backingArray = (E[]) new Object[3];;
		length = 3;
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
	 * @return: Newly created array from all items in the heap
	 */
	public Object[] toArray () {
		
		// Make an array that will be returned with an int to keep position
		Object[] heapArray = new Object[size];
		int index = 0;
				
		// Add all the existing objects from the old array into the new one
		for(E currentNode : backingArray) {
					
			if(currentNode != null) {
						
				// Add currentNode to the array
				heapArray[index] = currentNode;
				index++; // Increment the index to add to next
			}
		}
				
		return heapArray; // Return the completed array.
	}
	
	
	
	/**
	 * Determines whether to compare the two given items via Comparable or Comparator,
	 * then it compares the items and determines which is larger.
	 * 
	 * @param lhs: Left-hand variable we'll be making comparisons to
	 * @param rhs: Right-hand variable we'll be comparing with
	 * @return: 1 if lhs > rhs, -1 if lhs < rhs, and 0 if lhs == rhs
	 */
	@SuppressWarnings("unchecked")
	public int innerCompare(E lhs, E rhs) {
		
		// First check for either side being null
		if(lhs == null || rhs == null) {
			
			if(lhs != null && rhs == null) {
				return 1; // The lhs is greater since this rhs is empty.
			
			} else if(lhs == null && rhs != null) {
				return -1; // The rhs is greater since this lhs is empty.
			}
			
			// If they are both null return 0 since they are equal.
			return 0;
		
		} else {
			// Use natural ordering via Comparable if no Comparator is given.
			if(cmp != null) {
				
				// Finally use a comparator if one is given and neither item is null.
				return cmp.compare(lhs, rhs);
			}
			
			// Catch case for generic comparables
			return ((Comparable<? super E>)(lhs)).compareTo(rhs );
		}
	}
	
	
	
	/**
	 * Builds a binary heap from the given generic list. O(N)
	 * @param list: List to build from
	 */
	@SuppressWarnings("unchecked")
	public void buildHeap(List<? extends E> list) {
		clear();//Resets the heap to its default state.
		
		// Creates a backing array with its size being a full last level of the tree representation.
		int i = 1;
		while(list.size() > length) {
			length *= 2;
			length ++;
			i++;
		}
		
		backingArray = (E[]) new Object[length];
		
		// Add each element from the list to the binary heap.
	    for (int j = 0; j < list.size(); j++) {
	        backingArray[j] = list.get(j);
	        size++;
	    }

	    // Start from the last non-leaf node and percolate down to the root.
	    for (int k = (size / 2) - 1; k >= 0; k--) {
	        percolateDown(k);
	    }
//		
//		// Add each element from the list to the binary heap.
//		for(int j = 0; j < list.size(); j++) {		
//			
//			add(list.get(j));
//		}
	}
	
	
	
	/**
	 * Percolates the current item up the binary max heap until no larger smaller item exists
	 * directly above it.
	 * 
	 * @param index: Item to percolate up
	 */
	public void percolateUp(int index) {
		
		// Determines if the current node is greater than its parent
		if (innerCompare(backingArray[index], backingArray[(index-1)/2]) > 0) {
			
			// Data at the current index
			E childData = backingArray[index];
			
			// Since the child is larger, swap the parent and child.
			backingArray[index] = backingArray[ (index-1)/2 ];
			backingArray[ (index-1)/2 ] = childData;
			
			// Check the parent to see if the element can be traverse the heap even higher.
			percolateUp((index-1)/2);
			
		} else {
			return; // Do nothing since the parent is larger than the current child.
		}
	}
	
	
	
	/**
	 * Percolates the current item down the binary heap until no larger item exists anywhere below it. 
	 * 
	 * @param index: Item to percolate down.
	 */
	public void percolateDown(int index) {
		
		// Store value at the parameter
		E currentNode = backingArray[index];
				
		// Create variables for the left and right sides
		int leftIndex = (2*index) + 1;
		int rightIndex = (2*index) + 2;
		int greaterChildIndex;
		
		E leftData = null; E rightData = null; 
		E greaterChild = null;
		
		
		try { // Check if the left child is in bounds
			leftData = backingArray[leftIndex];
		} catch(Exception e) {
			
		}
		
		try { // Check if the right child is in bounds
			rightData = backingArray[rightIndex];
		} catch(Exception e) {
			
			
		}
		
		// Catch case if neither are within the backing array, done percolating
		if(rightData == null && leftData == null) {
			return;
		}
		
		// Find which child is greater
		boolean leftGreater = (innerCompare(backingArray[leftIndex], backingArray[rightIndex]) > 0);
		
		// Assign the greater child to the left or the right depending on the result of the comparison
		if(leftGreater) {
			greaterChildIndex = leftIndex;
		} else {
			greaterChildIndex = rightIndex;
		}
		
		greaterChild = backingArray[greaterChildIndex];
		
		// Now that we have the greater child, percolate down with it.
		backingArray[index] = greaterChild;
		backingArray[greaterChildIndex] = currentNode;
		percolateDown(greaterChildIndex);
		return; // Remove call from call stack once operations are complete
	}
	
	
	
	/** 
	 * @return the length of the backing array
	 */
	public int getLength () {
		return length;
	}
	
	
	
	/**
	 * Helper method to replace the max value with the parameter for testing
	 * 
	 * @param item: Item to replace max with
	 */
	public void replaceMax (E item) {
		
		backingArray[0] = item;
	}
	
}
