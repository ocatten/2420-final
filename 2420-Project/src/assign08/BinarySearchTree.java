package assign08;

import java.util.ArrayList; 
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class represents a BinarySearchTree data structure that adds items to the left if they're less than the parent vertex, and
 * places new elements to the right if they're greater than the parent. Each method to find an element runs O(logN) on average. Uses
 * SortedSet interface provided by Erin Parker
 * 
 * @param <Type> Data type stored in the tree
 * @author Parker Catten & Everett Oglesby
 * @version 10:30:23 FA-23 2420_020
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
	
	
/*========================================================== VERTEX CLASS ========================================================================*/
			
			
	/**
	 * Vertex class that houses generic Type object
	 * 
	 * @author Parker Catten & Everett Oglesby
	 * @version 10:30:23 FA-23 2420_020
	 */
	public class Vertex {

		// Fields
		private Type data;
		private Vertex leftChild = null; // Right/left pointers for tree
		private Vertex rightChild = null;
		public Vertex source; // Source pointer
		public boolean visited = false; // Visited flag
				
				
		/**
		 * @Constructor that stores the data in a new vertex
		 * 
		 * @param data: Generic object to be created into a vertex
		 */
		Vertex (Type data) {
			this.data = data;
		}
				
				
				
		/**
		 * @return data in the Vertex
		 */
		public Type getData() { 
			return data; 
		}
				
				
				
		/**
		 * Makes a new child node, putting it to the left or the right depending on its value
		 * 
		 * @param child: Vertex object that is the destination of the edge
		 */
		public <T extends Comparable<? super T>> void addChild (Vertex child) {
					
			// If the child is less than this vertex:
			if (cmp.compare(this.data, child.data) > 0) {
						
				// Place it to the left of this vertex, adjust child pointers
				child.source = this;
				leftChild = child;
						
			} else { // If the new child is greater than this vertex:
						
				// Place it to the right of this vertex, adjust child pointers
				child.source = this;
				rightChild = child;
			}
		}
	}

/*=============================================================================================================================================*/
	

	// Fields
	private Vertex head = null; // First Vertex that's added
	private int size = 0; // Tracker
	
	public Comparator<Type> cmp = new Comparator<Type>() { // Comparator for adding/finding Vertices
		public int compare(Type e1, Type e2) { return e1.compareTo(e2); } 
	};
	
	
	@Override
	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually inserted); otherwise, returns false
	 */
	public boolean add (Type item) {
		
		Vertex currVertex = head; // Puts tracker at the head
		Vertex newVertex = new Vertex(item); // New vertex to store the parameter
				
		// Move through vertices until none are left
		while(currVertex != null) {
					
			// If the new vertex's data is less than the tracker's position
			if(cmp.compare(item, currVertex.data) < 0) {
						
				// Catch case for a new root node
				if (currVertex.leftChild == null) {
							
					// Place it at the end of the tree to the left
					currVertex.leftChild = newVertex;
					newVertex.source = currVertex;
							
					size++;
					return true; // Update size and return true, tree was modified
				}
						
				// Move the tracker to the left
				currVertex = currVertex.leftChild; 
					
			// If the new vertex's data is greater than the tracker's position
			} else if(cmp.compare(item, currVertex.data) > 0) {
						
				// Catch case for a new root node
				if (currVertex.rightChild == null) {
							
					// Place it at the end of the tree to the right
					currVertex.rightChild = newVertex;
					newVertex.source = currVertex;
							
					size++;
					return true; // Update size and return true, tree was modified
				}
						
				// Move the tracker to the right
				currVertex = currVertex.rightChild; 
						
			} else { // If the new vertex isn't greater or less than the current node, it's equal, and therefore already in the tree				
				return false; // It's already here, nothing changed
			}
		
		}
		
		// Empty tree catch case
		head = newVertex; // Set the head as the parameter, update size, and return true
		size++;
		return true;
	}

	
	
	@Override
	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items - the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually inserted); otherwise,
	 *         returns false
	 */
	public boolean addAll(Collection<? extends Type> items) {
		
		boolean added = false; // Flag to see if anything was changed
		// Loop through each item to add it
		for(Type item : items) {
			
			// If something is added by the method:
			if(this.add(item)) {
				added = true; // Update the flag
			}
		}
		
		return added; // Return the flag
	}

	
	
	@Override
	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	public void clear() {
		
		// Uses a postOrder traversal helper method to delete each item starting at the head
		clearPostOrder(head);
		head = null; // Update the size and head fields
		size = 0;
	}
	
	
	
	/** 
	 * @clear() helper method for to recursively empty a list with a postOrder traversal algorithm
	 * 
	 * @param currVertex: Initially the vertex at the head of the tree, starting vertex on subsequent calls
	 */
	private void clearPostOrder (Vertex currVertex) {
		
		// If there's nodes to the left, move to them before the right as postOrder does
		if(currVertex.leftChild != null) {
			clearPostOrder(currVertex.leftChild);
		}
		
		// Move to the right after the leftmost nodes are traversed
		if(currVertex.rightChild != null) { 
			clearPostOrder(currVertex.rightChild);
		}
		
		// Clear the leftmost node until the tree is empty
		currVertex = null;
	}
	
	
	
	@Override
	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input item;
	 *         otherwise, returns false
	 */
	public boolean contains(Type item) {
		
		// Makes a tracker at the head
		Vertex currVertex = head;
					
		// Traverse tree as long as there are elements
		while(currVertex != null) {
				
			// If the target is to the left:
			if(cmp.compare(item, currVertex.data) < 0) {
				
				// If there's no nodes remaining, the parameter isn't in the tree
				if (currVertex.leftChild == null) {
					return false; // No match found
				}
						
				currVertex = currVertex.leftChild; // Adjust to the left
				
			// If the target is to the right:
			} else if(cmp.compare(item, currVertex.data) > 0) {
				
				// If there's no nodes remaining, the parameter isn't in the tree
				if (currVertex.rightChild == null) {
					return false; // No match found
				}
				
				currVertex = currVertex.rightChild; // Adjust to the right
							
			} else { // If the parameter is neither greater or less than the tracker, it's equal
				return true; // Match found
			}
		}
				
		return false; // Catch case for empty tree, cannot contain anything
	}

	
	
	@Override
	/**
	 * Determines if for each item in the specified collection, there is an item in
	 * this set that is equal to it.
	 * 
	 * @param items - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 */
	public boolean containsAll(Collection<? extends Type> items) {
		
		// Run contains() on each item in the list
		for(Type item : items) {
							
			// If a single element is missing:
			if(!this.contains(item)) {
				return false; // Tree does not contain each element in items
			}
		}
				
		return true; // All objects from items were found in the tree
	}

	
	
	@Override
	/**
	 * @return the first (i.e., smallest) item in this set.
	 * @throws NoSuchElementException if the set is empty
	 */
	public Type first() throws NoSuchElementException {

		// Empty tree catch case
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
				
		// Tracking vertex to traverse the tree
		Vertex currVertex = head;
				
		// Keep moving to the left until a root is found
		while(currVertex.leftChild != null) {
			currVertex = currVertex.leftChild;
		}
				
		return currVertex.data; // Return the data at the left-most node
	}

	
	
	@Override
	/**
	 * @return true if this set contains no items.
	 */
	public boolean isEmpty() {
		return head == null; // If the head doesn't exist, the tree is empty.
	}

	
	
	@Override
	/**
	 * @return the last (i.e., largest) item in this set.
	 * @throws NoSuchElementException if the set is empty
	 */
	public Type last() throws NoSuchElementException {
		
		// Empty tree catch case
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
						
		// Tracking vertex to traverse the tree
		Vertex currVertex = head;
						
		// Keep moving to the right until a root is found
		while(currVertex.rightChild != null) {
			currVertex = currVertex.rightChild;
		}
						
		return currVertex.data; // Return the data at the right-most node
	}

	
	
	@Override
	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if the input item was actually removed); otherwise, returns false
	 */
	public boolean remove(Type item) {
		
		Vertex currVertex = head; // Create tracker to traverse the tree

		// Move through vertices until none are left
		while(currVertex != null) {
					
			// If the item is to the left of the tracker
			if(cmp.compare(item, currVertex.data) < 0) {
							
				// Traverse to the left if it exists
				if (currVertex.leftChild != null) {
							
					currVertex = currVertex.leftChild;
							
				} else {
					return false; // If there's nothing to the left, a root was reached without finding a match
				}
							
			// If the item is to the right of the tracker
			} else if(cmp.compare(item, currVertex.data) > 0) {
							
				// Traverse to the right if it exists
				if (currVertex.rightChild != null) {	
					
					currVertex = currVertex.rightChild;
					
				} else {
					return false; // If there's nothing to the right, a root was reached without finding a match
				}
						
			} else { // If the target is neither greater nor less than the current position, they must be equal:
				
				// Catch case for removing the head:
				if(currVertex.equals(head)) {
					
					// If there's a child to the right:
					if(currVertex.rightChild != null) {
						
						// Take the left subtree on the deleted node and put it at the end of the right subtree.
						if(currVertex.leftChild != null) {
							
							// Start by finding the leftmost node of the right subtree:
							Vertex leftmostVertex = currVertex.rightChild;
							
							while(leftmostVertex.leftChild != null) {	
								leftmostVertex = leftmostVertex.leftChild;
							}
							
							// Now take the end of that subtree and make its left child equal to the left subtree of the original node
							leftmostVertex.leftChild = currVertex.leftChild;
							currVertex.leftChild.source = leftmostVertex;
						}
						
						// Adjust size, reset the head, delete the current position, and return true
						head = currVertex.rightChild;
						size--; 
						currVertex = null;
						return true;
					}
					
					// Adjust size, delete the current position, and return true
					size--; 
					currVertex = null;
					return true;
					
				}
						
				// Catch case for removing a root
				if(currVertex.leftChild == null && currVertex.rightChild == null) {
							
					currVertex = null; // Remove the source's pointer to the matching node

					// Adjust size and return true
					size--; 
					return true;		
						
				// Second case for a node with a child to the right
				} else if (currVertex.rightChild != null) {
							
					boolean rightParent = false; // Flag to check where to point the parent's children pointers
					
					// Determine if the node to be deleted is a left or right child
					if(currVertex.source.leftChild != null) { // Check if it exists before extracting data value
						
						// Check if the current position is equal to its parents left child, and if it is, this parent is to the right
						rightParent = currVertex.data.equals( currVertex.source.leftChild.data ); // If it isn't equal, parent is to the left
					}
					
					// If the deleted node is a left child:
					if(rightParent) {
						
						// Point the source's left child to this node's right child, and replace the current vertex's position
						currVertex.source.leftChild = currVertex.rightChild;
						currVertex.rightChild.source = currVertex.source;
						
						// Now, take the left subtree on the deleted node and put it at the end of the right subtree.
						if(currVertex.leftChild != null) {
							
							// Start by finding the leftmost node of the right subtree:
							Vertex leftmostVertex = currVertex.rightChild;
						
							while(leftmostVertex.leftChild != null) {	
								leftmostVertex = leftmostVertex.leftChild;
							}
						
							// Now take the end of that subtree and make its left child equal to the left subtree of the original node
							leftmostVertex.leftChild = currVertex.leftChild;
							currVertex.leftChild.source = leftmostVertex;
						}
						
					// If the deleted node is a right child:
					} else if (!rightParent) {
						
						// Point the source's right child to this node's right child, and replace the current vertex's position
						currVertex.source.rightChild = currVertex.rightChild;
						currVertex.rightChild.source = currVertex.source;
						
						// Now, take the left subtree on the deleted node and put it at the end of the right subtree.
						if(currVertex.leftChild != null) {
							// Start by finding the leftmost node of the right subtree:
							Vertex leftmostVertex = currVertex.rightChild;
						
							while(leftmostVertex.leftChild != null) {	
								leftmostVertex = leftmostVertex.leftChild;
							}
						
							// Now take the end of that subtree and make its left child equal to the left subtree of the original node
							leftmostVertex.leftChild = currVertex.leftChild;
							currVertex.leftChild.source = leftmostVertex;
						}
					}
						
					// Adjust size, delete the current position, and return true
					size--; 
					currVertex = null;
					return true;
						
				} else { // Final case for a node with only left children
					
					boolean rightParent = false; // Flag to check where to point the parent's children pointers
					
					// Determine if the node to be deleted is a left or right child
					if(currVertex.source.leftChild != null) { // Check if it exists before extracting data value
						
						// Check if the current position is equal to its parents left child, and if it is, this parent is to the right
						rightParent = currVertex.data.equals( currVertex.source.leftChild.data ); // If it isn't equal, parent is to the left
					}
					
					// If the deleted node is a left child:
					if(rightParent) {
						
						// Point the source's left child to this node's left child, and replace the current vertex's position
						currVertex.source.leftChild = currVertex.leftChild;
						currVertex.leftChild.source = currVertex.source;
						
					// If the deleted node is a right child:
					} else if (!rightParent) {
						
						// Point the source's right child to this node's left child, and replace the current vertex's position
						currVertex.source.rightChild = currVertex.leftChild;
						currVertex.leftChild.source = currVertex.source;
						
					}
						
					// Adjust size, delete the current position, and return true
					size--; 
					currVertex = null;
					return true;
				}
			}
		}	
		
		return false; // Empty tree catch case, nothing can be removed
	}

	
	
	@Override
	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually removed); otherwise,
	 *         returns false
	 */
	public boolean removeAll(Collection<? extends Type> items) {

		boolean removed = false; // Flag to see if anything was changed
		// Loop through each item to remove it
		for(Type item : items) {
			
			// If something is removed by the method:
			if(this.remove(item)) {
				removed = true; // Update the flag
			}
		}
		
		return removed; // Return the flag
	}

	
	
	@Override
	/**
	 * @return the number of items in this set
	 */
	public int size() {
		return size;
	}
	
	

	@Override
	/**
	 * @return an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	public ArrayList<Type> toArrayList() {

		ArrayList<Type> result = new ArrayList<Type>();
				
		// Run inOrder helper method to add each item to the list
		buildListInOrder(head, result);
				
		return result; // Return finalized list
	}
	
	
	
	/**
	 * @toArrayList() helper method that recursively creates an ordered list from a tree with an inOrder traversal algorithm
	 * 
	 * @param currVertex: Current position in the tree
	 * @return: Ordered ArrayList with each element in it
	 */
	private void buildListInOrder(Vertex currVertex, ArrayList<Type> result) {
		
		// Base case when all nodes have been traversed
		if (currVertex == null) {
			return;
		}
		
		// Catch case for a root
		if(currVertex.leftChild == null && currVertex.rightChild == null) {
			
			// Add this root to the result
			result.add(currVertex.data);
			return; // Root reached, return
		}
		
		// Traverse through all left children before moving to the right
		if(currVertex.leftChild != null) {
			buildListInOrder(currVertex.leftChild, result);
		}
		
		// Traverse through all right children now that left is done
		if(currVertex.rightChild != null) {
			buildListInOrder(currVertex.rightChild, result);
		}
		
		result.add(currVertex.getData()); // Now that everything has been traversed, add to the list
	}
	
}
