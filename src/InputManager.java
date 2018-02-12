import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Romain
 * 
 * Input Manager, capture the mouse inputs to control the player
 */
public class InputManager implements MouseListener, MouseMotionListener, Tickable{

	// mouse position in x and y coordinates
	private int mouseXPos = 0;
	private int mouseYPos = 0;
	
	// true if the mouse is pressed
	private boolean isMousePressed = false ;
	
	//the player to control
	protected Player player;
	
	/**
	 * Constructor
	 * @param player, the player to control
	 */
	public  InputManager (Player player) {
		this.player = player;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		isMousePressed = false ;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isMousePressed = true ;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isMousePressed = false ;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//update the mouse position
		mouseXPos = e.getX();
		mouseYPos = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//update the mouse position
		mouseXPos = e.getX();
		mouseYPos = e.getY();
		
	}

	@Override
	public void tick() {
		
		if (this.isMousePressed) {
			//if the mouse is pressed apply speed to the player
			this.player.applySpeed(mouseXPos, mouseYPos);
		} else {
			//if the mouse is not pressed, stop the player
			this.player.setxSpeed(0);
			this.player.setySpeed(0);
		}
	}

}
