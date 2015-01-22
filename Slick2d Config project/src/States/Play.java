package States;

import java.util.Random;

import org.lwjgl.util.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Villains.Mobs;
import Villains.Wolf;
import Heroes.Wizard;

public class Play extends BasicGameState {	
	
	Timer t = new Timer();
	float st = t.getTime();

	Mobs wolf = new Wolf(); 
	
	long startTime = System.currentTimeMillis() / 1000;
	
	Heroes Steve = new Wizard("Steve");


	Image gt = null;
	Image dt = null;
	Image pathVert = null;
	Image BLPath = null;
	Image BRPath = null;
	Image TRPath = null;
	Image Path = null;
	Image Castle = null;
	Image Hero1 = null;
	Image Enemy1 = null;
	Image UI = null;
	
	boolean Hero1Visible = false;
	boolean Enemy1Alive = true;
	boolean hero1Xrange = false;
	boolean hero1Yrange = false;
	boolean spawnEnemy = false;
	
	int hero1posX = 200;
	int hero1posY = 240;
	float enemy1posX = 550;
	float enemy1posY = 550;
	
	int wolvesCreated = 0;
	
	int tileWidth = 64;
	int tileHeight = 64;
		
	int[][] grass = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				 };
	
	int[][] path = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,2,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					 };
	
	


	    	
	    	//int[][][][][] enemy = new int[Stan.attack()][Stan.defense()][Stan.hp()][Stan.coordX()][Stan.coordY()];

	
	public Play(int State){

	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		

		
		
		Tile grassTile = new GrassTile();
		Tile dirtTile = new DirtTile();
		gt = grassTile.getTile();
		dt = dirtTile.getTile();
		pathVert = new Image("res/pathVert.png");
		BLPath = new Image("res/BLPath.png");
		BRPath = new Image("res/BRPath.png");
		TRPath = new Image("res/TRPath.png");
		Path = new Image("res/tree.png");
		Castle = new Image("res/castle.png");
		Hero1 = new Image("res/Hero1.png");
		Enemy1 = new Image("res/Enemy1.png");
		UI = new Image("res/UI.png");
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		long currentTime = System.currentTimeMillis() / 1000;
		
		//Sleep timer to slow FPS to 60
			try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		//Draw grass
		for(int y = 0; y < 16; y++){
			for(int x = 0; x < 12; x++){
				if(grass[x][y] == 0){
					gt.draw(y*64, x*64, tileWidth, tileHeight);
				}
					else if(grass[x][y] == 1){
						dt.draw(y*64, x*64, tileWidth, tileHeight);
				}
			}
		}
		
		//Draw path
		for(int y = 0; y < 16; y++){
			for(int x = 0; x < 12; x++){
				if(path[x][y] == 1){
					Path.draw(y*64, x*64, tileWidth*2, tileHeight*2);
				}
				else if(path[x][y] == 2){
					pathVert.draw(y*64, x*64, tileWidth, tileHeight);
				}
				else if(path[x][y] == 3){
					BRPath.draw(y*64, x*64, tileWidth, tileHeight);
				}
				else if(path[x][y] == 4){
					BLPath.draw(y*64, x*64, tileWidth, tileHeight);
				}
				else if(path[x][y] == 5){
					TRPath.draw(y*64, x*64, tileWidth, tileHeight);
				}
			}
		}
		
		
		Castle.draw(25, 90, tileWidth * 4, tileHeight * 3);
		
		if(currentTime > startTime + 5){
			Hero1Visible = true;
		Hero1.draw(hero1posX, hero1posY, tileWidth, tileHeight);
		}
		g.drawString(hero1posX + " - " + hero1posY, 400, 50);

		Enemy1.draw(wolf.coordX(), wolf.coordY(), tileWidth, tileHeight);
		
		g.drawString(enemy1posX + " - " + enemy1posY, 600, 50);

		
		UI.draw(0, 0, 1024, 768);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		

		
		Timer.tick();
		float elapsed = t.getTime() - st;
		if(elapsed % 5 >= 0 && elapsed % 5 < 1){

			

	    	int[] enemy = new int[6];
	    	{
	    			enemy[0] = wolf.attack();
	    			enemy[1] = wolf.defense();
	    			enemy[2] = wolf.hp();
	    			enemy[4] = wolf.coordX();
	    			enemy[5] = wolf.coordY();
	    	};
			
			st--;
		}
		
			//Move enemy 1
		
		
		if(elapsed % 2 >= 0 && elapsed % 2 < 1){

			if(enemy1posX < 974 && enemy1posX > 0 && enemy1posY > 0 && enemy1posY < 720 && Enemy1Alive == true && hero1Yrange != true && hero1Xrange != true){
				enemy1posX += 0.5;
				}
		}

		
		//Move hero to enemy
		if(Hero1Visible == true){
			if(hero1posX < enemy1posX - 50){
			hero1posX += 1;
			}else if(hero1posX > enemy1posX + 50){
				hero1posX -=1;
			}else{
				hero1Xrange = true;
			}
			
			if(hero1posY < enemy1posY){
				hero1posY +=1;
			}else if(hero1posY > enemy1posY){
				hero1posY -=1;
			}else{
				hero1Yrange = true;
			}
		}
		
		/*
		if(hero1posX >= enemy1posX - 100 && hero1posX <= enemy1posX + 50 && hero1posY >= enemy1posY - 50 && hero1posY <= enemy1posY + 50 && Stan.hp() >= 0){
		}
		*/
	}
	
	public int getID(){
		return 1;
	}
}
