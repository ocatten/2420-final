package assign06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;




/**
 * Tester class for the LinkedListStack
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 10:19:23
 */
public class LinkedListStackTester {
	
//============================================================= SET_UP ===============================================================================
	
	private  LinkedListStack<String> emptyStack;
	private  LinkedListStack<String> smallStack;
	private  LinkedListStack<Integer> largeStack;
	
	public  void setup () {
		
		emptyStack = new LinkedListStack<String>();
		
		smallStack = new LinkedListStack<String>();
		
		smallStack.push("z");
		smallStack.push("e");
		smallStack.push("b");
		smallStack.push("x");
		smallStack.push("u");
		smallStack.push("s");
		smallStack.push("t");
		smallStack.push("i");
		smallStack.push("m");
		
		
		largeStack = new LinkedListStack<Integer>();
		
		for(int i = 0; i < 10000; i++) {
			
			largeStack.push(i);
		}
	}
	
	
//=========================================================== CLEAR TESTS =============================================================================
	
	
	@Test
	public  void clearTestOnEmpty () {
		setup();
		emptyStack.clear();
	}
	
	
	
	@Test
	public  void clearTestOnSmall () {
		setup();
		
		assertEquals(smallStack.size(), 9);
		assertFalse(smallStack.isEmpty());
		
		smallStack.clear();
		assertTrue(smallStack.isEmpty());
	}
	
	
	
	@Test
	public  void clearTestOnLarge () {
		setup();
		
		assertEquals(largeStack.size(), 10000);
		assertFalse(largeStack.isEmpty());
		
		largeStack.clear();
		assertTrue(largeStack.isEmpty());
	}
	
	
	
//========================================================== IS_EMPTY TESTS ============================================================================


	@Test
	public  void isEmptyTestOnEmpty () {
		setup();
		
		assertTrue(emptyStack.isEmpty());
		emptyStack.push("a");
		assertFalse(emptyStack.isEmpty());
	}
	
	
	
	@Test
	public  void isEmptyTestOnSmall () {
		setup();
		
		assertFalse(smallStack.isEmpty());
		
		smallStack.pop();
		smallStack.pop();
		smallStack.pop();
		smallStack.pop();
		smallStack.pop();
		smallStack.pop();
		smallStack.pop();
		smallStack.pop();
		smallStack.pop();
		
		assertTrue(smallStack.isEmpty());
	}
	
	
	
	@Test
	public  void isEmptyTestOnLarge () {
		setup();
		
		assertFalse(largeStack.isEmpty());
		
		for(int i = 0; i < 10000; i++) {
			
			largeStack.pop();
		}
		
		assertTrue(largeStack.isEmpty());
	}
	
	
	
//=========================================================== PEEK TESTS =============================================================================
	
	
	@Test
	public  void peekTestOnEmpty () {
		setup();
		
		try {
			emptyStack.peek();
		} catch (Exception e) {
			
			assertTrue(true);
		}
		
	}
	
	
	
	@Test
	public  void peekTestOnSmall () {
		setup();
		
		assertEquals("m", smallStack.peek());
		assertEquals("m", smallStack.peek());
		
		smallStack.pop();
		
		assertEquals("i", smallStack.peek());
		assertEquals("i", smallStack.peek());
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public  void peekTestOnLarge () {
		setup();
		
		assertEquals(new Integer(9999), largeStack.peek());
		assertEquals(new Integer(9999), largeStack.peek());
		
		largeStack.pop();
		
		assertEquals(new Integer(9998), largeStack.peek());
		assertEquals(new Integer(9998), largeStack.peek());
	}
	
	
	
//=========================================================== POP TESTS ==============================================================================
	
	
	@Test
	public  void popTestOnEmpty () {
		setup();
		
		try {
			emptyStack.pop();
		} catch (Exception e) {
			
			assertTrue(true);
		}
		
		
	}
	
	
	
	@Test
	public  void popTestOnSmall () {
		setup();
		
		assertEquals("m", smallStack.pop());
		assertEquals("i", smallStack.pop());
		assertEquals("u", smallStack.pop());
		assertEquals("s", smallStack.pop());
		assertEquals("t", smallStack.pop());
		assertEquals("x", smallStack.pop());
		assertEquals("b", smallStack.pop());
		assertEquals("e", smallStack.pop());
		assertEquals("z", smallStack.pop());
		
		try {
			smallStack.pop();
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertTrue(smallStack.isEmpty());
	}
	
	
	
	@Test
	public  void popTestOnLarge () {
		setup();
		
		for(Integer i = 9999; i >= 0; i--) {
			assertEquals(i, largeStack.pop());
		}
		
		try {
			largeStack.pop();
		} catch (Exception e) {
			assertTrue(true);
		}
		
		assertTrue(largeStack.isEmpty());
	}
	
	
	
//=========================================================== PUSH TESTS =============================================================================
	
	
	@Test
	public  void pushTestOnEmpty () {
		setup();
		
		assertTrue(emptyStack.isEmpty());
		assertEquals(0, emptyStack.size());
		
		emptyStack.push("a");
		
		assertEquals("a", emptyStack.peek());
		assertFalse(emptyStack.isEmpty());
		assertEquals(1, emptyStack.size());
	}
	
	
	
	@Test
	public  void pushTestOnSmall () {
		setup();
		
		assertEquals("m", smallStack.peek());
		assertEquals(9, smallStack.size());
		
		smallStack.push("y");
		
		assertEquals("y", emptyStack.peek());
		assertEquals(10, emptyStack.size());
	}
	
	
	
	@Test
	@SuppressWarnings("removal")
	public  void pushTestOnLarge () {
		setup();
		
		assertEquals(new Integer(9999), largeStack.peek());
		assertEquals(10000, largeStack.size());
		
		largeStack.push(11111);
		assertEquals(new Integer(11111), largeStack.peek());
		assertEquals(10001, largeStack.size());
	}
	
	
	
//=========================================================== SIZE TESTS =============================================================================
	
	
	@Test
	public  void sizeTestOnEmpty () {
		setup();
		
		assertEquals(0, emptyStack.size());
		emptyStack.push("a");
		assertEquals(1, emptyStack.size());
		emptyStack.pop();
		assertEquals(0, emptyStack.size());
	}
	
	
	
	@Test
	public  void sizeTestOnSmall () {
		setup();
		
		assertEquals(9, smallStack.size());
		smallStack.push("y");
		assertEquals(10, smallStack.size());
		smallStack.pop();
		assertEquals(9, smallStack.size());
		
		smallStack.clear();
		assertEquals(0, smallStack.size());
	}
	
	
	
	@Test
	public  void sizeTestOnLarge () {
		setup();
		
		assertEquals(100000, largeStack.size());
		largeStack.push(11);
		assertEquals(100001, largeStack.size());
		smallStack.pop();
		assertEquals(100000, largeStack.size());
		
		largeStack.clear();
		assertEquals(0, largeStack.size());
	}
	
}