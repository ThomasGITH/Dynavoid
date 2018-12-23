package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class ControllsButton extends GameObject{
	
	public ControllsButton(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "controllsButton.png"));
				
		scaleX = 1.35;
		scaleY = 1.35;
	}

}
