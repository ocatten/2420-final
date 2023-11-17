package assign09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

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
		assertEquals(hashTable.size(), 5000);
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
			
			assertEquals(hashTable.size(), 5-i);
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
		assertEquals(5, hashTable.size());
		hashTable.clear();
		assertEquals(0, hashTable.size());
	}
	
	
	
	@Test
	public void clearOnLargeTest() {
		
		setupLargeTable();
		assertEquals(5000, hashTable.size());
		hashTable.clear();
		assertEquals(0, hashTable.size());
	}
	
	
	//----------------Get Tests-----------------------------------------------//
	
	@Test
	public void getOnEmptyTest() {
		assertNull(hashTable.get(0));
	}
	
	
	
	@Test
	public void getOnSmallTest() {
		
		setupSmallTable();
		
		assertEquals(hashTable.get(1), "one");
		assertEquals(hashTable.get(2), "two");
		assertEquals(hashTable.get(3), "three");
		assertEquals(hashTable.get(4), "four");
		assertEquals(hashTable.get(5), "five");
	}
	
	
	
	@Test
	public void getOnLargeTable() {
		
		setupLargeTable();
		
		for (Integer i = 0; i < 5000; i++) {
			assertEquals(hashTable.get(i), i.toString());
		}
	}
	
	//----------Rehash Tests----------------------------------------------------//
	
	@Test
	public void rehashOnEmptyTable() {
		hashTable.rehash();
		
		assertEquals(0,hashTable.size());
	}
	
	
	
	@Test
	public void rehashOnSmallTable() {
		
		setupSmallTable();
		assertEquals(10, hashTable.getLength());
		hashTable.rehash();
		assertEquals(20, hashTable.getLength());
	}
	
	
	
	@Test
	public void rehashOnLargeTable() {
		
		setupLargeTable();
		
		assertEquals(640, hashTable.getLength());
		assertEquals(5000, hashTable.size());
		
		for(Integer i = 5000; i < 1970; i++) {
			hashTable.put(i, i.toString());
		}
		
		assertEquals(5000, hashTable.size());
		assertEquals(640, hashTable.getLength());
	}
	
	
	//-----------Contains Key Tests---------------------------------------------//
	
	@Test
	public void containsKeyOnEmptyTest() {
		
		assertFalse(hashTable.containsKey(0));
	}
	
	
	
	@Test
	public void containsKeyOnSmallTest() {
		
		setupSmallTable();
		
		assertTrue(hashTable.containsKey(1));
		assertTrue(hashTable.containsKey(2));
		assertTrue(hashTable.containsKey(3));
		assertTrue(hashTable.containsKey(4));
		assertTrue(hashTable.containsKey(5));
		
		assertFalse(hashTable.containsKey(100));
	}
	
	
	
	@Test
	public void containsKeyOnLargeTest() {
		
		setupLargeTable();
		
		for(int i = 0; i < 5001; i++) {
			assertTrue(hashTable.containsKey(i));
		}
		
		assertFalse(hashTable.containsKey(100000));
	}
	
	
	//---------------Contains Value Test------------------------------------//
	
	@Test
	public void containsValueOnEmptyTest() {
		
		assertFalse(hashTable.containsValue("1"));
	}
	
	
	
	@Test
	public void containValueOnSmallTest() {
		
		setupSmallTable();
		
		assertTrue(hashTable.containsValue("one"));
		assertTrue(hashTable.containsValue("two"));
		assertTrue(hashTable.containsValue("three"));
		assertTrue(hashTable.containsValue("four"));
		assertTrue(hashTable.containsValue("five"));
		
		
		assertFalse(hashTable.containsValue("25"));
		assertFalse(hashTable.containsValue("eight"));
		assertFalse(hashTable.containsValue("Hello World!"));
	}
	
	
	
	@Test
	public void containsValueOnLargeTest() {
		
		setupLargeTable();
		
		for(Integer i = 0; i < 5000; i++) {
			assertTrue(hashTable.containsValue(i.toString()));
		}
		
		assertFalse(hashTable.containsValue("1000"));
	}
	
	
	
	//--------------Entries Tests-------------------------------------------//
	
	@Test
	public void entriesOnEmptyTest() {
		
		List< MapEntry<Integer, String> > entries = hashTable.entries();
		
		assertEquals(entries.size(), 0);
	}
	
	
	
	@Test
	public void entriesOnSmallTest() {
		
		setupSmallTable();
		
		List<MapEntry<Integer, String>> entries = hashTable.entries();
		
		for (MapEntry<Integer, String> mapEntry : entries) {
			assertEquals(mapEntry.getValue(), hashTable.get(mapEntry.getKey()));
		}
		
		assertEquals(5,entries.size());
	}
	
	
	
	@Test
	public void entriesOnLargeTest() {
		
		setupLargeTable();
		
		List< MapEntry<Integer, String> > entries = hashTable.entries();
		
		for (MapEntry<Integer, String> mapEntry : entries) {
			assertEquals(mapEntry.getValue(), hashTable.get(mapEntry.getKey()));
		}
		assertEquals(5000,entries.size());
	}
	
	
	//--------------------Put Tests-------------------------------------------//
	
	@Test
	public void putOnEmptyTable() {
		
		hashTable.put(1, "one");
		assertTrue(hashTable.containsKey(1));
		assertTrue(hashTable.containsValue("one"));
	}
	
	
	
	@Test
	public void putOnSmallTable() {
		
		setupSmallTable();
		hashTable.put(6, "six");
		assertTrue(hashTable.containsKey(6));
		assertTrue(hashTable.containsValue("six"));
	}
	
	
	
	@Test
	public void putOnLargeTable() {
		
		setupLargeTable();
		hashTable.put(10000, "ten thousand");
		assertTrue(hashTable.containsKey(10000));
		assertTrue(hashTable.containsValue("ten thousand"));
	}
	
	
	//---------------Remove Tests---------------------------------------------//
	
	@Test
	public void removeOnEmptyTest() {
		assertNull(hashTable.remove(1));
	}
	
	
	
	@Test
	public void removeOnSmallTest() {
		
		setupSmallTable();
		
		hashTable.remove(1);
		
		assertFalse(hashTable.containsValue("one"));
	}
	
	@Test
	public void removeEntireSmallList() {
		setupSmallTable();
		
		assertEquals(5,hashTable.size());
		
		hashTable.remove(1);
		hashTable.remove(2);
		hashTable.remove(3);
		hashTable.remove(4);
		hashTable.remove(5);
		
		assertEquals(0,hashTable.size());
	}
	
	
	
	@Test
	public void removeOnLargeTest() {
		
		setupLargeTable();

		
		hashTable.remove(200);
		assertFalse(hashTable.containsKey(200));
	}
	
	//-----------Multiple Mixed Method Tests------------------------------------//
	
	@Test
	public void removeItemJustPutIntoTheList() {
		setupSmallTable();
		
		hashTable.put(6, "six");
		hashTable.remove(6);
		
		assertEquals(5,hashTable.size());
		assertFalse(hashTable.containsKey(6));
	}
	
	@Test
	public void EntriesWithItemsPutAndRemovedFromTheList() {
		setupSmallTable();
		
		hashTable.put(6, "six");
		hashTable.remove(4);
		hashTable.put(9, "nine");
		hashTable.remove(6);
		hashTable.put(7, "seven");
		hashTable.put(8, "eight");
		hashTable.remove(7);
		
		List< MapEntry<Integer, String> > entries = hashTable.entries();
		
		assertEquals(6,entries.size());
	}
}