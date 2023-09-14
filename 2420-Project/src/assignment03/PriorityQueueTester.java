package assignment03;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Comparator;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tester for SimplePriorityQueue
 * 
 * @author: Parker Catten & Everett Ogelsby
 * @version: CS-2420_020 FA-2023
 */
public class PriorityQueueTester {

	SimplePriorityQueue<String> lettersQueue;
	SimplePriorityQueue<Integer> numsQueue;

	String[] mixedLetters= {"C","A","D","B","E"};
	Integer[] mixedNumbers = {7,2,5,9,8,3,10,1,4,6};
	
	ArrayList<String> mixedLettersArray;
	ArrayList<Integer> largeNumberArray;

	
	public void setup () {

		lettersQueue = new SimplePriorityQueue<String>();
		numsQueue = new SimplePriorityQueue<Integer>();
			
		mixedLettersArray = new ArrayList<String>();
		for(String i : mixedLetters) {
			mixedLettersArray.add(i);
		}
		
		
		largeNumberArray = new ArrayList<Integer>();
		for(Integer i : mixedNumbers) {
			largeNumberArray.add(i);
		}
		
		for(Integer i = 0; i < 250; i++) {
			largeNumberArray.add(i);
		}
		
		lettersQueue.insertAll(mixedLettersArray);
		numsQueue.insertAll(largeNumberArray);
		
	}
	
	
	
/*================================================================== CONSTRUCTOR ==========================================================================*/
	
	@Test
	public void comparableUsed () {
		
		SimplePriorityQueue<String> comparablePrioriQueue = new SimplePriorityQueue<String>();
		
		assertEquals(-1, comparablePrioriQueue.innerCompare("a", "b"));
	}
	
	
	
	@Test
	public void comparatorUsed () {
		
		Comparator<String> cmp = new Comparator<String> () { public int compare(String lhs, String rhs) {
			return rhs.compareTo(lhs);
		} };
		
		SimplePriorityQueue<String> comparatorPrioriQueue = new SimplePriorityQueue<String>(cmp);
		assertEquals(1, comparatorPrioriQueue.innerCompare("a", "b"));
	}
	
	
	
/*===================================================================== INSERT =============================================================================*/	
	
	@Test
	public void insertOnEmpty () {
		
		SimplePriorityQueue<String> newQueue = new SimplePriorityQueue<String>();
		assertEquals(0, newQueue.size());
		
		newQueue.insert("a");
		assertEquals(1, newQueue.size());
		assertEquals(newQueue.findMax(), "a");
	}
	
	
	
	@Test
	public void insertOnSmall () {
		setup();
		
		assertEquals(9, lettersQueue.size());
	}
	
	
	
	@Test
	public void insertOnLarge () {
		
		
	}
	
	
	
/*=================================================================== FIND_MAX =============================================================================*/
	
	@Test
	public void findMaxOnEmpty () {
		
		
	}
	
	
	
	@Test
	public void findMaxOnSmall () {
		
		
	}
	
	
	
	@Test
	public void findMaxOnLarge () {
		
		
	}
	
	
	
/*================================================================= DELETE_MAX ============================================================================*/
	
	@Test
	public void deleteMaxOnEmpty () {
		
		
	}
	
	
	
	@Test
	public void deleteMaxOnSmall () {
		
		
	}
	
	
	
	@Test
	public void deleteMaxOnLarge () {
		
		
	}
	
	
	
/*================================================================= INSERT_ALL ============================================================================*/
	
	@Test
	public void insertAllOnEmpty () {
		
		
	}
	
	
	
	@Test
	public void insertAllOnSmall () {
		
		
	}
	
	
	
	@Test
	public void insertAllOnLarge () {
		
		
	}
	
	
	
/*================================================================== CONTAINS ============================================================================*/
	
	@Test
	public void containsOnEmpty () {
		
		
	}
	
	
	
	@Test
	public void containsOnSmall () {
		
		
	}
	
	
	
	@Test
	public void containsOnLarge () {
		
		
	}
	
	
	
/*=================================================================== SIZE ===============================================================================*/
	
	@Test
	public void sizeOnEmpty () {
		
		
	}
	
	
	
	@Test
	public void sizeOnSmall () {
		
		
	}
	
	
	
	@Test
	public void sizeOnLarge () {
		
		
	}
	
	
	
/*=================================================================== CLEAR ================================================================================*/
	
	@Test
	public void clearOnEmpty () {
		
		
	}
	
	
	
	@Test
	public void clearOnSmall () {
		
		
	}
	
	
	
	@Test
	public void clearOnLarge () {
		
		
	}
	
	
	
/*================================================================ TO_STRING ===========================================================================*/
	
	@Test
	public void toStringOnEmpty () {
		
		
	}
	
	
	
	@Test
	public void toStringOnSmall () {
		
		
	}
	
	
	
	@Test
	public void toStringOnLarge () {
		
		
	}
	
	
	
/*================================================================= TO_STRING =============================================================================*/
	
	@Test
	public void binarySearchOnEmpty () {
		
		
	}
	
	
	
	@Test
	public void binarySearchOnSmall () {
		
		
	}
	
	
	
	@Test
	public void binarySearchOnLarge () {
		
		
	}

}
