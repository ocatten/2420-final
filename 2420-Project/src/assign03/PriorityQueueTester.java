package assign03;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;

import assign03.SimplePriorityQueue;

/*
 * Tester for the SimplePriorityQueue class.
 * 
 * @Author Everett Oglesby and Parker Catten
 * September 12, 2023
 */
public class PriorityQueueTester {
	
	SimplePriorityQueue currQueue;
	
	ArrayList<Integer> mixedNumArr;
	ArrayList<Integer> mixedNumArrLarge;
	ArrayList<String> mixedCharArr;
	
	public void setup() {
		currQueue = new SimplePriorityQueue();
		currQueue.clear();
			
		int[] mixedNums = {5,4,3,6,8,1,9,7,2};
		String[] mixedChars = {"G","C","E","F","A","B","D"};
		
		mixedNumArr = new ArrayList<Integer>();
		for(Integer i : mixedNums) {
			mixedNumArr.add(i);
		}
		
		mixedNumArrLarge = new ArrayList<Integer>();
		for(Integer i = 0; i < 250; i++) {
			mixedNumArrLarge.add(i);

		}
	}
	
	//------------------------------Empty Tests----------------------------------------------------------//
	
	@Test 
	public void insertOnEmptyList() {
		setup();
		
		Integer inserted = 1;
		currQueue.insert(inserted);
		
		assertEquals(1,currQueue.findMax());
	}
	
	@Test
	public void insertTwiceOnEmptyList() {
		setup();
		
		Integer insert = 1;
		currQueue.insert(insert);
		insert = 2;
		currQueue.insert(insert);
		
		assertEquals(2,currQueue.size());
		assertEquals(2,currQueue.findMax());
	}
	
	@Test
	public void insertMultipleTimesOnEmptyList() {
		setup();
		
		for(Integer i = 0; i < 5; i++) {
			currQueue.insert(i+1);
		}
		
		assertEquals(5,currQueue.size());
	}
	
	@Test
	public void insertAllOnEmptyArray() {
		setup();
		
		currQueue.insertAll(mixedNumArr);
		System.out.println(currQueue.toString());
		
		assertEquals(9,currQueue.findMax());
	}
	
	@Test
	public void containsWithEmptyItem() {
		setup();
		
		assertThrows(NoSuchElementException.class, () -> { currQueue.contains(null); });
	}
	
	@Test
	public void containsOnEmptyList(){
		setup();
		
		Integer dne = 1;
		
		assertFalse(currQueue.contains(dne));
	}
}
