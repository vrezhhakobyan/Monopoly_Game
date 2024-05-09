package classes;

import javax.swing.*;

public class PropertySquare extends Square {
	private int price;
	private int owner = -1;



	public PropertySquare(String name, int price) {
		super(name);
		this.price = price;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getPrice() {
		return price;
	}



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


	private int calculateRent() {
		// Calculate rent as 70% of the property's price
		return (int) (price * 0.7); // 70% of the property's price
	}
}
