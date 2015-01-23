package Villains;

import States.RandomNumberGenerator;

public class Wolf extends Mobs{
	
	RandomNumberGenerator r = new RandomNumberGenerator();
	
		public Wolf(){
		
		atk = 1;
		def = 3;
		hp = 20;
		posX = r.getX();
		posY = r.getY();
		
		}
		
		public int attack(){
			return atk;
		}

		public int defense() {
			return def;
		}
		
		public int hp(){
			return hp;
		}
		
		public float coordX(){
			float x = posX;
			return x;
		}
		
		public float coordY(){
			float y = posY;
			return y;
		}
		
		public float[] createMob(){
    		float[] enemy = new float[6];
			enemy[0] = attack();
			enemy[1] = defense();
			enemy[2] = hp();
			enemy[4] = coordX();
			enemy[5] = coordY();
			return enemy;
			
		}
}