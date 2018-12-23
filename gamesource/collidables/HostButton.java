package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class HostButton extends GameObject{
	
	public HostButton(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "button_host.png"));
				
		scaleX = 1.35;
		scaleY = 1.35;
	}

}
