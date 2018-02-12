package main;
import java.util.Random;

/**
 * 
 * @author Romain
 * 
 * Spawns Enemies at time intervals
 */
public class EnemySpawner implements Tickable{

	//times in milliseconds
	//duration between each enemy spawn
	private long timeBetweenEachSpawn = 500;
	//last time an enemy was spawned
	private long lastTimeSpawn = 0;
	
	private float initialEnemySpeed = 225f ;
	
	// reference to the game to have access to the game time
	private Game game ;
	
	/**
	 * Constructor
	 * @param game, reference to the game
	 */
	public EnemySpawner (Game game) {
		this.game = game;
	}

	@Override
	public void tick() {
		
		// time check, if waited long enough make an enemy spawn
		if (game.getCurrentGameTime() > this.lastTimeSpawn + this.timeBetweenEachSpawn) {
			this.spawnEnemy();
			this.lastTimeSpawn = game.getCurrentGameTime() ;
			
			// increase difficulty by reducing the time between each spawn
			if (game.getCurrentGameTime() < 25 * 000) { 
				timeBetweenEachSpawn *= 0.99 ;
			} else { // after 25 seconds reduce difficulty increase
				timeBetweenEachSpawn *= 0.995 ; 
			}
		}
		
	}
	
	/**
	 * Spawn an enemy from a random border
	 */
	public void spawnEnemy() {		
		
		float initialXPos = 0 ;
		float initialYPos = 0 ;
		
		float initialXSpeed = 0 ;
		float initialYSpeed = 0 ;
		
		Random random = new Random();
		
		switch (Border.getRandomBorder()) {
		
		case DOWN:
			initialXPos = random.nextInt(Game.windowWidth) ;
			initialYPos = Game.windowHeight ;
			initialXSpeed = 0 ;
			initialYSpeed = -initialEnemySpeed ;
			break;
			
		case LEFT:
			initialXPos = 0 ;
			initialYPos = random.nextInt(Game.windowHeight) ;
			initialXSpeed = initialEnemySpeed ;
			initialYSpeed = 0 ;
			break;
			
		case RIGHT:
			initialXPos = Game.windowWidth ;
			initialYPos = random.nextInt(Game.windowHeight) ;
			initialXSpeed = -initialEnemySpeed ;
			initialYSpeed = 0 ;
			break;
			
		case TOP:
			initialXPos = random.nextInt(Game.windowWidth) ; ;
			initialYPos = 0 ;
			initialXSpeed = 0 ;
			initialYSpeed = initialEnemySpeed ;
			break;
			
		default:
			break;
		}
		
		game.getGameActors().add(new Enemy(initialXPos, initialYPos, initialXSpeed, initialYSpeed, this.game));
	}

	public long getTimeBetweenEachSpawn() {
		return timeBetweenEachSpawn;
	}

	public void setTimeBetweenEachSpawn(long timeBetweenEachSpawn) {
		this.timeBetweenEachSpawn = timeBetweenEachSpawn;
	}

	public long getLastTimeSpawn() {
		return lastTimeSpawn;
	}

	public void setLastTimeSpawn(long lastTimeSpawn) {
		this.lastTimeSpawn = lastTimeSpawn;
	}

	public float getInitialEnemySpeed() {
		return initialEnemySpeed;
	}

	public void setInitialEnemySpeed(float initialEnemySpeed) {
		this.initialEnemySpeed = initialEnemySpeed;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
