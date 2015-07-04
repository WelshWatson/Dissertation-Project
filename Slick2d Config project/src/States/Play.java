package States;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Villains.Wolf;
import Heroes.Allies;
import Heroes.Knight;
import Heroes.Wizard;

public class Play extends BasicGameState {	

	int mouseX = 155;
	int mouseY = 255;
	int attackCounter = 0;
	int heroMovementCounter = 0;
	int wolvesCreated = 0;
	int alliesCreated = 0;
	ArrayList<Wolf> wolfArray = new ArrayList<Wolf>();
	ArrayList<Wizard> wizardArray = new ArrayList<Wizard>();
	ArrayList<Knight> knightArray = new ArrayList<Knight>();
	
	Timer t = new Timer();
	float st = t.getTime();
	long startTime = System.currentTimeMillis() / 1000;
	
	Image Castle = null;
	Image heroFacingRight = null;
	Image Enemy1 = null;
	Image UI = null;
	Image castle = null;
	Image grass = null;
	
	Image allyWizard = null;
	Image wizardRight = null;
	Image wizardLeft = null;
	Image wizardUp = null;
	Image wizardDown = null;
	
	Image allyKnight = null;
	Image knightRight = null;
	Image knightLeft = null;
	Image knightUp = null;
	Image knightDown = null;
	
	Image instructions = null;

	boolean Enemy1Alive = true;
	boolean hero1Xrange = false;
	boolean hero1Yrange = false;
	boolean setMovement = false;
	boolean wizardMoveRight = false;
	boolean knightMoveRight = false;
	boolean gameStart = false;
	boolean allyAttack = false;
	
	int heroX = 100;
	int heroY = 120;
	float enemy1posX;
	float enemy1posY;

	int tileWidth = 64;
	int tileHeight = 64;
	
	public Play(int State){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

		castle = new Image("res/newCastle.png");
		grass = new Image("res/GrassPic.png");
		heroFacingRight = new Image("res/HeroImage.png");
		Enemy1 = new Image("res/Enemy1.png");
		UI = new Image("res/UI.png");

		allyWizard = new Image("res/wizard/Down.png");
		
		wizardRight = new Image("res/wizard/Right.png");
		wizardLeft = new Image("res/wizard/Left.png");
		wizardUp = new Image("res/wizard/Up.png");
		wizardDown = new Image("res/wizard/Down.png");

		knightRight = new Image("res/knight/Right.png");
		knightLeft = new Image("res/knight/Left.png");
		knightUp = new Image("res/knight/Up.png");
		knightDown = new Image("res/knight/Down.png");
		
		instructions = new Image("res/Instructions.png");

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		if(gameStart == false){
			g.drawImage(instructions, 0, 0);
		g.drawString("Press the spacebar to begin!", 350, 300);
		
		Input Input = gc.getInput();
		if(Input.isKeyPressed(org.newdawn.slick.Input.KEY_SPACE)){
			gameStart = true;
		}
		}
		
		if(gameStart == true){
		grass.draw(0, 0, Game.screenWidth, Game.screenHeight);
		castle.draw(25, 50, 220, 220);
		g.drawString(mouseX + " - " + mouseY, 10, 300);

		Timer.tick();
		float elapsed = t.getTime() - st;
		
		//Add wolf every 30 seconds
		if(elapsed % 30 >= 0 && elapsed % 30 < 1){
			System.out.println("add wolf");
	    		Wolf wolf = new Wolf();
	    		wolfArray.add(wolf);
	    		wolvesCreated++;
	    		st--;
		}
		
		//Draw wolves
		 	if(wolvesCreated > 0){
			System.out.println("Draw wolf");
			for(int i = 0; i < wolfArray.size(); i++){
				Enemy1.draw(wolfArray.get(i).posX, wolfArray.get(i).posY, tileWidth, tileHeight);
				g.drawRect(wolfArray.get(i).posX - 32, wolfArray.get(i).posY - 20, tileWidth * 2, tileHeight * 2);
			}
		}
		
		//Draw wizards
		if(alliesCreated > 0){
			for(int i = 0; i < wizardArray.size(); i++){
				wizardArray.get(i).getImage().draw(wizardArray.get(i).posX, wizardArray.get(i).posY,tileWidth, tileHeight);
			}	
		}
		
		//Draw knight
		if(alliesCreated > 0){
			for(int i = 0; i < knightArray.size(); i++){
				knightArray.get(i).getImage().draw(knightArray.get(i).posX, knightArray.get(i).posY,tileWidth, tileHeight);
			}	
		}
		

		//Draw hero
		heroFacingRight.draw(heroX, heroY, tileWidth, tileHeight);
		g.drawRect(heroX - 32, heroY - 32, tileWidth * 2, tileHeight * 2);
		
		
		//Add wizard every 5 seconds
		if(elapsed % 10 >= 0 && elapsed % 12 < 1 && alliesCreated < 2){
			System.out.println("add wizard");
    		Wizard wizard = new Wizard();
    		wizard.setImage(wizardDown);
    		wizardArray.add(wizard);
    		alliesCreated++;
    		st--;
		}
		
		//Add knight every 5 seconds
		if(elapsed % 7 >= 0 && elapsed % 7 < 1 && alliesCreated < 1){
			System.out.println("add wizard");
    		Knight knight = new Knight();
    		knight.setImage(knightDown);
    		knightArray.add(knight);
    		alliesCreated++;
    		st--;
		}
		
		
		//Wizard attacks
		for(int i = 0; i < wizardArray.size(); i++){
			for(int j = 0; j < wolfArray.size(); j++){
				if(wolfArray.get(j).posX - wizardArray.get(i).posX < wizardArray.get(i).atkRange){
					if(wolfArray.get(j).posY - wizardArray.get(i).posY < wizardArray.get(i).atkRange){
						if(elapsed % 4 >= 0 && elapsed % 4 < 1){
					System.out.println("Wizard Attack!!" + attackCounter);
					st--;
						}
					}
						}
					}
				}
		
				

		UI.draw(0, 0, 1024, 768);
	}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

		float xDifference = 0;
		float yDifference = 0;
		float distance = 0;
		float nearest = 0;

		/*
		if(wolvesCreated > 0){
		if(wizardArray.get(0).coordX() < wolfArray.get(0).coordX()){
			nearest += wolfArray.get(0).coordX() - wizardArray.get(0).coordX();
		}else if(wizardArray.get(0).coordX() > wolfArray.get(0).coordX()){
			nearest += wizardArray.get(0).coordX() - wolfArray.get(0).coordX();
	}
		// a becomes X distance
		if(wizardArray.get(0).coordY() < wolfArray.get(0).coordY()){
			nearest += wolfArray.get(0).coordY() - wizardArray.get(0).coordY();
		}else if(wizardArray.get(0).coordY() > wolfArray.get(0).coordY()){
			nearest += wizardArray.get(0).coordY() - wolfArray.get(0).coordY();
	}
		}
		
		
		for(int w = 0; w < wizardArray.size(); w++){
			for(int e = 0; e < wolfArray.size(); e++){
				
				if(wizardArray.get(w).coordX() < wolfArray.get(e).coordX()){
					xDifference = wolfArray.get(e).coordX() - wizardArray.get(w).coordX();
				}else if(wizardArray.get(w).coordX() > wolfArray.get(e).coordX()){
					xDifference = wizardArray.get(w).coordX() - wolfArray.get(e).coordX();
			}
				// a becomes X distance
				if(wizardArray.get(w).coordY() < wolfArray.get(e).coordY()){
					yDifference = wolfArray.get(e).coordY() - wizardArray.get(w).coordY();
				}else if(wizardArray.get(w).coordY() > wolfArray.get(e).coordY()){
					yDifference = wizardArray.get(w).coordY() - wolfArray.get(e).coordY();
			}// b becomes Y distance
				distance = xDifference + yDifference;
				
				if(distance < nearest){
					nearest = distance;
				}
							}
		}
		*/
		if(gameStart == true){
			
		
		float elapsed = t.getTime() - st;
		
		//Sleep timer to slow FPS to 60
		try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		//Make minions follow hero		
		for(int i = 0; i < knightArray.size(); i++){
			if(allyAttack == false){
		if(knightArray.get(i).posX < (heroX) 
				&& knightArray.get(i).posY < heroY + 100
				&& knightArray.get(i).posY > heroY - 100){
			knightArray.get(i).setImage(knightRight);
		}
		if(knightArray.get(i).posX > (heroX) 
				&& knightArray.get(i).posY < heroY + 100
				&& knightArray.get(i).posY > heroY - 100){
			knightArray.get(i).setImage(knightLeft);
		}
		if(knightArray.get(i).posY < (heroY) 
				&& knightArray.get(i).posX < heroX + 50
				&& knightArray.get(i).posX > heroX - 50){
			knightArray.get(i).setImage(knightDown);
		}
		if(knightArray.get(i).posY > (heroY) 
				&& knightArray.get(i).posX < heroX + 50
				&& knightArray.get(i).posX > heroX - 50){
			knightArray.get(i).setImage(knightUp);
		}
		
		if(knightArray.get(i).posX < (heroX - 20) - (i * 10)){
			knightArray.get(i).posX += 0.8;
						
			}if(knightArray.get(i).posX > (heroX + 40) + (i * 10)){
				knightArray.get(i).posX -= 0.8;
			
			}if(knightArray.get(i).posY < (heroY - 30) - (i * 10)){
				knightArray.get(i).posY += 0.8;
			
			}if(knightArray.get(i).posY > (heroY + 50) + (i * 10)){
				knightArray.get(i).posY -= 0.8;
				}
		}
		}
		
		
		for(int i = 0; i < wizardArray.size(); i++){
			if(allyAttack == false){
			if(wizardArray.get(i).posX < (heroX) 
					&& wizardArray.get(i).posY < heroY + 100
					&& wizardArray.get(i).posY > heroY - 100){
				wizardArray.get(i).setImage(wizardRight);
			}
			if(wizardArray.get(i).posX > (heroX) 
					&& wizardArray.get(i).posY < heroY + 100
					&& wizardArray.get(i).posY > heroY - 100){
				wizardArray.get(i).setImage(wizardLeft);
			}
			if(wizardArray.get(i).posY < (heroY) 
					&& wizardArray.get(i).posX < heroX + 50
					&& wizardArray.get(i).posX > heroX - 50){
				wizardArray.get(i).setImage(wizardDown);
			}
			if(wizardArray.get(i).posY > (heroY) 
					&& wizardArray.get(i).posX < heroX + 50
					&& wizardArray.get(i).posX > heroX - 50){
				wizardArray.get(i).setImage(wizardUp);
			}
			
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
			
			//Hero proximity
		for(int j = 0; j < wolfArray.size(); j++){
		if(heroX + 96 > wolfArray.get(j).posX - 32 && heroX - 32 < wolfArray.get(j).posX + 96
				&& heroY + 96 > wolfArray.get(j).posY - 20 && heroY - 32 < wolfArray.get(j).posY + 96){
			
			
			
			 
				if(wizardArray.get(i).posX < wolfArray.get(j).posX - wizardArray.get(i).atkRange + 5){
				wizardArray.get(i).posX++;
				wizardArray.get(i).setImage(wizardRight);
				
				}if(wizardArray.get(i).posX > wolfArray.get(j).posX + wizardArray.get(i).atkRange - 5){
				wizardArray.get(i).posX--;
				wizardArray.get(i).setImage(wizardLeft);

				}if(wizardArray.get(i).posY < wolfArray.get(j).posY - wizardArray.get(i).atkRange + (5 * i)){
				wizardArray.get(i).posY++;
				wizardArray.get(i).setImage(wizardUp);

				}if(wizardArray.get(i).posY > wolfArray.get(j).posY + wizardArray.get(i).atkRange){
				wizardArray.get(i).posY--;
				wizardArray.get(i).setImage(wizardDown);

				}
			
			}
		
		//Target nearest enemy
		
		
		
		if(wolfArray.get(j).posX - wizardArray.get(i).posX < wolfArray.get(j).atkRange){
			if(wolfArray.get(j).posY - wizardArray.get(i).posY < wolfArray.get(j).atkRange){
				System.out.println("Wolf attack!!");
			}
			
		}
		
		}
		}
		
		}
			
		//Move enemy
		if(elapsed % 2 >= 0 && elapsed % 2 < 1){
			for(int x = 0; x < wolfArray.size(); x++){
			if(wolfArray.get(x).posX < 974 && wolfArray.get(x).posX > 20 && wolfArray.get(x).posY > 100 && wolfArray.get(x).posY < 600
					&& hero1Yrange != true && hero1Xrange != true){
				//Insert movement here
									}
				}
		}

		
		
		//Move hero
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
		
		
	
		
		/*
		if(heroSpawnX >= enemy1posX - 100 && heroSpawnX <= enemy1posX + 50 && heroSpawnY >= enemy1posY - 50
		 && heroSpawnY <= enemy1posY + 50 && Stan.hp() >= 0){
		}
		*/
}
	
	
	public int getID(){
		return 1;
	}
}
