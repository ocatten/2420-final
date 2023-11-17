package assignment10;

/**
 * Find K Largest Tester
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 07:19:23 CS-2420_001 SUM-2023
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class FindKLargestTester<E> {
	
	
	// Fields
	//private BinaryMaxHeap<Integer> numHeapEmpty;
	//private BinaryMaxHeap<Integer> numHeapSmall;
	//private BinaryMaxHeap<Integer> numHeapLarge;
	
	FindKLargest<Integer> kLargest = new FindKLargest<Integer>();
	
	private List<String> stringList = new ArrayList<String>();
	private List<Integer> smallList = new ArrayList<Integer>();
	private List<Integer> largeList = new ArrayList<Integer>();


/*=============================================================== SET UP ====================================================================================*/


	public void stringListSetUp () {

		for (Integer i = 0; i < 7; i++) {
			stringList.add(i.toString());
		}
	}
	
	
	
	public void smallListSetUp () {

		for (int i = 0; i < 7; i++) {
			smallList.add(i);
		}
	}



	public void largeListSetUp () {

		for (int i = 0; i < 10000; i++) {
			largeList.add(i);
		}
	}


/*========================================================== LARGEST_HEAP TESTS =============================================================================*/


	//ArrayLists
	//private ArrayList<Integer> numSmallList = new ArrayList<Integer>();
	//private ArrayList<Integer> numLargeList = new ArrayList<Integer>();
	@Test
	public void kLargestHeapComparableExceptionTest () {

		try {

			//FindKLargest.findKLargestHeap(new ArrayList<Integer>(), 7);
			assertTrue(false);

		} catch (Exception e) {
			assertTrue(true);
		}
	}



	@SuppressWarnings("removal")
	@Test
	public void kLargestHeapComparableOnSmallTest () {

		smallListSetUp();
		List<Integer> test = kLargest.findKLargestHeap(smallList, 7);

		assertEquals(new Integer(6), test.get(0));
		assertEquals(new Integer(5), test.get(1));
		assertEquals(new Integer(4), test.get(2));
	}



	@SuppressWarnings("removal")
	@Test
	public void kLargestHeapComparableOnLargestTest () {

		largeListSetUp();
		List<Integer> test = kLargest.findKLargestHeap(largeList, 10000);
		
		for(int i = 0; i < 10000; i++) {
			assertEquals(new Integer(9999-i), test.get(i));
		}
	}

	
	/*==================================================== COMPARATOR TESTS =================================================================*/

	
	@Test
	public void kLargestHeapComparatorOnEmptyTest () {

		smallListSetUp();
		
		List<String> stringList = new ArrayList<String>();
		
		Comparator<String> cmp = new Comparator<String>() {
			public int compare(String lhs, String rhs) {
				return lhs.compareTo(rhs);
			} 
		};
		
		@SuppressWarnings("static-access")
		List<String> test = kLargest.findKLargestHeap(stringList, 3, cmp);
		
		assertEquals("6", test.get(0));
		assertEquals("5", test.get(1));
		assertEquals("4", test.get(2));
	}
	
	



	@Test
	public void kLargestHeapComparatorOnSmallTest () {


	}



	@Test
	public void kLargestHeapComparatorOnLargeTest () {


	}


/*========================================================== LARGEST_SORT TESTS =============================================================================*/



	@Test
	public void kLargestSortComparableOnEmptyTest () {


	}



	@Test
	public void kLargestSortComparableOnSmallTest () {


	}



	@Test
	public void kLargestSortComparableOnLargestTest () {


	}



	@Test
	public void kLargestSortComparatorOnEmptyTest () {


	}



	@Test
	public void kLargestSortComparatorOnSmallTest () {


	}



	@Test
	public void kLargestSortComparatorOnLargeTest () {


	}

	
	
	
	//Binary heaps
	/*
	private BinaryMaxHeap<Integer> numHeapEmpty;
	private BinaryMaxHeap<Integer> numHeapSmall;
	private BinaryMaxHeap<Integer> numHeapLarge;
	
	//ArrayLists
	private List<Integer> numSmallList = new ArrayList<Integer>();
	private List<Integer> numLargeList = new ArrayList<Integer>();
	private List<String> stringSmallList = new ArrayList<String>();
	
	
	FindKLargest findKLargest;
	
	Comparator<Integer> cmpInt;
	Comparator<String> cmpString;

	
	//@BeforeEach
	void setup(){
		
		Random rng = new Random();
		
		//Create Small String List
		stringSmallList.add("double shot");
		stringSmallList.add("bread");
		stringSmallList.add("cake");
		stringSmallList.add("egg");
		stringSmallList.add("apple");
		stringSmallList.add("carrot");
		stringSmallList.add("cream");
		
		
		//Create Small ArrayList
		for(int i = 0; i < 15; i++) {
			//int num = rng.nextInt(10000);
			numSmallList.add(i);
		}
		
		//Create Large ArrayList
		for(int i = 0; i < 1000; i++) {
			int num = rng.nextInt(10000);
			numLargeList.add(num);
		}
		
		//Setup small heap
		numHeapSmall = new BinaryMaxHeap(numSmallList);
		//Setup large heap
		numHeapLarge = new BinaryMaxHeap(numLargeList);
		
		// Comparator object used for the generic objects 
		cmpInt = new Comparator<Integer>() { 
			public int compare(Integer e1, Integer e2) { return (e1).compareTo(e2); } };
	
		// Comparator object used for the generic objects 
		cmpString = new Comparator<String>() { 
			public int compare(String e1, String e2) { return (e1).compareTo(e2);}};	
	}
	
//================================================================TEST-------------------------------------------------------------
	
	@Test
	public void TestKLargestWithKEquals0() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestSort(numSmallList, 0);
		
		List<Integer> empty = new ArrayList<Integer>();
		
		assertEquals(empty, kLargest);
	}
	
	@Test
	public void TestKLargestWithKEquals0WithBinaryHeap() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(numSmallList, 0);
		
		List<Integer> empty = new ArrayList<Integer>();
		
		assertEquals(empty, kLargest);
	}
	
	
//---------------------------------------Find K Largest With Java Sort Tests-------------------------------------
	
	
	
	@Test
	public void TestKLargestOnJavaSortWithSingleK() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestSort(numSmallList, 1);
		
		boolean topLargest = (kLargest.get(0) == 14);
		assertEquals(true, topLargest);
	}
	
	@Test
	public void TestKLargestOnJavaSortWithKMultiple() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestSort(numSmallList, 3);
		

		Integer expected = 14;
		assertEquals(expected, kLargest.get(0));
		expected = 13;
		assertEquals(expected, kLargest.get(1));
		expected = 12;
		assertEquals(expected, kLargest.get(2));
	}
	
	@Test
	public void TestKLargestOnJavaSortWithKTheSizeOfTheList() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestSort(numSmallList, 15);
		

		for(int i = 0; i < 15; i++) {
			Integer expected = 14 - i;
			assertEquals(expected, kLargest.get(i));
		}		
	}
	
	@Test
	public void TestKLargestOnJavaSortWithLargeList() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestSort(numLargeList, 150);
		Integer prev = 10001;

		for(int i = 0; i < 150; i++) {
			boolean smallerThanLast = (prev >= kLargest.get(i));
			prev = kLargest.get(i);
			assertEquals(true, smallerThanLast);
		}		
	}
	
	@Test
	public void TestKLargestOnJavaSortComparator() {
		setup();
		
		List<String> kLargest = findKLargest.findKLargestSort(stringSmallList, 3, cmpString);
		

		String expected = "egg";
		assertEquals(expected, kLargest.get(0));
		expected = "double shot";
		assertEquals(expected, kLargest.get(1));
		expected = "cream";
		assertEquals(expected, kLargest.get(2));
	}
	
	
	
	//---------------------------------------Find K Largest With Binary Heap Tests-------------------------------------

	@Test
	public void TestBinaryHeapWithSingleK() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(numSmallList, 1);
		
		Integer expected = 14;
		
		assertEquals(expected, kLargest.get(0));
	}
	
	@Test
	public void TestBinaryHeapWithKBeingThree() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(numSmallList, 3);
		
		Integer expected = 14;
		
		assertEquals(expected, kLargest.get(0));
		expected = 13;
		assertEquals(expected, kLargest.get(1));
		expected = 12;
		assertEquals(expected, kLargest.get(2));
	}

	@Test
	public void TestBinaryHeapWithKBeingLengthOfTheList() {
		setup();
		
		List<Integer> kLargest = findKLargest.findKLargestHeap(numSmallList, 14);
		

		
		for(int i = 0; i < numSmallList.size(); i++){
			Integer expected = 14 - i;
			System.out.println("expected = " + expected + " actual is " + kLargest.get(i));
		}
	}
	*/
}