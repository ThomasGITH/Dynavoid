package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;

public class ControllsBackGround extends GameObject{
	
	public ControllsBackGround(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "stars4.jpg"));
				
		scaleX = 1.1;
		scaleY = 1.2;
		
		//stars2: 0.45, 0.45
		//stars3: 0.8, 0.8
	}

}
