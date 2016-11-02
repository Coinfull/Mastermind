package mastermind;

public class MasterMindDriver
{
	//entry point
   public static void main(String[] args)
   {
	  int arg;
	  
	  try{
		arg = Integer.parseInt(args[0]);
	  }catch(NumberFormatException e){
		System.out.println(e.toString());
		
		arg = 0;
	  }
	  
      MasterMind mm = new MasterMind(arg);
   }
}
   