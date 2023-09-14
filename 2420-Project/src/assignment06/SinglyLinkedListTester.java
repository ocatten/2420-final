/**
 * Tester class for the SinglyLinkedList
 * 
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 06:22:23 CS-2420_001 SUM-2023
 */
package assignment06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;

import assignment06.SinglyLinkedList.Node;

public class SinglyLinkedListTester {
	
	
	SinglyLinkedList<Integer> singlyLinkedList;
	
/*--------------------------------------------------- SET UP ----------------------------------------------------------*/
	
	public void emptyListSetUp () {
		
		singlyLinkedList = new SinglyLinkedList<Integer>();
	}
	
	
	
	public void smallListSetUp () {
		
		singlyLinkedList = new SinglyLinkedList<Integer>();
		
		for (int i = 1; i < 6; i++) {
			
			singlyLinkedList.addNode(i);
		}
	}
	
	
	
	public void largeListSetUp () {
		
		singlyLinkedList = new SinglyLinkedList<Integer>();
		
		for (int i = 1; i < 1000; i++) {
			
			singlyLinkedList.addNode(i);
		}
	}
	
	public SinglyLinkedList<Integer> createSmallSortedList() {
		
		SinglyLinkedList<Integer> smallList = new SinglyLinkedList();
		
		smallList.addNode(1);
		smallList.addNode(2);
		smallList.addNode(3);
		smallList.addNode(4);
		return smallList;
	}
	
	public SinglyLinkedList<Integer> createLargeSortedList() {
		
		SinglyLinkedList<Integer> largeSortedList = new SinglyLinkedList();
		
		for(int i = 0; i < 10000; i++) {
			
			largeSortedList.addNode(i);
		}
		
		return largeSortedList;
	}
	
	
	
	@Test
	public void createNodesTest() {
	
		SinglyLinkedList smallList = new SinglyLinkedList();
		
		smallList.addNode(1);
		smallList.addNode(2);
		smallList.addNode(3);
		smallList.addNode(4);
		
		Object[] testCase = smallList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[0]).data);
		}
		
		for(int i = 0; i < testCase.length - 1; i++) {
			assertEquals(testCase[i], i + 1);
		}
		// Simultaneously tests createNode and addNode
	}
	
	
/*------------------------------------------------ SIZE TESTS ------------------------------------------------------*/
	
	@Test
	public void sizeOnSmallTest () {
		
		smallListSetUp();
		assertEquals(5, singlyLinkedList.size());	
	}
	
	
	
	@Test
	public void sizeTestLarge() {
		
		SinglyLinkedList largeList = createLargeSortedList();
		assertEquals(10000, largeList.size());
	}
	
	
	
	@Test
	public void sizeTestEmpty() {
		
		SinglyLinkedList emptyList = new SinglyLinkedList();
		
		assertEquals(0, emptyList.size());
	}
	
	
/*------------------------------------------------ TO_ARRAY TESTS ------------------------------------------------------*/
	
	@Test
	public void toArrayTestOnSmall() {
		
		SinglyLinkedList smallList = createSmallSortedList();
		
		Object[] testCase = (Object[]) smallList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[0]).data);
		}
			
		for(int i = 0; i < testCase.length; i++) {
			assertEquals(testCase[i], smallList.get(i));
		}
	}
	
	
	
	@Test
	public void toArrayTestOnLarge() {
		
		SinglyLinkedList largeList = createLargeSortedList();
		
		//System.out.println("tester");
		
		Object[] testCase = (Object[]) largeList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(testCase[i]);
		}
		
		for(int i = 0; i < testCase.length; i++) {
			assertEquals(testCase[i], largeList.get(i));
		}
	}
	
	
	
	@Test
	public void toArrayTestOnEmpty() {
		
		SinglyLinkedList emptyList = new SinglyLinkedList();
		
		Object[] testCase = emptyList.toArray();
			
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[0]).data);
		}
		
		assertEquals(testCase.length, 0);
	}
	
	
/*------------------------------------------------ INSERT_FIRST TESTS ------------------------------------------------------*/
	
	@Test
	public void insertFirstTestOnSmall() {
		
		SinglyLinkedList smallList = createSmallSortedList();
		
		smallList.insertFirst(-1);
		Object[] testCase = (Object[]) smallList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[0]).data);
		}
		
		assertEquals(-1, testCase[0]);
	}
	
	
	
	@Test
	public void insertFirstTestOnLarge() {
		
		SinglyLinkedList largeList = createLargeSortedList();
		
		largeList.insertFirst(-1);
		Object[] testCase = (Object[]) largeList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[0]).data);
		}
		
		assertEquals(-1, testCase[0]);
	}
	
	
	
	@Test
	public void insertFirstTestOnEmpty() {
		
		SinglyLinkedList emptyList = new SinglyLinkedList();
		
		emptyList.insertFirst(-1);
		Object[] testCase = emptyList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[0]).data);
		}
		
		assertEquals(-1, testCase[0]);
	}
	

/*--------------------------------------------------- INSERT TESTS ------------------------------------------------------*/
	
	@Test
	public void insertTestOnSmall() {
		
		SinglyLinkedList smallList = createSmallSortedList();
		
		smallList.insert(1, -1);
		Object[] testCase = (Object[]) smallList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {	
			//System.out.println(((Node)testCase[0]).data);
		}
		
		assertEquals(-1, testCase[1]);
	}
	

	
	
	
	@Test
	public void insertTestOnLarge() {
		
		SinglyLinkedList largeList = createLargeSortedList();
		
		largeList.insert(1, -1);
		Object[] testCase = (Object[]) largeList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {	
			//System.out.println(((Node)testCase[0]).data);
		}
		
		assertEquals(-1, testCase[1]);
	}
	
	
	
	@Test
	public void insertTestOnEmpty() {
		
		SinglyLinkedList emptyList = new SinglyLinkedList();
		
		emptyList.insert(1, -1);
		Object[] testCase = (Object[]) emptyList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[0]).data);
		}
		
		assertEquals(-1, testCase[0]);
	}
	

/*------------------------------------------------- DELETE TESTS ------------------------------------------------------*/
	
	@Test
	public void deleteFirstOnSmall() {
		
		SinglyLinkedList smallList = createSmallSortedList();
		
		smallList.deleteFirst();
		Object[] testCase = (Object[]) smallList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[i]).data);
		}
		
		assertEquals(2, testCase[0]);
	}
	
	
	
	@Test
	public void deleteFirstOnLarge() {
		
		SinglyLinkedList largeList = createLargeSortedList();
		
		largeList.deleteFirst();
		Object[] testCase = (Object[]) largeList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[i]).data);
		}
		
		assertEquals(1, testCase[0]);
	}
	
	
	
	@Test
	public void deleteOnSmall() {
		
		SinglyLinkedList smallList = createSmallSortedList();
		
		smallList.delete(1);
		Object[] testCase = (Object[]) smallList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println(((Node)testCase[i]).data);
		}
		
		assertEquals(3, testCase[1]);
	}
	
	
	
	@Test
	public void deleteOnLarge() {
		
		SinglyLinkedList largeList = createLargeSortedList();
		Object[] testCase = (Object[]) largeList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.print(testCase[i] + " ");
		}
		//System.out.println();
		
		largeList.delete(1);
		testCase = (Object[]) largeList.toArray();
		
		for(int i = 0; i < testCase.length; i++) {
			//System.out.print(testCase[i] + " ");
		}
		//System.out.println();
		
		assertEquals(2, testCase[1]);
	}
	
	
/*------------------------------------------------- INDEX_OF TESTS ------------------------------------------------------*/	
	
	@Test
	public void indexOfOnSmall() {
		
		SinglyLinkedList smallList = createSmallSortedList();
	
		//System.out.println(smallList.indexOf(3));
		assertEquals(2, smallList.indexOf(3));
	} 
	
	
	
	@Test
	public void indexOfOnLarge() {
		
		SinglyLinkedList largeList = createLargeSortedList();
		
		//System.out.println(largeList.indexOf(100));
		assertEquals(100, largeList.indexOf(100));
	} 
	
	
	
	@Test
	public void indexOfOnEmpty() {
		
		SinglyLinkedList emptyList = new SinglyLinkedList();
		
		//System.out.println(emptyList.indexOf(100));
		assertEquals(-1, emptyList.indexOf(100));
	} 
	
	
/*----------------------------------------------- IS_EMPTY TESTS --------------------------------------------------------*/
	
	@Test
	public void isEmptyOnEmpty() {
		
		SinglyLinkedList emptyList = new SinglyLinkedList();
		assertTrue(emptyList.isEmpty());
	} 
	
	
	
	@Test
	public void isEmptyOnFull() {
		
		SinglyLinkedList smallList = createSmallSortedList();
		assertEquals(smallList.isEmpty(), false); // assertFalse is glitching for me for whatever reason.
	}
	
	
/*------------------------------------------------- CLEAR TESTS ---------------------------------------------------------*/
	
	
	@Test
	public void clearOnSmall() {
		
		SinglyLinkedList smallList = createSmallSortedList();
		smallList.clear();
		
		assertTrue(smallList.isEmpty());
	}
	
	
	
	@Test
	public void clearOnLarge() {
		
		SinglyLinkedList largeList = createLargeSortedList();
		largeList.clear();
		
		assertTrue(largeList.isEmpty());
	}
	
	
	
	@Test
	public void clearOnEmpty() {
		
		SinglyLinkedList emptyList = new SinglyLinkedList();
		emptyList.clear();
		
		assertTrue(emptyList.isEmpty());
	}
	
/*-------------------------------------------- ITERATOR TESTS ----------------------------------------------*/
	
	@Test
	public void hasNextIterator() {
		
		SinglyLinkedList smallList = createSmallSortedList();
		assertTrue(smallList.iterator().hasNext());
	}
	
	@Test
	public void testRemoveOnEmpty() {
		
		SinglyLinkedList<Integer> smallList = new SinglyLinkedList<>();
		Iterator<Integer> itr = smallList.iterator();
		
		try {
			
			itr.remove();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void testRemoveOnSingleItem() {
		
		SinglyLinkedList<Integer> smallList = new SinglyLinkedList<>();
		
		smallList.addNode(1);
		Iterator<Integer> itr = smallList.iterator();
		
		itr.next();
		itr.remove();
		
		try {
			
			itr.remove();
			assertTrue(false);
		
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void testRemoveOnceInIterator() {
		SinglyLinkedList<Integer> smallList = new SinglyLinkedList<>();
		
		for(Integer i = 1; i <= 4; i++) {
			smallList.addNode(i);
		}
		
		Iterator<Integer> itr = smallList.iterator();
		itr.next();
		itr.remove();
		Integer testNum = 2;
		assertEquals(testNum, smallList.head.data);
	}
	
	
	
	@Test
	public void testRemoveTwiceOnListWithSingleValue() {
		
		SinglyLinkedList<Integer> smallList = new SinglyLinkedList<>();
		
		smallList.addNode(1);
		
		Iterator<Integer> itr = smallList.iterator();
		itr.next();
		itr.remove();
		itr.next();
		
		try {
			
			itr.remove();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void testRemoveInTheMiddleOfSmallList() {
		SinglyLinkedList<Integer> smallList = new SinglyLinkedList<>();
		
		smallList.addNode(1);
		smallList.addNode(2);
		smallList.addNode(3);
		smallList.addNode(4);
		
		Iterator<Integer> itr = smallList.iterator();
		itr.next();
		itr.next();
		itr.remove();
		Integer expected = 3;
		assertEquals(expected, itr.next());
	}
	
	
	
	@Test
	public void testRemoveMultipleTimesInIterator() {
		SinglyLinkedList<Integer> smallList = new SinglyLinkedList<>();
		
		for(Integer i = 1; i <= 4; i++) {
			smallList.addNode(i);
		}
		
		Iterator<Integer> itr = smallList.iterator();
		
		itr.next();
		itr.remove();
		Integer testNum = 2;
		assertEquals(testNum, smallList.head.data);
	
		itr.next();
		itr.remove();
		testNum++;
		assertEquals(testNum,smallList.head.data);
		
		itr.next();
		itr.remove();
		testNum++;
		assertEquals(testNum,smallList.head.data);
	}
	
	
	@Test
	public void removeAndNextTestForIterator() {
		
		SinglyLinkedList<Integer> smallList = new SinglyLinkedList<>();
		
		smallList.addNode(1);
		smallList.addNode(2);
		smallList.addNode(3);
		smallList.addNode(4);
		
		Iterator<Integer> itr = smallList.iterator();
		itr.next();
		
		Object[] testCase = smallList.toArray();
		//for(int i = 0; i < testCase.length; i++) {
			//System.out.println( ((Node)testCase[i]).data );
		//}
		
		assertEquals( testCase[1], 2);
		
		itr.remove();
		itr.next();
		
		//testCase = smallList.toArray();
		//for(int i = 0; i < testCase.length; i++) {
			//System.out.println( ((Node)testCase[i]).data );
		//}
		assertEquals(testCase[1], 2);
		
		itr.remove();
		itr.next();
		
		//testCase = smallList.toArray();
		for(int i = 0; i < testCase.length-1; i++) {
			//System.out.println(testCase[i]);
		}
		assertEquals(testCase[1], 2);
		
		itr.remove();
		itr.next();
		
		//testCase = smallList.toArray();
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println( ((Node)testCase[i]).data );
		}
		assertEquals(testCase[0], 1);
		
		//itr.remove();
		//itr.next();
		
		//testCase = smallList.toArray();
		for(int i = 0; i < testCase.length; i++) {
			//System.out.println( ((Node)testCase[i]).data );
		}
		
		assertEquals(smallList.size(), 1);
	}
	
}
