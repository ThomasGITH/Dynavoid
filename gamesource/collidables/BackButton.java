package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class BackButton extends GameObject{
	
	public BackButton(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "back-button.png"));
				
		scaleX = 0.55;
		scaleY = 0.55;
	}

}
