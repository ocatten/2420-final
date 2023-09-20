 package assign03;
 
 /**
  * Tester for SimplePriority Queue
  * 
  * @author Parker Catten & Everett Oglesby
  * @version 09:12:23 CS-2420_020 FA-2023
  */

import static org.junit.Assert.assertEquals;  
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import org.junit.Test;

public class PriorityQueueTester {
	
	// Fields
	SimplePriorityQueue<Integer> emptyQueue;
	SimplePriorityQueue<Integer> smallIntQueue;
	SimplePriorityQueue<String> smallCharQueue;
	SimplePriorityQueue<Integer> largeQueue;
	
	ArrayList<Integer> mixedNumArr;
	ArrayList<Integer> mixedNumArrLarge;
	ArrayList<String> mixedCharArr;
	
	
	public void setup() {
		
		emptyQueue = new SimplePriorityQueue<Integer>();
		
		
		smallIntQueue = new SimplePriorityQueue<Integer>();
		int[] mixedNums = {5,4,3,6,8,1,9,7,2};
		
		mixedNumArr = new ArrayList<Integer>();
		for(Integer i : mixedNums) {
			mixedNumArr.add(i);
		}
		smallIntQueue.insertAll(mixedNumArr);
		
		
		smallCharQueue = new SimplePriorityQueue<String>();
		String[] mixedChars = {"G","C","E","F","A","B","D"};
		
		mixedCharArr = new ArrayList<String>();
		for(String i : mixedChars) {
			mixedCharArr.add(i);
		}
		smallCharQueue.insertAll(mixedCharArr);
		
		
		largeQueue = new SimplePriorityQueue<Integer>();
		
		mixedNumArrLarge = new ArrayList<Integer>();
		for(Integer i = 0; i < 250; i++) {
			mixedNumArrLarge.add(i);
		}
		largeQueue.insertAll(mixedNumArrLarge);
	}
	
	
/*============================================================= FIND_MAX TESTS ====================================================================*/
	
	
	@Test
	public void findMaxOnEmpty() {
		setup();
		
		try {
			
			emptyQueue.findMax();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void findMaxOnSmall() {
		setup();
		
		assertEquals(new Integer(9), smallIntQueue.findMax());
		assertEquals("G", smallCharQueue.findMax());
		
		smallIntQueue.insert(new Integer(10));
		assertEquals(new Integer(10), smallIntQueue.findMax());
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void findMaxOnLarge() {
		setup();
		
		assertEquals(new Integer(249), largeQueue.findMax());
		smallIntQueue.insert(new Integer(250));
		assertEquals(new Integer(250), smallIntQueue.findMax());
		
	}
	
	
/*============================================================ DELETE_DMAX TESTS ==================================================================*/
	
	
	@Test
	public void deleteMaxOnEmpty() {
		setup();
		
		try {
			
			emptyQueue.deleteMax();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void deleteMaxOnSmall() { 
		setup();
		
		assertEquals(new Integer(9), smallIntQueue.deleteMax());
		assertEquals(new Integer(8), smallIntQueue.findMax());
		assertEquals("G", smallCharQueue.findMax());
		
		smallIntQueue.insert(new Integer(10));
		assertEquals(new Integer(10), smallIntQueue.deleteMax());
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void deleteMaxOnLarge() {
		setup();
		
		assertEquals(new Integer(249), largeQueue.deleteMax());
		assertEquals(new Integer(248), largeQueue.findMax());
		
		smallIntQueue.insert(new Integer(250));
		assertEquals(new Integer(250), smallIntQueue.deleteMax());
	}
	
	
/*============================================================== INSERT TESTS =====================================================================*/
	
	
	@Test
	@SuppressWarnings("removal")
	public void insertOnEmpty() {
		setup();
		
		assertTrue(emptyQueue.isEmpty());
		assertEquals(emptyQueue.size(), 0);
		
		emptyQueue.insert(new Integer(7));
		assertEquals(emptyQueue.size(), 1);
		assertFalse(emptyQueue.isEmpty());
		
	}
	
	
	
	@Test
	public void insertOnSmall() {
		setup();
		
		assertEquals(smallIntQueue.size(), 9);
		smallIntQueue.insert(6);
		
		assertEquals(smallIntQueue.size(), 10);
		
		
		assertEquals(smallCharQueue.size(), 7);
		smallCharQueue.insert("q");
		
		assertEquals(smallCharQueue.size(), 8);
	}
	
	
	
	@Test
	public void insertOnLarge() {
		setup();
		
		assertEquals(largeQueue.size(), 250);
		largeQueue.insert(250);
		
		assertEquals(largeQueue.size(), 251);
	}
	
	
/*============================================================ INSERT_ALL TESTS ===================================================================*/
	
	
	@Test
	public void insertAllOnEmpty() {	
		setup();
		
		assertEquals(emptyQueue.size(), 0);
		
		emptyQueue.insertAll(mixedNumArr);
		assertEquals(emptyQueue.size(), 9);
	}
	
	
	
	@Test
	public void insertAllOnSmall() {
		setup();
		
		smallIntQueue.insertAll(mixedNumArrLarge);
		assertEquals(smallIntQueue.size(), 259);
	}
	
	
	
	@Test
	public void insertAllOnLarge() {
		setup();
		
		largeQueue.insertAll(mixedNumArrLarge);
		assertEquals(largeQueue.size(), 500);
	}
	
	
/*============================================================= CONTAINS TESTS ====================================================================*/
	
	
	@Test
	public void containsOnEmpty() {
		setup();
		
		assertFalse(emptyQueue.contains(1));
		emptyQueue.insert(1);
		
		assertTrue(emptyQueue.contains(1));
	}
	
	
	
	@Test
	public void containsOnSmall() {
		setup();
		
		assertFalse(smallIntQueue.contains(10));
		assertFalse(smallCharQueue.contains("z"));
	}
	
	
	
	
/*=============================================================== SIZE TESTS ======================================================================*/
	
	
	@Test
	public void sizeOnEmpty() {
		setup();
		
		assertEquals(emptyQueue.size(), 0);
	}
	
	
	
	@Test
	public void sizeOnSmall() {
		setup();
		
		assertEquals(smallIntQueue.size(), 9);
	}
	
	
	
	@Test
	public void sizeOnLarge() {
		setup();
		
		assertEquals(largeQueue.size(), 250);
	}
	
	
/*============================================================= IS_EMPTY TESTS ====================================================================*/
	
	
	@Test
	public void isEmptyOnEmpty() {
		setup();
		
		assertTrue(emptyQueue.isEmpty());
	}
	
	
	
	@Test
	public void isEmptyOnSmall() {
		setup();
		
		assertFalse(smallIntQueue.isEmpty());
		smallIntQueue.clear();
		assertTrue(smallIntQueue.isEmpty());
	}
	
	
	
	@Test
	public void isEmptyOnLarge() {
		setup();
		
		assertFalse(largeQueue.isEmpty());
		largeQueue.clear();
		assertTrue(largeQueue.isEmpty());
	}
	
	
/*=============================================================== CLEAR TESTS =====================================================================*/
	
	
	@Test
	public void clearOnEmpty() {
		setup();
		
		emptyQueue.insert(12);
		assertFalse(emptyQueue.isEmpty());
		emptyQueue.clear();
		assertTrue(emptyQueue.isEmpty());
		assertEquals(emptyQueue.size(), 0);
	}
	
	
	
	@Test
	public void clearOnSmall() {
		setup();
		
		assertFalse(smallIntQueue.isEmpty());
		smallIntQueue.clear();
		assertTrue(smallIntQueue.isEmpty());
		assertEquals(smallIntQueue.size(), 0);
	}
	
	
	
	@Test
	public void clearOnLarge() {
		setup();
		
		assertFalse(largeQueue.isEmpty());
		largeQueue.clear();
		assertTrue(largeQueue.isEmpty());
		assertEquals(largeQueue.size(), 0);
	}

}
