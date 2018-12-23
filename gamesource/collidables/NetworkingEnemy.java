package gamesource.collidables;

import java.util.ArrayList;

import gamesource.main.*;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;


public class NetworkingEnemy extends GameObject
{
	
	public static int enemyCounter = 0;
	public static double movementCode;
	public String movementDirection;
	public static double speed = 1.5;
	private double xPrevious, yPrevious;
	private int Direction;
	public int bulletCode;
	GameObject myPlayer;
	private int fireRate = 100;
	
	AudioClip sound = new AudioClip(soundLoc + "shoot5.mp3");

	public NetworkingEnemy(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		setSprite(new Image(imageLoc + "enemySpaceShip.png"));		
		scaleX = 0.28; //0.35
		scaleY = 0.28; //0.35
		
		setRotation(180);
		
		health = 100;
	}
	
	@Override
	public void Update()
	{
		
		if(getRotation() == 90) {movementDirection = "RIGHT";}
		if(getRotation() == -90) {movementDirection = "LEFT";}
		if(getRotation() == 0) {movementDirection = "UP";}
		if(getRotation() == 180) {movementDirection = "DOWN";}
		
		ArrayList<GameObject> playerList = Helper.GetGameObjectsOfType(Player.class);
		
		//HIER DE DETECTION-HIT-BOXES VOOR HET SCHIETEN VAN DE ENEMIES
		double RL = x + 100, RR = x + 650, RU = y - 100, RD = y +100; //RECHTS
		double LR = x - 100, LL = x - 650, LU = y - 100, LD = y +100; //LINKS
		double UR = x - 100, UL = x + 100, UU = y - 650, UD = y -100; //UP
		double DR = x - 100, DL = x + 100, DU = y + 650, DD = y +100; //DOWN
		
		if(playerList.size() > 0) {
		myPlayer = playerList.get(0);
		
		//HOST PLAYER DETECTION
		if(myPlayer.x > RL){if(myPlayer.x < RR){if(myPlayer.y > RU){if(myPlayer.y < RD) {
			movementDirection = "RIGHT";
			DetectsPlayer = true;}}}}
		
		if(myPlayer.x < LR){if(myPlayer.x > LL){if(myPlayer.y > LU){if(myPlayer.y < LD) {
			movementDirection = "LEFT";
			DetectsPlayer = true;}}}}
		
		if(myPlayer.x > UR){if(myPlayer.x < UL){if(myPlayer.y > UU){if(myPlayer.y < UD) {
			movementDirection = "UP";
			DetectsPlayer = true;}}}}
		
		if(myPlayer.x > DR){if(myPlayer.x < DL){if(myPlayer.y < DU){if(myPlayer.y > DD) {
			movementDirection = "DOWN";
			DetectsPlayer = true;}}}}
		}
		
		if (DetectsPlayer == true)
		{
			//FIRE-RATE-TIMER
			// LAAT DE ENEMY SCHIETEN WANNEER HIJ EEN SPELER DETECT
			if(fireRate <= 0) {
			sound.play();
			if(movementDirection == "RIGHT") {
			EnemyLightBeam lightbeam = new EnemyLightBeam(x + 80, y + 40);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = movementDirection;
			DetectsPlayer = false;
			}
			else if(movementDirection == "LEFT") {
			EnemyLightBeam lightbeam = new EnemyLightBeam(x - 80, y + 40);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = movementDirection;
			DetectsPlayer = false;
			}
			else if(movementDirection == "UP") {
			EnemyLightBeam lightbeam = new EnemyLightBeam(x, y - 35);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = movementDirection;
			DetectsPlayer = false;
			}
			else if(movementDirection == "DOWN") {
			EnemyLightBeam lightbeam = new EnemyLightBeam(x, y + 120);
			Helper.AddGameObject(lightbeam);
			lightbeam.lastDirection = movementDirection;
			DetectsPlayer = false;
			}
			fireRate = 100;
			}else {fireRate -= 5;}
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