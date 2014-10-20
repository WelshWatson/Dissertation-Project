package States;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	public Play(int State){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString("This will be the game window", 100, 100);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
	}
	
	public int getID(){
		return 1;
	}
}
