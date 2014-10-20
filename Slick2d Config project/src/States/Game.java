//Edited on laptop

package States;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	public static final String gamename = "Ham Blaster";//Game name
	//assign number to game state names
	public static final int menu = 0;
	public static final int play = 1;

	public Game(String gamename){
		super(gamename);
		//add state to the list
		this.addState(new Menu(menu));
		this.addState(new Play(play));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		//Initialising game states
		 this.getState(menu).init(gc, this);
		 this.getState(play).init(gc, this);
		 this.enterState(menu);//The first state that will appear when program run
	}
	
	public static void main(String[] args){
		AppGameContainer appgc; //Game container controls the game loop etc
		try{
			appgc = new AppGameContainer(new Game(gamename)); //Assigning a game to the GameContainer (appgc)
			appgc.setDisplayMode(640, 360, false); //Resolution - full screen boolean
			appgc.start();
		}catch(SlickException e){ //Slick has its own exception handler
			e.printStackTrace();
		}
	}
}