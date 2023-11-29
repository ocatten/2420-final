package assign10;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;


public class FindKLargestTester<E> {
	
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
	
	//------------k Largest Heap Tests--------------------------------------------------------------------------------------//
	@Test
	public void emptyListWithPositiveNumber() {
		assertThrows(IllegalArgumentException.class, () -> { findKLargest.findKLargestHeap(emptyList, 1); });
	}
	
	@Test
	public void negativeKGiven() {
		assertThrows(IllegalArgumentException.class, () -> { findKLargest.findKLargestHeap(smallList, -1); });
	}
	
	@Test
	public void OutofBoundsK() {
		assertThrows(IllegalArgumentException.class, () -> { findKLargest.findKLargestHeap(smallList, 100); });
	}
	
	@Test
	public void emptyListWithKEqualsZero() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(emptyList, 0);
		
		assertEquals(0,kLargest.size());
	}
	
	@Test
	public void kEqualsZeroOnSmallList() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(emptyList, 0);
		
		assertEquals(0,kLargest.size());
	}
	
	@Test
	public void kLargestComparable() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(smallList, 10);
		
		assertEquals(10,kLargest.size());
		
		
		
		
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 10-i;
			for(int j = 0; j < kLargest.size();j++) {
				System.out.print(kLargest.toArray()[i] + " ");

			}
			System.out.println(kLargest.get(i));
			assertEquals(expected,kLargest.get(i));
		}
	}
	
	@Test
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
	public void kLargestOnLargeList() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(largeList, 250);
		
		assertEquals(250,kLargest.size());
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 5000-i;
			assertEquals(expected,kLargest.get(i));
		}
	}
	
	//------------k Largest With Java Sort Tests--------------------------------------------------------------------------------------//

	@Test
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