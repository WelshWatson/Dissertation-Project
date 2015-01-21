package States;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DirtTile extends Tile{

	Image grassT = null;
	
	public DirtTile() throws SlickException{
		
			grassT = new Image("res/dirt.png");	
	}
	
	public Image getTile(){

		return grassT;
	}
}
