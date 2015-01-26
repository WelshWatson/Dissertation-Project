package Heroes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wizard extends Allies{	
	
		public Wizard() throws SlickException{
		
		atkDamage = 5;
		atkRange = 50;
		def = 3;
		hp = 15;
		posX = 155;
		posY = 255;
		image = null;

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

		public Image getImage() {
			return this.image;
		}

		@Override
		public void setImage(Image a) {
			this.image = a;
		}
		
		
}