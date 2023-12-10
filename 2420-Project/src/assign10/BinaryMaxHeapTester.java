package assign10;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * Tester class for the BinaryMaxHeap class, testing its various methods and operations
 * 
 * @author: Everett Oglesby & Parker Catten
 * @version: 11:28:23 FA-23 2420_020
 */
public class BinaryMaxHeapTester {
	
	
	private BinaryMaxHeap<Integer> intMaxHeap;
	
/*========================================================= PERCOLATE_UP() + ADD() ========================================================================*/
	
	@Test
	@SuppressWarnings("removal")
	public void createSmallHeap () {
		
		intMaxHeap = new BinaryMaxHeap<Integer>();
		
		for (int i = 0; i < 7; i++) {
			intMaxHeap.add(i);
		} 
		
		assertEquals(new Integer(6), intMaxHeap.peek());
		
		for (int i = 2; i < 7; i++) {
				
			assertEquals( intMaxHeap.innerCompare( (Integer)intMaxHeap.toArray()[i], 
												(Integer)intMaxHeap.toArray()[(i-1)/2]), -1);
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void createLargeHeap () {
		
		intMaxHeap = new BinaryMaxHeap<Integer>();
		
		for (int i = 0; i < 10000; i++) {
			intMaxHeap.add(i);
		}
		
		assertEquals(new Integer(9999), intMaxHeap.peek());
		
		for (int i = 2; i < 10000; i++) {
				
				
			assertEquals(-1, intMaxHeap.innerCompare((Integer)intMaxHeap.toArray()[i],
												  (Integer)intMaxHeap.toArray()[(i-1)/2]));
			
		}
	}
	
	
	
	@Test
	public void addOnSmallTest () {
		createSmallHeap();
		
		for (int i = 0; i < 10000; i++) {
			intMaxHeap.add(i);
		}
		
		
		for (int i = 1; i < 10000; i++) {
				
			assertEquals( -1, intMaxHeap.innerCompare( (Integer)intMaxHeap.toArray()[i], 
												  	(Integer)intMaxHeap.toArray()[(i-1)/2]) );
			
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void addOnLargeTest () {
		
		intMaxHeap = new BinaryMaxHeap<Integer>();
		
		for (int i = 0; i < 10000; i++) {
			intMaxHeap.add(i);
		}
		
		assertEquals(new Integer(9999), intMaxHeap.peek());
		
		for (int i = 1; i < 10000; i++) {
				
			assertEquals( intMaxHeap.innerCompare( (Integer)intMaxHeap.toArray()[i], 
												(Integer)intMaxHeap.toArray()[(i-1)/2]), -1);
			
		}
	}
	
	
	
/*========================================================= PERCOLATE_DOWN ================================================================================*/
	
	
	@Test
	public void percolateDownTest () {
		createSmallHeap();
		
		intMaxHeap.replaceMax(-1);
		intMaxHeap.percolateDown(0);
		
		// Print out resulting heap
		@SuppressWarnings("unused")
		Object[] heapArray = intMaxHeap.toArray();


		
	}
	
	
	
/*========================================================= IS_EMPTY() + CLEAR() =======================================================================*/
	
	
	@Test
	public void clearAndIsEmptyOnEmptyTest () {	
		
		intMaxHeap = new BinaryMaxHeap<Integer>();
		assertTrue(intMaxHeap.isEmpty());
		
		intMaxHeap.add(1);
		assertFalse(intMaxHeap.isEmpty());
		
		intMaxHeap.clear();
		assertTrue(intMaxHeap.isEmpty());
	}
	
	
	
	@Test
	public void clearAndIsEmptyOnSmallTest () {	
		
		createSmallHeap();
		assertFalse(intMaxHeap.isEmpty());
		
		intMaxHeap.clear();
		assertTrue(intMaxHeap.isEmpty());
		
		try {
			
			assertEquals(0, intMaxHeap.toArray()[0]);
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void clearAndIsEmptyOnLargeTest () {	
		
		createLargeHeap();
		assertFalse(intMaxHeap.isEmpty());
		
		intMaxHeap.clear();
		assertTrue(intMaxHeap.isEmpty());
		
		try {
			
			assertEquals(0, intMaxHeap.toArray()[0]);
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
/*========================================================= INNER_COMPARE() ==============================================================================*/
	
	
	@Test
	public void compareWithoutObjectTest () {
		
		intMaxHeap = new BinaryMaxHeap<Integer>();
		
		for (int i = 0; i < 7; i++) {
			
			intMaxHeap.add(i);
		}
		
		for (int i = 2; i < 7; i++) {
			if(intMaxHeap.toArray()[i] != null) {
				
				assertEquals( intMaxHeap.innerCompare( (Integer)intMaxHeap.toArray()[i], 
													(Integer)intMaxHeap.toArray()[(i-1)/2]), -1);
			
			}
		}
	}
	
	
	
	@Test
	public void compareWithObjectTest () {
		
		Comparator<Integer> cmp = new Comparator<Integer>() { 
			public int compare(Integer e1, Integer e2) { return e1.compareTo(e2); } };
		
		intMaxHeap = new BinaryMaxHeap<Integer>(cmp);
		
		
		for (int i = 0; i < 7; i++) {
			
			intMaxHeap.add(i);
		}
		
		for (int i = 2; i < 7; i++) {
			
			if(intMaxHeap.toArray()[i] != null) {
				
				assertEquals(-1, intMaxHeap.innerCompare((Integer)intMaxHeap.toArray()[i], 
													  (Integer)intMaxHeap.toArray()[(i-1)/2]));
			
			}
		}
	}
	
	
	
/*============================================================ RESIZE() =================================================================================*/
	
	
	@Test
	public void resizeOnEmptyTest () {
		
		intMaxHeap = new BinaryMaxHeap<Integer>();
		assertEquals(3, intMaxHeap.getLength());
		
		intMaxHeap.add(1);
		assertEquals(3, intMaxHeap.getLength());
	}
	
	
	@Test
	public void resizeOnSmallTest () {
		
		createSmallHeap();
		assertEquals(15, intMaxHeap.getLength());
		
		for(int i = 0; i < 8; i++) {
			intMaxHeap.add(7+i);
		}
		
		assertEquals(31, intMaxHeap.getLength());
	}
	
	
	
	@Test
	public void resizeOnLargeTest () {
		
		createLargeHeap();
		assertEquals(16383, intMaxHeap.getLength());
		
		for(int i = 0; i < 6383; i++) {
			intMaxHeap.add(9999+i);
		}
		
		assertEquals(32767, intMaxHeap.getLength());
	}
	
	
	
/*================================================================= PEEK() ==============================================================================*/
	
	
	@Test
	public void peekOnEmptyTest () {
		
		intMaxHeap = new BinaryMaxHeap<Integer>();
		
		try {
			
			intMaxHeap.peek();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void peekOnSmallTest () {
		createSmallHeap();
		
		assertEquals(new Integer(6), intMaxHeap.peek());
		intMaxHeap.add(10);
		intMaxHeap.add(8);
		assertEquals(new Integer(10), intMaxHeap.peek());
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void peekOnLargeTest () {
		createLargeHeap();
		
		assertEquals(new Integer(9999), intMaxHeap.peek());
		intMaxHeap.add(11000);
		intMaxHeap.add(10500);
		assertEquals(new Integer(11000), intMaxHeap.peek());
	}
	
	
	
/*=========================================================== EXTRACT_MAX() ==========================================================================
	
	
	@Test
	public void extractMaxOnEmptyTest () {
		
		intMaxHeap = new BinaryMaxHeap<Integer>();
		
		try {
			
			intMaxHeap.extractMax();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void extractMaxOnSmallTest () {
		createSmallHeap();
		
		for (int i = 6; 0 < i; i--) {
			
			assertEquals(new Integer(i), intMaxHeap.peek());
			assertEquals(i+1, intMaxHeap.size());
			
			assertEquals(new Integer(i), intMaxHeap.extractMax());
			assertEquals(new Integer(i-1), intMaxHeap.peek());
			assertEquals(i, intMaxHeap.size());
		}
		
	}
	
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void extractMaxOnLargeTest () {
		
		createLargeHeap();
		
		for (int i = 9999; 0 < i; i--) {
			
			assertEquals(new Integer(i), intMaxHeap.peek());
			assertEquals(i+1, intMaxHeap.size());
			assertEquals(new Integer(i), intMaxHeap.extractMax());
			
			assertEquals(new Integer(i-1), intMaxHeap.peek());
			assertEquals(i, intMaxHeap.size());
		}
	}
	
	
	
/*================================================================= SIZE() ==============================================================================
			
			
	@Test
	public void sizeOnEmptyTest () {
			
		intMaxHeap = new BinaryMaxHeap<Integer>();
		assertEquals(intMaxHeap.size(), 0);
	}
	
	
	
	@Test
	public void sizeOnSmallTest () {
			
		createSmallHeap();
		assertEquals(intMaxHeap.size(), 7);
		
		intMaxHeap.add(10);
		assertEquals(intMaxHeap.size(), 8);
		
		intMaxHeap.extractMax();
		assertEquals(intMaxHeap.size(), 7);
	}
	
	
	
	@Test
	public void sizeOnLargeTest () {
			
		createLargeHeap();
		assertEquals(intMaxHeap.size(), 10000);
		
		intMaxHeap.add(11000);
		assertEquals(intMaxHeap.size(), 10001);
		
		intMaxHeap.extractMax();
		assertEquals(intMaxHeap.size(), 10000);
	}
	
	
	
/*============================================================ TO_ARRAY() ===============================================================================*/
	
	
	@Test
	public void toArrayOnEmptyTest () {	
		intMaxHeap = new BinaryMaxHeap<Integer>();
		
		Object[] emptyArray = intMaxHeap.toArray();
		assertEquals(0, emptyArray.length);
	}
	
	
	
	@Test
	public void toArrayOnSmallTest () {	
		createSmallHeap();
		
		Object[] smallArray = intMaxHeap.toArray();
		assertEquals(7, smallArray.length);
		
		for (int i = 0; i < smallArray.length; i++) {
			
			assertTrue(smallArray[i] != null);
			assertTrue(1 > intMaxHeap.innerCompare((Integer)intMaxHeap.toArray()[i], 
					  							(Integer)intMaxHeap.toArray()[(i-1)/2]));
		}
	}
	
	
	
	@Test
	public void toArrayOnLargeTest () {	
		createLargeHeap();
		
		Object[] largeArray = intMaxHeap.toArray();
		assertEquals(10000, largeArray.length);
		
		for (int i = 0; i < largeArray.length; i++) {
			
			assertTrue(largeArray[i] != null);
			assertTrue(1 > intMaxHeap.innerCompare((Integer)intMaxHeap.toArray()[i], 
					  							(Integer)intMaxHeap.toArray()[(i-1)/2]));
		}
	}

	
	@Test
	public void buildHeap() {
		
		Comparator<Integer> cmp = new Comparator<Integer>() {
			public int compare(Integer lhs, Integer rhs) {
				
				if(lhs < rhs) {
					
					return 1;
				} else if (lhs > rhs) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		List<Integer> intList = new ArrayList<Integer>();
		
		intList.add(10);
		intList.add(40);
		intList.add(20);
		intList.add(50);
		intList.add(30);
		
		intMaxHeap = new BinaryMaxHeap<Integer>(cmp);
		
		intMaxHeap.buildHeap(intList);
		
		assertEquals(5,intMaxHeap.size());
		
		Integer expected = 10;
		
		assertEquals(expected,intMaxHeap.extractMax());
	}
}

