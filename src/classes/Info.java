package classes;

public class Info {
	public static void print(Player player, String msg) {
		System.out.println((player.getTotalWalk() + 1) +  player.getCurrentPosition() +  player.getMoney().getMoney() +  msg);
	}
}
