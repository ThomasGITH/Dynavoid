package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_SHOOT extends GameObject{
	
	public Controlls_SHOOT(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "shoot.png"));
				
		scaleX = 0.7;
		scaleY = 0.7;
	}

}
