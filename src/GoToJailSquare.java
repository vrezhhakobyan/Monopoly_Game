public class GoToJailSquare extends Square {
	public GoToJailSquare(String name) {
		super(name);
	}
	
	@Override
	public void doAction(Player player, Board board) {
		Info.print(player, player.getName() + " has go to Jail");
		board.movePlayer(player, -board.getTotalSquare() / 2, false);
	}
}
