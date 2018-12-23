package gamesource.collidables;

import java.util.concurrent.ThreadLocalRandom;

import gamesource.main.*;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;


public class PowerDown extends GameObject
{

	public PowerDown(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "red_power_up.png"));
		
		scaleX = 0.12;
		scaleY = 0.12;
		
	}
	
	public void Update() {
		setRotation(getRotation() - 1);
		
		//ADJUST SPAWN POSITION IF INSTANTLY COLLIDING WITH THE FOLLOWING OBJECTS:
		Player player = (Player)Helper.CheckCollisionRectangle(this, Player.class);
		SpaceCity SC = (SpaceCity)Helper.CheckCollisionRectangle(this, SpaceCity.class);
		PowerUp pu = (PowerUp)Helper.CheckCollisionRectangle(this, PowerUp.class);
		PowerUpTwo pu2 = (PowerUpTwo)Helper.CheckCollisionRectangle(this, PowerUpTwo.class);
		
		if((player != null)||(SC != null)||(pu != null)||(pu2 != null)) {
			int PDX = ThreadLocalRandom.current().nextInt(0, 1145);
			int PDY = ThreadLocalRandom.current().nextInt(80, 730);
			
			x = PDX;
			y = PDY;
		}
		
	}
	
}