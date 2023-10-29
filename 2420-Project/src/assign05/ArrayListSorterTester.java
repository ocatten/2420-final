package assign05;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
 
public class ArrayListSorterTester {
	
	ArrayListSorter arraySorter = new ArrayListSorter();
	
	ArrayList<Integer> smallMixedArr = new ArrayList<Integer>();
	ArrayList<Integer> largeMixedArr = new ArrayList<Integer>();
	ArrayList<Integer> emptyArr = new ArrayList<Integer>();

	@SuppressWarnings("static-access")
	public void setup() {
		smallMixedArr = arraySorter.generatePermuted(10);
		largeMixedArr = arraySorter.generatePermuted(1000);
	}
	
	/*-------------------ASCENDING, DESCENDING AND PERMUTED TESTS-----------------------------*/
	
	@SuppressWarnings("static-access")
	@Test
	public void createSmallAscendingArray() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> expected = new ArrayList<Integer>();
		
		result = arraySorter.generateAscending(10);
		for(Integer i = 0; i < 10; i++) {
			expected.add(i+1);
		}
		
		assertEquals(result,expected);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void createLargeAscendingArray() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> expected = new ArrayList<Integer>();
		
		result = arraySorter.generateAscending(1000);
		for(Integer i = 0; i < 1000; i++) {
			expected.add(i+1);
		}
		
		assertEquals(result,expected);
	}
	
	@Test
	public void createSmallDescendingArray() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> expected = new ArrayList<Integer>();
		
		//result = arraySorter.generateDescending(10);
		for(Integer i = 10; i > 0; i--) {
			expected.add(i);
		}
		System.out.println(result.toString());
		assertEquals(result,expected);
	}
	
	@Test
	public void createLargeDescendingArray() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> expected = new ArrayList<Integer>();
		
		//result = arraySorter.generateDescending(1000);
		for(Integer i = 1000; i > 0; i--) {
			expected.add(i);
		}
		
		assertEquals(result,expected);
	}
	
	@Test
	public void createSmallPermutedArray() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		//result = arraySorter.generatePermuted(10);
		
		ArrayList<Integer> contains = new ArrayList<Integer>();
		
		for(Integer i = 0; i < 10; i++) {
			for(int j = 0; j < contains.size(); j++) {
				if(contains.get(j) == result.get(i)) {
					assertEquals(-1,j);
				}
			}
			contains.add(result.get(i));
		}
	}
	
	@Test
	public void createLargePermutedArray() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		//result = arraySorter.generatePermuted(1000);
		
		ArrayList<Integer> contains = new ArrayList<Integer>();
		
		for(Integer i = 0; i < 1000; i++) {
			for(int j = 0; j < contains.size(); j++) {
				if(contains.get(j) == result.get(i)) {
					assertEquals(-1,j);
				}
			}
			contains.add(result.get(i));
		}
	}
	
	
	/*-------------------EMPTY TESTS-------------------------------------------------------*/
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortOnEmptyArray() {
		setup();
		
		//arraySorter.mergesort(emptyArr);
		ArrayList<Integer> expected = arraySorter.generateAscending(0);
		
		assertEquals(expected,emptyArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void quicksortOnEmptyArray() {
		setup();
		
		//arraySorter.quicksort(emptyArr);;
		ArrayList<Integer> expected = arraySorter.generateAscending(0);
		
		assertEquals(expected,emptyArr);
	}
	
	
	/*-------------------MERGESORT TESTS-----------------------------------------------------*/
	
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortOnSmallArray() {
		setup();
		
		//arraySorter.mergesort(smallMixedArr);;
		ArrayList<Integer> expected = arraySorter.generateAscending(10);
		
		assertEquals(expected,smallMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortOnLargeArray() {
		setup();
		
		arraySorter.mergesort(largeMixedArr);
		ArrayList<Integer> expected = arraySorter.generateAscending(1000);
		
		assertEquals(expected,largeMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortOnSmallDecendingArray() {
		setup();
		
		ArrayList<Integer> result = arraySorter.generateDescending(10);
		arraySorter.mergesort(result);
		ArrayList<Integer> expected = arraySorter.generateAscending(10);
		
		assertEquals(expected,result);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortOnLargeDecendingArray() {
		setup();
		
		ArrayList<Integer> result = arraySorter.generateDescending(100);
		arraySorter.mergesort(result);
		ArrayList<Integer> expected = arraySorter.generateAscending(100);
		
		assertEquals(expected,result);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortWithInsertionSortOf1() {
		setup();
		
		arraySorter.setInsertionThreshold(1);
		arraySorter.mergesort(smallMixedArr);;
		ArrayList<Integer> expected = arraySorter.generateAscending(10);
		
		assertEquals(expected,smallMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortWithInsertionSortOf4() {
		setup();
		
		arraySorter.setInsertionThreshold(4);
		arraySorter.mergesort(smallMixedArr);;
		ArrayList<Integer> expected = arraySorter.generateAscending(10);
		
		assertEquals(expected,smallMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortWithInsertionSortSizeOfArray() {
		setup();
		
		arraySorter.setInsertionThreshold(10);
		arraySorter.mergesort(smallMixedArr);;
		ArrayList<Integer> expected = arraySorter.generateAscending(10);
		
		assertEquals(expected,smallMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void mergesortOnLargeArrayWithInsertionSortInMid() {
		setup();
		
		arraySorter.setInsertionThreshold(4);
		arraySorter.mergesort(largeMixedArr);;
		ArrayList<Integer> expected = arraySorter.generateAscending(1000);
		
		assertEquals(expected,largeMixedArr);
	}
	
	/*-----------------INSERTION SORT TESTS -------------------------------------------------*/
	
	@SuppressWarnings("static-access")
	@Test
	public void insertionSortOnSmallArray() {
		setup();
		
		arraySorter.insertionSort(smallMixedArr,0,smallMixedArr.size()-1);
		ArrayList<Integer> expected = arraySorter.generateAscending(10);

		
		assertEquals(expected,smallMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void insertionSortOnLargeArray() {
		setup();
		
		arraySorter.insertionSort(largeMixedArr,0,largeMixedArr.size()-1);
		ArrayList<Integer> expected = arraySorter.generateAscending(1000);

		
		assertEquals(expected,largeMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void insertionSortOnDecendingArray() {
		setup();
		
		ArrayList<Integer> result = arraySorter.generateDescending(20);
		arraySorter.insertionSort(result,0,result.size()-1);
		ArrayList<Integer> expected = arraySorter.generateAscending(20);
		
		assertEquals(expected,result);
	}
	
	/*-------------------Quicksort Tests--------------------------------------------------*/

	@SuppressWarnings("static-access")
	@Test
	public void quicksortOnSmallArray() {
		setup();
		
		arraySorter.quicksort(smallMixedArr);;
		ArrayList<Integer> expected = arraySorter.generateAscending(10);
		
		assertEquals(expected,smallMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void quicksortOnLargeArray() {
		setup();
		
		arraySorter.quicksort(largeMixedArr);
		ArrayList<Integer> expected = arraySorter.generateAscending(1000);
		
		assertEquals(expected,largeMixedArr);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void quicksortOnDecendingArray() {
		setup();
		
		ArrayList<Integer> result = arraySorter.generateDescending(20);
		arraySorter.quicksort(result);
		ArrayList<Integer> expected = arraySorter.generateAscending(20);
		
		assertEquals(expected,result);
	}

}
