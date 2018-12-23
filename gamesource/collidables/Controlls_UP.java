package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_UP extends GameObject{
	
	public Controlls_UP(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "KandMouse/Light/Keyboard_White_Arrow_Up.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
