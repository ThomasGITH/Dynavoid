package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Logo extends GameObject{
	
	public Logo(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "dynavoid_logo.png"));
				
		scaleX = 1.2;
		scaleY = 1.2;
		
		//originele grote was 1.1, 1.1
	}

}
