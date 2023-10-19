package assign06;

import java.util.NoSuchElementException;


/**
 * This is a stack data structure with a SinglyLinkedList object used as the backing structure.
 * This structure is a First-In, Last-Out list of elements that can push, peek, or pop the top
 * of the stack. Used in The WebBrowser application.
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 10:19:23
 * 
 * @param <E> - the type of elements contained in the stack
 */
public class LinkedListStack<E> {
	
	
	// Fields
	SinglyLinkedList<E> backingList;
	
	
	/**
	 * @Constructor that sets up the backing SinglyLinkedList member variable for the stack.
	 */
	public LinkedListStack () {
		backingList = new SinglyLinkedList<E>();
	}

	
	
	/**
	 * Removes all of the elements from the stack by clearing the backingList
	 */
	public void clear() {
		backingList.clear();
	}
	
	
	
	/**
	 * @return true if the stack is empty, false otherwise. Uses backingList helper method to check.
	 */
	public boolean isEmpty() {
		return backingList.isEmpty();
	}

	
	
	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E peek() throws NoSuchElementException{
		return backingList.getFirst(); // Uses the head of the list as top of stack
	}
	
	
	
	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E pop() throws NoSuchElementException{
		return backingList.deleteFirst(); // Uses the head of the list as top of stack
	}
	
	
	
	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	public void push(E element) {
		
		backingList.insertFirst(element); // Uses the head of the list as top of stack
	}
	
	/**
	 * @return the number of elements in the stack
	 */
	public int size() {
		return backingList.size(); // Use the backing list's internal method.
	}
}
