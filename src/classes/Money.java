package classes;

/**
 * The Money class represents the amount of money a player has in a Monopoly game.
 */
public class Money {
	private int money; // Amount of money

	/**
	 * Constructs a Money object with zero money.
	 */
	public Money() {
		this(0);
	}

	/**
	 * Constructs a Money object with the specified amount of money.
	 *
	 * @param money The initial amount of money
	 */
	public Money(int money) {
		this.money = money;
	}

	/**
	 * Retrieves the current amount of money.
	 *
	 * @return The current amount of money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Adds the specified amount to the current money.
	 *
	 * @param amount The amount to add
	 */
	public void addMoney(int amount) {
		money += amount;
	}

	/**
	 * Subtracts the specified amount from the current money.
	 *
	 * @param amount The amount to subtract
	 */
	public void subtractMoney(int amount) {
		money -= amount;
	}

	/**
	 * Checks if the player is broke (has less than zero money).
	 *
	 * @return True if the player is broke, otherwise false
	 */
	public boolean isBrokeOut() {
		return money < 0;
	}
}
