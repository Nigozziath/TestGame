package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Game;

class GameTest {
	
	/**
	 * A test that tries to spawn a lot of enemies then tests the game over
	 */
	@Test
	void enemySpawnTest() {
		
		Game game = new Game();
		
		// make spawn 3 waves of 50 enemies to kill the player
		for (int j = 0; j < 3; j++) {
			for (int i = 0 ; i < 50 ; i++)
				
				//Spawn an enemy
				game.getEnemySpawner().spawnEnemy();
				
				 try {
					Thread.sleep(500); // sleep 0.5 second between each wave
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		try {
			Thread.sleep(3000); // wait for 3 seconds
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (game.isGameLoopRunning())
			fail("the player is still alive");
	}

	
	/**
	 * A test that tries to start 5 games at the same time
	 */
	@Test
	void multipleGamesTest() {
		
		// start 5 games at the same time
		for (int i = 0 ; i < 5 ; i++) {
			new Game();
		}
		
		try {
			Thread.sleep(5 * 1000); // wait for 5 seconds
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
