package Heroes;

public class Wizard extends Allies{	
		public Wizard(){
		
		atk = 5;
		def = 3;
		hp = 15;
		posX = 200;
		posY = 240;
		
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
		
		public float[] createAlly(){
    		float[] enemy = new float[6];
			enemy[0] = attack();
			enemy[1] = defense();
			enemy[2] = hp();
			enemy[4] = coordX();
			enemy[5] = coordY();
			return enemy;
			
		}
}