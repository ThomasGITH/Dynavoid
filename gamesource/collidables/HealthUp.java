package gamesource.collidables;

import java.util.concurrent.ThreadLocalRandom;

import gamesource.main.*;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;


public class HealthUp extends GameObject
{

	public HealthUp(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "healing.gif"));
		
		scaleX = 0.19;
		scaleY = 0.19;
				
	}
	
	
	public void Update() {
		setRotation(getRotation() - 1);
		
		//ADJUST SPAWN POSITION IF INSTANTLY COLLIDING WITH THE FOLLOWING OBJECTS:
		Player player = (Player)Helper.CheckCollisionRectangle(this, Player.class);
		SpaceCity SC = (SpaceCity)Helper.CheckCollisionRectangle(this, SpaceCity.class);
		PowerDown pd = (PowerDown)Helper.CheckCollisionRectangle(this, PowerDown.class);
		PowerUpTwo pu2 = (PowerUpTwo)Helper.CheckCollisionRectangle(this, PowerUpTwo.class);
		
		if((player != null)||(SC != null)||(pd != null)||(pu2 != null)) {
			int PUX = ThreadLocalRandom.current().nextInt(0, 1145);
			int PUY = ThreadLocalRandom.current().nextInt(80, 730);
			
			x = PUX;
			y = PUY;
		}
		
	}
	
}