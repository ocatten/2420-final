package assign09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import assignment09.HashTable;
import assignment09.MapEntry;

/**
 * Tester for the HashTable class.
 * 
 * @author Everett Oglesby and Parker Catten
 * @version November 2, 2023
 */
public class HashTableTester {

	private HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
	
	
	//-----------------Setup Tests-----------------------------------------//
	
	public void setupSmallTable() {
		hashTable.put(1, "one");
		hashTable.put(2, "two");
		hashTable.put(3, "three");
		hashTable.put(4, "four");
		hashTable.put(5, "five");
	}
	
	public void setupLargeTable() {
		
		int size = 5000;
		
		for(Integer i = 0; i < size; i++) {
			hashTable.put(i, i.toString());
		}
	}
	
	
	//---------------Size Tests----------------------------------------------//
	
	@Test
	public void sizeOnEmptyHashTable() {
		
		assertEquals(0, hashTable.size());
	}
	
	
	
	@Test
	public void sizeOnSmallHashTable() {
		setupSmallTable();
		assertEquals(hashTable.size(), 5);
		
	}
	
	
	
	@Test
	public void sizeOnLargeTest() {
		
		setupLargeTable();
		assertEquals(hashTable.size(), 1000);
		
	
		
		
		assertEquals(hashTable.size(), 0);
	}
	
	@Test
	public void sizeWithAddingToSmallHashTable() {
		setupSmallTable();
		assertEquals(hashTable.size(), 5);

		hashTable.put(6, "six");
		assertEquals(hashTable.size(), 6);
	}
	
	@Test
	public void sizeWithRemovingValuesfromSmallHashTable() {
		setupSmallTable();
		assertEquals(hashTable.size(), 5);

		List< MapEntry<Integer, String> > entries = hashTable.entries();
		
		
		for (int i = 0; i < entries.size(); i++ ) {
			
			assertEquals(hashTable.size(), 6-i);
			hashTable.remove(entries.get(i).getKey() );
		}
		
		
		assertEquals(hashTable.size(), 0);
	}
	
	//----------isEmpty Tests-------------------------------------------------//
	

	@Test
	public void isEmptyOnEmptyTest() {
		
		assertTrue(hashTable.isEmpty());
	}
	
	
	
	@Test
	public void isEmptyOnSmallTest() {
		
		setupSmallTable();
		assertFalse(hashTable.isEmpty());
	}
	
	
	
	@Test
	public void isEmptyOnLargeTest() {
		
		setupLargeTable();
		assertFalse(hashTable.isEmpty());

		hashTable.clear();
		assertTrue(hashTable.isEmpty());
	}
	
	//----------Clear Tests-----------------------------------------------------//
	
	@Test
	public void clearOnEmptyTest() {
		
		assertEquals(0, hashTable.size());
		hashTable.clear();
		assertEquals(0, hashTable.size());
	}
	
	
	
	@Test
	public void clearOnSmallTest() {
		
		setupSmallTable();
		assertEquals(11, hashTable.size());
		hashTable.clear();
		assertEquals(0, hashTable.size());
	}
	
	
	
	@Test
	public void clearOnLargeTest() {
		
		setupLargeTable();
		assertEquals(1000, hashTable.size());
		hashTable.clear();
		assertEquals(0, hashTable.size());
	}
	
}
