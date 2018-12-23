package gamesource.levels;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import gamesource.main.UserInfo;
import javafx.scene.image.Image;

public class Client{
	
	public String _imageLocation = GameObject.imageLoc + "playerSpaceShip.png";
	
	int HunterSpawnTime = 2000, DestroyerSpawnTime = 2000, itemSpawnTime = 750, playerSpawnTime = 225;
	int hunterSpawnRate = 3, destroyerSpawnRate = 2, enemySpawnRateTimer = 2500, enemyCounter = 0;
	
	public static int playerLives = 3, GeneralTimer, currentTimer, currentMinutes, currentSeconds;
	public static int oldHighscore = 0, oldHighScoreMinutes, oldHighScoreSeconds;
	
	
	public Player myPlayer;
	public NetworkingEnemy hunter;
	public Destroyer destroyer;
	public Player playerRef;
	public SpaceCity SCRef;
	
	public double HunterX = 500;
	
	public boolean hasNotInitialized = true;
	
	ArrayList<GameObject> _gameObjList;
	ArrayList<GameObject> _enemyObjList;
	ArrayList<GameObject> _enemyBulletObjList;
		
	public void levelUpdate() {
		
		_enemyObjList = Helper.GetGameObjectsOfType(NetworkingEnemy.class);
		_enemyBulletObjList = Helper.GetGameObjectsOfType(EnemyLightBeam.class);

		//RESPAWNS HUNTERS
		if(_enemyObjList.size() < enemyCounter) {
				hunter = new NetworkingEnemy(HunterX, -125);
				_gameObjList.add(hunter);
		  }
		
				//System.out.println("ELB: " + _enemyBulletObjList.size());
		try {
			UpdateClient();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println("SIZE: " +_enemyObjList.size() + " COUNTER: " + enemyCounter);
		//System.out.println(GameManager.EliminateClientPlayer);
		
		if(!_enemyObjList.isEmpty()) {
		//System.out.println("ENEMY MOV: " + _enemyObjList.get(0).mov + " SPEED: " + NetworkingEnemy.speed + " ENEMY OBJ SIZE: " + _enemyObjList.size() +  " HUNTERSPAWNTIME: " + HunterSpawnTime);
		}
		
		if(GameManager.EliminateClientPlayer == true) {
			Helper.RemoveGameObject(myPlayer);
			GameManager.EliminateClientPlayer = false;
		}
	}
	BackGround bg;
	OtherPlayer p2;

	public void initializeLevel() throws IOException {
		
		_gameObjList = Helper.GetGameObjectList();
		_gameObjList.clear();
		
		CreateClient();

		bg = new BackGround(0, 0);
		Helper.AddGameObject(bg);
		
		myPlayer = new Player(535, 500);
		myPlayer.setSprite(new Image(GameObject.imageLoc + "enemySpaceShip.png"));
		playerRef = myPlayer;
		
		
		// ADD PLAYER TO LIST OF OBJECTS TO UPDATE AND DRAW:
		Helper.AddGameObject(myPlayer);
		
		p2 = new OtherPlayer(570, 500);
		p2.setSprite(new Image(_imageLocation));
		_gameObjList.add(p2);
		
		
	}
	
	//SERVER-CONNECTION
	
	Scanner scanner;
	Socket socket;
	Scanner scanner2;
	double myInfo, serverinfo, key;
	PrintStream ps;
	
	public void CreateClient() throws IOException {
		//MAAK SOCKET EN SCANNERS AAN
		socket = new Socket(GameManager.IP, 1350); //localhost = 127.0.0.1
	}
	
	boolean positionValue = true;
	int asteroidAmount = 0;
	int posCounter = 0;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public void UpdateClient() throws IOException, ClassNotFoundException {
		
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
						
		UserInfo message = new UserInfo(myPlayer.movementCode ,myPlayer.bulletCode);
		message.eliminateHostPlayer = GameManager.EliminateHostPlayer;
		message.eliminateClientPlayer = GameManager.EliminateClientPlayer;
		doSomething(message);
		oos.writeObject(message);
		
		UserInfo returnMessage = (UserInfo)ois.readObject();
		p2.movementCode = returnMessage.movementCode;
		p2.bulletCode = returnMessage.bulletCode;
		GameManager.EliminateClientPlayer = returnMessage.eliminateClientPlayer;
		GameManager.EliminateHostPlayer = returnMessage.eliminateHostPlayer;

		enemyCounter = returnMessage.enemyCounter;
		
		
		if((!_enemyObjList.isEmpty())&&(enemyCounter > 0)) {
				if((enemyCounter >= 1)&&(_enemyObjList.size() >= 1)){
				_enemyObjList.get(0).setRotation(returnMessage.rotation0);
				_enemyObjList.get(0).x = returnMessage.x0;
				_enemyObjList.get(0).y = returnMessage.y0;
				_enemyObjList.get(0).DetectsPlayer = returnMessage.DP0;
				//System.out.println("ENEMY1: " + _enemyObjList.get(0).x + " " + _enemyObjList.get(0).y + " DETECTS PLAYER: " + _enemyObjList.get(0).DetectsPlayer);
				}
				
				if((enemyCounter >= 2)&&(_enemyObjList.size() >= 2)){
				_enemyObjList.get(1).setRotation(returnMessage.rotation1);
				_enemyObjList.get(1).x = returnMessage.x1;
				_enemyObjList.get(1).y = returnMessage.y1;
				_enemyObjList.get(1).DetectsPlayer = returnMessage.DP1;
				//System.out.println("ENEMY2: " + _enemyObjList.get(1).x + " " + _enemyObjList.get(1).y + " DETECTS PLAYER: " + _enemyObjList.get(1).DetectsPlayer);
				}
				
				if((enemyCounter >= 3)&&(_enemyObjList.size() >= 3)) {
				_enemyObjList.get(2).setRotation(returnMessage.rotation2);
				_enemyObjList.get(2).x = returnMessage.x2;
				_enemyObjList.get(2).y = returnMessage.y2;
				_enemyObjList.get(2).DetectsPlayer = returnMessage.DP2;
				//System.out.println("ENEMY3: " + _enemyObjList.get(2).x + " " + _enemyObjList.get(2).y + " DETECTS PLAYER: " + _enemyObjList.get(2).DetectsPlayer);
				}
				/*
				if(_enemyObjList.size() >= 4) {
				_enemyObjList.get(3).setRotation(returnMessage.rotation3);
				_enemyObjList.get(3).x = returnMessage.x3;
				_enemyObjList.get(3).y = returnMessage.y3;
				}
				
				*/
		}else {_enemyObjList.clear();}
	}
	
	public void doSomething(UserInfo message) {
		
	}
}
