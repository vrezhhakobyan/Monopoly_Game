package classes;

public class PropertySquare extends Square {
	int price;
	int owner = -1;
	
	public PropertySquare(String name, int price) {
		super(name);
		this.price = price;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public int getPrice() {
		return price;
	}
	
	@Override
	public void doAction(Player player, Board board) {
		if(owner < 0){
			System.out.println(player.getName() + ", do you want to buy " + getName() + "?");

		}else{
			if(owner != player.getID()){
				int lost = price * 70 / 100;
				Info.print(player, player.getName() + " lost " + lost + " money to " + board.getPlayer(owner).getName());
				player.getMoney().subtractMoney(lost);
				board.getPlayer(owner).getMoney().addMoney(lost);
			}
		}
	}
}
