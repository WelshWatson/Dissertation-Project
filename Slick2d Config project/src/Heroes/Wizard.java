package Heroes;

public class Wizard extends Allies{	
		public Wizard(){
		
		atkDamage = 5;
		atkRange = 50;
		def = 3;
		hp = 15;
		posX = 155;
		posY = 255;
		
		}
		
		public int attack(int a){
			a = a - atkDamage;
			return a;
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
}