package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class ResetHighscore extends GameObject{
	
	public ResetHighscore(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "button_reset-highscore.png"));
				
		scaleX = 1.1;
		scaleY = 1.1;
	}

}
