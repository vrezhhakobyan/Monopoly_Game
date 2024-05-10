package classes;

/**
 * The TaxSquare class represents a tax square in a Monopoly game.
 * Players landing on this square pay a fixed tax amount.
 */
public class TaxSquare extends Square {

    /**
     * Constructs a TaxSquare object with the given name.
     *
     * @param name The name of the tax square
     */
    public TaxSquare(String name) {
        super(name);
    }

    /**
     * Performs the action associated with landing on a tax square.
     * Deducts a fixed tax amount from the player's money.
     *
     * @param player The player landing on the tax square
     * @param board  The game board
     */
    @Override
    public void doAction(Player player, Board board) {
        player.getMoney().subtractMoney(200); // Deduct fixed tax amount from player's money
    }
}
