package classes;

import javax.swing.*;

/**
 * The PropertySquare class represents a property square in a Monopoly game.
 * Players can buy properties and pay rent if they land on properties owned by other players.
 */
public class PropertySquare extends Square {
	private int price; // Price of the property
	private int owner = -1; // ID of the owner player (-1 indicates no owner)

	/**
	 * Constructs a PropertySquare object with the given name and price.
	 *
	 * @param name  The name of the property square
	 * @param price The price of the property
	 */
	public PropertySquare(String name, int price) {
		super(name);
		this.price = price;
	}

	/**
	 * Sets the owner of the property.
	 *
	 * @param owner The ID of the player who owns the property
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}

	/**
	 * Retrieves the price of the property.
	 *
	 * @return The price of the property
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Performs the action associated with landing on a property square.
	 * If the property has no owner, prompts the player to buy it.
	 * If the property has an owner, calculates and pays rent.
	 *
	 * @param player The player landing on the property square
	 * @param board  The game board
	 */
	@Override
	public void doAction(Player player, Board board) {
		if (owner < 0) {
			// If the property has no owner, prompt the player to buy it
			int result = JOptionPane.showConfirmDialog(null, player.getName() + ", do you want to buy " + getName() + " for $" + price + "?", "Buy Property", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				player.getMoney().subtractMoney(price); // Deduct the price from player's balance
				setOwner(player.getID()); // Set the player as the owner
				Info.print(player, player.getName() + " bought " + getName() + " for $" + price);
			}
		} else {
			// If the property has an owner, calculate and pay rent
			int rent = calculateRent();
			Player ownerPlayer = board.getPlayer(owner);
			ownerPlayer.getMoney().addMoney(rent); // Add rent to owner's balance
			player.getMoney().subtractMoney(rent); // Deduct rent from player's balance
			Info.print(player, player.getName() + " paid $" + rent + " rent to " + ownerPlayer.getName());
		}
	}

	/**
	 * Calculates the rent of the property as 70% of its price.
	 *
	 * @return The calculated rent
	 */
	private int calculateRent() {
		return (int) (price * 0.7); // 70% of the property's price
	}
}
