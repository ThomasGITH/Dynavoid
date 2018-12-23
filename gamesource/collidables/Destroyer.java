package gamesource.collidables;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gamesource.main.*;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;


public class Destroyer extends GameObject
{
	AudioClip sound = new AudioClip(soundLoc + "shoot5.mp3");

	public static int enemyCounter = 0;
	String MovementDirection;
	int rotation;
	double speed = 1.50;
	int fireRate = 100;
	
	public int respawnTimer;
	
	public Destroyer(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "HeavyEnemy.png"));
		
		scaleX = 0.55;
		scaleY = 0.55;
		
		rotation = 180;
		MovementDirection = "DOWN";
		
		health = 100;
		
	}
	
	public double xPrevious = 0;
	public double yPrevious = 0;
	public boolean DetectsCity;
	
	public void Update() {
		
		xPrevious = x;
		yPrevious = y;
		
		if(MovementDirection == "RIGHT") {x += speed;y += 0;rotation = 90; setRotation(rotation);}
		else if(MovementDirection == "LEFT"){x -= speed; y -= 0;rotation = -90; setRotation(rotation);}
		else if(MovementDirection == "UP") {y -= speed;x -= 0;rotation = 0; setRotation(rotation);}
		else if(MovementDirection == "DOWN"){y += speed; x += 0;rotation = 180; setRotation(rotation);}
		
		ArrayList<GameObject> SCList = Helper.GetGameObjectsOfType(SpaceCity.class);
		Wall asteroid = (Wall)Helper.CheckCollisionRectangle(this, Wall.class);
		
		if(SCList.size() > 0) {
			GameObject spaceCity = SCList.get(0);
			if(y >= spaceCity.y - 10){
				if(spaceCity.x > x) {MovementDirection = "RIGHT";}
				else if(spaceCity.x < x){MovementDirection = "LEFT";}
			}
			
			if(MovementDirection == "RIGHT"){
				if(x >= 420) {
				x = xPrevious;
				y = yPrevious;
				DetectsCity = true;
				}
			}else if(MovementDirection == "LEFT"){
				if(x <= 720) {
				x = xPrevious;
				y = yPrevious;
				DetectsCity = true;
				}
			}
			
			if((DetectsCity == true)||(Helper.CheckCollisionRectangle(this, Wall.class) != null)) {
				
				if (asteroid != null)
				{
					x = xPrevious;
					y = yPrevious;
					if(fireRate <0) {
					Helper.RemoveGameObject(asteroid);
					fireRate = 100;
					}else {fireRate -= 5;}
				}
				
				if(MovementDirection == "RIGHT"){
					if(fireRate <= 0) {
					DestroyerLightBeam lightbeam = new DestroyerLightBeam(x + 95, y + 55);
					Helper.AddGameObject(lightbeam);
					lightbeam.lastDirection = MovementDirection;
					fireRate = 100;
					}else {fireRate -= 40;}
				}
				if(MovementDirection == "LEFT") {
					if(fireRate <= 0) {
					DestroyerLightBeam lightbeam = new DestroyerLightBeam(x - 45, y + 55);
					Helper.AddGameObject(lightbeam);
					lightbeam.lastDirection = MovementDirection;
					fireRate = 100;
					}else {fireRate -= 40;}
				}
			}
			
		}
				
	}
	
}