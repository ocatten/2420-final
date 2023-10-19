package assign06;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SinglyLinkedListTester {
	
	SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<Integer>();
	
	//----------------------Insert First Tests------------------------------------------//
	
	@Test
	public void insertFirstOnEmptyList() {
		Integer test = 1;
		linkedList.insertFirst(test);
		Integer expected = 1;
		
		assertEquals(expected,linkedList.head.data);
	}
	
	@Test
	public void insertFirstMultipleTimes() {
		Integer test = 1;
		linkedList.insertFirst(test);
		test = 2;
		linkedList.insertFirst(test);
		test = 3;
		linkedList.insertFirst(test);
		test = 4;
		linkedList.insertFirst(test);
		test = 5;
		linkedList.insertFirst(test);
		Integer expected = 5;
		
		assertEquals(expected,linkedList.head.data);
	}
	
	//-----------------------Insert Tests-----------------------------------------------//
	
	@Test
	public void insertOnEmptyList() {
		Integer test = 1;
		linkedList.insert(0,test);
		Integer expected = 1;
		
		assertEquals(expected,linkedList.head.data);
	}
	
	@Test
	public void insertOnListOfSize1(){
		Integer test = 1;
		linkedList.insertFirst(test);
		test = 2;
		linkedList.insert(0,test);
		Integer expected = 2;
		
		assertEquals(expected,linkedList.head.data);
	}
	
	@Test
	public void insertOnListOfSize3(){
		Integer test = 1;
		linkedList.insertFirst(test);
		test = 2;
		linkedList.insertFirst(test);
		test = 3;
		linkedList.insertFirst(test);
		test = 4;
		linkedList.insert(1,test);
		Integer expected = 3;
		
		assertEquals(expected,linkedList.head.data);
	}
	
	@Test
	public void insertOnLastNode() {
		Integer test = 1;
		linkedList.insertFirst(test);
		test = 2;
		linkedList.insertFirst(test);
		test = 3;
		linkedList.insertFirst(test);
		test = 4;
		linkedList.insert(3,test);
		Integer expected = 4;
		
		assertEquals(expected,linkedList.head.next.data);
	}
	
	//-----------------------Size Tests------------------------------------------------------------//
	@Test
	public void sizeOnEmptyList() {
		assertEquals(0,linkedList.size());
	}
	
	@Test
	public void sizeOnSmallList() {
		Integer test = 1;
		linkedList.insertFirst(test);
		test = 2;
		linkedList.insertFirst(test);
		test = 3;
		linkedList.insertFirst(test);
		test = 4;
		linkedList.insertFirst(test);
		test = 5;
		linkedList.insertFirst(test);
		
		assertEquals(5,linkedList.size());
	}

}