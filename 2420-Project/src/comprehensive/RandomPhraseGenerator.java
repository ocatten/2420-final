package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/*
 * Creates a random phrase from the given critia in the input file and returns the number of 
 * specified phrases. Each random phrase is made from terminals and non-terminals. Non-terminals 
 * act as the certain from what category to pick from and the terminals are the options within the
 * category.
 * 
 * @Authors Everett Oglesby and Parker Catten
 * @Version December 2, 2023
 */
public class RandomPhraseGenerator {
	
	//Map to hold all the phrases and their respective sources. Strings represent non-terminals and the List<String> is a list of terminals.
	Map<String, List<String>> grammerPhrases = new HashMap<>();
	
	//Phrase for each of the random phrases generated to start with
	String startingPhrase;
	

	/*
	 * Creates a random phrase by using the given file to determine who to create a random phrase
	 * and it creates the given number of phrases.
	 */
	public RandomPhraseGenerator(String file, int numOfPhrases) {
		startingPhrase = null;//Set the startingPhrase to null.
		
		readFile(file);//Fills the HashMap.
		
		//Generates the list of random phrases.
		List<String> randomPhrases = generateRandomPhrases(numOfPhrases);
		
		
		System.out.println("Printing random phrases: ");
		//Print the phrases
		for(int i = 0; i < numOfPhrases; i++) {
			System.out.println(randomPhrases.get(i));
		}
	}
	
	
	/*
	 * Creates the grammerPhrases HashMap from the given file. Each key is a non-terminal which represents a category. Each
	 * of the values in Hashmap are ArrayList's of terminals, which represent the items in the corresponding non-terminal.
	 */
	public void readFile(String file)  {
		
		
		String currTerminal = null;//Tracker for current category.
		String currNonTerminal = null;//Tracker for current item.
		
		//Determines whether the current line should be added or ignored.
		boolean withinNonTerminal = false;
		
		//Check that the file given exists.
		try (Scanner in = new Scanner(new File(file))) {
            while (in.hasNextLine()) {
                String nextLine = in.nextLine();
                
                //Check each line for its content
                if(nextLine.startsWith("{")){//Check if the line has a "{" to signal the start of the next non-terminal.
                	//Since an angle bracket is given, a non-terminal is expected next.
                	nextLine = in.nextLine().trim();
                	grammerPhrases.put(nextLine,new ArrayList<String>());
                	
                	withinNonTerminal = true;
                	currNonTerminal = nextLine;
                	
                }else if(nextLine.startsWith("}")) {//Closes the current non-terminal.
                	//Set the function to outside of a non-terminal and set the current terminal and non-terminal to null.
                	withinNonTerminal = false;
                	currTerminal = null;
                	currNonTerminal = null;
                	
                }else if(withinNonTerminal) {//If nonterminal or phrase given, it means non-terminal incorrectly started.
                	
                	currTerminal = nextLine.trim();
                	
                	//Add the current terminal to its nonterminal ArrayList
                	grammerPhrases.get(currNonTerminal).add(currTerminal);
                }else {
                	//Do nothing here.
                }

            }
        } catch (FileNotFoundException e) {
            //Handles file not found exception
            System.out.println("File not found: " + file);

        }
	}
	
	/*
	 * Generates random phrases using HashMap, grammerPhrases.
	 */
	public List<String> generateRandomPhrases(int numOfPhrases) {
		ArrayList<String> returnStrings = new ArrayList<String>();
		
		//Creates a random phrase and adds it to the ArrayList of random phrases.
		for(int i = 0; i < numOfPhrases; i++) {
			returnStrings.add(constructPhrase());
		}
		
		return returnStrings;
	}
	
	/*
	 * Generates each phrase starting from the given Start phrase.
	 */
	public String constructPhrase() {

		Random rng = new Random();

		//List to store the parts of the generated phrase to be later constructed from.
		List<String> parts = new ArrayList<String>();
		
		//Create a stack to track the new Strings to add to the parts
		Stack<String> nextPhrases = new Stack<String>();
		
		LinkedList<String> phrases = new LinkedList<String>();
		
		//Push <start> to the stack to began creating the random phrase.
		phrases.addFirst("<start>");
		
		while(!phrases.isEmpty()) {
			String currString = phrases.removeFirst();

			
			//Checks if a non-terminal is contained within the current string.
			if(currString.contains("<") && currString.contains(">")) {
				//Checks that the current String contains only a non-terminal.
				if(currString.charAt(0) == '<' && currString.charAt(currString.length()-1) == '>') {
					//Choose a random phrase from the current non-terminal and push it to the Stack.
					List<String> currList = grammerPhrases.get(currString);
					phrases.addFirst(currList.get(rng.nextInt(currList.size())));
				}
				//If the String isn't just a non-terminal, then split the non-terminals from the rest of String and push each of the sections to the Stack.
				else {

					String[] segments = currString.split("(?=<)|(?<=>)");
					for(int i = 0; i < segments.length;i++) {
						phrases.add(i,segments[i]);
					}
				}				
            } 
			//If the currString doesn't contain a non-terminal, then add it to the ArrayList.
			else {
                parts.add(currString);
            }
       }

		//For testing
//		System.out.println("Parts: ");
//		for(String part:parts) {
//			System.out.println(part);
//		}
		
        String result = "";
		
        //Add each of the items in the String to the rest of the String
        for(String part: parts) {
        	result += part;
        }
         
		
		return result;//Finally return the current random phrase generated.
	}
	
    public static void main(String[] args) {
        RandomPhraseGenerator generator = new RandomPhraseGenerator("src/comprehensive/poetic_sentence.g", 5);
    }
}

///*
// * Generates each phrase starting from the given Start phrase.
// */
//public String constructPhrase() {
//
//	Random rng = new Random();
//
//	//List to store the parts of the generated phrase to be later constructed from.
//	List<String> parts = new ArrayList<String>();
//	
//	//Create a stack to track the new Strings to add to the parts
//	Stack<String> nextPhrases = new Stack<String>();
//	
//	nextPhrases.push("<start>");//Push <start> to the stack to began creating the random phrase.
//	
//	while(!nextPhrases.isEmpty()) {
//		String currString = nextPhrases.pop();
//		//System.out.println(currString);
//		
//		//Checks if a non-terminal is contained within the current string.
//		if(currString.contains("<") && currString.contains(">")) {
//			//Checks that the current String contains only a non-terminal.
//			if(currString.charAt(0) == '<' && currString.charAt(currString.length()-1) == '>') {
//				//Choose a random phrase from the current non-terminal and push it to the Stack.
//				List<String> currList = grammerPhrases.get(currString);
//				nextPhrases.push(currList.get(rng.nextInt(currList.size())));
//			}
//			//If the String isn't just a non-terminal, then split the non-terminals from the rest of String and push each of the sections to the Stack.
//			else {
//				//List<String> segments = new ArrayList<String>();
//				String[] segments = currString.split("(?=<)|(?<=>)");
//				for(String segment:segments) {
//						nextPhrases.push(segment);
//				}
//			}				
//        } 
//		//If the currString doesn't contain a non-terminal, then add it to the ArrayList.
//		else {
//            parts.add(currString);
//        }
//   }
//
//	//For testing
////	System.out.println("Parts: ");
////	for(String part:parts) {
////		System.out.println(part);
////	}
//	
//    String result = "";
//	
//    //Add each of the items in the String to the rest of the String
//    for(String part: parts) {
//    	result += part;
//    }
//     
//	
//	return result;//Finally return the current random phrase generated.
//}