package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_MUTE extends GameObject{
	
	public Controlls_MUTE(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "mute.png"));
				
		scaleX = 0.65;
		scaleY = 0.65;
	}

}
