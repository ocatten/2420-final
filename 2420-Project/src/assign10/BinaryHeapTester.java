package assign10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;


public class BinaryHeapTester {
	
	private BinaryMaxHeap<Integer> maxHeap;
	
	@Test
	public void smallHeapComparable() {
		maxHeap = 
	}
	
	@Test
	public void smallHeapComparator() {
		
	}
	
	@Test
	@SuppressWarnings("removal")
	public void smallHeapSetUp() {
		
		numHeap = new BinaryMaxHeap<Integer>();
		
		for (int i = 0; i < 7; i++) {
			numHeap.add(i);
		} 
		
		assertEquals(new Integer(6), numHeap.peek());
		
		for (int i = 2; i < 7; i++) {
				
			assertEquals( numHeap.innerCompare( (Integer)numHeap.toArray()[i], 
												(Integer)numHeap.toArray()[(i-1)/2]), -1);
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void largeHeapSetUp() {
		
		numHeap = new BinaryMaxHeap<Integer>();
		
		for (int i = 0; i < 10000; i++) {
			numHeap.add(i);
		}
		
		assertEquals(new Integer(9999), numHeap.peek());
		
		for (int i = 2; i < 10000; i++) {
				
				
			assertEquals(-1, numHeap.innerCompare((Integer)numHeap.toArray()[i],
												  (Integer)numHeap.toArray()[(i-1)/2]));
			
		}
	}
	
	
	
	@Test
	public void addOnSmallTest() {
		smallHeapSetUp();
		
		for (int i = 0; i < 10000; i++) {
			numHeap.add(i);
		}
		
		
		for (int i = 1; i < 10000; i++) {
				
			assertEquals( -1, numHeap.innerCompare( (Integer)numHeap.toArray()[i], 
												  	(Integer)numHeap.toArray()[(i-1)/2]) );
			
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void addOnLargeTest() {
		
		numHeap = new BinaryMaxHeap<Integer>();
		
		for (int i = 0; i < 10000; i++) {
			numHeap.add(i);
		}
		
		assertEquals(new Integer(9999), numHeap.peek());
		
		for (int i = 1; i < 10000; i++) {
				
			assertEquals( numHeap.innerCompare( (Integer)numHeap.toArray()[i], 
												(Integer)numHeap.toArray()[(i-1)/2]), -1);
			
		}
	}
	
	
/*============================================================ RESIZE TESTS =================================================================================*/
	
	
	@Test
	public void resizeOnEmptyTest() {
		
		numHeap = new BinaryMaxHeap<Integer>();
		assertEquals(3, numHeap.getArrayLength());
		
		numHeap.add(1);
		assertEquals(3, numHeap.getArrayLength());
	}
	
	
	@Test
	public void resizeOnSmallTest() {
		
		smallHeapSetUp();
		assertEquals(15, numHeap.getArrayLength());
		
		for(int i = 0; i < 8; i++) {
			numHeap.add(7+i);
		}
		
		assertEquals(31, numHeap.getArrayLength());
	}
	
	
	
	@Test
	public void resizeOnLargeTest() {
		
		largeHeapSetUp();
		assertEquals(16383, numHeap.getArrayLength()); // Found with graphing calculator
		
		for(int i = 0; i < 6383; i++) {
			numHeap.add(9999+i);
		}
		
		assertEquals(32767, numHeap.getArrayLength());
	}
	
	
/*========================================================= INNER_COMPARE TESTS =============================================================================*/
	
	
	@Test
	public void compareWithoutObjectTest() {
		
		numHeap = new BinaryMaxHeap<Integer>();
		
		for (int i = 0; i < 7; i++) {
			
			numHeap.add(i);
			
			/*
			System.out.println();
			for(Object item : numHeap.toArray()) {
				System.out.println(item + " ");
			}
			System.out.println();
			*/
		}
		
		for (int i = 2; i < 7; i++) {
			if(numHeap.toArray()[i] != null) {
				
				assertEquals( numHeap.innerCompare( (Integer)numHeap.toArray()[i], 
													(Integer)numHeap.toArray()[(i-1)/2]), -1);
			
			}
		}
	}
	
	
	
	@Test
	public void compareWithObjectTest() {
		
		Comparator<Integer> cmp = new Comparator<Integer>() { 
			public int compare(Integer e1, Integer e2) { return e1.compareTo(e2); } };
		
		numHeap = new BinaryMaxHeap<Integer>(cmp);
		
		
		for (int i = 0; i < 7; i++) {
			
			numHeap.add(i);
			
			/*
			System.out.println();
			for(Object item : numHeap.toArray()) {
				System.out.println(item + " ");
			}
			System.out.println();
			*/
			
		}
		
		for (int i = 2; i < 7; i++) {
			
			if(numHeap.toArray()[i] != null) {
				
				assertEquals(-1, numHeap.innerCompare((Integer)numHeap.toArray()[i], 
													  (Integer)numHeap.toArray()[(i-1)/2]));
			
			}
		}
	}
	
	
/*===================================================== EXTRACT_MAX & PERCOLATE_DOWN TESTS ==================================================================*/
	
	
	@Test
	public void extractMaxOnEmptyTest() {
		
		numHeap = new BinaryMaxHeap<Integer>();
		
		try {
			
			numHeap.extractMax();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@SuppressWarnings("removal")
	@Test
	public void extractMaxOnSmallTest() {
		
		smallHeapSetUp();
		
		// Heap starts at 0
		for (int i = 6; 0 < i; i--) {
			
			assertEquals(new Integer(i), numHeap.peek());
			assertEquals(i+1, numHeap.size());
			
			assertEquals(new Integer(i), numHeap.extractMax());
			assertEquals(new Integer(i-1), numHeap.peek());
			assertEquals(i, numHeap.size());
		}
		
	}
	
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void extractMaxOnLargeTest() {
		
		largeHeapSetUp();
		
		// Heap starts at 0
		for (int i = 9999; 0 < i; i--) {
			
			assertEquals(new Integer(i), numHeap.peek());
			assertEquals(i+1, numHeap.size());
			
			assertEquals(new Integer(i), numHeap.extractMax());
			assertEquals(new Integer(i-1), numHeap.peek());
			assertEquals(i, numHeap.size());
		}
	}
	
	
/*================================================================= PEEK TESTS ==============================================================================*/
	
	
	@Test
	public void peekOnEmptyTest() {
		
		numHeap = new BinaryMaxHeap<Integer>();
		
		try {
			
			numHeap.peek();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void peekOnSmallTest() {
		smallHeapSetUp();
		
		assertEquals(new Integer(6), numHeap.peek());
		numHeap.add(10);
		numHeap.add(8);
		assertEquals(new Integer(10), numHeap.peek());
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public void peekOnLargeTest() {
		largeHeapSetUp();
		
		assertEquals(new Integer(9999), numHeap.peek());
		numHeap.add(11000);
		numHeap.add(10500);
		assertEquals(new Integer(11000), numHeap.peek());
	}
	
	
/*================================================================= SIZE TESTS ==============================================================================*/
			
			
	@Test
	public void sizeOnEmptyTest() {
			
		numHeap = new BinaryMaxHeap<Integer>();
		assertEquals(numHeap.size(), 0);
	}
	
	
	
	@Test
	public void sizeOnSmallTest() {
			
		smallHeapSetUp();
		assertEquals(numHeap.size(), 7);
		
		numHeap.add(10);
		assertEquals(numHeap.size(), 8);
		
		numHeap.extractMax();
		assertEquals(numHeap.size(), 7);
	}
	
	
	
	@Test
	public void sizeOnLargeTest() {
			
		largeHeapSetUp();
		assertEquals(numHeap.size(), 10000);
		
		numHeap.add(11000);
		assertEquals(numHeap.size(), 10001);
		
		numHeap.extractMax();
		assertEquals(numHeap.size(), 10000);
	}
	
	
/*========================================================= CLEAR & IS_EMPTY TESTS ==========================================================================*/
	
	
	@Test
	public void clearAndIsEmptyOnEmptyTest() {	
		
		numHeap = new BinaryMaxHeap<Integer>();
		assertTrue(numHeap.isEmpty());
		
		numHeap.add(1);
		assertFalse(numHeap.isEmpty());
		
		numHeap.clear();
		assertTrue(numHeap.isEmpty());
	}
	
	
	
	@Test
	public void clearAndIsEmptyOnSmallTest() {	
		
		smallHeapSetUp();
		assertFalse(numHeap.isEmpty());
		
		numHeap.clear();
		assertTrue(numHeap.isEmpty());
		
		try {
			
			assertEquals(0, numHeap.toArray()[0]);
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void clearAndIsEmptyOnLargeTest() {	
		
		largeHeapSetUp();
		assertFalse(numHeap.isEmpty());
		
		numHeap.clear();
		assertTrue(numHeap.isEmpty());
		
		try {
			
			assertEquals(0, numHeap.toArray()[0]);
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
/*============================================================ TO_ARRAY TESTS ===============================================================================*/
	
	
	@Test
	public void toArrayOnEmptyTest() {	
		numHeap = new BinaryMaxHeap<Integer>();
		
		Object[] emptyArray = numHeap.toArray();
		assertEquals(0, emptyArray.length);
	}
	
	
	
	@Test
	public void toArrayOnSmallTest() {	
		smallHeapSetUp();
		
		Object[] smallArray = numHeap.toArray();
		assertEquals(7, smallArray.length);
		
		for (int i = 0; i < smallArray.length; i++) {
			
			assertTrue(smallArray[i] != null);
			assertTrue(1 > numHeap.innerCompare((Integer)numHeap.toArray()[i], 
					  							(Integer)numHeap.toArray()[(i-1)/2]));
		}
	}
	
	
	
	@Test
	public void toArrayOnLargeTest() {	
		largeHeapSetUp();
		
		Object[] largeArray = numHeap.toArray();
		assertEquals(10000, largeArray.length);
		
		for (int i = 0; i < largeArray.length; i++) {
			
			assertTrue(largeArray[i] != null);
			assertTrue(1 > numHeap.innerCompare((Integer)numHeap.toArray()[i], 
					  							(Integer)numHeap.toArray()[(i-1)/2]));
		}
	}

}

