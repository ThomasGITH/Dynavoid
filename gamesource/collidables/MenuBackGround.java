package gamesource.collidables;

import gamesource.main.GameObject;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MenuBackGround extends GameObject{

	int duration;
	
	//Media sound = new Media("FILE:///D:/Users/tlins/eclipse-workspace/SpaceWars/src/sounds/space_travel.mp3");
	//public MediaPlayer music = new MediaPlayer(sound);  <--- wilt niet ophouden met afspelen om een of andere reden
		
	public MenuBackGround(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "stars5B.jpg"));
				
		scaleX = 1.1;
		scaleY = 1.1;
		
		//stars4: 1.1, 1.2
		//stars5B: 1.1, 1.1
		
        //music.setStartTime(Duration.seconds(1.5));
        //music.setVolume(0.7);
		//music.play();
	}

}
