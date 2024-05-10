package classes;

/**
 * The GoToJailSquare class represents the Go To Jail square in a Monopoly game.
 * When a player lands on this square, they are immediately sent to jail.
 */
public class GoToJailSquare extends Square {

	/**
	 * Constructs a GoToJailSquare object with the given name.
	 *
	 * @param name The name of the Go To Jail square
	 */
	public GoToJailSquare(String name) {
		super(name);
	}

	/**
	 * Sends the player to jail when they land on the Go To Jail square.
	 *
	 * @param player The player landing on the Go To Jail square
	 * @param board  The game board
	 */
	@Override
	public void doAction(Player player, Board board) {
		Info.print(player, player.getName() + " has go to Jail");
		board.movePlayer(player, -board.getTotalSquare() / 2, false); // Moves the player to the Jail square
	}
}
