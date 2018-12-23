package gamesource.levels;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import gamesource.collidables.*;
import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import gamesource.main.SaveLoad;
import javafx.scene.image.Image;

public class MainGameLevel{
	
	public String _imageLocation = GameObject.imageLoc + "playerSpaceShip.png";
	
	int HunterSpawnTime = 2000, DestroyerSpawnTime = 2000, itemSpawnTime = 750, playerSpawnTime = 225;
	int hunterSpawnRate = 3, destroyerSpawnRate = 2, enemySpawnRateTimer = 2500;
	public static int playerLives = 3;
	
	public int GeneralTimer;
	public static int currentTimer;
	public static int currentMinutes, currentSeconds;
	
	public static int oldHighscore = 0;
	public static int oldHighScoreMinutes, oldHighScoreSeconds;
	
	
	public Player myPlayer;
	public Enemy hunter;
	public Destroyer destroyer;
	public Player playerRef;
	public SpaceCity SCRef;
	
	public boolean hasNotInitialized = true;
	
	ArrayList<GameObject> _gameObjList;
		
	ArrayList<GameObject> SCList;
	
	public void levelUpdate() {
		
		SCList = Helper.GetGameObjectsOfType(SpaceCity.class);
		
		GeneralTimer += 1;
		if(GeneralTimer >= 60) {
			currentSeconds += 1;
			GeneralTimer = 0;
			currentTimer += 1;
			if(currentSeconds >= 60) {
				currentSeconds = 0;
				currentMinutes += 1;
			}
		}
		
			//RESPAWNS HUNTERS
			if(Enemy.enemyCounter < hunterSpawnRate) {
			HunterSpawnTime -= 10;
				if(HunterSpawnTime <= 0) {
					int HunterX = ThreadLocalRandom.current().nextInt(10, 1145);
					hunter = new Enemy(HunterX, -125);
					_gameObjList.add(hunter);
					Enemy.enemyCounter += 1;
					HunterSpawnTime = 2000;
				}
			}
			
			//RESPAWNS DESTROYERS
			if(Destroyer.enemyCounter < destroyerSpawnRate) {
				DestroyerSpawnTime -= 10;
				if(DestroyerSpawnTime <= 0) {
					int LeftSpawn = ThreadLocalRandom.current().nextInt(10, 300);
					int RightSpawn = ThreadLocalRandom.current().nextInt(920, 1145);
					int picker = ThreadLocalRandom.current().nextInt(0, 10);
					if(picker > 5) {
						destroyer = new Destroyer(RightSpawn, -125);
					_gameObjList.add(destroyer);
					}else{
						destroyer = new Destroyer(LeftSpawn, -125);
						_gameObjList.add(destroyer);
					}
					Destroyer.enemyCounter += 1;
					DestroyerSpawnTime = 2000;
				}
			}
			
			//VERHOOGT DE SPAWN-RATE/DIFFICULTY
			if(enemySpawnRateTimer <= 0) {
				hunterSpawnRate += 1;
				destroyerSpawnRate += 1;
				enemySpawnRateTimer = 2500;
			}else {enemySpawnRateTimer -= 1;}
			
			//System.out.println("HUNTERS: " + Enemy.enemyCounter + " DESTROYERS: " + Destroyer.enemyCounter);
			
			//RESPAWNS PLAYER, CHECKT ZIJN LEVENS, EN SAVED DE (HIGH)SCORE
			ArrayList<GameObject> playerList = Helper.GetGameObjectsOfType(Player.class);
			if((playerList.size() < 1)&&(playerLives > 0)) {
				if(playerSpawnTime <= 0) {
				myPlayer = new Player(570, 500);
				myPlayer.setSprite(new Image(_imageLocation));
				playerRef = myPlayer;
				_gameObjList.add(myPlayer);
				playerSpawnTime = 225;
				}else {playerSpawnTime -= 1;}
			}else if((playerLives == 0)||(SCList.size() < 1)) { //SWITCH NAAR GAME-OVER SCHERM
				bg.music.stop();
				if(playerSpawnTime <= 0) {
					playerSpawnTime = 225;
					playerLives = 3;
					SaveLoad.loadGame(); //LAAD DE OUDE HIGHSCORE OM HEM TE KUNNEN VERGELIJKEN MET DE HUIDIGE SCORE
					if(currentTimer >= oldHighscore) {
						GameManager.numberOfLevel = 4;
						SaveLoad.saveGame("SAVE");
					}else {
					GameManager.numberOfLevel = 3;}
					Enemy.enemyCounter = 0; Destroyer.enemyCounter = 0;
					currentTimer = 0; currentSeconds = 0; currentMinutes = 0;
				}else {playerSpawnTime -= 1;}
			}
			
			ArrayList<GameObject> puList = Helper.GetGameObjectsOfType(PowerUp.class);
			ArrayList<GameObject> pu2List = Helper.GetGameObjectsOfType(PowerUpTwo.class);
			ArrayList<GameObject> pdList = Helper.GetGameObjectsOfType(PowerDown.class);
			ArrayList<GameObject> huList = Helper.GetGameObjectsOfType(HealthUp.class);

			//RESPAWNS POWER-UPS/DOWN'S
			double itemAmount = puList.size() + pu2List.size() + pdList.size() + huList.size();
			
			if(itemAmount < 2) {
				
				if(itemSpawnTime <= 0){
					
				int randomItem = ThreadLocalRandom.current().nextInt(0, 100);
				int X = ThreadLocalRandom.current().nextInt(10, 1145);
				int Y = ThreadLocalRandom.current().nextInt(80, 730);
				
					if(randomItem < 25) {
						PowerUp pu = new PowerUp(X, Y);
						_gameObjList.add(pu);
					}else if(randomItem > 75) {
						PowerUpTwo pu2 = new PowerUpTwo(X, Y);
						_gameObjList.add(pu2);
					}else if((randomItem > 50)&&(randomItem < 75)){
						HealthUp hu = new HealthUp(X, Y);
						_gameObjList.add(hu);
					}else {
						PowerDown pd = new PowerDown(X, Y);
						_gameObjList.add(pd);
					}
					itemSpawnTime = 750;
				}else {itemSpawnTime -= 1;}				
			}			
	}
	BackGround bg;
	public void initializeLevel() {
		
		_gameObjList = Helper.GetGameObjectList();
		_gameObjList.clear();

		bg = new BackGround(0, 0);
		Helper.AddGameObject(bg);
		
		myPlayer = new Player(570, 500);
		myPlayer.setSprite(new Image(_imageLocation));
		playerRef = myPlayer;
		
		
		// ADD PLAYER TO LIST OF OBJECTS TO UPDATE AND DRAW:
		Helper.AddGameObject(myPlayer);
		
		//PLACE SPACE-CITY
		SpaceCity sc = new SpaceCity(550, 700);
		Helper.AddGameObject(sc);
		SCRef = sc;
		
		
		//PLACE OBSTACLES
		for(int x = 0; x < 37; x++) {
		int WallX = ThreadLocalRandom.current().nextInt(0, 1145);
		int WallY = ThreadLocalRandom.current().nextInt(80, 730);
		
		Wall wallTest = new Wall(WallX, WallY);
		Helper.AddGameObject(wallTest);
		}
		
		SaveLoad.loadGame();
		
	}
	

}
