package gamesource.collidables;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gamesource.main.*;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;


public class Enemy extends GameObject
{
	public static int enemyCounter = 0;
	
	double enemyRange;
	AudioClip sound = new AudioClip(soundLoc + "shoot5.mp3");

	public Enemy(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "enemySpaceship.png"));
		
		scaleX = 0.28;
		scaleY = 0.28;
		
		setRotation(180);
		
		health = 100;
	}
	
	public double xPrevious = 0;
	public double yPrevious = 0;
	
	double speed = 1.5;
	public String MovementDirection = "DOWN";
	public int DirectionTimer = 3000;
	public int laserAmount = 1;
	GameObject myPlayer;
	GameObject otherPlayer;
	int fireRate = 100;
	public boolean isMoving;
	
	public int rotation;
	
	public void Update() {
		
		//Onthoud vorige positie
		xPrevious = x;
		yPrevious = y;
		
		//Randomize de direction
		DirectionTimer -= 25;
		
		if(DirectionTimer == 0) {
		double EnemyMov = ThreadLocalRandom.current().nextDouble(2.5, 10);
		if(EnemyMov <= 2.5) {MovementDirection = "RIGHT"; mov = 3;
		}else if ((EnemyMov >= 2.5)&&((EnemyMov < 5))){MovementDirection = "LEFT";mov = 2;}
		else if ((EnemyMov >= 5)&&((EnemyMov < 7.5))){MovementDirection = "UP";mov = 1;}
		else if ((EnemyMov >= 7.5)&&((EnemyMov <= 10))){MovementDirection = "DOWN";mov = 4;}
		
		DirectionTimer = 3000; //Resets de timer
		}
		
		//Apply de direction
		if(MovementDirection == "RIGHT") {x += speed;y += 0;rotation = 90; setRotation(rotation); }
		else if(MovementDirection == "LEFT"){x -= speed; y -= 0;rotation = -90; setRotation(rotation);}
		else if(MovementDirection == "UP") {y -= speed;x -= 0;rotation = 0; setRotation(rotation);}
		else if(MovementDirection == "DOWN"){y += speed; x += 0;rotation = 180; setRotation(rotation);}
		
		//Border-collision
		if(x >= 1145) {MovementDirection = "LEFT";}else if(x <= 0) {MovementDirection = "RIGHT";}
		if(y >= 800) {MovementDirection = "UP";}else if(y <= 30) {MovementDirection = "DOWN";}
			
			//HIER WORD DE SPELER-INFORMATIE AANGEGEVEN VANUIT DE HELPER CLASS
			ArrayList<GameObject> playerList = Helper.GetGameObjectsOfType(Player.class);
			ArrayList<GameObject> player2List = Helper.GetGameObjectsOfType(OtherPlayer.class);
			
			//HIER DE DETECTION-HIT-BOXES VOOR HET SCHIETEN VAN DE ENEMIES
			double RL = x + 100, RR = x + 650, RU = y - 100, RD = y +100; //RECHTS
			double LR = x - 100, LL = x - 650, LU = y - 100, LD = y +100; //LINKS
			double UR = x - 100, UL = x + 100, UU = y - 650, UD = y -100; //UP
			double DR = x - 100, DL = x + 100, DU = y + 650, DD = y +100; //DOWN
			
			if(playerList.size() > 0) {
			myPlayer = playerList.get(0);
			
			//HOST PLAYER DETECTION
			if(myPlayer.x > RL){if(myPlayer.x < RR){if(myPlayer.y > RU){if(myPlayer.y < RD) {
				MovementDirection = "RIGHT";
				DetectsPlayer = true;}}}}
			
			if(myPlayer.x < LR){if(myPlayer.x > LL){if(myPlayer.y > LU){if(myPlayer.y < LD) {
				MovementDirection = "LEFT";
				DetectsPlayer = true;}}}}
			
			if(myPlayer.x > UR){if(myPlayer.x < UL){if(myPlayer.y > UU){if(myPlayer.y < UD) {
				MovementDirection = "UP";
				DetectsPlayer = true;}}}}
			
			if(myPlayer.x > DR){if(myPlayer.x < DL){if(myPlayer.y < DU){if(myPlayer.y > DD) {
				MovementDirection = "DOWN";
				DetectsPlayer = true;}}}}
			}
			
			
			if(!player2List.isEmpty()) {
			otherPlayer = player2List.get(0);
			
			//CLIENT PLAYER DETECTION
			if(otherPlayer.x > RL){if(otherPlayer.x < RR){if(otherPlayer.y > RU){if(otherPlayer.y < RD) {
				MovementDirection = "RIGHT";
				DetectsPlayer = true;}}}}
			
			if(otherPlayer.x < LR){if(otherPlayer.x > LL){if(otherPlayer.y > LU){if(otherPlayer.y < LD) {
				MovementDirection = "LEFT";
				DetectsPlayer = true;}}}}
			
			if(otherPlayer.x > UR){if(otherPlayer.x < UL){if(otherPlayer.y > UU){if(otherPlayer.y < UD) {
				MovementDirection = "UP";
				DetectsPlayer = true;}}}}
			
			if(otherPlayer.x > DR){if(otherPlayer.x < DL){if(otherPlayer.y < DU){if(otherPlayer.y > DD) {
				MovementDirection = "DOWN";
				DetectsPlayer = true;}}}}
			}
			
			
		
		Wall rots = (Wall)Helper.CheckCollisionRectangle(this, Wall.class);

		// CHECK COLLISION WITH WALLS:
		if ((Helper.CheckCollisionRectangle(this, Wall.class) != null)||(DetectsPlayer == true))
		{
			// SET TO PREVIOUS POSITION WHEN A WALL IS HIT:
			x = xPrevious;
			y = yPrevious;
			if(fireRate <= 0) {

			if (rots != null)
			{
				Helper.RemoveGameObject(rots);
				fireRate = 100;
			}
			}
			//FIRE-RATE-TIMER
			// LAAT DE ENEMY SCHIETEN WANNEER HIJ EEN SPELER DETECT
			if(fireRate <= 0) {
			sound.play();
			if(MovementDirection == "RIGHT") {
			EnemyLightBeam lightbeam = new EnemyLightBeam(x + 80, y + 40);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = MovementDirection;
			DetectsPlayer = false;
			}
			else if(MovementDirection == "LEFT") {
			EnemyLightBeam lightbeam = new EnemyLightBeam(x - 80, y + 40);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = MovementDirection;
			DetectsPlayer = false;
			}
			else if(MovementDirection == "UP") {
			EnemyLightBeam lightbeam = new EnemyLightBeam(x, y - 35);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = MovementDirection;
			DetectsPlayer = false;
			}
			else if(MovementDirection == "DOWN") {
			EnemyLightBeam lightbeam = new EnemyLightBeam(x, y + 120);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = MovementDirection;
			DetectsPlayer = false;
			}
			fireRate = 100;
			}else {fireRate -= 5;}
		}
		
	}
	
	
	
}