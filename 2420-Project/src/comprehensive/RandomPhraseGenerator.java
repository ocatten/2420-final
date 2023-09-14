package comprehensive;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RandomPhraseGenerator {
	File input;
	int numOfPhrases;
	ArrayList phrases;
	
//	public RandomPhraseGenerator(File input, int numOfPhrases) {
//		
//		this.input = input;
//		this.numOfPhrases = numOfPhrases;
//	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(null);
		if(in.hasNextInt()) {
			numOfPhrases = in.nextInt();
		}
		if(in.hasNext()) {
			input = in.next();
		}
		
	}
	
	public void scanFile(File input) {
		boolean readingInput = false;
		
		Scanner in = new Scanner(input);
		while(in.hasNext()) {
			if(in.hasNext("{")) {
				readingInput = true;
				in.next();
			}
			while(readingInput) {
				if(in.hasNext("}")) {
					readingInput = false;
					in.next();
				}
				
			}
		}
	}
}
