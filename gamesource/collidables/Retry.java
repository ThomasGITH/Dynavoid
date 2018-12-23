package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Retry extends GameObject{
	
	public Retry(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "button_retry.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
