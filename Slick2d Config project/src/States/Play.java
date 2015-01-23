package States;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Villains.Wolf;
import Heroes.Allies;
import Heroes.Wizard;

public class Play extends BasicGameState {	

	int mouseX = 230;
	int mouseY = 270;
	boolean setMovement = false;
	
	int wolvesCreated = 0;
	int heroesCreated = 0;
	ArrayList<Wolf> etd = new ArrayList<Wolf>();
	ArrayList<Wizard> wiz = new ArrayList<Wizard>();
	
	Timer t = new Timer();
	float st = t.getTime();
	long startTime = System.currentTimeMillis() / 1000;
	
	Allies Steve = new Wizard();


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
	
	boolean Enemy1Alive = true;
	boolean hero1Xrange = false;
	boolean hero1Yrange = false;
	
	int heroX = 200;
	int heroY = 240;
	float enemy1posX;
	float enemy1posY;

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


		Timer.tick();
		float elapsed = t.getTime() - st;
		if(elapsed % 5 >= 0 && elapsed % 5 < 1){

	    		Wolf wolf = new Wolf();
	    		etd.add(wolf);
	    		wolvesCreated++;
			st--;
		}
		if(wolvesCreated > 0){
			for(int i = 0; i < etd.size(); i++){
				Enemy1.draw(etd.get(i).posX, etd.get(i).posY, tileWidth, tileHeight);
		}
		}
		
		g.drawString(enemy1posX + " - " + enemy1posY, 600, 50);
		Hero1.draw(heroX, heroY, tileWidth, tileHeight);
		
		
		if(elapsed % 5 >= 0 && elapsed % 5 < 1 && heroesCreated < 12){
    		Wizard wizard = new Wizard();
    		wiz.add(wizard);
    		heroesCreated++;
    		st--;
		}
		
		if(heroesCreated > 0){
			for(int i = 0; i < wiz.size(); i++){
				Hero1.draw(wiz.get(i).posX, wiz.get(i).posY,tileWidth -20, tileHeight - 20);
				System.out.println(wiz.size());
		}	
		}
		UI.draw(0, 0, 1024, 768);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

		for(int i = 0; i < wiz.size(); i++){

			if(wiz.get(i).posX < (heroX - 20) - (i * 10)){
			wiz.get(i).posX++;
			}if(wiz.get(i).posX > (heroX + 20) + (i * 10)){
			wiz.get(i).posX--;
			}	
			
			if(wiz.get(i).posY < (heroY - 30) - (i * 10)){
				wiz.get(i).posY++;
			}if(wiz.get(i).posY > (heroY + 50) + (i * 10)){
			wiz.get(i).posY--;
				}
		}
		
		float elapsed = t.getTime() - st;
			
		//Move enemy
		if(elapsed % 2 >= 0 && elapsed % 2 < 1){
			for(int i = 0; i < etd.size(); i++){
			if(etd.get(i).posX < 974 && etd.get(i).posX > 20 && etd.get(i).posY > 100 && etd.get(i).posY < 600
					&& hero1Yrange != true && hero1Xrange != true){
					//Move
									}
				}
		}

		//Move hero
		

		if(elapsed > 2){
			if(Mouse.isButtonDown(0)){
				setMovement = true;
				mouseX = Mouse.getX();
				mouseY = Math.abs(gc.getHeight() - Mouse.getY());
			}
			if(heroX < mouseX - 30){
				heroX++;
			}if(heroX > mouseX - 30){
				heroX--;
			}if(heroY < mouseY - 30){
				heroY++;
			}if(heroY > mouseY - 30){
				heroY--;
			}
		}
	}
		
		/*
		if(heroSpawnX >= enemy1posX - 100 && heroSpawnX <= enemy1posX + 50 && heroSpawnY >= enemy1posY - 50 && heroSpawnY <= enemy1posY + 50 && Stan.hp() >= 0){
		}
		*/
	
	
	public int getID(){
		return 1;
	}
}
