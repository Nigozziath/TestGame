import java.awt.Color;

/**
 * 
 * @author Romain
 *
 * represent a physical element of the game that can be displayed
 * for the purpose of this game, its aspect is a circle
 */
public class Actor implements Tickable{

	//represents the location of the actor in x coordinates
	protected float xLocation ;
	//represents the location of the actor in y coordinates
	protected float yLocation ;
	
	//represents the speed of the actor in x coordinates
	protected float xSpeed = 50f ;
	//represents the speed of the actor in y coordinates
	protected float ySpeed = 0f ;
	
	//Actor's aspect is a circle, here are its size (diameter)  and color
	protected float size ;
	protected Color color ;
	
	//reference to game
	protected Game game ;
	
	/**
	 * Constructor 
	 * @param xLoc is the initial position of the actor at its creation in x coordinates
	 * @param yLoc is the initial position of the actor at its creation in y coordinates
	 */
	public Actor(float xLoc, float yLoc, Game game) {
		this.xLocation = xLoc;
		this.yLocation = yLoc;
		this.game = game;
	}
	
	@Override
	public void tick() {
		// update the actor location using its speed
		xLocation += xSpeed / Game.framePerSecond ;
		yLocation += ySpeed / Game.framePerSecond ;
	}
	
	// Getters and Setters
	
	public float getxLocation() {
		return xLocation;
	}

	public void setxLocation(float xLocation) {
		this.xLocation = xLocation;
	}

	public float getyLocation() {
		return yLocation;
	}

	public void setyLocation(float yLocation) {
		this.yLocation = yLocation;
	}

	public float getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
