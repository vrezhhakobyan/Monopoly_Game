import classes.*;
import ui.MonopolyGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Monopoly extends MonopolyGUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(MonopolyGUI::new);

		// Wait for the user to select the number of players
		while (getNumPlayers() == 0) {
			try {
				Thread.sleep(100); // Sleep for a short time
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Get the number of players
		int numPlayers = getNumPlayers();

		// Initialize players
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			String playerName = JOptionPane.showInputDialog("Enter name for player " + (i + 1) + ":");
			players.add(new Player(i + 1, playerName));
		}


		// Initialize the board
		Board board = new Board(players);
		Die die1 = new Die();
		Die die2 = new Die();

		// Start the game loop
		while (!board.hasWinner()) {
			for (Player player : players) {
				takeTurn(player, die1, die2, board);
				if (board.hasWinner()) {
					break;
				}
			}
		}

		// Print the winner
		Player winner = board.getWinner();
		MonopolyGUI.consoleTextArea.append("Game over!\n" + winner.getName() + " is the winner, having avoided bankruptcy!\n");
	}

	private static void takeTurn(Player player, Die die1, Die die2, Board board) {
		MonopolyGUI.consoleTextArea.setText(""); // Clear the text area
		MonopolyGUI.consoleTextArea.append(player.getName() + "'s money: $" + player.getMoney().getMoney() + "\n");
		MonopolyGUI.consoleTextArea.append(player.getName() + "'s turn:\n");
		int face1 = player.tossDie(die1);
		int face2 = player.tossDie(die2);
		int totalFace = face1 + face2;
		MonopolyGUI.consoleTextArea.append(player.getName() + " rolled a " + face1 + " and " + face2 + " (total: " + totalFace + ").\n");
		Square landedSquare = board.movePlayer(player, totalFace);
		MonopolyGUI.consoleTextArea.append(player.getName() + " landed on " + landedSquare.getName() + ".\n");

		if (landedSquare instanceof PropertySquare) {
			boolean choice = promptToBuyProperty(player, (PropertySquare) landedSquare);
			MonopolyGUI.consoleTextArea.append("Player chose: " + (choice ? "Yes" : "No") + "\n");
		}
	}




	private static boolean promptToBuyProperty(Player player, PropertySquare square) {
		int price = square.getPrice();
		int result = JOptionPane.showConfirmDialog(null, "Do you want to buy " + square.getName() + " for $" + price + "?", "Buy Property", JOptionPane.YES_NO_OPTION);
		return result == JOptionPane.YES_OPTION;
	}
}