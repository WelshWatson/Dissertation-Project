package States;

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
	
	long startTime = System.currentTimeMillis() / 1000;
	
	Heroes Steve = new Wizard("Steve");
	Mobs Stan = new Wolf("Stan");
	int stanHP = Stan.getHp();

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
	
	int hero1posX = 200;
	int hero1posY = 240;
	int enemy1posX = 550;
	int enemy1posY = 550;
	
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


			try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		
		long currentTime = System.currentTimeMillis() / 1000;

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
		
		//if(currentTime > startTime + 5){
			Hero1Visible = true;
		Hero1.draw(hero1posX, hero1posY, tileWidth, tileHeight);
		//}
		if(Enemy1Alive == true){
		Enemy1.draw(enemy1posX, enemy1posY, tileWidth, tileHeight);
		}
		
		UI.draw(0, 0, 1024, 768);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

		long currentTime = System.currentTimeMillis() / 1000;
		
		if(enemy1posX < 974 && enemy1posX > 0 && enemy1posY > 0 && enemy1posY < 720 && Enemy1Alive == true){
		enemy1posX ++;
		enemy1posY --;
		}
		if(Hero1Visible == true){
			if(hero1posX < enemy1posX - 50){
			hero1posX += 2;
			}else if(hero1posX > enemy1posX +50){
				hero1posX -=2;
			}else{
				hero1Xrange = true;
			}
			
			if(hero1posY < enemy1posY){
				hero1posY +=2;
			}else if(hero1posY > enemy1posY){
				hero1posY -=2;
			}else{
				hero1Yrange = true;
			}
		}
		
		if(hero1Xrange == true && hero1Yrange == true && currentTime % startTime == 5){
			stanHP -= Steve.getAtk();
			if(stanHP < 0){
				Enemy1Alive = false;
			}
		}
	}
	
	public int getID(){
		return 1;
	}


}
