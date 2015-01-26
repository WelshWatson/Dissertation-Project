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
import Heroes.Wizard;

public class Play extends BasicGameState {	

	int mouseX = 155;
	int mouseY = 255;
	int attackCounter = 0;
	int heroMovementCounter = 0;
	int allyMovementCounter = 0;
	int wolvesCreated = 0;
	int alliesCreated = 0;
	ArrayList<Wolf> wolfArray = new ArrayList<Wolf>();
	ArrayList<Wizard> wizardArray = new ArrayList<Wizard>();
	
	Timer t = new Timer();
	float st = t.getTime();
	long startTime = System.currentTimeMillis() / 1000;
	
	Allies Steve = new Wizard();

	Image Castle = null;
	Image heroFacingRight = null;
	Image Enemy1 = null;
	Image UI = null;
	Image castle = null;
	Image grass = null;
	Image allyWizard = null;
	
	Image[] wizardRight = new Image[7];
	Image[] wizardLeft = new Image[7];
	
	Image[] knightRight = new Image[7];
	Image[] knightLeft = new Image[7];
	
	boolean Enemy1Alive = true;
	boolean hero1Xrange = false;
	boolean hero1Yrange = false;
	boolean setMovement = false;
	boolean wizardMoveRight = false;
	boolean knightMoveRight = false;
	boolean gameStart = false;
	
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
		heroFacingRight = new Image("res/knight/Right0.png");
		allyWizard = new Image("res/wizard/Right0.png");
		Enemy1 = new Image("res/Enemy1.png");
		UI = new Image("res/UI.png");
				
		wizardRight[0] = new Image("res/wizard/Right0.png");
		wizardRight[1] = new Image("res/wizard/Right1.png");
		wizardRight[2] = new Image("res/wizard/Right2.png");
		wizardRight[3] = new Image("res/wizard/Right3.png");
		wizardRight[4] = new Image("res/wizard/Right4.png");
		wizardRight[5] = new Image("res/wizard/Right5.png");
		wizardRight[6] = new Image("res/wizard/Right6.png");
		
		wizardLeft[0] = new Image("res/wizard/Left0.png");
		wizardLeft[1] = new Image("res/wizard/Left1.png");
		wizardLeft[2] = new Image("res/wizard/Left2.png");
		wizardLeft[3] = new Image("res/wizard/Left3.png");
		wizardLeft[4] = new Image("res/wizard/Left4.png");
		wizardLeft[5] = new Image("res/wizard/Left5.png");
		wizardLeft[6] = new Image("res/wizard/Left6.png");

		knightRight[0] = new Image("res/knight/Right0.png");
		knightRight[1] = new Image("res/knight/Right1.png");
		knightRight[2] = new Image("res/knight/Right2.png");
		knightRight[3] = new Image("res/knight/Right3.png");
		knightRight[4] = new Image("res/knight/Right4.png");
		knightRight[5] = new Image("res/knight/Right5.png");
		knightRight[6] = new Image("res/knight/Right6.png");
		
		knightLeft[0] = new Image("res/knight/Left0.png");
		knightLeft[1] = new Image("res/knight/Left1.png");
		knightLeft[2] = new Image("res/knight/Left2.png");
		knightLeft[3] = new Image("res/knight/Left3.png");
		knightLeft[4] = new Image("res/knight/Left4.png");
		knightLeft[5] = new Image("res/knight/Left5.png");
		knightLeft[6] = new Image("res/knight/Left6.png");


	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		if(gameStart == false){
			g.drawString("Instructions will go here, but for now...", 330, 280);
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
	    		Wolf wolf = new Wolf();
	    		wolfArray.add(wolf);
	    		wolvesCreated++;
	    		st--;
		}
		
		//Draw wolves
		if(wolvesCreated > 0){
			for(int i = 0; i < wolfArray.size(); i++){
				Enemy1.draw(wolfArray.get(i).posX, wolfArray.get(i).posY, tileWidth, tileHeight);
				g.drawRect(wolfArray.get(i).posX - 32, wolfArray.get(i).posY - 20, tileWidth * 2, tileHeight * 2);
		}
		}
		

		//Draw hero
		heroFacingRight.draw(heroX, heroY, tileWidth - 20, tileHeight - 10);
		
		//Add wizard every 5 seconds
		if(elapsed % 5 >= 0 && elapsed % 5 < 1 && alliesCreated < 5){
    		Wizard wizard = new Wizard();
    		wizardArray.add(wizard);
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
		
				
		//Draw wizards
		if(alliesCreated > 0){
			for(int i = 0; i < wizardArray.size(); i++){
				allyWizard.draw(wizardArray.get(i).posX, wizardArray.get(i).posY,tileWidth -30, tileHeight - 20);
		}	
		}
		UI.draw(0, 0, 1024, 768);
	}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

		if(gameStart == true){
			
		
		float elapsed = t.getTime() - st;
		
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
			switch(allyMovementCounter){
			case 0:  allyWizard = wizardRight[0];
			case 10:  allyWizard = wizardRight[1]; break;
			case 20:  allyWizard = wizardRight[2]; break;
			case 30:  allyWizard = wizardRight[3]; break;
			case 40:  allyWizard = wizardRight[4]; break;
			case 50:  allyWizard = wizardRight[5]; break;
			case 60:  allyWizard = wizardRight[6];
					  allyMovementCounter = 0;     break;
			};
			allyMovementCounter++;
			}if(wizardArray.get(i).posX > (heroX + 40) + (i * 10)){
			wizardArray.get(i).posX -= 0.5;
			switch(allyMovementCounter){
			case 0:  allyWizard = wizardLeft[0];
			case 10:  allyWizard = wizardLeft[1]; break;
			case 20:  allyWizard = wizardLeft[2]; break;
			case 30:  allyWizard = wizardLeft[3]; break;
			case 40:  allyWizard = wizardLeft[4]; break;
			case 50:  allyWizard = wizardLeft[5]; break;
			case 60:  allyWizard = wizardLeft[6];
					  allyMovementCounter = 0; 	  break;
			};
			}if(wizardArray.get(i).posY < (heroY - 30) - (i * 10)){
			wizardArray.get(i).posY += 0.5;
			}if(wizardArray.get(i).posY > (heroY + 50) + (i * 10)){
			wizardArray.get(i).posY -= 0.5;
				}
		
		
		//Allies to enemy proximity attack
		for(int j = 0; j < wolfArray.size(); j++){
		if(heroX + 96 > wolfArray.get(j).posX - 32 && heroX - 32 < wolfArray.get(j).posX + 96
				&& heroY + 96 > wolfArray.get(j).posY - 20 && heroY - 32 < wolfArray.get(j).posY + 96){
				if(wizardArray.get(i).posX < wolfArray.get(j).posX - wizardArray.get(i).atkRange + 5){
				wizardArray.get(i).posX++;
				switch(allyMovementCounter){
				case 0:  allyWizard = wizardRight[0];
				case 10:  allyWizard = wizardRight[1]; break;
				case 20:  allyWizard = wizardRight[2]; break;
				case 30:  allyWizard = wizardRight[3]; break;
				case 40:  allyWizard = wizardRight[4];
							allyMovementCounter = 0; 
							break;
				};
				allyMovementCounter++;
				}if(wizardArray.get(i).posX > wolfArray.get(j).posX + wizardArray.get(i).atkRange - 5){
				wizardArray.get(i).posX--;
				}if(wizardArray.get(i).posY < wolfArray.get(j).posY - wizardArray.get(i).atkRange + (5 * i)){
				wizardArray.get(i).posY++;
				}if(wizardArray.get(i).posY > wolfArray.get(j).posY + wizardArray.get(i).atkRange){
				wizardArray.get(i).posY--;
				}
			}
		
		if(wolfArray.get(j).posX - wizardArray.get(i).posX < wolfArray.get(j).atkRange){
			if(wolfArray.get(j).posY - wizardArray.get(i).posY < wolfArray.get(j).atkRange){
				System.out.println("Wolf attack!!");
			}
			
		}
		
		}
		}
		
		
			
		//Move enemy
		if(elapsed % 2 >= 0 && elapsed % 2 < 1){
			for(int i = 0; i < wolfArray.size(); i++){
			if(wolfArray.get(i).posX < 974 && wolfArray.get(i).posX > 20 && wolfArray.get(i).posY > 100 && wolfArray.get(i).posY < 600
					&& hero1Yrange != true && hero1Xrange != true){
				//Insert movement here
									}
				}
		}

		
		
		//Move hero
		if(elapsed > 3){
			if(Mouse.isButtonDown(0)){
				setMovement = true;
				mouseX = Mouse.getX();
				mouseY = Math.abs(gc.getHeight() - Mouse.getY());
			}
			if(heroX < mouseX - 30){
				heroX++;
				/*
				wizardRight[0] = new Image("res/wizard/wizardStanding.png");
				wizardRight[1] = new Image("res/wizard/Wizard 1.png");
				wizardRight[2] = new Image("res/wizard/Wizard 2.png");
				wizardRight[3] = new Image("res/wizard/Wizard 3.png");
				wizardRight[4] = new Image("res/wizard/Wizard 4.png");
	*/
				
						switch(heroMovementCounter){
						case 0:  heroFacingRight = knightRight[0];
						case 10:  heroFacingRight = knightRight[1]; break;
						case 20:  heroFacingRight = knightRight[2]; break;
						case 30:  heroFacingRight = knightRight[3]; break;
						case 40:  heroFacingRight = knightRight[4]; break;
						case 50:  heroFacingRight = knightRight[5]; break;
						case 60:  heroFacingRight = knightRight[6];
									heroMovementCounter = 0; 
									break;
						};
						heroMovementCounter++;
					
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
}
	
	
	public int getID(){
		return 1;
	}
}
