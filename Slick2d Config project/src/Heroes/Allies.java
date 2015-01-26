package Heroes;

public abstract class Allies {

		public int atkDamage;
		public int atkRange;
		public int def;
		public int hp;
		public float posX;
		public float posY;

		
		public abstract int attack(int a);
		public abstract int defense();
		public abstract int hp();
		public abstract float coordX();
		public abstract float coordY();

	}
