package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * This method is used to create and interact with a binary max heapBackingArr. It implements the PriorityQueue interface
 * and is able to uses applicable methods such as add, clear, remove, etc. The binary max heapBackingArr is represented 
 * as a tree with each node being larger than the nodes below it. When a node is added to the tree, it is added
 * to the next available space and is percolated up until all the nodes directly above it are larger and all the 
 * nodes below are smaller.	
 * 
 * @Authors Everett Oglesby and Parker Catten
 */
public class BinaryMaxheapBackingArr<E> implements PriorityQueue<E>{
	
	E[] heapBackingArr;//Stores the heapBackingArr as a basic array.
	int heapBackingArrLength;//Stores the length of the backing array.
	
	int size; //Tracker for the number of items in the binary heapBackingArr.

	private Comparator<? super E> cmp;//Empty comparator to be replaced if a comparator is given.
	/*
	 * This constructor is used to create a empty binary heapBackingArr and
	 * it assumes elements are ordered using their natural ordering.
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxheapBackingArr() {
		
	}

	/*
	 * If this constructor is used to create a empty binary heapBackingArr, then elements
	 * are ordered using the provided Comparator.
	 */
	public BinaryMaxheapBackingArr(Comparator<? super E>) {
		
	}

	/*
	 * This constructor creates a binary heapBackingArr from the given list using the
	 * given element's natural ordering.
	 */
	public BinaryMaxheapBackingArr(List<? extends E>) {
		
	}
	
	/*
	 * This constructor creates a binary heapBackingArr from the list of generic elements
	 * using the given comparator.
	 */
	public BinaryMaxheapBackingArr(List<? extends E>, Comparator<? super E>) {
		
	}


	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	public void add(E item) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns and removes the maximum item this priority queue.
	 * O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extractMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the number of items in this priority queue.
	 * O(1)
	 */
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise.
	 * O(1)
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Empties this priority queue of items.
	 * O(1)
	 */
	public void clear() {
		//Clear the heap by setting each value to default
		heapBackingArrLength = 3;
		heapBackingArr = (E[]) new Object[heapBackingArrLength];
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
	 */
	public Object[] toArray() {
		
		Object[] toArr = new Object[size];//Object array to be returned
		
		int toArrIndex = 0;//Tracker for the current object to be placed in the toArr
		
		
		//Loop through the whole backing array
		for(int i = 0; i < heapBackingArr.length;i++) {
			
			//If the current object isn't null then add it to the next open position in the toArr.
			if(heapBackingArr != null) {
				toArr[toArrIndex] = heapBackingArr[i];
				toArrIndex++;
			}
		}
		
		return toArr;
	}
	
	
	//-------Helper Methods-------------------------------------------------//
	
	/*
	 * Determines whether to compare the two given items via Comparable or Comparator,
	 * then it compares the items and determines which is larger. Returns 1 if the left 
	 * hand side is greater and -1 if the right hand side is greater.
	 */
	public int innerCompare(E lhs, E rhs) {
		//First check for either side being null
		if(lhs == null || rhs == null) {
			if(lhs != null && rhs == null) {
				return 1; //The lhs is greater since this rhs is empty.
			}
			else if(lhs == null && rhs != null) {
				return 1; //The rhs is greater since this lhs is empty.
			}
			//If they are both null return 0 since they are equal.
			return 0;
		}
		else {
			//Use natural ordering via Comparable if no Comparator is given.
			if(cmp == null) {
				int naturalOrdering = ((Comparable<? super E>)(lhs)).compareTo(rhs );
				return naturalOrdering;
			}
			//Finally use a comparator if one is given and neither item is null.
			return cmp.compare(lhs,rhs);
		}
	}
	
	/*
	 * Builds a binary heapBackingArr from the given generic list.
	 */
	public void buildheapBackingArr(List<? extends E> list) {
		//Creates a backing array with its size being a full last level of the tree representation.
		int i = 1;
		while(list.size() >= heapBackingArrLength) {
			heapBackingArrLength = 2^i;
			i++;
		}
		heapBackingArr = (E[]) new Object[heapBackingArrLength];
		
		// Add each element from the list to the binary heapBackingArr.
		for(int j = 0; j < list.size(); j++) {				
			heapBackingArr[list.size()+1 - (i+2)] = list.get(i);

			size++;
		}
	}
	
	/*
	 * Percolates the current item up the binary max heapBackingArr until no larger smaller item exists
	 * directly above it. The given index is the position of the item being perculated up.
	 */
	public void perculateUp(int index) {
		
		
		//Determines if the current node is greater than its parent
		if (innerCompare(heapBackingArr[index], heapBackingArr[(index-1)/2]) > 0) {
			
			//Data at the current index
			E lowerData = heapBackingArr[index];
			
			//Since the child is larger, swap the parent and child.
			heapBackingArr[index] = heapBackingArr[ (index-1)/2 ];
			heapBackingArr[ (index-1)/2 ] = lowerData;
			
			//Check the parent to see if the element can be traverse the heapBackingArr even higher.
			perculateUp((index-1)/2);
			
		} else {
			return; //Do nothing since the parent is larger than the current child.
		}
	}
	
	
	/*
	 * Percolates the current item down the binary heapBackingArr until no larger item exists anywhere below it. The 
	 * 
	 */
	public void perculateDown() {
		
	}
}