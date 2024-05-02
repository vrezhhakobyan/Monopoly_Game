package classes;

import classes.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private int currentTurn = 0;
	private List<Player> players = new ArrayList<>();
	private Square[] squares = new Square[40];

	public Board(List<Player> players) {
		this.players = players;

		for (int i = 0; i < squares.length; i++) {
			if (i == 0) {
				squares[i] = new GoSquare("GO");
			} else if (i == 9) {
				squares[i] = new JailSquare("Jail");
			} else if (i == 19) {
				squares[i] = new FreeParkingSquare("Free parking");
			} else if (i == 29) {
				squares[i] = new GoToJailSquare("Go to Jail");
			} else if (i == 2 || i == 7 || i == 17 || i == 22 || i == 33 || i == 36) {
				squares[i] = new ChanceSquare("Chance Square");
			} else {
				squares[i] = new PropertySquare("House square", 100); // Temporary line(Here I haven't created the child classes yet)
			}
		}
	}

	public Square movePlayer(Player player, int face) {
		return movePlayer(player, face, true);
	}

	public Square movePlayer(Player player, int face, boolean count) {
		if (player.isBrokeOut()) {
			return squares[player.getCurrentPosition()];
		}
		int newPosition = normalizePosition(player.getCurrentPosition() + face);
		player.setPosition(newPosition);
		System.out.println(player.getName() + " goes to " + squares[player.getCurrentPosition()].getName());
		squares[newPosition].doAction(player, this);
		if (player.getMoney().isBrokeOut()) {
			System.out.println(player.getName() + " has been broke out!");
			player.setBrokeOut(true);
		} else {
			if (count) {
				player.nextTurn();
			}
		}
		return squares[newPosition];
	}

	public boolean hasWinner() {
		int ingame = 0;
		for (Player player : players) {
			if (!player.isBrokeOut()) {
				ingame++;
			}
		}
		return ingame <= 1;
	}

	public Player getWinner() {
		if (!hasWinner()) {
			return null;
		}
		for (Player player : players) {
			if (!player.isBrokeOut()) {
				return player;
			}
		}
		return null;
	}

	public Player getMaxMoneyPlayer() {
		Player maxplayer = null;
		for (Player player : players) {
			if (maxplayer == null || maxplayer.getMoney().getMoney() < player.getMoney().getMoney()) {
				maxplayer = player;
			}
		}
		return maxplayer;
	}

	public int normalizePosition(int position) {
		return position % squares.length;
	}

	public Player getCurrentPlayer() {
		return players.get(currentTurn);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void nextTurn() {
		if (++currentTurn >= players.size()) {
			currentTurn = 0;
		}
	}

	public Player getPlayer(int id) {
		return players.get(id);
	}

	public int getTotalSquare() {
		return squares.length;
	}
}
