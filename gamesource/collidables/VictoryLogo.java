package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class VictoryLogo extends GameObject{
	
	public VictoryLogo(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "victory.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
