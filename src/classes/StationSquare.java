
package classes;

public class StationSquare extends Square {
    private int price;
    private int owner = -1;

    public StationSquare(String name, int price) {
        super(name);
        this.price = price;
    }

    @Override
    public void doAction(Player player, Board board) {
        if (owner < 0) {
            System.out.println(player.getName() + ", do you want to buy " + getName() + " for $" + price + "?");

        } else {
            if (owner != player.getID()) {
                int lost = price * 70 / 100;
                Info.print(player, player.getName() + " lost $" + lost + " to " + board.getPlayer(owner).getName());
                player.getMoney().subtractMoney(lost);
                board.getPlayer(owner).getMoney().addMoney(lost);
            }
        }
    }

    public int getPrice() {
        return price;
    }
}
