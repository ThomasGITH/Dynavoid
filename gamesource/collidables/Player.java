package gamesource.collidables;

import java.util.ArrayList;

import gamesource.main.*;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;


public class Player extends GameObject
{
	
	public double xPrevious = 0;
	public double yPrevious = 0;
	public static int Direction = 3;
	
	AudioClip shoot = new AudioClip(soundLoc + "shoot8.wav");
	AudioClip power_up = new AudioClip(soundLoc + "power_up4.wav");
	AudioClip power_down = new AudioClip(soundLoc + "shutDown.wav");
	AudioClip health_up = new AudioClip(soundLoc + "health_up.wav");
			
	public Player(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		scaleX = 0.28; //0.35
		scaleY = 0.28; //0.35
		
		health = 100;
	}
	
	private int speed = 7;
	int PowerUpDuration = 600;
	int PowerDownDuration = 600;
	ForceField ff = null;
	public int movementCode;
	public int bulletCode;
	
	@Override
	public void Update()
	{
		// REMEMBER PREVIOUS POSITION FIRST:
		xPrevious = x;
		yPrevious = y;
		
		// THEN APPLY NEW POSITION:
		if ((Input.KEYS_PRESSED.contains("A"))||(Input.KEYS_PRESSED.contains("LEFT")))
		{
			x -= speed;
			setRotation(-90);
			Direction = 1;
			movementCode = 2;
		}
		else if ((Input.KEYS_PRESSED.contains("D"))||(Input.KEYS_PRESSED.contains("RIGHT")))
		{
			x += speed;
			setRotation(90);
			Direction = 2;
			movementCode = 3;
		}
		
		else if ((Input.KEYS_PRESSED.contains("W"))||(Input.KEYS_PRESSED.contains("UP")))
		{
			y -= speed;
			setRotation(0);
			Direction = 3;
			movementCode = 1;
		}
		else if ((Input.KEYS_PRESSED.contains("S"))||(Input.KEYS_PRESSED.contains("DOWN")))
		{
			y += speed;
			setRotation(180);
			Direction = 4;
			movementCode = 4;
		}else {
			movementCode = 0;
		}

		//SHOOT MECHANIC
		if ((Input.KEYS_RELEASED.contains("SPACE"))||(Input.MOUSE_PRESSED_LB))
		{
			shoot.play();
			bulletCode = 1;
			if(Direction == 2) {
			LightBeam lightbeam = new LightBeam(x + 80, y + 35);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = Direction;
			}
			else if(Direction == 1) {
			LightBeam lightbeam = new LightBeam(x - 80, y + 35);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = Direction;
			}
			else if(Direction == 3) {
			LightBeam lightbeam = new LightBeam(x, y - 35);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = Direction;
			}
			else if(Direction == 4) {
			LightBeam lightbeam = new LightBeam(x, y + 120);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = Direction;
			}
			Input.MOUSE_PRESSED_LB = false; //PREVENT BULLET SPAMM
		}
		
		else
		{
			bulletCode = 0;
			// CONDITIONAL ASSIGNMENT, WORKS AS FOLLOWS.
			// VALUE = CONDITION (if-statement) ? VALUE WHEN TRUE : VALUE WHEN FALSE;
			scaleX -= scaleX > 0.35 ? 0.01 : 0;
			scaleY -= scaleY > 0.35 ? 0.01 : 0;
		}
		
		if ((Input.KEYS_RELEASED.contains("M"))){
			ArrayList<GameObject> backgroundList = Helper.GetGameObjectsOfType(BackGround.class);
			BackGround BG = (BackGround)Helper.CheckCollisionRectangle(this, BackGround.class);
			if(BG.music.isMute()) {
			BG.music.setMute(false);
			}else{BG.music.setMute(true);}
		}
		
		//RETURN TO MAIN MENU
		if ((Input.KEYS_RELEASED.contains("ESCAPE"))){
			GameManager.numberOfLevel = 0;
		}
		
		PowerUp pu = (PowerUp)Helper.CheckCollisionRectangle(this, PowerUp.class);
		PowerDown pd = (PowerDown)Helper.CheckCollisionRectangle(this, PowerDown.class);
		PowerUpTwo pu2 = (PowerUpTwo)Helper.CheckCollisionRectangle(this, PowerUpTwo.class);
		HealthUp hu = (HealthUp)Helper.CheckCollisionRectangle(this, HealthUp.class);
		
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
		
		// CHECK COLLISION WITH POWER-UP's AND GIVE THE PLAYER AN EFFECT:
		if (pu != null)
		{
			power_up.play();
			Helper.RemoveGameObject(pu);
			speed *= 2;
			PowerUpDuration -= 1;
		}if(PowerUpDuration < 600) {PowerUpDuration -= 1;}
		 if(PowerUpDuration <= 0) {speed /= 2; PowerUpDuration = 600;}
		 
		// CHECK COLLISION WITH POWER-UP's AND GIVE THE PLAYER AN EFFECT:
		if (pu2 != null)
		{
			Helper.RemoveGameObject(pu2);
			ff = new ForceField(x, y);
			Helper.AddGameObject(ff);
			PowerUpDuration -= 1;
		}if(PowerUpDuration < 600) {PowerUpDuration -= 1;}
		 if(PowerUpDuration <= 0) { PowerUpDuration = 600;}
		 if(ff != null){if(ff.timer == 0){Helper.RemoveGameObject(ff);}}
		 
		 //POWER DOWN'S
		if (pd != null)
		{
			power_down.play();
			Helper.RemoveGameObject(pd);
			speed = 0;
			PowerDownDuration -= 1;
		}if(PowerDownDuration < 600) {PowerDownDuration -= 1;}
		 if(PowerDownDuration <= 0) {speed = 7; PowerDownDuration = 600;}
		
		 
		 //POWER DOWN'S
		if (hu != null)
		{
			health_up.play();
			Helper.RemoveGameObject(hu);
			health += 40;
			//MAKE SURE HEALTH NEVER GOES ABOVE 100
			if(health >= 100) {
				health = 100;
			}
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