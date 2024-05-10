package classes;

/**
 * The Square class represents a square on the Monopoly game board.
 * Each square can have specific actions associated with it.
 */
public abstract class Square {
	private String name; // Name of the square

	/**
	 * Constructs a Square object with the given name.
	 *
	 * @param name The name of the square
	 */
	public Square(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the name of the square.
	 *
	 * @return The name of the square
	 */
	public String getName() {
		return name;
	}

	/**
	 * Performs the action associated with landing on this square.
	 *
	 * @param player The player landing on the square
	 * @param board  The game board
	 */
	public abstract void doAction(Player player, Board board);
}
