package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class DefeatLogo extends GameObject{
	
	public DefeatLogo(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "defeat.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
