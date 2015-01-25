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
	int alliesCreated = 0;
	ArrayList<Wolf> wolfArray = new ArrayList<Wolf>();
	ArrayList<Wizard> wizardArray = new ArrayList<Wizard>();
	
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
		if(elapsed % 30 >= 0 && elapsed % 30 < 1){

	    		Wolf wolf = new Wolf();
	    		wolfArray.add(wolf);
	    		wolvesCreated++;
	    		st--;
		}
		if(wolvesCreated > 0){
			for(int i = 0; i < wolfArray.size(); i++){
				Enemy1.draw(wolfArray.get(i).posX, wolfArray.get(i).posY, tileWidth, tileHeight);
				g.drawRect(wolfArray.get(i).posX - 32, wolfArray.get(i).posY - 20, tileWidth * 2, tileHeight * 2);
		}
		}
		
		g.drawString(enemy1posX + " - " + enemy1posY, 600, 50);
		Hero1.draw(heroX, heroY, tileWidth, tileHeight);
		g.drawRect(heroX - 32, heroY - 32, tileWidth * 2, tileHeight * 2);
		
		if(elapsed % 5 >= 0 && elapsed % 5 < 1 && alliesCreated < 12){
    		Wizard wizard = new Wizard();
    		wizardArray.add(wizard);
    		alliesCreated++;
    		st--;
		}
		
		if(alliesCreated > 0){
			for(int i = 0; i < wizardArray.size(); i++){
				Hero1.draw(wizardArray.get(i).posX, wizardArray.get(i).posY,tileWidth -30, tileHeight - 20);
		}	
		}
		UI.draw(0, 0, 1024, 768);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

		//Sleep timer to slow FPS to 60
		
		try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		//Make minions follow hero
		for(int i = 0; i < wizardArray.size(); i++){
			if(wizardArray.get(i).posX < (heroX - 20) - (i * 10)){
			wizardArray.get(i).posX += 0.5;
			}if(wizardArray.get(i).posX > (heroX + 40) + (i * 10)){
			wizardArray.get(i).posX -= 0.5;
			}if(wizardArray.get(i).posY < (heroY - 30) - (i * 10)){
			wizardArray.get(i).posY += 0.5;
			}if(wizardArray.get(i).posY > (heroY + 50) + (i * 10)){
			wizardArray.get(i).posY -= 0.5;
				}
		
		
		//Allies to enemy proximity attack
		for(int j = 0; j < wolfArray.size(); j++){
		if(heroX + 96 > wolfArray.get(j).posX - 32 && heroX - 32 < wolfArray.get(j).posX + 96
				&& heroY + 96 > wolfArray.get(j).posY - 20 && heroY - 32 < wolfArray.get(j).posY + 96){
			
				if(wizardArray.get(i).posX < wolfArray.get(j).posX - wizardArray.get(i).atkRange){
				wizardArray.get(i).posX++;
				}if(wizardArray.get(i).posX > wolfArray.get(j).posX + wizardArray.get(i).atkRange){
				wizardArray.get(i).posX--;
				}if(wizardArray.get(i).posY < wolfArray.get(j).posY - wizardArray.get(i).atkRange + (5 * i)){
				wizardArray.get(i).posY++;
				}if(wizardArray.get(i).posY > wolfArray.get(j).posY + wizardArray.get(i).atkRange){
				wizardArray.get(i).posY--;
				}
			}
		
		//Attacks
		if(wolfArray.get(j).posX - wizardArray.get(i).posX < wizardArray.get(i).atkRange){
			if(wolfArray.get(j).posY - wizardArray.get(i).posY < wizardArray.get(i).atkRange){
			System.out.println("Wizard Attack!!");
			}
		}
		
		if(wolfArray.get(j).posX - wizardArray.get(i).posX < wolfArray.get(j).atkRange){
			if(wolfArray.get(j).posY - wizardArray.get(i).posY < wolfArray.get(j).atkRange){
				System.out.println("Wolf attack!!");
			}
			
		}
		
		}
		}
		
		float elapsed = t.getTime() - st;
			
		//Move enemy
		if(elapsed % 2 >= 0 && elapsed % 2 < 1){
			for(int i = 0; i < wolfArray.size(); i++){
			if(wolfArray.get(i).posX < 974 && wolfArray.get(i).posX > 20 && wolfArray.get(i).posY > 100 && wolfArray.get(i).posY < 600
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
			}if(heroY < mouseY - 50){
				heroY++;
			}if(heroY > mouseY - 50){
				heroY--;
			}
		}
	}
		
		/*
		if(heroSpawnX >= enemy1posX - 100 && heroSpawnX <= enemy1posX + 50 && heroSpawnY >= enemy1posY - 50
		 && heroSpawnY <= enemy1posY + 50 && Stan.hp() >= 0){
		}
		*/
	
	
	public int getID(){
		return 1;
	}
}
