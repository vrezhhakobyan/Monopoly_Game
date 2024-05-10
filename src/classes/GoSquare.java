package classes;

/**
 * The GoSquare class represents the Go square in a Monopoly game.
 * When a player passes or lands on this square, they collect $200.
 */
public class GoSquare extends Square {

	/**
	 * Constructs a GoSquare object with the given name.
	 *
	 * @param name The name of the Go square
	 */
	public GoSquare(String name) {
		super(name);
	}

	/**
	 * Gives $200 to the player when they land on the Go square.
	 *
	 * @param player The player landing on the Go square
	 * @param board  The game board
	 */
	@Override
	public void doAction(Player player, Board board) {
		Info.print(player, player.getName() + " is at Go... Giving 200 money");
		player.getMoney().addMoney(200);
	}
}
