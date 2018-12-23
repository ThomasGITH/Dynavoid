package gamesource.collidables;

import gamesource.main.*;
import javafx.scene.image.*;


public class Wall extends GameObject
{
	
	public Wall(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "Asteroid.png"));
		
		scaleX = 0.12;
		scaleY = 0.12;
		
		health = 100;
	}
	
	public void Update() {
		Player player = (Player)Helper.CheckCollisionRectangle(this, Player.class);
		Wall asteroid = (Wall)Helper.CheckCollisionRectangle(this, Wall.class);
		Enemy enemy = (Enemy)Helper.CheckCollisionRectangle(this, Enemy.class);
		SpaceCity SC = (SpaceCity)Helper.CheckCollisionRectangle(this, SpaceCity.class);
		
		if((health <= 0)||(player != null)||(enemy != null)||(SC != null)) {
			Helper.RemoveGameObject(asteroid);
		}
	}
	
}