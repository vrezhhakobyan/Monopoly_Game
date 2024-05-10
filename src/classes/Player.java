package classes;

/**
 * The Player class represents a player in a Monopoly game.
 */
public class Player {
	private int totalWalk = 0; // Total number of steps taken by the player
	private int position = 0; // Current position of the player on the board
	private final int id; // ID of the player
	private final String name; // Name of the player
	private boolean broke_out = false; // Flag indicating if the player is broke
	private final Money money = new Money(1000); // Money owned by the player

	/**
	 * Constructs a Player object with the specified ID and name.
	 *
	 * @param id   The ID of the player
	 * @param name The name of the player
	 */
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Retrieves the total number of steps taken by the player.
	 *
	 * @return The total number of steps taken by the player
	 */
	public int getTotalWalk() {
		return totalWalk;
	}

	/**
	 * Simulates rolling a die and returns the result.
	 *
	 * @param die The die object used for rolling
	 * @return The result of the die roll
	 */
	public int tossDie(Die die) {
		return die.getFace();
	}

	/**
	 * Retrieves the current position of the player on the board.
	 *
	 * @return The current position of the player
	 */
	public int getCurrentPosition() {
		return position;
	}

	/**
	 * Sets the current position of the player on the board.
	 *
	 * @param position The new position of the player
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Increments the total number of steps taken by the player for each turn.
	 */
	public void nextTurn() {
		totalWalk++;
	}

	/**
	 * Retrieves the name of the player.
	 *
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the Money object representing the money owned by the player.
	 *
	 * @return The Money object representing the player's money
	 */
	public Money getMoney() {
		return money;
	}

	/**
	 * Retrieves the ID of the player.
	 *
	 * @return The ID of the player
	 */
	public int getID() {
		return id;
	}

	/**
	 * Sets the broke_out flag indicating if the player is broke.
	 *
	 * @param broke_out The value of the broke_out flag
	 */
	public void setBrokeOut(boolean broke_out) {
		this.broke_out = broke_out;
	}

	/**
	 * Checks if the player is broke.
	 *
	 * @return True if the player is broke, otherwise false
	 */
	public boolean isBrokeOut() {
		return broke_out;
	}

	/**
	 * Sets the player in jail.
	 *
	 * @param isInJail True if the player is in jail, otherwise false
	 */
	public void setInJail(boolean isInJail) {
		// In progress.....
	}
}
