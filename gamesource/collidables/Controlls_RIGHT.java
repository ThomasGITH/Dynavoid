package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_RIGHT extends GameObject{
	
	public Controlls_RIGHT(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "KandMouse/Light/Keyboard_White_Arrow_Right.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
