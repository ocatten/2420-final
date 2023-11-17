package assign09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a HashTable data structure with two generic data types (K & V) for the Key objects and Value objects respectively.
 * This data structure can retrieve, add, and remove items with O(1) time using separate chaining, creating LinkedLists for each index of
 * the backing array for collisions. Rehashes once the load factor becomes 10.
 * 
 * @param <K>: Data type for the Key object
 * @param <V>: Data type for the Value object
 * 
 * @author: Everett Oglesby & Parker Catten
 * @version: 11:11:23
 */
public class HashTable<K, V> implements Map<K, V> {
	
	// Fields
	private ArrayList< LinkedList< MapEntry<K, V> > > hashTable = new ArrayList<LinkedList<MapEntry<K,V>>>(); // Backing list of LinkedList for separate chaining
	private int size = 0; // Tracker for number of elements
	private int length = 10; // Number of LinkedLists in the backing lists
	
	/**
	 * @Constructor for creating the backing list for separate chaining
	 */
	public HashTable() {
		
		// Set up the backing table with 10 empty LinkedLists
		for (int i = 0; i < length; i++) {
			hashTable.add(new LinkedList< MapEntry<K, V> >());
		}
	}
	
	
	
	@Override
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
	 * Helper method that takes the hashCode of an object and modulates it by the length to guarantee a valid position in the backing list
	 * 
	 * @param key: Generic object key to find hashCode
	 * @return: Index where the key's value will be added to
	 */
	public int compress (Object key) {
		return Math.abs(key.hashCode()) % length;
	}
	
	
	
	/**
	 * @return the current length of the backing list
	 */
	public int getLength () {
		return length;
	}
	
	
	
	@Override
	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {
		
		// Go to the list at the key's hash value:
		for(MapEntry<K , V> entry : hashTable.get(compress(key)) ) {
			
			if(entry.getKey().equals(key)) {
				return true; // Match is found
			}
		}
		
		return false; // No match found
	}
	
	

	@Override
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

		// Follows the same logic as containsKey(), but has to iterate through the list wiht no hashCode
		for (LinkedList<MapEntry<K,V>> tableEntry : hashTable) {
			
			// Iterate through each LinkedList
			for (MapEntry<K,V> entry : tableEntry) {
				
				if (entry.getValue().equals(value)) {
					return true; // Match found
				}
			}
		}
		
		return false; // No match
	}

	
	
	@Override
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

		LinkedList< MapEntry<K, V> > entries = new LinkedList< MapEntry<K, V> >(); // Empty list to return
				
		// Iterate through each LinkedList and add their existing values
		for(LinkedList< MapEntry<K, V> > chainList : hashTable) {
					
			if(chainList != null) {
				entries.addAll( (Collection< ? extends MapEntry<K, V> >) chainList ); // Add every value in every list
			}
		}
				
		return entries; // Now every value has been added, return the list
	}
	
	
	
	@Override
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

		// Get the chained list at the hashCode
		LinkedList< MapEntry<K, V> > chainedList = hashTable.get( compress(key) );
				
		// Iterate through this key's position in the backing list
		for(MapEntry<K, V> entry : chainedList) {
					
			if(entry.getKey().equals(key)) {
				return entry.getValue(); // Return the matching value if one is found
			}
		}
				
		return null; // No matching object exists
	}
	
	
	
	@Override
	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0; // Evaluate if there aren't any added items
	}
	
	
	
	@Override
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

		// Hash the key for where this entry will go
		LinkedList< MapEntry<K, V> > chainedList = hashTable.get(this.compress(key));
				
		// Iterate through the chainedList to check for a match
		for (MapEntry<K, V> entry: chainedList) {
					
			// Catch case for an entry that has the same key, replaces the old value
			if(entry.getKey().equals(key)) {
						
						
				// Store the replaced value to return what changed
				V oldValue = entry.getValue();
				
				entry.setValue(value);
				return oldValue; // Return changed Value
			}
		}
				
		// If this key isn't already in the list, it's a new value, so add it to the end of the chain
		chainedList.add(new MapEntry<K, V>(key, value));
		size++; // Update tracker
		
		// Calculate overhead to check for rehashing
		if((double)size / (double)length >= 10) {
			this.rehash(); // If lambda >= 10, rehash()
		}
				
		return null; // No object was changed
	}
	
	
	
	/**
	 * Once too many objects are added, collisions will become too common and make the HashTable very inefficient. As a result,
	 * the HashTable must occasionally rehash all of its values into a new backing list of a larger size. Since this table uses
	 * separate chaining, this will double the size and then re-add all the items from the last table into the new one.
	 */
	public void rehash () {
		
		length *= 2; // Double the size of the new backing list
			
		// Store the previous values in a new List to re-add
		List< MapEntry<K, V> > oldEntries = this.entries();
			
		// Clear the backing list and reset the tracker
		hashTable.clear();
		size = 0;
		
		// Make new empty LinkedLists to add to
		for(int i = 0; i < length; i++) {
			hashTable.add(new LinkedList< MapEntry<K, V> >());
		}
		
		// Get all values from the previous table into the new one
		for(MapEntry<K,V> entry : oldEntries) {
			this.put(entry.getKey(), entry.getValue());
		}
	}
	
	
	
	@Override
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
		
		// Go to the chainedList at the key's hash
		LinkedList< MapEntry<K, V> > chainedList = hashTable.get(this.compress(key));
				
		// Go through each entry at the proper index
		for (MapEntry<K, V> entry: chainedList) {
					
			// If a matching key is found
			if (entry.getKey().equals(key)) {
						
				chainedList.remove(entry); // Remove the target and update the size tracker
				size--; 

				return entry.getValue(); // Return the removed value
			}
		}
				
		return null; // Catch case for no existing match
	}
	
	
	
	@Override
	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return the number of mappings in this map
	 */
	public int size() {
		return size;
	}

}
