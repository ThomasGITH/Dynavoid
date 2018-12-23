package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class ClientButton extends GameObject{
	
	public ClientButton(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "button_join.png"));
				
		scaleX = 1.35;
		scaleY = 1.35;
	}

}
