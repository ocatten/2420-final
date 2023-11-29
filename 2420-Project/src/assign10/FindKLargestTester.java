package assign10;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * Tester class for the FindKLargest class, testing its various methods and operations
 * 
 * @author: Everett Oglesby & Parker Catten
 * @version: 11:28:23 FA-23 2420_020
 */
public class FindKLargestTester<E> {
	
	// Fields
	private Comparator<Integer> cmp;
	
	List<Integer> emptyList;
	List<Integer> smallList;
	List<Integer> largeList;
	
	FindKLargest findKLargest;

	
	public void setup() {
		findKLargest = new FindKLargest();
		
		emptyList = new ArrayList<Integer>();
		
		smallList = new ArrayList<Integer>();
		for(Integer i = 1; i < 11; i++) {
			smallList.add(i);
		}
		
		largeList = new ArrayList<Integer>();
		for(Integer i = 1; i < 5001; i++) {
			largeList.add(i);
		}
	} 
	
	
	
/*========================================================= K_LARGEST_HEAP() ========================================================================*/
	
	
	
	@Test
	@SuppressWarnings("static-access")
	public void emptyListWithPositiveNumber() {
		assertThrows(IllegalArgumentException.class, () -> { findKLargest.findKLargestHeap(emptyList, 1); });
	}
	
	
	
	@Test
	@SuppressWarnings("static-access")
	public void negativeKGiven() {
		assertThrows(IllegalArgumentException.class, () -> { findKLargest.findKLargestHeap(smallList, -1); });
	}
	
	
	
	@Test
	@SuppressWarnings("static-access")
	public void OutofBoundsK() {
		assertThrows(IllegalArgumentException.class, () -> { findKLargest.findKLargestHeap(smallList, 100); });
	}
	
	
	
	@Test
	public void emptyListWithKEqualsZero() {
		setup();
		
		@SuppressWarnings("static-access")
		List<Integer> kLargest = findKLargest.findKLargestHeap(emptyList, 0);
		
		assertEquals(0,kLargest.size());
	}
	
	
	
	@Test
	@SuppressWarnings("static-access")
	public void kEqualsZeroOnSmallList() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(emptyList, 0);
		
		assertEquals(0,kLargest.size());
	}
	
	
	
	@Test
	@SuppressWarnings("static-access")
	public void kLargestComparable() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(smallList, 3);
		
		assertEquals(3,kLargest.size());
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 10-i;
			assertEquals(expected,kLargest.get(i));
		}
	}
	
	
	
	@Test
	@SuppressWarnings("static-access")
	public void kLargestComparator() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(smallList, 4, cmp);
		
		assertEquals(4,kLargest.size());
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 10-i;
			assertEquals(expected,kLargest.get(i));
		}
	}
	
	
	
	@Test
	@SuppressWarnings("static-access")
	public void kLargestOnLargeList() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(largeList, 250);
		
		assertEquals(250,kLargest.size());
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 5000-i;
			assertEquals(expected,kLargest.get(i));
		}
	}
	
	
	
/*=========================================================== K_LARGEST_SORT =========================================================================*/
	
	
	
	@Test
	@SuppressWarnings("static-access")
	public void kLargestWithComparableJavaSort() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestSort(smallList, 5);
		
		assertEquals(5,kLargest.size());
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 10-i;
			assertEquals(expected,kLargest.get(i));
		}
	}

	
	
	@Test
	@SuppressWarnings("static-access")
	public void kLargestWithComparatorJavaSort() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestSort(smallList, 5, cmp);
		
		assertEquals(5,kLargest.size());
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 10-i;
			assertEquals(expected,kLargest.get(i));
		}
	}
}