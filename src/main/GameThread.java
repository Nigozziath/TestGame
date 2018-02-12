package main;

public class GameThread extends Thread {

	// reference to the game
	private Game game;
	
	  public GameThread(Game game, String name){

	    super(name);
	    this.game = game;

	  }

	  @Override
	  public void run(){
		  
		  while(game.isGameLoopRunning()) // game loop
		  {
			  try {
				  
				  //execute the actions of the frame
				  game.resolveAFrame ();
 
				  // calculate the time before the next frame
				  long timeToWaitUntillTheNextFrame = 1000 / Game.framePerSecond // normal time interval between each frame 
						  - (System.currentTimeMillis() - (game.getStartTime() + game.getCurrentGameTime())) ; // a small time correction in case of the frame take time to resolve 
				  
				  if (timeToWaitUntillTheNextFrame > 0) // security for avoiding negative time
				  Thread.sleep(timeToWaitUntillTheNextFrame); // sleep until the next frame
    	  
			  } catch (InterruptedException e) {
				  e.printStackTrace();
			  }
		  }
	  }
	}