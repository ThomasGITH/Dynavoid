package gamesource.collidables;

import java.util.concurrent.ThreadLocalRandom;

import gamesource.main.*;
import javafx.scene.image.*;


public class PowerUpTwo extends GameObject
{

	public PowerUpTwo(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "blue_power_up.png"));
		
		scaleX = 0.12;
		scaleY = 0.12;
		
	}
	
	public void Update() {
		setRotation(getRotation() - 1);
		
		//ADJUST SPAWN POSITION IF INSTANTLY COLLIDING WITH THE FOLLOWING OBJECTS:
		Player player = (Player)Helper.CheckCollisionRectangle(this, Player.class);
		SpaceCity SC = (SpaceCity)Helper.CheckCollisionRectangle(this, SpaceCity.class);
		PowerDown pd = (PowerDown)Helper.CheckCollisionRectangle(this, PowerDown.class);
		PowerUp pu = (PowerUp)Helper.CheckCollisionRectangle(this, PowerUp.class);
		
		if((player != null)||(SC != null)||(pd != null)||(pu != null)) {
			int PUTX = ThreadLocalRandom.current().nextInt(0, 1145);
			int PUTY = ThreadLocalRandom.current().nextInt(80, 730);
			
			x = PUTX;
			y = PUTY;
		}
		
	}
	
}