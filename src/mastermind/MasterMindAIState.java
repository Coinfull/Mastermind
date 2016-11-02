package mastermind;

public class MasterMindAIState implements MasterMindState {
	private MasterMindAI[] AIs;
	private int currentAI;
	
	private MasterMind masterMind;
	
	public MasterMindAIState(MasterMind mind){
		masterMind = mind;
		
		currentAI = 1;
		AIs = new MasterMindAI[3];
		AIs[0] = new MasterMindAIRandom(mind);
		AIs[1] = new MasterMindAIConsistent(mind);
		AIs[2] = new MasterMindAIMiniMax(mind);
	}
	
	//Used so you don't have to double click the button 
	//to change to that ai from the player state
	public void setAI(int num){
		currentAI = num;
	}
	
	public void mouseClicked(int x_click, int y_click){
		int change = masterMind.changeAI(x_click, y_click);
		if(change == 0){
			masterMind.changeState(masterMind.getPlayerState());
			return;
		}else if(change > 0){
			currentAI = change;
			return;
		}
		
		masterMind.addGuess(AIs[currentAI - 1].nextGuess());
	}
}