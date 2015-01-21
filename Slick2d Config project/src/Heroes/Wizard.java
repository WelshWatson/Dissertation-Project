package Heroes;

import States.Heroes;

public class Wizard extends Heroes{


	
	private String name;
	private int atk = 5;
	private int def = 1;
	private int hp = 20;
	
	public Wizard(String n){

		name = n;
		String type = "wizard";

	}

	
	public int getAtk(){
		int atk = this.atk;
		return atk;
	}


	public int getDef() {
		int def = this.def;
		return def;
	}
	
	public int getHp(){
		int hp = this.hp;
		return hp;
	}
}