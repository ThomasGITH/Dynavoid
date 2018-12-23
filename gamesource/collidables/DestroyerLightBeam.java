package gamesource.collidables;

import java.util.ArrayList;

import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;

public class DestroyerLightBeam extends GameObject{

	public DestroyerLightBeam(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "Red_laser.png"));
	
		scaleX = 0.04;
		scaleY = 0.25;
	}
	
	private int speed = 26;
	public String lastDirection;
	
	public void Update() {
		switch(lastDirection) {
		case "LEFT" : setRotation(0); x -= speed; break;
		case "RIGHT" : setRotation(180); x += speed; break;
		case "UP" : setRotation(90); y -= speed; break;
		case "DOWN" : setRotation(-90); y += speed; break;
		}
		
		EnemyLightBeam bullet = (EnemyLightBeam)Helper.CheckCollisionRectangle(this, EnemyLightBeam.class);
		SpaceCity SC = (SpaceCity)Helper.CheckCollisionRectangle(this, SpaceCity.class);
		
		//GEEF PLAYER-INFORMATIE OP
		ArrayList<GameObject> SCList = Helper.GetGameObjectsOfType(SpaceCity.class);
		
		if(SC != null) {
			Helper.RemoveGameObject(bullet);
			SC.health -= 5;
			if(SC.health <= 0) {
			CityExplosion explosion = new CityExplosion(SC.x, SC.y);
			Helper.AddGameObject(explosion);
			Helper.RemoveGameObject(SC);
			Helper.RemoveGameObject(bullet);
			}
		}
		
		//BULLET CLEANUP WHEN OUT OF GAME-VIEW
		if(((x <= 0)||(x >= 1165))||((y <= 0)||(y >= 800))) {
			Helper.RemoveGameObject(bullet);
		}
	}

}
