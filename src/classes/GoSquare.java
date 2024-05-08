package classes;

public class GoSquare extends Square {
	public GoSquare(String name) {
		super(name);
	}
	
	@Override
	public void doAction(Player player, Board board) {
		Info.print(player, player.getName() + " is at Go... Giving 200 money");
		player.getMoney().addMoney(200);
	}
}
