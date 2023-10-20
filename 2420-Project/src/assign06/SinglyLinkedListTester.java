package assign06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class SinglyLinkedListTester {
	
	SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<Integer>();
	
	//----------------------Insert First Tests------------------------------------------//
	
	@Test
	public void insertFirstOnEmptyList() {
		Integer test = 1;
		linkedList.insertFirst(test);
		Integer expected = 1;
		
		assertEquals(expected,linkedList.getHead().data);
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
		
		assertEquals(expected,linkedList.getHead().data);
	}
	
	//-----------------------Insert Tests-----------------------------------------------//
	
	@Test
	public void insertOnEmptyList() {
		Integer test = 1;
		linkedList.insert(0,test);
		Integer expected = 1;
		
		assertEquals(expected,linkedList.getHead().data);
	}
	
	@Test
	public void insertOnListOfSize1(){
		Integer test = 1;
		linkedList.insertFirst(test);
		test = 2;
		linkedList.insert(0,test);
		Integer expected = 2;
		
		assertEquals(expected,linkedList.getHead().data);
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
		
		assertEquals(expected,linkedList.getHead().data);
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
		
		
		
		assertEquals(4,linkedList.size());
		assertEquals(expected,linkedList.getHead().next.data);
	}
	
	@Test
	public void insertOnLargeList() {
		for(Integer i = 1; i <= 100; i++) {
			linkedList.insertFirst(i);
		}
		Integer inserted = 1000;
		linkedList.insert(50,inserted);
		
		assertEquals(101,linkedList.size());
		assertEquals(inserted,linkedList.get(50));
	}
	
	//-----------------------IndexOf Tests---------------------------------------------------------//
	
	@Test
	public void indexOfOnEmptyList() {
		int index = linkedList.indexOf(1);
		assertEquals(-1,index);
	}
	
	@Test
	public void indexOfWithNullValue() {
		linkedList.insertFirst(1);
		
		int index = linkedList.indexOf(null);
		assertEquals(-1,index);
	}
	
	@Test
	public void indexOfOnFirst() {
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
		Integer result = 5;
		
		int index = linkedList.indexOf(result);
		
		assertEquals(0,index);
	}
	
	@Test
	public void indexOfOnLast() {
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
		Integer result = 1;
		
		int index = linkedList.indexOf(result);
		
		assertEquals(4,index);
	}
	
	@Test
	public void indexOfOnLargeList() {
		for(Integer i = 1; i <= 100; i++) {
			linkedList.insertFirst(i);
		}
		Integer lookup = 55;
		
		int index = linkedList.indexOf(55);
		
		assertEquals(45,index);
	}
	
	
	//-----------------------Delete First Tests----------------------------------------------------//
	@Test
	public void deleteFirstOnEmpty() {
		//assertThrows()
	}
	
	@Test
	public void deleteFirstOnListWithSingleNode() {
		Integer test = 1;
		linkedList.insertFirst(test);
		
		linkedList.deleteFirst();
		
		assertNull(linkedList.getHead());
		assertEquals(0,linkedList.size());
	}
	
	@Test
	public void deleteFirstMultipleTimes() {
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
		
		linkedList.deleteFirst();
		linkedList.deleteFirst();
		linkedList.deleteFirst();
		
		Integer expected = 2;
		
		assertEquals(2,linkedList.size());
		assertEquals(expected,linkedList.getFirst());
	}
	
	@Test
	public void deleteAllItemsInLargeList() {
		for(Integer i = 1; i <= 100; i++) {
			linkedList.insertFirst(i);
		}
		
		for(Integer i = 1; i <= 100; i++) {
			linkedList.deleteFirst();
		}
		
		assertNull(linkedList.getHead());
		assertEquals(0,linkedList.size());
	}
	
	//-----------------------Delete Tests----------------------------------------------------//
	
	@Test
	public void deleteOnEmpty() {
		//assertThrows()
	}
	
	@Test
	public void deleteOnListWithSingleNode() {
		Integer test = 1;
		linkedList.insertFirst(test);
		
		linkedList.delete(0);
	}
	
	@Test
	public void deleteFirstIndex() {
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

		Integer expected = 4;
		linkedList.delete(0);
		
		assertEquals(expected,linkedList.getFirst());
	}
	
	@Test
	public void deleteLastIndex() {
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

		Integer expected = 4;
		linkedList.delete(0);
		
		assertEquals(4,linkedList.size());
		assertEquals(expected,linkedList.getFirst());
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

	@Test
	public void sizeOnLargeList() {
		for(Integer i = 1; i <= 100; i++) {
			linkedList.insertFirst(i);
		}
		
		assertEquals(100,linkedList.size());
	}
	
	//------------------------Get First Tests------------------------------------------//
	
	@Test
	public void getFirstOnEmpty() {
		//assertThrows()
	}
	
	@Test
	public void getFirstOnSmall() {
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
		
		assertEquals(expected,linkedList.getFirst());
	}
	
	@Test
	public void getFirstOnLarge() {
		for(Integer i = 1; i <= 100; i++) {
			linkedList.insertFirst(i);
		}
		Integer expected = 100;
		
		assertEquals(expected,linkedList.getFirst());
	}
	
	//-------------Get Tests------------------------------------------------------------//
	@Test
	public void getOnEmpty() {
		//assertThrows linkedList.get(0);
	}
	
	@Test
	public void getOnListWithSingleNode() {
		Integer test = 1;
		linkedList.insertFirst(test);
		Integer expected = 1;
		
		assertEquals(expected,linkedList.get(0));
	}
	
	@Test
	public void getInMiddleOfList() {
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
		Integer expected = 3;
		
		assertEquals(expected,linkedList.get(2));
	}
	
	@Test
	public void getAtEndOfList() {
		for(Integer i = 1; i <= 100; i++) {
			linkedList.insertFirst(i);
		}
		Integer expected = 90;
		
		assertEquals(expected,linkedList.get(10));
	}
	
	
	//------------------To Array Tests----------------------------------------------------//
	
	@Test
	public void toArrayOnEmptyList() {
		Object[] toArray = linkedList.toArray();
		
		assertEquals(0,toArray.length);
	}
	
	@Test
	public void toArrayOnListWithSingleNode() {
		Integer test = 1;
		linkedList.insertFirst(test);
		Object[] toArray = linkedList.toArray();
		Integer expected = 1;
		
		assertEquals(1,toArray.length);
		assertEquals(expected,toArray[0]);
	}
	
	@Test
	public void toArrayOnSmallList() {
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
		Object[] toArray = linkedList.toArray();
		
		
		assertEquals(5,toArray.length);
		Integer expected = 5;
		assertEquals(expected,toArray[0]);
		expected = 1;
		assertEquals(expected,toArray[4]);
	}
	
	@Test
	public void toArrayOnLarge() {
		for(Integer i = 1; i <= 100; i++) {
			linkedList.insertFirst(i);
		}
		Object[] toArray = linkedList.toArray();
		
		assertEquals(100,toArray.length);
		for(Integer i = 0; i < 100; i++) {
			assertEquals(100-i,toArray[i]);
		}
	}
}