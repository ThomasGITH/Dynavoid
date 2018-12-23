package gamesource.collidables;

import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class CityExplosion extends GameObject{

	int duration;
	
	Media sound = new Media(soundLoc + "CityExplosion.wav");
	MediaPlayer MP = new MediaPlayer(sound);
	
	public CityExplosion(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "CityExplosion4.gif"));
		
		x -= 60;
		y -= 60;
		
		scaleX = 0.8;
		scaleY = 0.8;
		
		duration = 200;
		
		MP.play();
	}
	
	public void Update() {
		CityExplosion exp = (CityExplosion)Helper.CheckCollisionRectangle(this, CityExplosion.class);
		
		duration -= 4;
		
		if(duration <= 0) {
			Helper.RemoveGameObject(exp);
		}
	}

}
