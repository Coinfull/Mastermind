package mastermind;

public class MasterMindPlayerState implements MasterMindState {
	private MasterMind masterMind;
	
	private int numClicked = 0;
	
	public MasterMindPlayerState(MasterMind mind){
		masterMind = mind;
	}
	
	public void mouseClicked(int x_click, int y_click){
		if(masterMind.getNumGuesses() == 0 && numClicked == 0){
			int change = masterMind.changeAI(x_click, y_click);
			if(change > 0){
				masterMind.changeState(masterMind.getAIState());
				masterMind.getAIState().setAI(change);
				return;
			}
		}
		
		if(numClicked == 4){
			numClicked = 0;
			
			int change = masterMind.changeAI(x_click, y_click);
			if(change > 0){
				masterMind.changeState(masterMind.getAIState());
				masterMind.getAIState().setAI(change);
				return;
			}
		}
		
		int is_color_selected = masterMind.isColorSelected(x_click, y_click);
		if (is_color_selected > 0)
		{
			masterMind.addGuess(is_color_selected);
			numClicked++;
		}
	}
}