package classes;

/**
 * The ChanceSquare class represents a Chance square in a Monopoly game.
 * When a player lands on this square, they draw a Chance card, which may result in various actions.
 */
public class ChanceSquare extends Square {

    private static final String[] CHANCE_CARDS = {
            "Advance to Go. Collect $200.",
            "Go back 3 spaces.",
            "Go directly to Jail.",
            "Pay poor tax of $15.",
            "Bank pays you $20.",
    };

    /**
     * Constructs a ChanceSquare object with the given name.
     *
     * @param name The name of the Chance square
     */
    public ChanceSquare(String name) {
        super(name);
    }

    /**
     * Performs the action associated with landing on a Chance square.
     * Draws a Chance card randomly and executes the corresponding action.
     *
     * @param player The player landing on the Chance square
     * @param board  The game board
     */
    @Override
    public void doAction(Player player, Board board) {
        System.out.println(player.getName() + " reaches to Chance square");
        int chanceNumber = (int) (Math.random() * CHANCE_CARDS.length); // Randomly select a chance

        switch (chanceNumber) {
            case 0: // Move player to Go square
                player.setPosition(0);
                player.getMoney().addMoney(200); // Collect $200
                break;
            case 1: // Move the player back 3 spaces
                System.out.println("Go back 3 spaces!");
                board.movePlayer(player, -3, true);
                break;
            case 2: // Go directly to Jail
                System.out.println("Go directly to Jail!");
                player.setInJail(true); // Set the player in jail
                break;
            case 3: // Pay poor tax
                player.getMoney().subtractMoney(15); // Pay $15
                break;
            case 4: // Bank pays you
                player.getMoney().addMoney(20); // Collect $20
                break;
            default:
                System.out.println("No chance action taken.");
                break;
        }
    }
}
