package assign05;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ArrayListSorterTester {
	
	ArrayListSorter arraySorter = new ArrayListSorter();
	
	ArrayList<Integer> smallMixedArr = new ArrayList<Integer>();
	ArrayList<Integer> largeMixedArr = new ArrayList<Integer>();

	public void setup() {
		smallMixedArr = arraySorter.generatePermuted(10);
		largeMixedArr = arraySorter.generatePermuted(1000);
	}
	
	
	
	/*-------------------MERGESORT TESTS-----------------------------------------------------*/
	@Test
	public void mergesortOnSmallArray() {
		setup();
		
		arraySorter.mergesort(smallMixedArr);;
		ArrayList<Integer> expected = arraySorter.generateAscending(10);
		
		assertEquals(expected,smallMixedArr);
	}
	
	@Test
	public void mergesortOnLargeArray() {
		setup();
		
		arraySorter.mergesort(largeMixedArr);
		ArrayList<Integer> expected = arraySorter.generateAscending(1000);
		
		assertEquals(expected,largeMixedArr);
	}
	
	@Test
	public void mergesortOnDecendingArray() {
		setup();
		
		ArrayList<Integer> result = arraySorter.generateDescending(20);
		arraySorter.mergesort(result);
		ArrayList<Integer> expected = arraySorter.generateAscending(20);
		
		assertEquals(expected,result);
	}
	
	/*-----------------INSERTION SORT TESTS -------------------------------------------------*/
	
	@Test
	public void insertionSortOnSmallArray() {
		setup();
		
		arraySorter.insertionSort(smallMixedArr,0,smallMixedArr.size());
		ArrayList<Integer> expected = arraySorter.generateAscending(10);

		
		assertEquals(expected,smallMixedArr);
	}
	
	@Test
	public void insertionSortOnLargeArray() {
		setup();
		
		arraySorter.insertionSort(largeMixedArr,0,largeMixedArr.size());
		ArrayList<Integer> expected = arraySorter.generateAscending(1000);

		
		assertEquals(expected,largeMixedArr);
	}
	
	@Test
	public void insertionSortOnDecendingArray() {
		setup();
		
		ArrayList<Integer> result = arraySorter.generateDescending(20);
		arraySorter.insertionSort(result,0,result.size());
		ArrayList<Integer> expected = arraySorter.generateAscending(20);
		
		assertEquals(expected,result);
	}
}
