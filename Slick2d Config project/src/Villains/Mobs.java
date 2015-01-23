package Villains;

public abstract class Mobs {

	public int atk;
	public int def;
	public int hp;
	public float posX;
	public float posY;

	
	public abstract int attack();
	public abstract int defense();
	public abstract int hp();
	public abstract float coordX();
	public abstract float coordY();
	public abstract float[] createMob();

}
