package classes;

public class JailSquare extends Square {
	public JailSquare(String name) {
		super(name);
	}
	
	@Override
	public void doAction(Player player, Board board) {
		System.out.println(player + " riches to free parking" );
	}
}
