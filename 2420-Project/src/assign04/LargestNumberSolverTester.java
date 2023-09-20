package assign04;

/**
 * Tester class for LargestNumberSolver
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version 09:17:23 CS-2420_020 FA-2023
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class LargestNumberSolverTester {
	
	
	// Fields
	private Integer[] emptyNumArr;
	private Integer[] tinyNumArr;
	private Integer[] smallNumArr;
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
		largeNumArr = new Integer[1000];
		
		Random rand = new Random();

		
		rand = new Random();
		for(int i = 0; i < 1000; i++) {
			largeNumArr[i] = rand.nextInt(1000);
		}
	}
	
	
/*=========================================================== INSERTION_SORT TESTS ==================================================================*/
	
	
	@Test
	@SuppressWarnings("deprecation")
	public  void insertionSortEmpty () {
		setup();
		
		numberSolver.insertionSort(emptyNumArr, cmp);
		
		assertEquals(0,emptyNumArr.length);
	}

	
	
	@Test
	@SuppressWarnings("deprecation")
	public  void insertionSortSmall () {
		setup();
		
//		System.out.println(smallArray[0]);
//		System.out.println(smallArray[1]);
//		System.out.println(smallArray[2]);
//		System.out.println(smallArray[3]);
//		System.out.println(smallArray[4]);
//		System.out.println(smallArray[5]);
//		System.out.println(smallArray[6]);
//		System.out.println(smallArray[7]);
//		System.out.println(smallArray[8]);
//		System.out.println(smallArray[9]);
//		System.out.println();
//		
//		numberSolver.insertionSort(smallArray, cmp);
//		
//		System.out.println(smallArray[0]);
//		System.out.println(smallArray[1]);
//		System.out.println(smallArray[2]);
//		System.out.println(smallArray[3]);
//		System.out.println(smallArray[4]);
//		System.out.println(smallArray[5]);
//		System.out.println(smallArray[6]);
//		System.out.println(smallArray[7]);
//		System.out.println(smallArray[8]);
//		System.out.println(smallArray[9]);
//		
//		assertEquals(smallArray, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		
		numberSolver.insertionSort(smallNumArr, cmp);
		
		for(Integer i = 1; i > 10; i++) {
			assertEquals(i,smallNumArr[i]);
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
	public  void findLargestNumberLarge () {
		setup();
		
		BigInteger expected = BigInteger.valueOf(987654321);
		BigInteger result = numberSolver.findLargestNumber(largeNumArr);
		
		assertEquals(expected,result);
	}
	
	
/*========================================================= FIND_LARGEST_INT TESTS ==================================================================*/
	
	
	@Test
	public  void findLargestIntEmpty () {
		setup();
	}

	
	
	@Test
	public  void findLargestIntSmall () {
		setup();
	}


	
	@Test
	public  void findLargestIntLarge () {
		setup();
	}
	
	
/*========================================================= FIND_LARGEST_LONG TESTS =================================================================*/
	
	
	@Test
	public  void findLargestLongEmpty () {
		setup();
	}

	
	
	@Test
	public  void findLargestLongSmall () {
		setup();
	}


	
	@Test
	public  void findLargestLongLarge () {
		setup();
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
	public  void sumOnLarge () {
		setup();
		
		ArrayList<Integer[]> large = new ArrayList<Integer[]>();
		large.add(largeNumArr);
		large.add(largeNumArr);
		
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger result = numberSolver.sum(large);
		
		assertEquals(expected,result);
	}
	
	public void sumWithAddingSameThingMultipleTimes() {
		setup();
		
		ArrayList<Integer[]> multiple = new ArrayList<Integer[]>();
		multiple.add(tinyNumArr);
		
		int sum = 54321*5;
		
		BigInteger expected = BigInteger.valueOf(sum);
		BigInteger result = numberSolver.sum(multiple);
		
		assertEquals(expected,result);
	}
	
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
	}

	
	
	@Test
	public void findKthLargestSmall () {
		setup();
	}


	
	@Test
	public void findKthLargestLarge () {
		setup();
	}
	
	
/*============================================================= READ_FILE TESTS =====================================================================*/
	
	
	@Test
	public void readFileEmpty () {
		setup();
	}

	
	
	@Test
	public void readFileSmall () {
		setup();
	}


	
	@Test
	public void readFileLarge () {
		setup();
	}

}
