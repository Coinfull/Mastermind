package mastermind;

import util.Random;

public class MasterMindAIRandom implements MasterMindAI {
	int guessNumber;
	
	private MasterMind masterMind;
	
	public MasterMindAIRandom(MasterMind mind){
		masterMind = mind;
	}
	
	public Guess nextGuess(){
		Random random = Random.getRandomNumberGenerator();
		
		Guess guess = new Guess(masterMind.getNumGuesses() + 1);
		
		for(int i = 0; i < 4; ++i){
			int rand = random.randomInt(1, 7);
			
			guess.addColor(rand);
		}
		
		return guess;
	}
}