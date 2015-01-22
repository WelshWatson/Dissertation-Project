package States;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	Image playNow = null;
	Image exitGame = null;
	Image hButton = null;
	Image screenShot = null;
	
	Boolean playNowHighlighted = false;
	Boolean exitGameHighlighted = false;
	
	int posX;
	int posY;
	
	public Menu(int State){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
		hButton = new Image("res/hButton.png");
		screenShot = new Image("res/screenShot.png");
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		screenShot.draw(25, 25, Game.screenWidth - 50, Game.screenHeight - 50);
		g.drawString("Welcome to the Game!", 410, 200);		
		if(playNowHighlighted == true){
			hButton.draw(400, 250);
		}
		if(exitGameHighlighted == true){
			hButton.draw(400, 350);
		}
		playNow.draw(400, 250);
		exitGame.draw(400, 350);
		
		g.drawString(posX + " - " + posY, 410, 50);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		posX = Mouse.getX();
		posY = Math.abs(gc.getHeight() - Mouse.getY());
		
		
		
		//Mouse button over PlayNow
		if((posX > 400 && posX < 600) && ((posY > 250 && posY < 290))){
			playNowHighlighted = true;
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}else{
			playNowHighlighted = false;
		}
		//Mouse button over Exit Game
		if((posX > 400 && posX < 600) && ((posY > 350 && posY < 390))){
			exitGameHighlighted = true;
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}else{
			exitGameHighlighted = false;
		}
	}
	
	public int getID(){
		return 0;
	}
}
