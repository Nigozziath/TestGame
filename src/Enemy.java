import java.awt.Color;

/**
 * 
 * @author Romain
 * 
 * Bad guy from the game, if the player touches one of them, it's game over
 */
public class Enemy extends Actor {

	/**
	 * Constructor
	 * @param xLoc, initial position in x coordinates
	 * @param yLoc, initial position in y coordinates
	 * @param game, reference to the game
	 */
	public Enemy(float xLoc, float yLoc, Game game) {
		super(xLoc, yLoc, game);
		this.size = 75f;
		this.color = Color.RED;
	}
	 /**
	  * Constructor
	  * @param xLoc, initial position in x coordinates
	  * @param yLoc, initial position in y coordinates
	  * @param xSpeed, initial speed in x coordinates
	  * @param ySpeed, initial speed in y coordinates
	  * @param game, reference to the game
	  */
	public Enemy(float xLoc, float yLoc, float xSpeed, float ySpeed, Game game) {
		this(xLoc, yLoc, game);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	public void tick() {
		super.tick();
		
		// check if the enemy is out of the window to remove it from the game actors set
		if (this.xLocation < - 25 || this.yLocation < - 25 || this.xLocation > Game.windowWidth + 25 || this.yLocation > Game.windowHeight + 25) {

			this.game.getGameActorsToDestroy().add(this);
			
			//try to delete the object
			try {
				this.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
}
