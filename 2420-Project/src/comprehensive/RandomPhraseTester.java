package comprehensive;

import java.util.Random;

import assign10.BinaryMaxHeap;

public class RandomPhraseTester {
	
	

	public static void main(String[] args) {
		
		int timesToLoop = 100000;

		//Uncomment the line below to test the basic priority queue methods.
		addTest(timesToLoop);
		//increasingNumOfNonTerminals();


		
	}
	
	
	
	public static void addTest(int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 21; n ++) {
			

			

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
			RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/poetic_sentence.g",loopTime);
			
			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / (1000);
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	
	public static void increasingNumOfNonTerminals() {
		
		for(int n = 1; n < 11; n++) {
			
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
				
			startTime = System.currentTimeMillis();
					
			if(n==1) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/1.g",1000000);
			}else if(n==2) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/2.g",1000000);
			}else if(n==3) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/3.g",1000000);
			}else if(n==4) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/4.g",1000000);
			}else if(n==5) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/5.g",1000000);
			}else if(n==6) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/6.g",1000000);
			}else if(n==7) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/7.g",1000000);
			}else if(n==8) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehens	ive/8.g",1000000);
			}else if(n==9) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/9.g",1000000);
			}else if(n==10) {
				RandomPhraseGenerator phraseGenerator = new RandomPhraseGenerator("src/comprehensive/10.g",1000000);
			}
			
			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / (1000);
			System.out.println(n + "\t" + averageTime);
		}
	}
}