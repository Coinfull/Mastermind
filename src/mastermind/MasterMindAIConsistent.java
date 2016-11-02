package mastermind;

import util.Random;

public class MasterMindAIConsistent implements MasterMindAI {
	private MasterMind masterMind;
	
	public MasterMindAIConsistent(MasterMind mind){
		masterMind = mind;
	}
	
	public Guess nextGuess(){
		Random random = Random.getRandomNumberGenerator();
		
		boolean goodGuess = false;
		
		Guess first = new Guess(masterMind.getNumGuesses() + 1);
		for(int i = 0; i < 4; ++i){
				int rand = random.randomInt(1, 7);
				first.addColor(rand);
		}
		
		if(masterMind.getNumGuesses() == 0){
			return first;
		}
		
		Guess second;
		do{
			second = new Guess(masterMind.getNumGuesses() + 1);
			for(int i = 0; i < 4; ++i){
				int rand = random.randomInt(1, 7);
				second.addColor(rand);
			}

			for(int i = 1; i <= masterMind.getNumGuesses(); ++i){
				Guess prev = masterMind.getGuess(i);
	
				Guess tmp = new Guess(prev);

				prev.computeResult(second);
				
				if(tmp.getNumBlack() == prev.getNumBlack() && tmp.getNumWhite() == prev.getNumWhite()){
					goodGuess = true;
				}else{
					goodGuess = false;
					break;
				}
			}
		}while(!goodGuess);
		
		return second;
	}
}