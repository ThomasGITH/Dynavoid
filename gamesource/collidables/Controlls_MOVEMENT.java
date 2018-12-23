package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_MOVEMENT extends GameObject{
	
	public Controlls_MOVEMENT(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "movement.png"));
				
		scaleX = 0.7;
		scaleY = 0.7;
	}

}
