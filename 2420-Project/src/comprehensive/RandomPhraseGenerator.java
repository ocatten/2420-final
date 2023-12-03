package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		
		readFile(file);//Fill the HashMap 
		
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
                	nextLine = in.nextLine();//Skips the current line to find the next non-terminal.
                	currNonTerminal = nextLine.trim();
                	//System.out.println(currNonTerminal);
                	if(currNonTerminal.equals("<start>")) {
                		if(startingPhrase != null) {
                			//Throw error
                			System.out.println("Error!");
                		}
                		
                		startingPhrase = in.nextLine();//Sets the starting phrase as the next line.
                		System.out.println("Starting Line Found. Starting phrase is: " + startingPhrase);
                		//Either use break or return, I forgot which one tbh
                		break;  		
                	}
                	
                	//This creates an ArrayList to hold all the terminals associated with the current non-terminal.
                	grammerPhrases.put(currNonTerminal, new ArrayList<>());
                	withinNonTerminal = true;
                	
                }else if(nextLine.startsWith("}")) {//Closes the current non-terminal.
                	//Set the function to outside of a terminal and set the current terminal and non-terminal to null.
                	withinNonTerminal = false;
                	currTerminal = null;
                	currNonTerminal = null;
                	
                }else if(withinNonTerminal) {//If nonterminal or phrase given, it means non-terminal incorrectly started.
                	currTerminal = nextLine.trim();
                	
                	//Add the current terminal to its nonterminal ArrayList
                	grammerPhrases.get(currNonTerminal).add(currTerminal);
                }
                
                else {//For testing, delete this line later.
                	//System.out.println("File contains line not applicable: ");
                }
                //System.out.println(nextLine);
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
		for(int i = 0; i < numOfPhrases; i++) {
			//S
			returnStrings.add(constructPhrase());
		}
		//
		return returnStrings;
	}
	
	/*
	 * Generates each phrase starting from the given Start phrase.
	 */
	public String constructPhrase() {


		//List to store the parts of the generated phrase to be later constructed from.
		List<String> parts = new ArrayList<String>();
		
		//Create a stack to track the new Strings to add to the parts
		Stack<String> nextPhrases = new Stack<String>();
		
		nextPhrases.push(startingPhrase);
		
		System.out.println(startingPhrase);
		
		while(!nextPhrases.isEmpty()) {
			String currString = nextPhrases.pop();
			//System.out.println(currString);
			if(currString.contains("<") && currString.contains(">")) {
				//Break up the String and push each of the sections
				List<String> segments = new ArrayList<String>();
				
				
            } else {
                parts.add(currString);
            }
       }


		
        String result = "";
		
        //Add each of the items in the String to the rest of the String
        for(String part: parts) {
        	result += part;
        }
         
		
		return result;// 
	}
	
    public static void main(String[] args) {
        RandomPhraseGenerator generator = new RandomPhraseGenerator("src/comprehensive/poetic_sentence.g", 5);
    }
}
	