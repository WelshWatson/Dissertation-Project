package States;

import java.util.Random;

public class RandomNumberGenerator {

	public float getX(){
		
		Random r = new Random();
		float a = r.nextInt(750) + 250;
		
		return a;
		
	}
	
	public float getY(){
		
		Random r = new Random();
		float a = r.nextInt(500) + 100;
		
		return a;
	}
}
