package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Game;
import main.InputManager;
import main.Player;

class InputManagerTest {

	/**
	 * Test that tries to simulate mouse inputs to move the player around then go back to the center
	 */
	@Test
	void inputManagerTest() {
		
		Game game = new Game();
		InputManager inputManager = game.getInputManager();
		Player player = inputManager.getPlayer();
		
		//prevent the enemy spawner to spawn enemy for avoiding game over
		game.getEnemySpawner().setTimeBetweenEachSpawn(1000000000000L); 
		
		// remove the mouse detection for simulating the wanted behavior
		game.getPanel().removeMouseListener(inputManager);
		game.getPanel().removeMouseMotionListener(inputManager);
		
		// simulate mouse left button down 
		inputManager.setMousePressed(true);
		
		// simulate mouse location in a corner
		inputManager.setMouseXPos(0);
		inputManager.setMouseYPos(0);
		
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// simulate mouse location in a corner
		inputManager.setMouseXPos(Game.windowWidth);
		inputManager.setMouseYPos(0);
		
		try {
			Thread.sleep(1750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// simulate mouse location in a corner
		inputManager.setMouseXPos(Game.windowWidth);
		inputManager.setMouseYPos(Game.windowHeight - 50);
		
		try {
			Thread.sleep(1750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// simulate mouse location in a corner
		inputManager.setMouseXPos(0);
		inputManager.setMouseYPos(Game.windowHeight - 50);
		
		try {
			Thread.sleep(1750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// simulate mouse location at the center
		inputManager.setMouseXPos(Game.windowWidth / 2);
		inputManager.setMouseYPos(Game.windowHeight / 2);
		
		
		try {
			Thread.sleep(3000); // let the time to the player to go back to the center
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Test if the player is at the center of the window
		if ((player.getxLocation() - Game.windowWidth / 2 > player.getDistanceSensibility())
				|| 
				(player.getyLocation() - Game.windowHeight / 2 > player.getDistanceSensibility())
				) {
			fail("Player not in center");
		}
	}

}
