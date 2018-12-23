package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class MultiplayerButton extends GameObject{
	
	public MultiplayerButton(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "button_multiplayer.png"));
				
		scaleX = 1.05;
		scaleY = 1.05;
	}

}