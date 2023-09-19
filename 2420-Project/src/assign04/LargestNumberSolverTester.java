package assign04;

/**
 * Tester class for LargestNumberSolver  
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version 09:17:23 CS-2420_020 FA-2023
 */

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class LargestNumberSolverTester {
	
	
	// Fields
	private static Integer[] emptyArray;
	private static Integer[] smallArray;
	private static Integer[] largeArray;
	private static Comparator<Integer> cmp;
	
	
	public static void setup () {
		
		cmp = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) { return o1.compareTo(o2); }
		};
		
		emptyArray = new Integer[0];
		smallArray = new Integer[10];
		largeArray = new Integer[10000];
		
		Random rand = new Random();
		for(int i = 0; i < 10; i++) {
			smallArray[i] = rand.nextInt(10);
		}
		
		rand = new Random();
		for(int i = 0; i < 10000; i++) {
			smallArray[i] = rand.nextInt(10000);
		}
	}
	
	
/*=========================================================== INSERTION_SORT TESTS ==================================================================*/
	
	
	@Test
	public static void insertionSortEmpty () {
		setup();
		
		Integer[] newArray = new Integer[]{1};
		LargestNumberSolver.insertionSort(newArray, cmp);
		assertTrue(newArray.equals(new Integer[]{1}));
	}

	
	
	@Test
	public static void insertionSortSmall () {
		setup();
		
		LargestNumberSolver.insertionSort(smallArray, cmp);
		assertTrue(smallArray.equals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
	}


	
	@Test
	public static void insertionSortLarge () {
		setup();
		
		Integer[] newArray = new Integer[10000];
		for(int i = 1; i < 10000; i++) {
			newArray[i-1] = i;
		}
		
		LargestNumberSolver.insertionSort(largeArray, cmp);
		assertTrue(largeArray.equals(newArray));
	}
	
	
/*========================================================= FIND_LARGEST_NUM TESTS ==================================================================*/
	
	
	@Test
	public static void findLargestNumberEmpty () {
		setup();
		
		assertEquals(LargestNumberSolver.findLargestNumber(smallArray), new BigInteger("98765432110"));
	}

	
	
	@Test
	public static void findLargestNumberSmall () {
		setup();
	}


	
	@Test
	public static void findLargestNumberLarge () {
		setup();
	}
	
	
/*========================================================= FIND_LARGEST_INT TESTS ==================================================================*/
	
	
	@Test
	public static void findLargestIntEmpty () {
		setup();
	}

	
	
	@Test
	public static void findLargestIntSmall () {
		setup();
	}


	
	@Test
	public static void findLargestIntLarge () {
		setup();
	}
	
	
/*========================================================= FIND_LARGEST_LONG TESTS =================================================================*/
	
	
	@Test
	public static void findLargestLongEmpty () {
		setup();
	}

	
	
	@Test
	public static void findLargestLongSmall () {
		setup();
	}


	
	@Test
	public static void findLargestLongLarge () {
		setup();
	}
	
	
/*=============================================================== SUM TESTS =========================================================================*/
	
	
	@Test
	public static void sumEmpty () {
		setup();
	}

	
	
	@Test
	public static void sumSmall () {
		setup();
	}


	
	@Test
	public static void sumLarge () {
		setup();
	}
	
	
/*=========================================================== FIND_K_LARGEST TESTS ==================================================================*/
	
	
	@Test
	public static void findKthLargestEmpty () {
		setup();
	}

	
	
	@Test
	public static void findKthLargestSmall () {
		setup();
	}


	
	@Test
	public static void findKthLargestLarge () {
		setup();
	}
	
	
/*============================================================= READ_FILE TESTS =====================================================================*/
	
	
	@Test
	public static void readFileEmpty () {
		setup();
	}

	
	
	@Test
	public static void readFileSmall () {
		setup();
	}


	
	@Test
	public static void readFileLarge () {
		setup();
	}

}
