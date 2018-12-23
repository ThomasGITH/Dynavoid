package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_LEFT extends GameObject{
	
	public Controlls_LEFT(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "KandMouse/Light/Keyboard_White_Arrow_Left.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
