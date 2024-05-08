// IncomeTaxSquare.java
package classes;

public class TaxSquare extends Square {
    public TaxSquare(String name) {
        super(name);
    }

    @Override
    public void doAction(Player player, Board board) {
        player.getMoney().subtractMoney(200);
    }
}
