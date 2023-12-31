package assign07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GraphTester {
	
	Graph<String> smallGraph = new Graph<String>();
	
	GraphUtility graphHelper = new GraphUtility();
	
	List<String> smallSources;
	List<String> smallDestinations;
	
	public void setup() {
		smallSources = new ArrayList<String>();
		smallSources.add("a");
		smallSources.add("b");
		smallSources.add("c");
		smallSources.add("d");
		smallSources.add("a");
		
		smallDestinations = new ArrayList<String>();
		smallDestinations.add("b");
		smallDestinations.add("c");
		smallDestinations.add("d");
		smallDestinations.add("e");
		smallDestinations.add("c");
		
	}

	@Test
	public void testDFSTrue() {
		setup();
		
		@SuppressWarnings("static-access")
		boolean result = graphHelper.areConnected(smallSources, smallDestinations, "a", "e");
		assertTrue(result);
	}
	
	@Test
	public void testDFSFalse() {
		setup();
		
		@SuppressWarnings("static-access")
		boolean result = graphHelper.areConnected(smallSources, smallDestinations, "e", "a");
		assertFalse(result);
	}
	
	@Test
	public void testBFS() {
		setup();
		
		@SuppressWarnings("static-access")
		List<String> result = graphHelper.shortestPath(smallSources, smallDestinations, "a", "e");
		
		for(int i = 0; i <result.size();i++) {
			if(i == 0) {
				assertEquals("a",result.get(i));
			}
			if(i == 1) {
				assertEquals("c",result.get(i));
			}
			if(i == 2) {
				assertEquals("d",result.get(i));
			}
			if(i == 3) {
				assertEquals("e",result.get(i));
			}
		}
	}
}
