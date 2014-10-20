package simpleslickgame;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame
{
	Image bg = null;
	
	Image myship = null;
	
	Image myLazer0 = null;
	Image myLazer1 = null;
	Image myLazer2 = null;
	Image myLazer3 = null;
	Image myLazer4 = null;
	Image myLazer5 = null;
	Image myLazer6 = null;
	
	int lazerNum = 0;
	
	Boolean showLazer0 = false;
	Boolean showLazer1 = false;
	Boolean showLazer2 = false;
	Boolean showLazer3 = false;
	Boolean showLazer4 = false;
	Boolean showLazer5 = false;
	Boolean showLazer6 = false;
	
	float shipX = 600;
	float shipY = 600;
	
	float lazer0X = shipX - 10;
	float lazer0Y = shipY - 60;
	float lazer1X = shipX - 10;
	float lazer1Y = shipY - 60;
	float lazer2X = shipX - 10;
	float lazer2Y = shipY - 60;
	float lazer3X = shipX - 10;
	float lazer3Y = shipY - 60;
	float lazer4X = shipX - 10;
	float lazer4Y = shipY - 60;
	float lazer5X = shipX - 10;
	float lazer5Y = shipY - 60;
	float lazer6X = shipX - 10;
	float lazer6Y = shipY - 60;
	
	Image NME1 = null;
	float NME1_X = 25;
	float NME1_Y = 0;
	
	Boolean XRight = true;
	Boolean YDown = true;
	Boolean fire = false;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	//Initialise images etc
	public void init(GameContainer gc) throws SlickException {
	bg = new Image("stars.png");
	myship = new Image("myship.png");
	
	myLazer0 = new Image("myLazer.png");
	myLazer1 = new Image("myLazer.png");
	myLazer2 = new Image("myLazer.png");
	myLazer3 = new Image("myLazer.png");
	myLazer4 = new Image("myLazer.png");
	myLazer5 = new Image("myLazer.png");
	myLazer6 = new Image("myLazer.png");

	NME1 = new Image("ship.png");
	
	}

	//Animation and event handling
	public void update(GameContainer gc, int i) throws SlickException {
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			//
			e.printStackTrace();
		}
		System.out.println(fire);

		//Key listener??
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_W))
		{
			shipY -= 1;
		}
		if(input.isKeyDown(Input.KEY_S))
		{
			shipY += 1;
		}
		if(input.isKeyDown(Input.KEY_A))
		{
			shipX -= 1;
		}
		if(input.isKeyDown(Input.KEY_D))
		{
			shipX += 1;
		}
		if(input.isKeyPressed(Input.KEY_SPACE))
		{
			System.out.println(lazerNum);
			fire = true;
			if(lazerNum >= 6){
				lazerNum = 0;
			}else
			{
			lazerNum++;
			}
			
			//Give laser shot starting point
			if(lazerNum == 0){
			lazer0X = shipX - 10;
			lazer0Y = shipY - 60;
				}else if(lazerNum == 1){
				lazer1X = shipX - 10;
				lazer1Y = shipY - 60;
					}else if(lazerNum == 2){
					lazer2X = shipX - 10;
					lazer2Y = shipY - 60;
					}else if(lazerNum == 3){
						lazer3X = shipX - 10;
						lazer3Y = shipY - 60;
						}else if(lazerNum == 4){
							lazer4X = shipX - 10;
							lazer4Y = shipY - 60;
							}else if(lazerNum == 5){
								lazer5X = shipX - 10;
								lazer5Y = shipY - 60;
								}else if(lazerNum == 6){
									lazer6X = shipX - 10;
									lazer6Y = shipY - 60;
								}
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			System.exit(0);
		}
		

		//Enemy movement algorithm
		if(NME1_X <= 0){
			XRight = true;
			}else if (NME1_X >= 1200){
			XRight = false;
		}
		if(XRight == true){
			NME1_X += 0.7;}
			else if (XRight == false){
				NME1_X -= 0.7;
			}
		
		if(NME1_Y <= 0){
			YDown = true;
			}else if(NME1_Y >= 720){
				YDown = false;
			}
		
		if(YDown == true){
			NME1_Y+= 0.3;
			}else if(YDown == false){
				NME1_Y -= 0.3;
		}
		
		//Find which laser, give it travel speed
		if(fire == true && lazerNum == 0){
			showLazer0 = true;
			while(lazer0Y > 0){
			lazer0Y -= 2;
			}showLazer0 = false;
			
		}else if(fire == true && lazerNum == 1){
			lazer1Y -= 2;
			
			showLazer1 = true;
			
		}else if(fire == true && lazerNum == 2){
			lazer2Y -= 2;	
			showLazer2 = true;

		}else if(fire == true && lazerNum == 3){
			lazer3Y -= 2;	
			showLazer3 = true;

		}else if(fire == true && lazerNum == 4){
			lazer4Y -= 2;		
			showLazer4 = true;

		}else if(fire == true && lazerNum == 5){
			lazer5Y -= 2;
			showLazer5 = true;

		}else if(fire == true && lazerNum == 6){
			lazer6Y -= 2;
			showLazer6 = true;

		}
	}
	
	// Render images here
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
	
		bg.draw(0, 0, 1280, 720);
		myship.draw(shipX, shipY, 50, 120);
		
		//Render laser dependant on which one fired 0-6
			if(fire == true && lazerNum == 0){
				while(showLazer0 == true){
				myLazer0.draw(lazer0X, lazer0Y);
				System.out.println("myLazer0.png used");
			}
			}
			if(fire == true &&lazerNum == 1){
				myLazer1.draw(lazer1X, lazer1Y);
				System.out.println("myLazer1.png used");
			}
			if(fire == true &&lazerNum == 2){
				myLazer2.draw(lazer2X, lazer2Y);
			}
			if(fire == true &&lazerNum == 3){
				myLazer3.draw(lazer3X, lazer3Y);
			}
			if(fire == true &&lazerNum == 4){
				myLazer4.draw(lazer4X, lazer4Y);
			}
			if(fire == true &&lazerNum == 5){
				myLazer5.draw(lazer5X, lazer5Y);
			}
			if(fire == true &&lazerNum == 6){
				myLazer6.draw(lazer6X, lazer6Y);				
			}
		
		NME1.draw(NME1_X, NME1_Y, 30, 80);
		
	}


	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			
			appgc.setDisplayMode(1280, 720, false);
			appgc.setFullscreen(true);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}