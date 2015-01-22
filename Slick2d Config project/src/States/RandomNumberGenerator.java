package States;

import java.util.Random;

public class RandomNumberGenerator {

	public int getX(){
		
		Random r = new Random();
		int a = r.nextInt(750) + 250;
		
		return a;
		
	}
	
	public int getY(){
		
		Random r = new Random();
		int a = r.nextInt(700);
		
		return a;
	}
}
