import classes.*;
import ui.MonopolyGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		consoleTextArea.append("Game over!\n" + winner.getName() + " is the winner, having avoided bankruptcy!\n");
	}

	private static void takeTurn(Player player, Die die1, Die die2, Board board) {

		consoleTextArea.append(player.getName() + "'s turn:\n");
		int face1 = player.tossDie(die1);
		int face2 = player.tossDie(die2);
		int totalFace = face1 + face2;
		consoleTextArea.append(player.getName() + " rolled a " + face1 + " and " + face2 + " (total: " + totalFace + ").\n");
		Square landedSquare = board.movePlayer(player, totalFace);
		consoleTextArea.append(player.getName() + " landed on " + landedSquare.getName() + ".\n");

		if (landedSquare instanceof PropertySquare) {
			boolean choice = MonopolyGUI.choice;
			if (choice) {
				promptToBuyProperty(player, (PropertySquare) landedSquare);
			}
		}
	}
	private static void promptToBuyProperty(Player player, PropertySquare square) {
		int price = square.getPrice();
		if (player.getMoney().getMoney() >= price) {
			square.setOwner(player.getID());
			player.getMoney().subtractMoney(price);
			consoleTextArea.append(player.getName() + " bought " + square.getName() + " for $" + price + ".\n");
		} else {
			consoleTextArea.append("Insufficient funds. " + player.getName() + " cannot buy " + square.getName() + ".\n");
		}
	}
}
