package mastermind;

import java.util.List;
import java.util.ArrayList;
import util.Random;

class MasterMindAIMiniMax implements MasterMindAI {
	private MasterMind masterMind;
	
	private List<Guess> allGuesses;
	private List<Guess> possible;

	public MasterMindAIMiniMax(MasterMind mind){
		masterMind = mind;
		
		allGuesses = new ArrayList<Guess>();
		possible = new ArrayList<Guess>();
		
		for(int i = 1; i <= 7; ++i){
			for(int j = 1; j <= 7; ++j){
				for(int k = 1; k <= 7; ++k){
					for(int l = 1; l <= 7; ++l){
						Guess guess = new Guess(masterMind.getNumGuesses() + 1);
						guess.addColor(i);
						guess.addColor(j);
						guess.addColor(k);
						guess.addColor(l);
						
						allGuesses.add(guess);
					}
				}
			}
		}
		
		possible = allGuesses;
	}
	
	public Guess nextGuess(){
		//Generate the basic 4 color guess
		if(masterMind.getNumGuesses() == 0){
			Guess initial = new Guess(1);
							
			for(int i = 1; i <= 4; ++i){
				initial.addColor(i);
			}
			
			return initial;
		}
		
		for(int i = 1; i <= masterMind.getNumGuesses(); ++i){
			Guess prev = masterMind.getGuess(i);
			
			for(int j = 0; j < possible.size(); ++j){
				int[] buttons = masterMind.reportTestResult(possible.get(j), prev);
				
				if(buttons[0] != prev.getNumBlack() ||
				   buttons[1] != prev.getNumWhite()){
					   possible.remove(j);
					   --j;
				}
			}
		}
		
		Guess bestGuess = null; //What we will return	
		int max = 0;

		for(Guess possibleGuess : allGuesses){
			int min = 0;
		
			for(int black = 0; black <= 4; ++black){
				for(int white = 0; white <= (4 - black); ++white){
					int count = 0;
					
					for(Guess guess : possible){						
						int []ar = guess.reportResult(possibleGuess);
						
						if(ar[0] != black || ar[1] != white){
							++count;
						}
					}
					
					//Set the new minimum
					if(min <= count){
						min = count;
						bestGuess = new Guess(possibleGuess);
						bestGuess.setID(masterMind.getNumGuesses() + 1);
					}
				}
			}
			
			//Check the minimum versus the other gueses
			if(min >= max){
				max = min;
				bestGuess = new Guess(possibleGuess);
				bestGuess.setID(masterMind.getNumGuesses() + 1);
			}
		}
		
		for(int i = 0; i < possible.size(); i++){
			Guess guess = possible.get(i);
			
			if(guess.toString().equals(bestGuess.toString())){
				possible.remove(i);
			}
		}
		
		System.out.println(possible.size());

		return bestGuess;
	}
}