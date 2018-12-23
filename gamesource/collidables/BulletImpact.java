package gamesource.collidables;

import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BulletImpact extends GameObject{

	int duration;
	
	Media sound = new Media(soundLoc + "Explosion.wav");
	MediaPlayer MP = new MediaPlayer(sound);
	
	public BulletImpact(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "hit.gif"));
		
		x += 37;
		y += 75;
		
		scaleX = 0.20;
		scaleY = 0.20;
		
		duration = 50;
		
		MP.setVolume(0.3);
		MP.play();
	}
	
	public void Update() {
		BulletImpact poof = (BulletImpact)Helper.CheckCollisionRectangle(this, BulletImpact.class);
		
		duration -= 2.5;
		
		if(duration <= 0) {
			Helper.RemoveGameObject(poof);
		}
		
	}

}
