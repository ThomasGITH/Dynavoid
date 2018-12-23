package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class TryAgain extends GameObject{
	
	public TryAgain(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "tryAgain.png"));
				
		scaleX = 0.4;
		scaleY = 0.4;
	}

}
