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
	
	
	
	/*
	 * Takes in the given command-line arguments and returns the number of specified random phrases
	 * created using the given file.
	 */
    public static void main(String[] args) {
    	
    	//Fields to hold given args.
    	String fileToRun = args[0];
    	int numOfPhrases = Integer.parseInt(args[1]);
    	
    	RandomPhraseGenerator generator = new RandomPhraseGenerator(fileToRun, numOfPhrases);
    }

	/*
	 * Creates a random phrase by using the given file to determine who to create a random phrase
	 * and it creates the given number of phrases.
	 */
	public RandomPhraseGenerator(String file, int numOfPhrases) {
		
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
                String nextLine = in.nextLine();//Stores the next line from the file as a String.
                
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
		
		//LinkedList to store each of the sections of the phrase.
		LinkedList<String> phrases = new LinkedList<String>();
		
		//Add <start> to the front of the LinkedList to begin creating the random phrase.
		phrases.addFirst("<start>");
		
		while(!phrases.isEmpty()) {
			//Remove the first item from the LinkedList and store it in a String value.
			String currString = phrases.removeFirst();

			
			//Checks if a non-terminal is contained within the current string.
			if(currString.contains("<") && currString.contains(">")) {
				//Checks that the current String contains only a non-terminal.
				if(currString.charAt(0) == '<' && currString.charAt(currString.length()-1) == '>' && !currString.contains(" ")) {
					//Choose a random phrase from the current non-terminal ArrayList and add it to the front of the LinkedList.
					List<String> currList = grammerPhrases.get(currString);
					phrases.addFirst(currList.get(rng.nextInt(currList.size())));
				}
				//If the String isn't just a non-terminal, then split the non-terminals from the rest of the String sections and add each part to the LinkedList.
				else {
					//Syntax for splitting the String.
					String[] segments = currString.split("(?=<)|(?<=>)");
					
					//Adds the segments in avoid mixing up the positions.
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
		
        String result = "";//String to hold the result.
		
        //Add each of the Strings in the parts ArrayList to form the returned phrase.
        for(String part: parts) {
        	result += part;
        }
         
		
		return result;//Finally return the current random phrase generated.
	}
}
