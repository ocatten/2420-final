package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/*
 * A simple priority queue that utilizes the PriorityQueue interface. Sorts objects
 * from low to high using a binary search which sorts the objects as they're added
 * to the queue. Finding the max and min of the queue are O(1) operations.
 * 
 * @Author Everett Oglesby and Parker Catten 
 * September 12, 2023
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>{

	//The capacity of the backing array is doubled as needed for the Simple Priority Queue.
	@SuppressWarnings("unchecked")
	private E[] array;
	private int size = 0;
	private int startingSize = 8;
	private Comparator<? super E> cmp = null;
	
	/*
	 * This constructor orders objects using there natural ordering through comparable.
	 */
	public SimplePriorityQueue() {
		array = (E[]) new Object[startingSize];
	}
	
	
	/*
	 * This constructor orders objects using the given comparator.
	 */
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		this.cmp = cmp;
		array = (E[]) new Object[startingSize];
	}


	/**
	 * Retrieves, but does not remove, the maximum element in this priority
	 * queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	public E findMax() throws NoSuchElementException{
		//Throws NoSuchElementException if the array is empty
		if(isEmpty()) {
			throw new NoSuchElementException("No objects exist in the queue :/");
		}
		else {
			return array[size-1];
		}
	}

	/**
	 * Retrieves and removes the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	public E deleteMax() throws NoSuchElementException{
		//Throws NoSuchElementException if the array is empty
		if(isEmpty()) {
			throw new NoSuchElementException("No objects exist in the queue :/");
		}
		
		E max =  array[size-1];//The max element to be returned and deleted
		
		size--;//Minus the size by one to remove the max from the list
		
		return max;
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@SuppressWarnings("unchecked")
	public void insert(E item) {
		
		//If a null item is inserted throw NoSuchElement exception.
		if(item == null) {
			throw new NoSuchElementException("Null inserted");
		}
		
		//This is the only place where the array is resized in the code since insert is the 
		//only place items are added to the queue.
		if(size == array.length) {
			//Double the capacity of the array
			int length = size*2;
			E[] tempArray = (E[]) new Object[length];
			//Copy the items in the array over to the newly resized array.
			for(int i = 0; i < size; i++) {
				tempArray[i] = array[i];
			}
	        array = tempArray;
		}
		
		//If the the array is empty, create a new array with just the given item.
		if(size == 0) {
			array[0] = item;
			size++;
		}
		else {
			//Create an array to hold the array with the inserted item
			size++;
			E[] tempArray = (E[]) new Object[size];
			
			
			//Flag for if the item has been added or not
			int itemAdded = 0;
			
			int index = binarySearch(item);//Finds the index of the item to be added
		
			for(int i = 0; i < size; i++) {
				//If the item hasn't been added, check to see if the item can be added.
				if(itemAdded == 0) {
					if(i == index || index < 0) {
						//Add the item to the current index and add one to itemAdded
						tempArray[i] = item;
						itemAdded++;
					}
				}	
				
				//Checks so that an extra variable isn't added at the end of the list.
				if(i + itemAdded < size) {
					tempArray[i + itemAdded] = array[i];
				}		
			}
			//Set the array equal to the newly created tempArray.
			array = tempArray;
			
		}
		
	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	public void insertAll(Collection<? extends E> coll) {
		//Insert each item into the priority queue from the given collection one at a time.
		for(E item: coll) {
			insert(item);
		}
	}

	/**
	 * Indicates whether this priority queue contains the specified element.
	 * 
	 * @param item - the element to be checked for containment in this priority queue
	 */
	public boolean contains(E item) {
		
		//If given item is null, throw NoSuchElementException
		if(item == null) {
			throw new NoSuchElementException("Null inserted");
		}
		
		
		//If the list is empty then return false.
		if(size == 0) {
			return false;
		}
		
		//If the array has only one item, check the items to see if they match.
		if(size == 1) {
			if(array[0].equals(item)) {
				return true;
			}
			return false;
		}
		
		int index = binarySearch(item);//Finds the index of the given item
		
		//If the item at the given index matches the given item, return true.
		if(array[index-1].equals(item)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @return the number of elements in this priority queue
	 */
	public int size() {
		
		return size;
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		//If the size is equal to zero, the array is empty
		return size == 0;
	}

	/**
	 * Removes all of the elements from this priority queue. The queue will be
	 * empty when this call returns.
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		array = (E[]) new Object[startingSize]; // Resets the array
		size = 0;
	}
	
	/*
	 * Finds the index at which an item should be placed at in the priority queue.
	 */
	@SuppressWarnings("unchecked")
	public int binarySearch(E item) {
		
		//Fields for binary search
		int high = size-1;
		int mid = 0;
		int low = 0;
		
		while(low <= high) {
			
			//Find the new mid index for each iteration
			mid = ((high + low) / 2);
			

			
			//If the item is greater than the midpoint, set the low as the mid equal to the low, making
			//which makes the binary search through the upper half of the array.
			if (compareFind(item, array[mid]) > 0) {
				low = mid + 1;
				
			} 
			//If the item is lower than the midpoint, then the high becomes the new midpoint which makes the
			//binary search through the lower half of the array.
			else if (compareFind(item, array[mid]) < 0) {
				high = mid - 1;
			}
			//Return the midpoint
			else {
				return mid;
			}
		}
		
		//If the high is equal to the low return the mid
		return mid;
	}

	
	/**
	 * Checks for nulls and uses either comparable or comparator depending on which 
	 * one is given.
	 */
	@SuppressWarnings("unchecked")
	public int compareFind(E lhs, E rhs) {
		
		//First check for nulls
		if(lhs == null && rhs == null) {
			return 0; //Neither null value is larger.
		}else if(rhs == null && lhs != null) {
			return 1; //The right side must be bigger since the left side is null
		} else if(rhs != null && lhs == null) {
			return -1; //The left side must be bigger since the right side is null
		}
		
		//If no comparator is given then sort with comparable
		if(cmp == null) {
			
			return ((Comparable<? super E>)(lhs)).compareTo(rhs);
		}
		
		//If a comparator is given use the comparator
		return cmp.compare(lhs, rhs);
	}
	
	/*
	 * Converts a SimplePriorityQueue into a String
	 */
	public String toString() {

		//String to return
		String queueToString = "";
		
		//Loop through each index in the backing array and add the item to the the queueToString
		for(int i = 0; i < array.length; i++) {
			String current = array[i].toString();
			queueToString = queueToString + ", " + current;
		}
		return queueToString;
	}
}
