package classes;

/**
 * The UtilitySquare class represents a utility square in a Monopoly game.
 * Players can buy utilities and pay rent if they land on utilities owned by other players.
 */
public class UtilitySquare extends Square {
    private int price; // Price of the utility
    private int owner = -1; // ID of the owner player (-1 indicates no owner)

    /**
     * Constructs a UtilitySquare object with the given name and price.
     *
     * @param name  The name of the utility square
     * @param price The price of the utility
     */
    public UtilitySquare(String name, int price) {
        super(name);
        this.price = price;
    }

    /**
     * Performs the action associated with landing on a utility square.
     * If the utility has no owner, prompts the player to buy it.
     * If the utility has an owner, calculates and pays rent.
     *
     * @param player The player landing on the utility square
     * @param board  The game board
     */
    @Override
    public void doAction(Player player, Board board) {
        if (owner < 0) {
            System.out.println(player.getName() + ", do you want to buy " + getName() + " for $" + price + "?");
        } else {
            if (owner != player.getID()) {
                int lost = price * 70 / 100; // Calculate rent as 70% of the utility's price
                Info.print(player, player.getName() + " lost $" + lost + " to " + board.getPlayer(owner).getName());
                player.getMoney().subtractMoney(lost); // Deduct rent from player's balance
                board.getPlayer(owner).getMoney().addMoney(lost); // Add rent to owner's balance
            }
        }
    }

    /**
     * Retrieves the price of the utility.
     *
     * @return The price of the utility
     */
    public int getPrice() {
        return price;
    }
}
