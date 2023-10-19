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
		linkedList.insertFirst(test);
		linkedList.insertFirst(test);
		linkedList.insert(1,test);
	}
	
	@Test
	public void insertOnLastNode() {
		Integer test = 1;
		linkedList.insertFirst(test);
		linkedList.insertFirst(test);
		linkedList.insertFirst(test);
		linkedList.insert(3,test);
	}
}