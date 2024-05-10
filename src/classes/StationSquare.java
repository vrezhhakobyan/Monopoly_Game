package classes;

/**
 * The StationSquare class represents a station square in a Monopoly game.
 * Players can buy stations and pay rent if they land on stations owned by other players.
 */
public class StationSquare extends Square {
    private int price; // Price of the station
    private int owner = -1; // ID of the owner player (-1 indicates no owner)

    /**
     * Constructs a StationSquare object with the given name and price.
     *
     * @param name  The name of the station square
     * @param price The price of the station
     */
    public StationSquare(String name, int price) {
        super(name);
        this.price = price;
    }

    /**
     * Performs the action associated with landing on a station square.
     * If the station has no owner, prompts the player to buy it.
     * If the station has an owner, calculates and pays rent.
     *
     * @param player The player landing on the station square
     * @param board  The game board
     */
    @Override
    public void doAction(Player player, Board board) {
        if (owner < 0) {
            System.out.println(player.getName() + ", do you want to buy " + getName() + " for $" + price + "?");
        } else {
            if (owner != player.getID()) {
                int lost = price * 70 / 100; // Calculate rent as 70% of the station's price
                Info.print(player, player.getName() + " lost $" + lost + " to " + board.getPlayer(owner).getName());
                player.getMoney().subtractMoney(lost); // Deduct rent from player's balance
                board.getPlayer(owner).getMoney().addMoney(lost); // Add rent to owner's balance
            }
        }
    }

    /**
     * Retrieves the price of the station.
     *
     * @return The price of the station
     */
    public int getPrice() {
        return price;
    }
}
