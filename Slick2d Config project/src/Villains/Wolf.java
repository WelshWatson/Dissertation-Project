package Villains;

import States.RandomNumberGenerator;

public class Wolf extends Mobs{
	
	RandomNumberGenerator r = new RandomNumberGenerator();
	
		public Wolf(){
		
		atk = 1;
		def = 3;
		hp = 20;
		
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
		
		public int coordX(){
			int x = r.getX();
			return x;
		}
		
		public int coordY(){
			int y = r.getY();
			return y;
		}
}