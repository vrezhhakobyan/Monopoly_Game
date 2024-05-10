package classes;

/**
 * The FreeParkingSquare class represents a Free Parking square in a Monopoly game.
 * When a player lands on this square, no action is taken.
 */
public class FreeParkingSquare extends Square {

    /**
     * Constructs a FreeParkingSquare object with the given name.
     *
     * @param name The name of the Free Parking square
     */
    public FreeParkingSquare(String name) {
        super(name);
    }

    /**
     * Performs no action when a player lands on the Free Parking square.
     *
     * @param player The player landing on the Free Parking square
     * @param board  The game board
     */
    @Override
    public void doAction(Player player, Board board) {
        System.out.println(player.getName() + " reaches to free parking");
    }
}
