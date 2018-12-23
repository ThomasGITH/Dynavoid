package gamesource.collidables;

import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Explosion extends GameObject{

	int duration;
	
	Media sound = new Media(soundLoc + "Explosion.wav");
	MediaPlayer MP = new MediaPlayer(sound);
	
	public Explosion(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "explosion.gif"));
		
		x -= 25;
		y -= 25;
		
		scaleX = 0.8;
		scaleY = 0.8;
		
		duration = 50;
		
		MP.play();
	}
	
	public void Update() {
		Explosion exp = (Explosion)Helper.CheckCollisionRectangle(this, Explosion.class);
		
		duration -= 5;
		
		if(duration <= 0) {
			Helper.RemoveGameObject(exp);
		}		
	}

}
