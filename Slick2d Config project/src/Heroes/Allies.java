package Heroes;

import org.newdawn.slick.Image;

public abstract class Allies {

		public int atkDamage;
		public int atkRange;
		public int def;
		public int hp;
		public float posX;
		public float posY;
		public Image image;


		
		public abstract int attack(int a);
		public abstract int defense();
		public abstract int hp();
		public abstract float coordX();
		public abstract float coordY();
		public abstract Image getImage();
		public abstract void setImage(Image a);

	}
