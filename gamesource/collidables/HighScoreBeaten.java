package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class HighScoreBeaten extends GameObject{
	
	public HighScoreBeaten(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "beatHighScore.png"));
				
		scaleX = 0.4;
		scaleY = 0.4;
	}

}
