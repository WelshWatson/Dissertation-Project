package Villains;

public class Wolf extends Mobs{

		
		private String name;
		private int atk = 1;
		private int def = 3;
		private int hp = 20;

		
		public Wolf(String n){

			name = n;
			String type = "Wolf";

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