package classes;


public class ChanceSquare extends Square {
    private static final String[] CHANCE_CARDS = {
            "Advance to Go. Collect $200.",
            "Go back 3 spaces.",
            "Go directly to Jail.",
            "Pay poor tax of $15.",
            "Bank pays you $20.",
    };

    public ChanceSquare(String name) {
        super(name);
    }

    @Override
    public void doAction(Player player, Board board) {
        System.out.println(player.getName() + " riches to Chance classes.Square");
        int chanceNumber = (int) (Math.random() * 3) + 1; // Randomly select a chance


        switch (chanceNumber) {
            case 0: // Move player to Go square
                player.setPosition(0);
                player.getMoney().addMoney(200); // Collect $200
                break;
            case 1: // Move the player back 3 spaces
                System.out.println("Go back 3 spaces!");
                board.movePlayer(player, -3, true);
                break;
            case 2:
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