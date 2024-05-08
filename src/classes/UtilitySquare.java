// UtilitySquare.java
package classes;

public class UtilitySquare extends Square {
    private int price;

    public UtilitySquare(String name, int price) {
        super(name);
        this.price = price;
    }

    @Override
    public void doAction(Player player, Board board) {
        // Implement the action for Utility Square if needed
    }
}
