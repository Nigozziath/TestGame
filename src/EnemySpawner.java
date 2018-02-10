import java.util.Random;

/**
 * 
 * @author Romain
 * Spawns Enemies
 */
public class EnemySpawner implements Tickable{

	//times in milliseconds
	private long timeBetweenEachSpawn = 650;
	private long lastTimeSpawn = 0;
	
	private float initialEnemySpeed = 225f ;
	
	// ref to the game to have access to the game time
	private Game game ;
	
	public EnemySpawner (Game game) {
		this.game = game;
	}

	@Override
	public void tick() {
		
		// timer check, if waited long enough make an enemy spawn
		if (game.getCurrentGameTime() > this.lastTimeSpawn + this.timeBetweenEachSpawn) {
			this.spawnEnemy();
			this.lastTimeSpawn = game.getCurrentGameTime() ;
			
			// increase difficulty by reducing the time between each spawn
			if (game.getCurrentGameTime() > 5f ) {
				timeBetweenEachSpawn *= 0.99 ;
			}
		}
		
	}
	
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
}
