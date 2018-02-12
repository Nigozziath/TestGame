package main;
import java.util.Random;

/**
 * 
 * @author Romain
 *
 * represent each border of the window
 */

public enum Border {
	TOP, DOWN , LEFT, RIGHT ;
	
	/**
	 * @return a random border value
	 */
	public static Border getRandomBorder() {
		
		Border randBorder = null;
		
		switch (new Random().nextInt(4)) {
		
	        case 0:  randBorder = TOP;
	        	break;
	                 
	        case 1:  randBorder = DOWN;
	        	break;
	        
	        case 2:  randBorder = LEFT;
	        	break;
	        
	        case 3:  randBorder = RIGHT;
	        	break;
		}
		return randBorder ;
	}
}
