package main;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

import javax.swing.JFrame;

 /**
  * 
  * @author Romain
  *
  * Main component of the program that initialize the game components and handle the game loop
  */
public class Game {

	// the number of frame per second, used in the game loop
	public static final int framePerSecond = 50;
	
	// window dimensions
	public static final int windowWidth = 800;
	public static final int windowHeight = 800;
	
	// time in milliseconds
	private long startTime = System.currentTimeMillis() ;
	private long currentGameTime;
	
	// graphic components
	private GameFrame frame;
	private GamePanel panel;
	
	// Tickables
	private InputManager inputManager;
	private Player player ;
	private EnemySpawner enemySpawner;
	// set of actors to be update and displayed
	private HashSet<Actor> gameActors = new HashSet<Actor>();
	// set of actors to be removed from the gameActors set (when out of bound)
	private HashSet<Actor> gameActorsToDestroy = new HashSet<Actor>();
	
	//the Thread that is running the game loop
	private GameThread gameThread ;
	private boolean isGameLoopRunning = false ;
	
	/**
	 * Constructor, initialize the whole game
	 */
	public Game() {
		
		//initialize graphic components
		this.initializeGraphicComponents() ;
		
		//create the player at the center and add it to the actorList
		this.player = new Player(this.frame.getWidth()/2 , this.frame.getHeight()/2, this);
		this.gameActors.add(this.player); 
		
		//create input manager and adds it to the mouse listener list of the game panel
		this.inputManager = new InputManager(this.player);
		this.panel.addMouseListener((MouseListener) this.inputManager);
		this.panel.addMouseMotionListener((MouseMotionListener) this.inputManager);
		
		//create enemySpawner
		this.enemySpawner = new EnemySpawner(this);
		
		//start the game loop
		this.startGameLoop();
	}
	
	/**
	 * Make the game loop starts
	 */
	public void startGameLoop(){

		//create a new thread to start the game loop
		gameThread = new GameThread(this, "GameThread");
		gameThread.start();
		
		this.isGameLoopRunning = true ;
		
	  }

	/**
	 * Execute all the actions for a game frame
	 */
	public void resolveCurrentFrame () {
		
		// update current game time
  	  this.currentGameTime = System.currentTimeMillis() - this.startTime ;

  	  // make tick the input manager, the enemy spawner and all actors
  	  this.makeTickAllTickables();

  	  // make the game panel update the display
  	  this.panel.repaint();
	}
	
	/**
	 * Instantiate game frame and panel
	 */
	private void initializeGraphicComponents () {
		
		//Initialize frame and panel
		
		// create frame
		this.frame = new GameFrame(windowWidth,windowHeight);
		//create panel
		this.panel = new GamePanel(this);
		//add it to the frame
		this.frame.setContentPane(panel);
	}
	
	/**
	 * Make all Tickables tick and check player collision with enemies.
	 * Handle out of bound enemy destruction too.
	 */
	private void makeTickAllTickables() {

  	  // make the input manager tick
  	  this.inputManager.tick();
  	  
  	  // for all the actors, make them tick 
  	  for (Actor act : gameActors) {
  		  if (act != null) {
    		  // make the actor tick
    		  act.tick() ;
  		  }
  	  }
  	  
  	  // removed out of bound enemies from gameActors set
  	  for (Actor act : gameActorsToDestroy) {
  		  if (act != null) {
  			  this.gameActors.remove(act);
  		  }
  	  }
  	  
  	  // then clears gameActorsToDestroy set
  	  this.gameActorsToDestroy.clear();
  	  
  	  // make the enemy Spawner tick
  	  this.enemySpawner.tick();
  	  
  	  // check collision with the player for all remaining enemies
	  for (Actor act : gameActors) {
		  if (act != null) {
			  
			  // check if the enemy is colliding with the player
			  if (act.getClass().isAssignableFrom(Enemy.class)) {
				  this.checkPlayerCollision((Enemy)act);  
			  }
		  }
	  }

	}
	
	/**
	 * Check if the player is colliding with an enemy
	 * @param enemy is the enemy for the collision check
	 */
	public void checkPlayerCollision (Enemy enemy) {
		
		// distance between their center
		float distanceBetween = (float) Math.sqrt(
		Math.pow(this.player.getxLocation() - enemy.getxLocation(), 2) 
		+
		Math.pow(this.player.getyLocation() - enemy.getyLocation(), 2) 
		) ;
		
		// the sum of their ray (half of their size)
		float sizeSum = enemy.getSize() / 2 + this.player.getSize() / 2;
		
		// if the distance is lesser than the sum of the rays, the two are colliding
		if (distanceBetween < sizeSum) {
			// game over !
			// stop the game loop
			this.isGameLoopRunning = false ;
		}	
	}		
	
	// Getters and Setters
	
	public HashSet<Actor> getGameActors() {
		return gameActors;
	}

	public void setGameActors(HashSet<Actor> gameActors) {
		this.gameActors = gameActors;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}

	public GamePanel getPanel() {
		return panel;
	}

	public void setPanel(GamePanel panel) {
		this.panel = panel;
	}

	public InputManager getInputmanager() {
		return inputManager;
	}

	public void setInputmanager(InputManager inputmanager) {
		this.inputManager = inputmanager;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public EnemySpawner getEnemySpawner() {
		return enemySpawner;
	}

	public void setEnemySpawner(EnemySpawner enemySpawner) {
		this.enemySpawner = enemySpawner;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getCurrentGameTime() {
		return currentGameTime;
	}

	public void setCurrentGameTime(long currentGameTime) {
		this.currentGameTime = currentGameTime;
	}

	public boolean isGameRunning() {
		return isGameLoopRunning;
	}

	public void setGameRunning(boolean isGameRunning) {
		this.isGameLoopRunning = isGameRunning;
	}

	public HashSet<Actor> getGameActorsToDestroy() {
		return gameActorsToDestroy;
	}

	public void setGameActorsToDestroy(HashSet<Actor> gameActorsToDestroy) {
		this.gameActorsToDestroy = gameActorsToDestroy;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public void setInputManager(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	public GameThread getGameThread() {
		return gameThread;
	}

	public void setGameThread(GameThread gameThread) {
		this.gameThread = gameThread;
	}

	public boolean isGameLoopRunning() {
		return isGameLoopRunning;
	}

	public void setGameLoopRunning(boolean isGameLoopRunning) {
		this.isGameLoopRunning = isGameLoopRunning;
	}    
	
}
