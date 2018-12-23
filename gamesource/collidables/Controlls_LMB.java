package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_LMB extends GameObject{
	
	public Controlls_LMB(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "KandMouse/Light/Keyboard_White_Mouse_Left.png"));
				
		scaleX = 2;
		scaleY = 2;
	}

}
