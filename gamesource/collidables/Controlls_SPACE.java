package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_SPACE extends GameObject{
	
	public Controlls_SPACE(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "KandMouse/Light/Keyboard_White_SPACE.png"));
				
		scaleX = 2;
		scaleY = 2;
	}

}
