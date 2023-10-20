package assign06;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;


/**
 * Tester class for the WebBrowser
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 10:19:23
 */
public class WebBrowserTester {
	
//============================================================= SET_UP ===============================================================================
	
	// Fields
	private static WebBrowser emptyBrowser;
	private static WebBrowser noHistory;
	private static WebBrowser noForward;
	private static WebBrowser fullBrowser;
	
	
	public static void setup () throws MalformedURLException {
		
		emptyBrowser = new WebBrowser();
		
		
		noHistory = new WebBrowser();
		
		noHistory.visit(new URL("http://www.example.com/docs/resource1.html"));
		noHistory.visit(new URL("https://www.google.com/"));
		noHistory.visit(new URL("https://www.youtube.com/"));
		
		noHistory.back();
		noHistory.back();
		noHistory.back();
		
		
		noForward = new WebBrowser();
		
		noForward.visit(new URL("http://www.example.com/docs/resource1.html"));
		noForward.visit(new URL("https://www.google.com/"));
		noForward.visit(new URL("https://www.youtube.com/"));
		
		
		fullBrowser = new WebBrowser();
		
		fullBrowser.visit(new URL("http://www.example.com/docs/resource1.html"));
		fullBrowser.visit(new URL("https://www.google.com/"));
		fullBrowser.visit(new URL("https://www.youtube.com/"));
		
		fullBrowser.visit(new URL("http://www.example.com/docs/resource1.html"));
		fullBrowser.visit(new URL("https://www.google.com/"));
		fullBrowser.visit(new URL("https://www.youtube.com/"));
		
		fullBrowser.back();
		fullBrowser.back();
		fullBrowser.back();
	}
	
	
	
//========================================================= CONSTRUCTOR TESTS ========================================================================
	
	
	@Test
	public static void constructorTestOnEmpty () throws MalformedURLException {
		setup();
		
		
	}
	
	
	
	@Test
	public static void constructorTestOnSmall () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void constructorTestOnLarge () throws MalformedURLException {
		setup();
		
	}
	
	
	
//=========================================================== VISIT TESTS ============================================================================
	
	
	@Test
	public static void visitTestOnEmpty () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void visitTestOnSmall () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void visitTestOnLarge () throws MalformedURLException {
		setup();
		
	}
	
	
	
//=========================================================== BACK TESTS =============================================================================
	
	
	@Test
	public static void backTestOnEmpty () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void backTestOnSmall () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void backTestOnLarge () throws MalformedURLException {
		setup();
		
	}
	
	
	
//========================================================== FORWARD TESTS ===========================================================================
	
	
	@Test
	public static void forwardTestOnEmpty () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void forwardTestOnSmall () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void forwardTestOnLarge () throws MalformedURLException {
		setup();
		
	}
	
	
	
//========================================================== HISTORY TESTS ===========================================================================

	
	@Test
	public static void historyTestOnEmpty () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void historyTestOnSmall () throws MalformedURLException {
		setup();
		
	}
	
	
	
	@Test
	public static void historyTestOnLarge () throws MalformedURLException {
		setup();
		
	}
	
}
