package classes;

public class JailSquare extends Square {
	public JailSquare(String name) {
		super(name);
	}
	
	@Override
	public void doAction(Player player, Board board) {
		System.out.println(player + " lost 500$" );
		player.getMoney().subtractMoney(500);
	}
}
