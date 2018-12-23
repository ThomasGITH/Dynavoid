package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class WaitingLogo extends GameObject{
	
	public WaitingLogo(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "waiting.png"));
				
		scaleX = 0.80;
		scaleY = 0.80;
	}

}
