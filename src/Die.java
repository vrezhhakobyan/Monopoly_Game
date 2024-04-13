import java.util.Random;


public class Die {

	public int getFace() {
		Random random = new Random();
		return 1+random.nextInt(6);
	}
}
