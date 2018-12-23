package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class StartButton extends GameObject{
	
	public StartButton(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "startButton.png"));
				
		scaleX = 1.35;
		scaleY = 1.35;
	}

}
