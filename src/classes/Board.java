package classes;

import java.util.List;
public class Board {
	private final List<Player> players;
	int[] propertyPrices = {0, 60, 0, 60, 0, 200, 100, 0, 100, 120, 0, 140, 150, 140, 160, 200,180,0,180,200,0,220,0,220,240,200,260,260,150,280,0,300,300,0,320,200,0,350,0,400};

	private final Square[] squares = new Square[40];

	public Board(List<Player> players) {
		this.players = players;

		for (int i = 0; i < squares.length; i++) {
			if (i == 0) {
				squares[i] = new GoSquare("GO");
			} else if (i == 1) {
				squares[i] = new PropertySquare("Old Kent Road", propertyPrices[i]);
			} else if(i == 2 || i == 17 || i == 33){
				squares[i] = new ChanceSquare("Community Chest");
			} else if (i==3) {
				squares[i] = new PropertySquare("Whitechapel Road", propertyPrices[i]);
			} else if (i == 4) {
				squares[i] = new TaxSquare("Income Tax");
			} else if (i == 5 || i == 15 || i == 25 || i == 35) {
				squares[i] = new PropertySquare("Station Square", 200); // Station Square
			} else if (i == 6) {
				squares[i] = new PropertySquare("The Angel, Islington", propertyPrices[i]);
			} else if (i == 7 || i == 22|| i == 36) {
				squares[i] = new ChanceSquare("Chance Square");
			} else if (i == 8) {
				squares[i] = new PropertySquare("Euston Road", propertyPrices[i]);
			} else if (i == 9) {
				squares[i] = new PropertySquare("Pentonville Road", propertyPrices[i]);
			}else if (i == 10) {
				squares[i] = new JailSquare("Jail square/visitor");
			}else if (i == 11) {
				squares[i] = new PropertySquare("Pall Mall", propertyPrices[i]);
			} else if (i == 12) {
				squares[i] = new PropertySquare("Electric Company", 150); // Electric Company
			} else if (i == 13) {
				squares[i] = new PropertySquare("Whitehall", propertyPrices[i]);
			} else if (i == 14) {
				squares[i] = new PropertySquare("Northumberland Avenue", propertyPrices[i]);
			} else if (i == 16) {
				squares[i] = new PropertySquare("Bow Street", propertyPrices[i]);
			} else if (i == 18) {
				squares[i] = new PropertySquare("Marlborough Street", propertyPrices[i]);
			} else if (i == 19) {
				squares[i] = new PropertySquare("Vine Street", propertyPrices[i]);
			} else if (i == 20) {
				squares[i] = new FreeParkingSquare("Free Parking");
			}else if (i == 21) {
				squares[i] = new PropertySquare("Strand", propertyPrices[i]);
			} else if (i == 23) {
				squares[i] = new PropertySquare("Fleet Street", propertyPrices[i]);
			} else if (i == 24) {
				squares[i] = new PropertySquare("Trafalgar Square", propertyPrices[i]);
			} else if (i == 26) {
				squares[i] = new PropertySquare("Leicester Square", propertyPrices[i]);
			} else if (i == 27) {
				squares[i] = new PropertySquare("Coventry Street", propertyPrices[i]);
			} else if (i == 28) {
				squares[i] = new PropertySquare("Water Works", 150); // Water Works
			} else if (i == 29) {
				squares[i] = new PropertySquare("Piccadilly", propertyPrices[i]);
			} else if (i == 30) {
				squares[i] = new GoToJailSquare("Go to jail");
			}else if (i == 31) {
				squares[i] = new PropertySquare("Regent Street", propertyPrices[i]);
			} else if (i == 32) {
				squares[i] = new PropertySquare("Oxford Street", propertyPrices[i]);
			} else if (i == 34) {
				squares[i] = new PropertySquare("Bond Street", propertyPrices[i]);}
			else if (i == 37) {
				squares[i] = new PropertySquare("Park Lane", propertyPrices[i]);
			} else if (i == 38) {
				squares[i] = new TaxSquare("Super Tax");
			} else {
				squares[i] = new PropertySquare("Mayfair", propertyPrices[i]);
			}
		}
	}


	public Square[] getSquares() {
		return squares;
	}

	public void movePlayer(Player player, int face, boolean count) {
		if (player.isBrokeOut()) {
			return;
		}
		int newPosition = normalizePosition(player.getCurrentPosition() + face);
		player.setPosition(newPosition);
		System.out.println(player.getName() + " goes to " + squares[newPosition].getName());
		squares[newPosition].doAction(player, this);
		if (player.getMoney().isBrokeOut()) {
			System.out.println(player.getName() + " has been broke out!");
			player.setBrokeOut(true);
		} else {
			if (count) {
				player.nextTurn();
			}
		}
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
	public int normalizePosition(int position) {
		return position % squares.length;
	}

	public Player getPlayer(int id) {
		return players.get(id);
	}

	public int getTotalSquare() {
		return squares.length;
	}
}
