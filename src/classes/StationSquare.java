
package classes;

public class StationSquare extends Square {
    private int price;

    public StationSquare(String name, int price) {
        super(name);
        this.price = price;
    }

    @Override
    public void doAction(Player player, Board board) {
        // Implement the action for Station Square if needed
    }
}
