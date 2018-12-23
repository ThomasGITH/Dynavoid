package gamesource.collidables;

import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class BackGround extends GameObject{

	int duration;
	
	Media sound = new Media(soundLoc + "space_travel.mp3");
	public MediaPlayer music = new MediaPlayer(sound);
	
	public BackGround(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "stars3.jpg"));
		
		scaleX = 0.8;
		scaleY = 0.8;
		
		//stars2: 0.45, 0.45
		//stars3: 0.8, 0.8
		
		Duration myDuration;
        music.setStartTime(Duration.seconds(15.0));
        music.setVolume(0.7);
		music.play();
	}

}
