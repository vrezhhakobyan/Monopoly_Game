package classes;

import java.util.Random;

/**
 * The Die class represents a six-sided die used in the game.
 * Players roll the die to determine the number of spaces they move on the board.
 */
public class Die {

	/**
	 * Retrieves the face value of the die after rolling.
	 *
	 * @return The face value of the die (an integer between 1 and 6)
	 */
	public int getFace() {
		Random random = new Random();
		return 1 + random.nextInt(6); // Generates a random number between 1 and 6
	}
}

