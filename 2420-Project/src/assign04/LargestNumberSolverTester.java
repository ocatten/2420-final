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
	Integer[] emptyArray;
	Integer[] smallArray;
	Integer[] largeArray;
	
	
	public void setup () {
		
		Random rand = new Random();
		
		emptyArray = new Integer[0];
		smallArray = new Integer[10];
		largeArray = new Integer[10000];
		
		for(int i = 0; i < 10; i++) {
			smallArray[i] = i;
		}
		
		for(int i = 0; i < 10000; i++) {
			smallArray[i] = i;
		}
	}
	
	
/*=========================================================== INSERTION_SORT TESTS ==================================================================*/
	
	
	@Test
	public static void insertionSortEmpty () {
		
	}

	
	
	public static void insertionSortSmall () {
		
	}



	public static void insertionSortLarge () {
	
	}
	
	
/*========================================================= FIND_LARGEST_NUM TESTS ==================================================================*/
	
	
	@Test
	public static void findLargestNumberEmpty () {
		
	}

	
	
	@Test
	public static void findLargestNumberSmall () {
		
	}


	
	@Test
	public static void findLargestNumberLarge () {
	
	}
	
	
/*========================================================= FIND_LARGEST_INT TESTS ==================================================================*/
	
	
	@Test
	public static void findLargestIntEmpty () {
		
	}

	
	
	@Test
	public static void findLargestIntSmall () {
		
	}


	
	@Test
	public static void findLargestIntLarge () {
	
	}
	
	
/*========================================================= FIND_LARGEST_LONG TESTS =================================================================*/
	
	
	@Test
	public static void findLargestLongEmpty () {
		
	}

	
	
	@Test
	public static void findLargestLongSmall () {
		
	}


	
	@Test
	public static void findLargestLongLarge () {
	
	}
	
	
/*=============================================================== SUM TESTS =========================================================================*/
	
	
	@Test
	public static void sumEmpty () {
		
	}

	
	
	@Test
	public static void sumSmall () {
		
	}


	
	@Test
	public static void sumLarge () {
	
	}
	
	
/*=========================================================== FIND_K_LARGEST TESTS ==================================================================*/
	
	
	@Test
	public static void findKthLargestEmpty () {
		
	}

	
	
	@Test
	public static void findKthLargestSmall () {
		
	}


	
	@Test
	public static void findKthLargestLarge () {
	
	}
	
	
/*============================================================= READ_FILE TESTS =====================================================================*/
	
	
	@Test
	public static void readFileEmpty () {
		
	}

	
	
	@Test
	public static void readFileSmall () {
		
	}


	
	@Test
	public static void readFileLarge () {
	
	}

}
