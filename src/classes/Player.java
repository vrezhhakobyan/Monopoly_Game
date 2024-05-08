package classes;

public class Player {
	int totalWalk = 0;
	int position = 0;
	int id;
	String name;
	boolean broke_out = false;
	Money money = new Money(1000);
	
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getTotalWalk() {
		return totalWalk;
	}
	
	public int tossDie(Die die) {
		return die.getFace();
	}

	public int getCurrentPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public void nextTurn() {
		totalWalk++;
	}
	
	public String getName() {
		return name;
	}
	
	public Money getMoney() {
		return money;
	}
	
	public int getID() {
		return id;
	}
	
	public void setBrokeOut(boolean broke_out) {
		this.broke_out = broke_out;
	}
	
	public boolean isBrokeOut() {
		return broke_out;
	}


}
