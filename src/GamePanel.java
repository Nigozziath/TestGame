import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author Romain
 *
 * Panel that display all actors of the game
 */

public class GamePanel extends JPanel{

	// reference to the game to have an access to the game actors
	private Game game ;
	
	/**
	 * constructor
	 * @param game, reference to the game
	 */
	public GamePanel(Game game) {
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g){
		//paint in white the whole panel to reset it
	    g.setColor(Color.white);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    
	    // draw all the actors in the game
	    for (Actor act : this.game.getGameActors()) {
	    	
	    	// draw the actor using its location, size and color
	    	g.setColor(act.getColor());
	    	g.fillOval((int) (act.getxLocation() - act.getSize() / 2 ), (int) (act.getyLocation() - act.getSize() ) , (int) act.getSize(), (int) act.getSize());
	    }
	    
	    // if the game is over, print "game over"
	    if (!this.game.isGameRunning()) {
	    	g.setFont(new Font("TimesRoman", Font.BOLD, 40));
	    	g.setColor(Color.black);
	    	g.drawString("Game Over", this.getWidth() / 2 - 100, this.getHeight() / 2);
	    	g.drawString("You survived " + (float) game.getCurrentGameTime() / 1000 + " seconds" , this.getWidth() / 2 - 225, this.getHeight() / 2 + 50);
	    }
  
	}
	
}
