package main;
import javax.swing.JFrame;

/**
 * 
 * @author Romain
 *
 * Window for the game
 */
public class GameFrame extends JFrame {
	
	/**
	 * Constructor
	 * @param sizeX, size in width
	 * @param sizeY, size in height
	 */
	public GameFrame(int sizeX, int sizeY) {

		this.setTitle("Game");
		this.setSize(sizeX, sizeY);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		this.setVisible(true);

	}
		
}
