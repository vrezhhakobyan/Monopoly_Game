import classes.*;

import java.util.Scanner;

public class Monopoly {
	public static void main(String[] args) {


		// Create two players
		Player player1 = new Player(1, "classes.Player 1");
		Player player2 = new Player(2, "classes.Player 2");

		// Create a board with two players
		Board board = new Board(2);
		board.getPlayers()[0] = player1;
		board.getPlayers()[1] = player2;

		// Create a die
		Die die = new Die();

		// Start the game loop
		while (!board.hasWinner()) {
			// classes.Player 1's turn
			takeTurn(player1, die, board);

			// Check if classes.Player 1 won
			if (board.hasWinner()) {
				break;
			}

			// classes.Player 2's turn
			takeTurn(player2, die, board);
		}

		// Print the winner
		Player winner = board.getWinner();
		System.out.println("\nGame over!");
		System.out.println(winner.getName() + " is the winner, having avoided bankruptcy!");
	}

	private static void takeTurn(Player player, Die die, Board board) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n" + player.getName() + "'s turn:");
		int face = player.tossDie(die);
		System.out.println(player.getName() + " rolled a " + face + ".");
		Square landedSquare = board.movePlayer(player, face);
		System.out.println(player.getName() + " landed on " + landedSquare.getName() + ".");
		System.out.println(player.getName() + "has " + player.getMoney());
		if (landedSquare instanceof PropertySquare) {


			promptToBuyProperty(player, (PropertySquare) landedSquare, scanner);
		}
	}

	private static void promptToBuyProperty(Player player, PropertySquare square, Scanner scanner) {
		System.out.println("Do you want to buy " + square.getName() + "? (Y/N)");
		String response = scanner.nextLine().trim().toUpperCase();
		if (response.equals("Y")) {
			int price = square.getPrice();
			if (player.getMoney().getMoney() >= price) {
				square.setOwner(player.getID());
				player.getMoney().subtractMoney(price);
				System.out.println(player.getName() + " bought " + square.getName() + " for $" + price + ".");
			} else {
				System.out.println("Insufficient funds. " + player.getName() + " cannot buy " + square.getName() + ".");
			}
		}
	}
}
