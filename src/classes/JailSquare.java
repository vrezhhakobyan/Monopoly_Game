package classes;

/**
 * The JailSquare class represents a Jail square in a Monopoly game.
 * When a player lands on this square, no action is taken.
 */
public class JailSquare extends Square {

	/**
	 * Constructs a JailSquare object with the given name.
	 *
	 * @param name The name of the Jail square
	 */
	public JailSquare(String name) {
		super(name);
	}

	/**
	 * Performs no action when a player lands on the Jail square.
	 *
	 * @param player The player landing on the Jail square
	 * @param board  The game board
	 */
	@Override
	public void doAction(Player player, Board board) {
		System.out.println(player + " reaches to Jail");
	}
}
