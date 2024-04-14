package classes;

public class FreeParkingSquare extends Square {
    public FreeParkingSquare(String name) {
        super(name);
    }

    @Override
    public void doAction(Player player, Board board) {
        System.out.println(player.getName() + " riches to free parking");
    }
}
