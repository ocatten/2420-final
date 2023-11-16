package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import assignment09.MapEntry;

/**
 * This class represents a HashTable data structure with two generic data types (K & V) for the Key objects and Value objects respectively.
 * This data structure can retrieve, add, and remove items with O(1) time using separate chaining, creating LinkedLists for each index of
 * the backing array for collisions. Rehashes once the load factor becomes 10.
 * 
 * @param <K>: Data type for the Key object
 * @param <V>: Data type for the Value object
 * 
 * @author: Everett Oglesby & Parker Catten
 * @version: 11:16:23
 */
public class HashTable<K, V> implements Map<K, V> {
	
	// Fields
	ArrayList< LinkedList< MapEntry<K, V> > > hashTable = new ArrayList<LinkedList<MapEntry<K,V>>>(); // Backing list of LinkedList for separate chaining
	int size = 0; // Tracker for number of elements
	int length = 10; // Number of LinkedLists in the backing lists
	
	
	/**
	 * @Constructor for creating the backing list for separate chaining
	 */
	public HashTable() {
		
		// Set up the backing table with 10 empty LinkedLists
		for (int i = 0; i < length; i++) {
			hashTable.add(new LinkedList< MapEntry<K, V> >());
		}
	}
	
	
	
	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length) for quadratic probing or separate chaining
	 */
	public void clear() {
		
		// Iterate through each LinkedList and clear it
		for(LinkedList< MapEntry<K, V> > entry : hashTable) {
			entry.clear();
		}
				
		size = 0; // Reset size tracker
	}
	
	
	
	/**
	 * Helper method that takes the hashCode of an object and modulates it by the length to guarantee a valid position
	 * 
	 * @param key: Generic object key to find hashCode
	 * @return: Index where the key's value will be added to
	 */
	public int compression (Object key) {
		return Math.abs(key.hashCode()) % length;
	}
	
	

	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {
		
		// Iterate through the entries to find a viable position:
		for(MapEntry<K , V> entry : hashTable.get(compression(key)) ) {
						
			if(entry.getKey().equals(key)) {
				return true; // Match is found
			}
		}	
				
		return false; // No match found
	}

	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length) for quadratic probing
	 * O(table length + N) for separate chaining
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	public boolean containsValue(V value) {
		return false;
	}

	/**
	 * Returns a List view of the mappings contained in this map, where the ordering of 
	 * mapping in the list is insignificant.
	 * 
	 * O(table length) for quadratic probing
	 * O(table length + N) for separate chaining
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	public List<MapEntry<K, V>> entries(){
		return null;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	public V get(K key) {
		return null;
	}

	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * (I.e., if the key already exists in this map, resets the value; 
	 * otherwise adds the specified key-value pair.)
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V put(K key, V value) {
		return null;
	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V remove(K key) {
		return null;
	}

	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return the number of mappings in this map
	 */
	public int size() {
		return 0;
	}

}
