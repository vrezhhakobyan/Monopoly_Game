import classes.*;
import ui.MonopolyGUI;
import javax.swing.*;
import java.awt.*;
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

			// Set initial position based on the grid mapping
			Point initialPosition = mapIndexToGrid(i);
			MonopolyGUI.playerPositionsX[i] = initialPosition.y * MonopolyGUI.SQUARE_SIZE +1;
			MonopolyGUI.playerPositionsY[i] = initialPosition.x * MonopolyGUI.SQUARE_SIZE +1;
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

	private static Point mapIndexToGrid(int index) {
		int row, col;

		if (index < 10) {
			row = 10;
			col = 10 - index;
		} else if (index < 20) {
			row = 10 - (index - 10);
			col = 0;
		} else if (index < 30) {
			row = 0;
			col = index - 20;
		} else {
			row = index - 30;
			col = 10;
		}

		return new Point(row, col);
	}



	private static void takeTurn(Player player, Die die1, Die die2, Board board) {
		MonopolyGUI.consoleTextArea.setText(""); // Clear the text area
		MonopolyGUI.consoleTextArea.append(player.getName() + "'s money: $" + player.getMoney().getMoney() + "\n");
		MonopolyGUI.consoleTextArea.append(player.getName() + "'s turn:\n");
		int face1 = player.tossDie(die1);
		int face2 = player.tossDie(die2);
		int totalFace = face1 + face2;
		MonopolyGUI.consoleTextArea.append(player.getName() + " rolled a " + face1 + " and " + face2 + " (total: " + totalFace + ").\n");

		// Move the player
		movePlayer(player, totalFace, board);

		Square landedSquare = board.getSquares()[player.getCurrentPosition()];
		MonopolyGUI.consoleTextArea.append(player.getName() + " landed on " + landedSquare.getName() + ".\n");



		if (landedSquare instanceof PropertySquare) {
			boolean choice = promptToBuyProperty(player, (PropertySquare) landedSquare);
			MonopolyGUI.consoleTextArea.append("Player chose: " + (choice ? "Yes" : "No") + "\n");
		} else if (landedSquare instanceof StationSquare) {
			boolean choice = promptToBuyStation(player, (StationSquare) landedSquare);
			MonopolyGUI.consoleTextArea.append("Player chose: " + (choice ? "Yes" : "No") + "\n");
		} else if (landedSquare instanceof UtilitySquare) {
			boolean choice = promptToBuyUtility(player, (UtilitySquare) landedSquare);
			MonopolyGUI.consoleTextArea.append("Player chose: " + (choice ? "Yes" : "No") + "\n");
		}

	}


	private static void movePlayer(Player player, int steps, Board board) {
		int currentPosition = player.getCurrentPosition();
		int totalSquares = board.getTotalSquare();
		int perimeterSquares = totalSquares;
		int newPosition = (currentPosition + steps) % perimeterSquares;

		// Ensure newPosition is within the bounds of the board
		if (newPosition < 0)
			newPosition += perimeterSquares;

		// Calculate the new position in the grid
		Point newPositionGrid = mapIndexToGrid(newPosition);

		// Update player position
		player.setPosition(newPosition);

		// Calculate new positions based on the board's dimensions
		MonopolyGUI.playerPositionsX[player.getID() - 1] = newPositionGrid.y * MonopolyGUI.SQUARE_SIZE;
		MonopolyGUI.playerPositionsY[player.getID() - 1] = newPositionGrid.x * MonopolyGUI.SQUARE_SIZE;

		MonopolyGUI.getBoardPanel().repaint(); // Repaint the board to update player positions
	}

	private static boolean promptToBuyProperty(Player player, PropertySquare square) {
		int price = square.getPrice();
		int result = JOptionPane.showConfirmDialog(null, player.getName() + " Do you want to buy " + square.getName() + " for $" + price + "?", "Buy Property", JOptionPane.YES_NO_OPTION);
		return result == JOptionPane.YES_OPTION;
	}
	private static boolean promptToBuyStation(Player player, StationSquare square) {
		int price = square.getPrice();
		int result = JOptionPane.showConfirmDialog(null, player.getName() + " Do you want to buy " + square.getName() + " for $" + price + "?", "Buy Property", JOptionPane.YES_NO_OPTION);
		return result == JOptionPane.YES_OPTION;
	}
	private static boolean promptToBuyUtility(Player player, UtilitySquare square) {
		int price = square.getPrice();
		int result = JOptionPane.showConfirmDialog(null, player.getName() + " Do you want to buy " + square.getName() + " for $" + price + "?", "Buy Property", JOptionPane.YES_NO_OPTION);
		return result == JOptionPane.YES_OPTION;
	}
}
