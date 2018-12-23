package gamesource.collidables;

import java.util.ArrayList;

import gamesource.main.*;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;


public class OtherPlayer extends GameObject
{
	
	public double movementCode = 0;
	private int speed = 7;
	private double xPrevious, yPrevious;
	private int Direction;
	public int bulletCode;
	
	public OtherPlayer(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		setSprite(new Image(imageLoc + "enemySpaceShip.png"));		
		scaleX = 0.28; //0.35
		scaleY = 0.28; //0.35
		
		health = 100;
	}
	
	@Override
	public void Update()
	{
		
		// REMEMBER PREVIOUS POSITION FIRST:
		xPrevious = x;
		yPrevious = y;
		
		if(movementCode == 1) {
			y -= speed;
			setRotation(0);
			Direction = 3;
		}
		else if(movementCode == 2) {
			x -= speed;
			setRotation(-90);
			Direction = 1;
		}
		else if(movementCode == 3) {
			x += speed;
			setRotation(90);
			Direction = 2;
		}
		else if(movementCode == 4) {
			y += speed;
			setRotation(180);
			Direction = 4;
		}else if(movementCode == 0) {
			
		}
		
		if(bulletCode == 1) {
			if(Direction == 2) {
			LightBeam lightbeam = new LightBeam(x + 80, y + 35);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = Direction;
			}else if(Direction == 1) {
			LightBeam lightbeam = new LightBeam(x - 80, y + 35);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = Direction;
			}else if(Direction == 3) {
			LightBeam lightbeam = new LightBeam(x, y - 35);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = Direction;
			}else if(Direction == 4) {
			LightBeam lightbeam = new LightBeam(x, y + 40);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = Direction;
			}
		}

		
		// CHECK COLLISION WITH WALLS:
		if (Helper.CheckCollisionRectangle(this, Wall.class) != null)
		{
			// SET TO PREVIOUS POSITION WHEN A WALL IS HIT:
			x = xPrevious;
			y = yPrevious;
		}
		
		//BORDER COLLISION
		if((x <= 0)||(x >= 1165)||(y <= -30)||(y >= 740)) {
			x = xPrevious;
			y = yPrevious;
		}
	}
	
	@Override
	public void Draw(GraphicsContext gc)
	{
		
		// EXTRA SHADOW EFFECT:
		//gc.setFill(Color.rgb(0, 0, 0, 0.5));
		//gc.fillOval(x, y + (getHeight() * 0.9), getWidth(), 50 * scaleY);
		
		
		// ORIGINAL DRAWING METHOD FROM THE INHERITED GAMEOBJECT CLASS:
		super.Draw(gc);
		
	}
	
	
}