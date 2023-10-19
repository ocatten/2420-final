package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class SinglyLinkedList<E> implements List<E>{
	
	//Tracker for the head of the list
	public Node head = null;

	
	private int listSize = 0;
	
	/*
	 * Default constructor for the singly linked list
	 */
	public SinglyLinkedList() {
		
	}
	
	/**
	 * Each item in the linked list exists as a node with generic data and trackers to the
	 * next node. This allows O(1) behavior for getting, adding and removing the head
	 * of the list.
	 */
	class Node{
		
		E data;
		
		//Tracker for the next node
		Node next;

		
		/**
		 * Constructor that instantiates the node with the given data.
		 * 
		 * @param data: The data given to the current node
		 */
		public Node(E data) {
			this.next = null;
			this.data = data;
		}
		
	}

	
	/**
	 * Inserts an element at the beginning of the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @param element - the element to add
	 */
	public void insertFirst(E element) {
		
		Node newNode = new Node(element);
		
		//If the list is empty then just set the newly created node with the given
		//element as the head.
		if(size() == 0) {
			head = newNode;
		}
		//If the head isn't empty then set the head to the next node from the current
		//node and set the current node as the head.
		else {
			newNode.next = head;
			head = newNode;
		}
		
		//Increase the size of the list by 1.
		listSize ++;
	}

	/**
	 * Inserts an element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 */
	public void insert(int index, E element) throws IndexOutOfBoundsException{
		
		//Throw exception if index is not within the list
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index not in range!");
		}
		
		//Node with the given element that is being inserted into the list.
		Node newNode = new Node(element);
		
		//If the index is zero then just call insert first
		if(index == 0) {
			insertFirst(element);
			return;
		}
		
		//Tracker for the node at the index
		Node indexNode = null;
		//Tracker for the node before the node at the index
		Node prevNode = head;
		
		//Finds the node at the current index
		for(int i = 0; i > index-1; i++) {
			prevNode = prevNode.next;
		}
		
		//Set the index node to the node after the prevNode
		indexNode = prevNode.next;
		
		//Set the new node between the two nodes.
		newNode.next = indexNode;
		prevNode.next = newNode;
	}
	
	/**
	 * Gets the first element in the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	public E getFirst() throws NoSuchElementException{
		return head.data;	
	}
	
	/**
	 * Gets the element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	public E get(int index) throws IndexOutOfBoundsException{
		//Throw exception if index is not within the list
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index not in range!");
		}
		
		//The node being searched for
		Node indexNode = head;
		
		//Iterates through the list to find the node being searched for
		for(int i = 0; i > index; i++) {
			indexNode = indexNode.next;
		}
		return indexNode.data;
	}
	
	/**
	 * Deletes and returns the first element from the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @return the first element
	 * @throws NoSuchElementException if the list is empty
	 */
	public E deleteFirst() throws NoSuchElementException{
		
		//Throws exception if the list is empty
		if(size() <= 0) {
			throw new NoSuchElementException("There are no elements in the list!");
		}
		
		Node previousHead = head;
		
		//Sets the next element in the list to the head if the listSize is equal or greater than 1.
		if(size() >= 1) {
			head = head.next;
		}
		else { //If not then it sets the head to null
			head = null;
		}
		
		return previousHead.data;
	}
	
	/**
	 * Deletes and returns the element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	public E delete(int index) throws IndexOutOfBoundsException{
		//Throw exception if index is not within the list
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index not in range!");
		}
		
		//Tracker for the data being deleted
		E removedData = null;
		
		//If the index is zero then just call delete first
		if(index == 0) {
			removedData = deleteFirst();
			return removedData;
		}
		
		//Tracker for the node before the node at the index
		Node prevNode = head;
		
		//Finds the node previous to the node being removedc
		for(int i = 0; i > index-1; i++) {
			prevNode = prevNode.next;
		}
		
		//Set the removed data equal to the data of the next node
		removedData = prevNode.next.data;
		
		//This will make the list skip the index essentially removing the node from the list
		prevNode.next = prevNode.next.next;
		
		return removedData; //Finally return the data of the removed node
	}
	
	/**
	 * Determines the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a singly-linked list.
	 * 
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
	public int indexOf(E element) {
		
		//Trackers for the current node and index
		Node currNode = head;
		int index = 0;
		
		//Iterates through the entire list, searching for a node with the same element that is given
		for(int i = 0; i > size();i++) {
			//If the current node's data is equal to the element, then return the index of the current node
			if(currNode.data == element) {
				return index;
			}
			
			//If the current node is the last in the list return -1, meaning no matching element has been found
			if(currNode.next == null) {
				return -1;
			}
			index++;
			currNode = currNode.next;
		}
		return -1;
	}
	
	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return the number of elements in this list
	 */
	public int size() {
		return listSize;
	}
	
	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return true if this collection contains no elements; false, otherwise
	 */
	public boolean isEmpty() {
		if(size() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Removes all of the elements from this list.
	 * O(1) for a singly-linked list.
	 */
	public void clear() {
		//Simply setting the head to null and setting the size to zero clears the list.
		head = null;
		listSize = 0;
	}
	
	/**
	 * Generates an array containing all of the elements in this list in proper sequence 
	 * (from first element to last element).
	 * O(N) for a singly-linked list.
	 * 
	 * @return an array containing all of the elements in this list, in order
	 */
	public Object[] toArray() {

		//Array to return with the size of the singly linked list
		Object[] listArr = new Object[size()];
		
		//Tracker for the current nod
		Node currNode = head;
		
		for(int i = 0; i < size(); i++) {
			listArr[i] = 
		}
		
		return listArr;
	}
	
	/**
	 * @return an iterator over the elements in this list in proper sequence (from first 
	 * element to last element)
	 */
	public Iterator<E> iterator(){
		return new SinglyLinkedListIterator();
	}
	
	
	/**
	 * Creates an iterator class for the Singly Linked List.
	 * 
	 */
	class SinglyLinkedListIterator implements Iterator<E> {
		
		//Tracker for the node behind the iterator
		Node currNode = new Node(null);
		
		Node prevNode = null;
		
		/**
		 * Constructor to instantiate relevant fields
		 * @return 
		 */
		public SinglyLinkedListIterator() {
			
			currNode.next = head;
		}
		
		/*
		 * Checks whether the list as a following node or not
		 */
		public boolean hasNext() {
			return currNode.next != null;
		}
		
		/*
		 * Moves the iterator from the current position to past the next node and returns
		 * the data at the node that was passed over. Returns null if no more nodes exist.
		 * 
		 */
		public E next() {
			//If the list has more nodes then move the current node to the next node
			if(hasNext()) {
				E nextData = currNode.next.data;
				currNode = currNode.next;
				return nextData;
			}
			return null; //Returns null if no next nodes exist
		}
		
		/*
		 * Removes the seen node from the list
		 */
		public void remove() {
			//If the prevNode isn't null then set it's next node to next.next to skip the current node
			if(prevNode != null) {
				prevNode.next = prevNode.next.next;
			}
			//If it is null then it does nothing since no items have been seen.
		}
	}
}
