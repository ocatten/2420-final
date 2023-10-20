package assign06;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;


/**
 * Tester class for WebBrowser.java
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 10:19:23
 */
public class WebBrowserTester {
	
//============================================================= SET_UP ===============================================================================
	
	// Fields
	private   WebBrowser emptyBrowser;
	private   WebBrowser noHistory;
	private   WebBrowser noForward;
	private   WebBrowser fullBrowser;
	
	private   SinglyLinkedList<URL> history;
	
	
	public void setup () throws MalformedURLException {
		
		history = new SinglyLinkedList<URL>();
		history.insertFirst(new URL("http://www.example.com/docs/resource1.html"));
		history.insertFirst(new URL("http://www.google.com/"));
		history.insertFirst(new URL("http://www.youtube.com/"));
		
		
		emptyBrowser = new WebBrowser();
		
		
		noHistory = new WebBrowser();
		
		noHistory.visit(new URL("http://www.example.com/docs/resource1.html"));
		noHistory.visit(new URL("http://www.google.com/"));
		noHistory.visit(new URL("http://www.youtube.com/"));
		
		noHistory.back();
		noHistory.back();
		
		
		noForward = new WebBrowser();
		
		noForward.visit(new URL("http://www.example.com/docs/resource1.html"));
		noForward.visit(new URL("http://www.google.com/"));
		noForward.visit(new URL("http://www.youtube.com/"));
		
		
		fullBrowser = new WebBrowser();
		
		fullBrowser.visit(new URL("http://www.example.com/docs/resource1.html"));
		fullBrowser.visit(new URL("http://www.google.com/"));
		fullBrowser.visit(new URL("http://www.youtube.com/"));
		
		fullBrowser.visit(new URL("http://www.example.com/docs/resource1.html"));
		fullBrowser.visit(new URL("http://www.google.com/"));
		fullBrowser.visit(new URL("http://www.youtube.com/"));
		
		fullBrowser.back();
		fullBrowser.back();
		fullBrowser.back();
	}
	
	
	
//========================================================= CONSTRUCTOR TESTS ========================================================================
	
	
	@Test
	public void constructorTest () throws MalformedURLException {
		setup();
		
		emptyBrowser = new WebBrowser(history);
		
		assertEquals("http://www.google.com/", emptyBrowser.back().toString());
		assertEquals("http://www.example.com/docs/resource1.html", emptyBrowser.back().toString());
	}
	
	
	
//=========================================================== VISIT TESTS ============================================================================
	
	
	@Test
	public void visitTestOnEmpty () throws MalformedURLException {
		setup();
		
		
		try {
			
			emptyBrowser.back();
			assertTrue(false);
			
		} catch (Exception e) {
			assertTrue(true);
		}
		
		try {
			
			emptyBrowser.forward();
			assertTrue(false);
			
		} catch (Exception e) {
			assertTrue(true);
		}
		
		
		emptyBrowser.visit(new URL("http://www.google.com/"));
		emptyBrowser.visit(new URL("http://www.youtube.com/"));
		
		assertEquals(emptyBrowser.back().toString(), "http://www.google.com/");
		assertEquals(emptyBrowser.forward().toString(), "http://www.youtube.com/");
	}
	
	
	
	@Test
	public void visitTestOnSmall () throws MalformedURLException {
		setup();
		
		assertFalse(noHistory.getForwardButton().isEmpty());
		noHistory.visit(new URL("http://www.google.com"));
		assertTrue(noHistory.getForwardButton().isEmpty());
		

		assertEquals("http://www.example.com/docs/resource1.html", noHistory.back().toString());
	}
	
	
	
	@Test
	public void visitTestOnLarge () throws MalformedURLException {
		setup();
		
		fullBrowser.visit(new URL("http://www.pizza.com"));
		fullBrowser.visit(new URL("http://www.apple.com"));
		
		assertEquals("http://www.pizza.com", fullBrowser.back().toString());
		assertEquals("http://www.youtube.com/", fullBrowser.back().toString());
	}
	
	
	
//=========================================================== BACK TESTS =============================================================================
	
	
	@Test
	public void backTestOnEmpty () throws MalformedURLException {
		setup();
		
		try {
			
			emptyBrowser.back();
			assertTrue(false);
			
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	
	
	@Test
	public void backTestOnSmall () throws MalformedURLException {
		setup();
		
		assertEquals("http://www.google.com/", noForward.back().toString());
		assertEquals("http://www.example.com/docs/resource1.html", noForward.back().toString());
		
		try {
			
			noForward.back();
			assertTrue(false);
			
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void backTestOnLarge () throws MalformedURLException {
		setup();
		
		assertEquals("http://www.google.com/", fullBrowser.back().toString());
		assertEquals("http://www.example.com/docs/resource1.html", fullBrowser.back().toString());
		
		try {
			
			fullBrowser.back();
			assertTrue(false);
			
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	
	
//========================================================== FORWARD TESTS ===========================================================================
	
	
	@Test
	public void forwardTestOnEmpty () throws MalformedURLException {
		setup();
		
		try {
			
			emptyBrowser.forward();
			assertTrue(false);
			
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	
	
	@Test
	public void forwardTestOnSmall () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public void forwardTestOnLarge () throws MalformedURLException {
		setup();
		
	}
	
	
	
//========================================================== HISTORY TESTS ===========================================================================

	
	@Test
	public void historyTestOnEmpty () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public void historyTestOnSmall () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public void historyTestOnLarge () throws MalformedURLException {
		setup();
		
	}
	
}
