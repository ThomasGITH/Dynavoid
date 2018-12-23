package gamesource.collidables;

import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class DefeatBackground extends GameObject{

	int duration;
	
	AudioClip sound = new AudioClip(soundLoc + "defeat.wav");
	
	public DefeatBackground(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "stars3.jpg"));
		
		scaleX = 0.8;
		scaleY = 0.8;
		
		//stars2: 0.45, 0.45
		//stars3: 0.8, 0.8
		
		sound.play();
	}

}
