package assign10;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
		for(Integer i = 1; i < 10; i++) {
			smallList.add(i);
		}
		
		largeList = new ArrayList<Integer>();
		for(Integer i = 1; i < 5000; i++) {
			largeList.add(i);
		}
	}
	
	//------------k Largest Heap Tests--------------------------------------------------------------------------------------//
	public void emptyListGivenToHeap() {
		
	}
	
	public void negativeKGivenToHeap() {
		
	}
	
	public void emptyListWithKEqualsZero() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(emptyList, 0);
		
		assertEquals(0,kLargest.size());
	}
	
	public void kEqualsZeroOnSmallList() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(emptyList, 0);
		
		assertEquals(0,kLargest.size());
	}
	
	public void kLargestComparable() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(smallList, 3);
		
		assertEquals(3,kLargest.size());
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 10-i;
			assertEquals(expected,kLargest.get(i));
		}
	}
	
	public void kLargestComparator() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(smallList, 4, cmp);
		
		assertEquals(4,kLargest.size());
		for(Integer i = 0; i < kLargest.size();i++) {
			Integer expected = 10-i;
			assertEquals(expected,kLargest.get(i));
		}
	}
	
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



}