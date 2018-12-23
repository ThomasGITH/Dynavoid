package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_A extends GameObject{
	
	public Controlls_A(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "KandMouse/Light/Keyboard_White_A.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
