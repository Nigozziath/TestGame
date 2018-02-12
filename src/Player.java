import java.awt.Color;

/**
 * 
 * @author Romain
 *
 * The actor controlled by the player
 */
public class Player extends Actor{

	// speed factor that determined the movement speed of the player
	private float speedFactor = 500f ;
	
	// minimum mouse distance for start to move, smooth the movement at low distance.
	private float distanceSensibility = 5f ;
	
	/**
	 * Constructor
	 * @param xLoc, initial position in x coordinates
	 * @param yLoc, initial position in y coordinates
	 * @param game, reference to the game
	 */
	public Player(float xLoc, float yLoc, Game game) {
		super(xLoc, yLoc, game);
		
		this.size = 60f;
		this.color = Color.BLUE;
	}
	
	/**
	 * Use the mouse position to calculate the new speed of the player
	 * @param mouseXLoc, mouse position in x coordinates
	 * @param mouseYLoc, mouse position in y coordinates
	 */
	public void applySpeed(int mouseXLoc, int mouseYLoc) {

		// position delta in x and y coordinates
		float xDelta = mouseXLoc -  this.xLocation;
		float yDelta = mouseYLoc -  this.yLocation;
		
		// calculate the distance between the player and the mouse
		float distance = (float) Math.sqrt(xDelta * xDelta + yDelta * yDelta);
		
		if (distance > this.distanceSensibility) {
			//apply speed to the player
			this.setxSpeed(xDelta / distance * speedFactor);
			this.setySpeed(yDelta / distance * speedFactor);
		} else {
			//stop the player for avoiding shaking
			this.setxSpeed(0);
			this.setySpeed(0);
		}
	}

	//getters and setters
	
	public float getSpeedFactor() {
		return speedFactor;
	}

	public void setSpeedFactor(float speedFactor) {
		this.speedFactor = speedFactor;
	}

	public float getDistanceSensibility() {
		return distanceSensibility;
	}

	public void setDistanceSensibility(float distanceSensibility) {
		this.distanceSensibility = distanceSensibility;
	}
}
