package Villains;

public abstract class Mobs {

	public int atk;
	int def;
	int hp;
	int posX;
	int posY;
	
	public abstract int attack();
	public abstract int defense();
	public abstract int hp();
	public abstract int coordX();
	public abstract int coordY();

}
