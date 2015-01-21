package States;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GrassTile extends Tile{

	Image grassT = null;
	
	public GrassTile() throws SlickException{
		
			grassT = new Image("res/newgrass.png");	
	}
	
	public Image getTile(){

		return grassT;
	}
}
