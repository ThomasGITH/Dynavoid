package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class Controlls_W extends GameObject{
	
	public Controlls_W(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "KandMouse/Light/Keyboard_White_W.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
