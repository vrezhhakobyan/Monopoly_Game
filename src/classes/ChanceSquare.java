package classes;

public class ChanceSquare extends Square {
    public ChanceSquare(String name) {
        super(name);
    }

    @Override
    public void doAction(Player player, Board board) {
        System.out.println(player.getName() + " riches to Chance classes.Square");
    }
}
