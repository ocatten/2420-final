package assign06;

import java.net.URL;
import java.util.NoSuchElementException;


/**
 * This class simulates a WebBrowser with back and forward buttons that use stacks. The buttons keep a record of the URL objects
 * the WebBrowser has visited and updates them respectively as it traverses new pages.
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 10:19:23
 */
public class WebBrowser {
	
	// Fields
	private LinkedListStack<URL> backButton;
	private LinkedListStack<URL> forwardButton;
	
	
	/**
	 * @Constructor that makes two empty stacks for the back and forward buttons.
	 */
	public WebBrowser () {
		
		backButton = new LinkedListStack<URL>();
		forwardButton = new LinkedListStack<URL>();
	}
	
	
	
	/**
	 * @Constructor that makes an empty forward button but adds all URL's from the parameter into the backButton.
	 * The first webpage in the list is the "current" webpage visited, and the remaining webpages are ordered
	 * from most recently visited to least recently visited.
	 * 
	 * @param history: SinglyLinkedList of URL's to add to the BackButton
	 */
	public WebBrowser (SinglyLinkedList<URL> history) {
		
		this(); // Sets up relevant fields by calling the other constructor
		
		// Iterate from the last element in the list to the first, adding each one to the backButton in reverse order.
		for(int i = history.size()-1; i >= 0; i--) {
			
			backButton.push(history.get(i));
		}
	}
	
	
	
	/**
	 * This method simulates visiting a webpage, given as a URL. Clears the forward button stack, since there is no
	 * URL to visit next.
	 * 
	 * @param webpage: URL to visit
	 */
	public void visit (URL webpage) {
		
		// Clears the forwardButton
		forwardButton.clear();
		
		// Adds the new webpage to the top of the backButton's stack, as it's the current webpage
		backButton.push(webpage);
	}
	
	
	
	/**
	 * This method simulates using the back button, returning the URL visited. Adjusts
	 * back and forward buttons accordingly.
	 * 
	 * @return: New current webpage
	 * @throws NoSuchElementException: Links to an external site if there is no previously-visited URL.
	 */
	public URL back () throws NoSuchElementException {
		
		// Add the current page to the forwardButton and delete the current page from the backButton.
		forwardButton.push( backButton.pop() );
		
		return backButton.peek(); // Return the new current webpage
	}
	
	
	
	/**
	 * This method simulates using the forward button, returning the URL visited. Adjusts
	 * back and forward buttons accordingly.
	 * 
	 * @return: New current webpage
	 * @throws NoSuchElementException: Links to an external site if there is no URL to visit.
	 */
	public URL forward () throws NoSuchElementException {
		
		// Add the new page to the backButton and remove it from the forwardButton
		backButton.push( forwardButton.pop() );
				
		return backButton.peek(); // Return the new current webpage
	}
	
	
	
	/**
	 * This method generates a history of URLs visited, as a list of URL objects
	 * ordered from most recently visited to least recently visited (including
	 * the "current" webpage visited), without altering subsequent behavior of
	 * this web browser. "Forward" URLs are not included. The behavior of the 
	 * method must be O(N), where N is the number of URLs.
	 * 
	 * @return: A SinglyLinkedList of each visited page stored in the backButton.
	 */
	public SinglyLinkedList<URL> history () {
		
		// Add the current webpage to the history.
		SinglyLinkedList<URL> temp = new SinglyLinkedList<URL>();

		// Get an array from the backing list. O(N)
		URL[] tempArr = (URL[])backButton.getList().toArray();
		
		// Add these elements from the back to front using insertFirst, adding up to O(N).
		for(int i = tempArr.length-1; i >= 0; i--) {
			
			temp.insertFirst(tempArr[i]);
		}
		
		return temp; // Return the completed array
	}
	
}
