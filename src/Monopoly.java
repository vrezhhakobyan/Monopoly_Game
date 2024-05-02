import classes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Monopoly {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Monopoly!");
		System.out.println("How many players will play? (2 to 6)");

		int numPlayers = scanner.nextInt();
		while (numPlayers < 2 || numPlayers > 6) {
			System.out.println("Invalid number of players. Please enter a number between 2 and 6.");
			numPlayers = scanner.nextInt();
		}

		// Initialize players
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			System.out.println("Enter name for player " + (i + 1) + ":");
			String playerName = scanner.next();
			players.add(new Player(i + 1, playerName));
		}

		// Initialize the board
		Board board = new Board(players);

		// Create a die
		Die die = new Die();

		// Start the game loop
		while (!board.hasWinner()) {
			for (Player player : players) {
				takeTurn(player, die, board, scanner);
				if (board.hasWinner()) {
					break;
				}
			}
		}

		// Print the winner
		Player winner = board.getWinner();
		System.out.println("\nGame over!");
		System.out.println(winner.getName() + " is the winner, having avoided bankruptcy!");

		// Close scanner
		scanner.close();
	}

	private static void takeTurn(Player player, Die die, Board board, Scanner scanner) {
		System.out.println("\n" + player.getName() + "'s turn:");
		int face = player.tossDie(die);
		System.out.println(player.getName() + " rolled a " + face + ".");
		Square landedSquare = board.movePlayer(player, face);
		System.out.println(player.getName() + " landed on " + landedSquare.getName() + ".");
		System.out.println(player.getName() + " has $" + player.getMoney().getMoney() + ".");

		if (landedSquare instanceof PropertySquare) {
			promptToBuyProperty(player, (PropertySquare) landedSquare, scanner);
		}
	}

	private static void promptToBuyProperty(Player player, PropertySquare square, Scanner scanner) {
		System.out.println("Do you want to buy " + square.getName() + "? (Y/N)");
		String response = scanner.next().trim().toUpperCase();
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
