package States;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	Image playNow = null;
	Image exitGame = null;
	Image hButton = null;
	
	Boolean playNowHighlighted = false;
	Boolean exitGameHighlighted = false;
	
	public Menu(int State){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
		hButton = new Image("res/hButton.png");
		
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString("Welcome to Buckyland!", 100, 50);		
		if(playNowHighlighted == true){
			hButton.draw(100, 100);
		}
		if(exitGameHighlighted == true){
			hButton.draw(100, 200);
		}
		playNow.draw(100, 100);
		exitGame.draw(100, 200);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		//Mouse button over PlayNow
		if((posX > 100 && posX < 311) && ((posY > 209 && posY < 260))){
			playNowHighlighted = true;
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}else{
			playNowHighlighted = false;
		}
		//Mouse button over Exit Game
		if((posX > 100 && posX < 311) && ((posY > 109 && posY < 160))){
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
