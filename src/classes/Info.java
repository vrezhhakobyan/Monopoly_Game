package classes;

/**
 * The Info class provides utility methods for printing information related to the game.
 */
public class Info {

	/**
	 * Prints the specified message along with player information.
	 *
	 * @param player The player whose information is to be printed
	 * @param msg    The message to be printed
	 */
	public static void print(Player player, String msg) {
		System.out.println((player.getTotalWalk() + 1) + " " + player.getCurrentPosition() + " " + player.getMoney().getMoney() + " " + msg);
	}
}
