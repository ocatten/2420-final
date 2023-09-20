package assign04;

/**
 * Tester class for LargestNumberSolver
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version 09:17:23 CS-2420_020 FA-2023
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Random;

public class LargestNumberSolverTester {
	
	
	// Fields
	private Integer[] emptyArray;
	private Integer[] smallArray;
	private Integer[] largeArray;
	private Comparator<Integer> cmp;
	
	private LargestNumberSolver numberSolver;
	
	
	public  void setup () {
		
		numberSolver = new LargestNumberSolver();
		cmp = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) { return o1.compareTo(o2); }
		};
		
		emptyArray = new Integer[0];
		smallArray = new Integer[10];
		largeArray = new Integer[10000];
		
		smallArray[0] = 7;
		smallArray[1] = 5;
		smallArray[2] = 8;
		smallArray[3] = 2;
		smallArray[4] = 1;
		smallArray[5] = 3;
		smallArray[6] = 4;
		smallArray[7] = 6;
		smallArray[8] = 10;
		smallArray[9] = 9;
		
		/*Random rand = new Random();
		for(int i = 0; i < 10; i++) {
			smallArray[i] = rand.nextInt(10);
		}*/
		
		Random rand = new Random();
		for(int i = 0; i < 10000; i++) {
			largeArray[i] = rand.nextInt(10000);
		}
	}
	
	
/*=========================================================== INSERTION_SORT TESTS ==================================================================*/
	
	
	@Test
	@SuppressWarnings("deprecation")
	public  void insertionSortEmpty () {
		setup();
		
		Integer[] newArray = new Integer[]{1};
		numberSolver.insertionSort(newArray, cmp);
		System.out.println(newArray[0]);
		assertEquals(newArray, new Integer[]{1});
	}

	
	
	@Test
	@SuppressWarnings("deprecation")
	public  void insertionSortSmall () {
		setup();
		
		System.out.println(smallArray[0]);
		System.out.println(smallArray[1]);
		System.out.println(smallArray[2]);
		System.out.println(smallArray[3]);
		System.out.println(smallArray[4]);
		System.out.println(smallArray[5]);
		System.out.println(smallArray[6]);
		System.out.println(smallArray[7]);
		System.out.println(smallArray[8]);
		System.out.println(smallArray[9]);
		System.out.println();
		
		numberSolver.insertionSort(smallArray, cmp);
		
		System.out.println(smallArray[0]);
		System.out.println(smallArray[1]);
		System.out.println(smallArray[2]);
		System.out.println(smallArray[3]);
		System.out.println(smallArray[4]);
		System.out.println(smallArray[5]);
		System.out.println(smallArray[6]);
		System.out.println(smallArray[7]);
		System.out.println(smallArray[8]);
		System.out.println(smallArray[9]);
		
		assertEquals(smallArray, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
	}


	
	@Test
	public  void insertionSortLarge () {
		setup();
		
		//numberSolver.insertionSort(largeArray, cmp);
		
		/*Integer[] newArray = new Integer[10000];
		for(int i = 1; i < 10000; i++) {
			newArray[i-1] = i;
		}
		
		assertTrue(largeArray.equals(newArray));*/
	}
	
	
/*========================================================= FIND_LARGEST_NUM TESTS ==================================================================*/
	
	
	@Test
	public  void findLargestNumberEmpty () {
		setup();
		
		//assertTrue(numberSolver.findLargestNumber(emptyArray).equals(new BigInteger("0")));
	}

	
	
	@Test
	public  void findLargestNumberSmall () {
		setup();
		
		//assertTrue(numberSolver.findLargestNumber(smallArray).equals(new BigInteger("98765432110")));
	}


	
	@Test
	public  void findLargestNumberLarge () {
		setup();
		
		//assertTrue(numberSolver.findLargestNumber(largeArray).toString(). );
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
	public  void sumEmpty () {
		setup();
	}

	
	
	@Test
	public  void sumSmall () {
		setup();
	}


	
	@Test
	public  void sumLarge () {
		setup();
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
