package assign04;

/**
 * Tester class for LargestNumberSolver
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version 09:17:23 CS-2420_020 FA-2023
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class LargestNumberSolverTester {
	
	
	// Fields
	private Integer[] emptyNumArr;
	private Integer[] tinyNumArr;
	private Integer[] smallNumArr;
	private Integer[] smallMixedNumArr;
	private Integer[] largeNumArr;
	
	
	private Comparator<Integer> cmp;
	
	private LargestNumberSolver numberSolver;
	
	
	public  void setup () {
		
		numberSolver = new LargestNumberSolver();
		cmp = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) { return o1.compareTo(o2); }
		};
		
		emptyNumArr = new Integer[0];
		tinyNumArr = new Integer[] {5,2,1,4,3};
		smallNumArr = new Integer[]{4,2,3,8,6,1,9,7,5};
		smallMixedNumArr = new Integer[] {987,432,1,65};
		largeNumArr = new Integer[1000];
		
		Random rand = new Random();

		
		rand = new Random();
		for(int i = 0; i < 1000; i++) {
			largeNumArr[i] = rand.nextInt(1000);
		}
	}
	
	
/*=========================================================== INSERTION_SORT TESTS ==================================================================*/
	
	
	@Test
	public  void insertionSortEmpty () {
		setup();
		
		numberSolver.insertionSort(emptyNumArr, cmp);
		assertEquals(0,emptyNumArr.length);
	}

	
	
	@Test
	public  void insertionSortSmall () {
		setup();
		
		numberSolver.insertionSort(smallNumArr, cmp);
		
		for(Integer i = 1; i > 10; i++) {
			assertEquals(i, smallNumArr[i]);
		}
	}


	
	@Test
	public  void insertionSortLarge () {
		setup();
		
		numberSolver.insertionSort(largeNumArr, cmp);
		

		for(Integer i = 1; i < 999; i++) {
			Integer current = largeNumArr[i];
			Integer next = largeNumArr[i+1];
			
			assertEquals(true, current <= next);
		}
		

	}
	
	
/*========================================================= FIND_LARGEST_NUM TESTS ==================================================================*/
	
	
	@Test
	public  void findLargestNumberEmpty () {
		setup();
		
		BigInteger expected = BigInteger.valueOf(0);
		BigInteger result = numberSolver.findLargestNumber(emptyNumArr);
		assertEquals(expected,result);
	}
	
	
	
	@Test
	public  void findLargestNumberSmall () {
		setup();
		
		BigInteger expected = BigInteger.valueOf(987654321);
		BigInteger result = numberSolver.findLargestNumber(smallNumArr);
		assertEquals(expected,result);
	}

	
	
	@Test
	public void findLargestNumberWithMixedNumbers() {
		setup();
		
		BigInteger expected = BigInteger.valueOf(987654321);
		BigInteger result = numberSolver.findLargestNumber(smallMixedNumArr);
		assertEquals(expected, result);
	}
	
	
/*========================================================= FIND_LARGEST_INT TESTS ==================================================================*/
	
	
	@Test
	public  void findLargestIntEmpty () {
		setup();
		
		numberSolver.findLargestInt(emptyNumArr);
	}

	
	
	@Test
	public  void findLargestIntSmall () {
		setup();
		
		Integer[] testCase = new Integer[] {3, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		
		assertEquals(numberSolver.findLargestInt(testCase), 1311111111);
	}


	
	@Test
	public  void findLargestIntLarge () {
		setup();
		
		Integer[] testCase = new Integer[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 1};
		
		assertEquals(numberSolver.findLargestInt(testCase), 1999999999);
	}
	
	
/*========================================================= FIND_LARGEST_LONG TESTS =================================================================*/
	
	
	@Test
	public  void findLargestLongEmpty () {
		setup();
		
		numberSolver.findLargestLong(emptyNumArr);
	}

	
	
	@SuppressWarnings("removal")
	@Test
	public void findLargestLongSmall () {
		setup();
		
		Integer[] testCase = new Integer[] {9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		
		assertEquals(numberSolver.findLargestLong(testCase), new Long("1911111111111111111").longValue());
	}


	
	@SuppressWarnings("removal")
	@Test
	public void findLargestLongLarge () {
		setup();
		
		Integer[] testCase = new Integer[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1};
		
		assertEquals(numberSolver.findLargestLong(testCase), new Long("1999999999999999999").longValue());
	}

	
/*=============================================================== SUM TESTS =========================================================================*/
	
	
	@Test
	public  void sumOnEmpty () {
		setup();
		
		ArrayList<Integer[]> empty = new ArrayList<Integer[]>();
		empty.add(emptyNumArr);
		
		BigInteger expected = BigInteger.valueOf(0);
		BigInteger result = numberSolver.sum(empty);
		
		assertEquals(expected,result);
	}

	
	
	@Test
	public  void sumOnSmall () {
		setup();
		
		ArrayList<Integer[]> small = new ArrayList<Integer[]>();
		small.add(tinyNumArr);
		small.add(smallNumArr);
		
		int sum = 987654321 + 54321;
		
		BigInteger expected = BigInteger.valueOf(sum);
		BigInteger result = numberSolver.sum(small);
		
		assertEquals(expected,result);
	}

	
	
	@Test
	public void sumWithAddingSameThingMultipleTimes () {
		setup();
		
		ArrayList<Integer[]> multiple = new ArrayList<Integer[]>();
		
		multiple.add(tinyNumArr);
		multiple.add(tinyNumArr);
		multiple.add(tinyNumArr);
		multiple.add(tinyNumArr);
		multiple.add(tinyNumArr);
		
		int sum = 54321*5;
		
		BigInteger expected = BigInteger.valueOf(sum);
		BigInteger result = numberSolver.sum(multiple);
		
		assertEquals(expected, result);
	}
	
	
	
	@Test
	public void sumWithMultipleAddends() {
		setup();
		
		ArrayList<Integer[]> multiple = new ArrayList<Integer[]>();
		multiple.add(tinyNumArr);
		multiple.add(smallNumArr);
		multiple.add(new Integer[] {8,7,6});
		multiple.add(new Integer[] {4,7,6,2});
		
		int sum = 987654321 + 54321 + 876 + 7642;
		
		BigInteger expected = BigInteger.valueOf(sum);
		BigInteger result = numberSolver.sum(multiple);
		
		assertEquals(expected,result);
	}
	
	
/*=========================================================== FIND_K_LARGEST TESTS ==================================================================*/
	
	
	@Test
	public void findKthLargestEmpty () {
		setup();
		
		ArrayList<Integer[]> testList = new ArrayList<Integer[]>();
		testList.add(emptyNumArr);
		
		//Tests to see if any errors are thrown.
	}
	
	@Test
	public void findKthLargestEmptyListWithKas1() {
		setup();
		
		ArrayList<Integer[]> testList = new ArrayList<Integer[]>();
		testList.add(emptyNumArr);
		
		assertThrows(IllegalArgumentException.class, () -> { numberSolver.findKthLargest(testList,1); });
	}

	
	
	@Test
	public void findKthLargestSmall () {
		setup();
		
		ArrayList<Integer[]> testList = new ArrayList<Integer[]>();
		testList.add(tinyNumArr);
		testList.add(smallNumArr);
		Integer[] result = numberSolver.findKthLargest(testList, 0);
		 
		for(int i = 0; i < result.length;i++) {
			System.out.println("Result = " +result[i]);
			assertEquals(smallNumArr[i],result[i]);
		}
		
	}
	
	@Test
	public void findKthLargestForLargeAmountOfLists() {
		setup();
		
		ArrayList<Integer[]> testList = new ArrayList<Integer[]>();
		
		for(int i = 0; i < 100; i++) {
			Integer[] currArray = new Integer[5];
			for(int j = 0; j < 5; j++) {
				Random rand = new Random();
				currArray[j] = rand.nextInt(9);
			}
			testList.add(currArray);
		}
		Integer[] largest = new Integer[]{1,2,3,4,5,6};//Acts as the expected output.
		testList.add(largest);
		
		Integer[] result = numberSolver.findKthLargest(testList, 0);
		
		for(int i = 0; i < 6; i++) {
			assertEquals(largest[i],result[i]);
		}
	}
	
	@Test
	public void findKthLargestWithMultipleOfTheSameList() {

		setup();
		
		ArrayList<Integer[]> testList = new ArrayList<Integer[]>();
		testList.add(smallMixedNumArr);
		testList.add(smallMixedNumArr);
		testList.add(smallMixedNumArr);
		testList.add(smallMixedNumArr);
		testList.add(smallMixedNumArr);
		testList.add(smallMixedNumArr);
		Integer[] result = numberSolver.findKthLargest(testList, 0);
		 
		for(int i = 0; i < result.length;i++) {
			System.out.println("Result = " +result[i]);
			assertEquals(smallMixedNumArr[i],result[i]);			
		}
	}


	
	@Test
	public void findKthLargestLargeLists() {
		setup();
		
		ArrayList<Integer[]> testList = new ArrayList<Integer[]>();
		testList.add(largeNumArr);
		testList.add(smallNumArr); 
		
		Integer[] result = numberSolver.findKthLargest(testList, 0);
		
		for(int i = 0; i < result.length;i++) {
			System.out.println("Result = " +result[i]);
			assertEquals(largeNumArr[i],result[i]);
		}
	}
	
	
/*============================================================= READ_FILE TESTS =====================================================================*/
	
	
	@Test
	public void readFileEmpty () {
		setup();
		
		numberSolver.readFile(null);
	}
}