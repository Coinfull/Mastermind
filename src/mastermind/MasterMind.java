package mastermind;

import java.awt.Graphics;
import java.awt.Image;

import gui.Drawable;
import util.Random;

class MasterMind implements Drawable
{
	private Image board_img;
	private Guesses current_guesses;
	private MasterMindState currentState;
	private MasterMindGameOverState gameOverState;
	private MasterMindAIState AIState;
	private MasterMindPlayerState playerState;
	
	public MasterMindPlayerState getPlayerState(){
		return playerState;
	}
	
	public MasterMindAIState getAIState(){
		return AIState;
	}
	
	public void changeState(MasterMindState state){
		currentState = state;
	}
	
	public MasterMind(int arg)
   {
	  board_img = gui.ImageLoader.getImageLoader().getImage("images/mastermind_board.jpg");
      current_guesses = new Guesses();

      gui.BasicGUI mm_gui = new gui.BasicGUI(457, 690, "Master Mind", this, null);
      mm_gui.setVisible(true);
	  
	  AIState = new MasterMindAIState(this);
	  gameOverState = new MasterMindGameOverState();
	  playerState = new MasterMindPlayerState(this);
	  
	  switch(arg){
		case 0:
			currentState = playerState;
			break;
		case 1:
			currentState = AIState;
			break;
		default:
			currentState = playerState;
			break;
	  }
   }
   
   public void mouseClicked(int x, int y) 
   {
	   if (isGameOver()){
		   currentState = gameOverState;
	   }
	   
		currentState.mouseClicked(x, y);
	}
	
	public void draw(Graphics g, int width, int height)
   {
		g.drawImage(board_img, 0, 0, null);
		current_guesses.draw(g);
   }
   
   public boolean isPlayerGuessComplete()
   {
	   return current_guesses.isPlayerGuessComplete();
   }

   //report result when comparing two guesses to each other (first parameter tested against the second parameter)
   public int[] reportTestResult(Guess one, Guess two)
   {
		assert (one != null && two != null) : "Guess is null.";
		return one.reportResult(two);
   }
   
   public Guess getGuess(int guess_num)
   {
		assert guess_num >= 1 : "Invalid guess_num";
		return current_guesses.getGuess(guess_num);
   }
   
   public int getNumGuesses()
   {
	   return current_guesses.getNumGuesses();
   }
   
   public boolean isGameOver()
   {
	   return current_guesses.isGameOver();
   }
   
   public int isColorSelected(int x, int y)
   {
	   return current_guesses.isColorSelected(x, y);
   }
   
   public void addGuess(int color)
   {
	   current_guesses.addGuess(color);
   }
   
   public void addGuess(Guess ai_guess)
   {
	   current_guesses.addGuess(ai_guess);
   }
   
   public int changeAI(int x, int y)
   {
	   int w_half = 12;
	   int h_half = 13;
	   
	   int center_x = 430;
	   
	   int center_0_y = 542;
	   int center_1_y = 571;
	   int center_2_y = 602;
	   int center_3_y = 630;
	   
	   if (Math.abs(x-center_x) < w_half)
	   {
		   if(Math.abs(y-center_0_y) < h_half)
		   {
			   return 0;
		   }
		   
		   else if(Math.abs(y-center_1_y) < h_half)
		   {
			   return 1;
		   }
		   
		   else if(Math.abs(y-center_2_y) < h_half)
		   {
			   return 2;
		   }
		   
		   else if(Math.abs(y-center_3_y) < h_half)
		   {
			   return 3;
		   }
	   }
	   
	   return -1;
   }
  
	public void keyPressed(char key)
   {
   }
}
