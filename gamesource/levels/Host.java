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

public class Host{
	
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
	
	double HunterSpawnX;
	
	public boolean hasNotInitialized = true;
	
	ArrayList<GameObject> _gameObjList;
	ArrayList<GameObject> _enemyObjList;
	
	public void levelUpdate() {
		
		_enemyObjList = Helper.GetGameObjectsOfType(Enemy.class);

		//RESPAWNS HUNTERS
		if(_enemyObjList.size() < hunterSpawnRate) {
		HunterSpawnTime -= 10;
			if(HunterSpawnTime <= 0) {
				int HunterX = ThreadLocalRandom.current().nextInt(10, 1145);
				hunter = new Enemy(HunterX, -125);
				_gameObjList.add(hunter);
				Enemy.enemyCounter += 1;
				HunterSpawnTime = 2000;
			}
		}
		
		//VERHOOGT DE SPAWN-RATE/DIFFICULTY
		if(enemySpawnRateTimer <= 0) {
			hunterSpawnRate += 1;
			destroyerSpawnRate += 1;
			enemySpawnRateTimer = 2500;
		}else {enemySpawnRateTimer -= 1;}
		
		
		try {
			UpdateServer();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(GameManager.EliminateHostPlayer == true) {
			Helper.RemoveGameObject(myPlayer);
			GameManager.EliminateHostPlayer = false;
		}
	}
	BackGround bg;
	OtherPlayer p2;
	boolean dir = false;
	public void initializeLevel() throws IOException {
		
		_gameObjList = Helper.GetGameObjectList();
		_gameObjList.clear();
		
		CreateServer();

		bg = new BackGround(0, 0);
		Helper.AddGameObject(bg);
		
		myPlayer = new Player(570, 500);
		myPlayer.setSprite(new Image(_imageLocation));
		playerRef = myPlayer;
		
		
		// ADD PLAYER TO LIST OF OBJECTS TO UPDATE AND DRAW:
		Helper.AddGameObject(myPlayer);
		
		p2 = new OtherPlayer(535, 500);
		p2.setSprite(new Image(GameObject.imageLoc + "enemySpaceShip.png"));
		_gameObjList.add(p2);
		
	}
	
	//SERVER-CONNECTION
	double info, testTemp;
	ServerSocket server;
	Socket soc;
	Scanner sc;
	PrintStream test, asteroidPositionData;
	
	public void CreateServer() throws IOException {
		server = new ServerSocket(1350);
		System.out.println("Server started without errors.");
		System.out.println("Waiting for a connection..");
		soc = server.accept();		
	}
	int asteroidAmount = 0;
	boolean asteroidX = true;
	ObjectOutputStream oos;
	public void UpdateServer() throws IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
		oos = new ObjectOutputStream(soc.getOutputStream());
		
		UserInfo message = new UserInfo(myPlayer.movementCode, myPlayer.bulletCode);
		doSomething(message);
		oos.writeObject(message);
		
		UserInfo returnMessage = (UserInfo)ois.readObject();
		p2.movementCode = returnMessage.movementCode;
		p2.bulletCode = returnMessage.bulletCode;
		GameManager.EliminateHostPlayer = returnMessage.eliminateHostPlayer;
		GameManager.EliminateClientPlayer = returnMessage.eliminateClientPlayer;
	}
	
	public void doSomething(UserInfo message) throws IOException {
		message.enemyCounter = _enemyObjList.size();
		message.eliminateClientPlayer = GameManager.EliminateClientPlayer;
		message.eliminateHostPlayer = GameManager.EliminateHostPlayer;
		if(!_enemyObjList.isEmpty()) {
				message.rotation0 = _enemyObjList.get(0).getRotation();
				message.x0 = _enemyObjList.get(0).x;
				message.y0 =_enemyObjList.get(0).y;
				message.DP0 = _enemyObjList.get(0).DetectsPlayer;
				
				if(_enemyObjList.size() >= 2) {
				message.rotation1 = _enemyObjList.get(1).getRotation();
				message.x1 = _enemyObjList.get(1).x;
				message.y1 =_enemyObjList.get(1).y;
				message.DP1 = _enemyObjList.get(1).DetectsPlayer;
				}
				
				if(_enemyObjList.size() >= 3) {
				message.rotation2 = _enemyObjList.get(2).getRotation();
				message.x2 = _enemyObjList.get(2).x;
				message.y2 =_enemyObjList.get(2).y;
				message.DP2 = _enemyObjList.get(2).DetectsPlayer;
				}
				/*
				if(_enemyObjList.size() >= 4) {
				message.rotation3 = _enemyObjList.get(3).getRotation();
				message.x3 = _enemyObjList.get(3).x;
				message.y3 =_enemyObjList.get(3).y;
				}
				
				
				if(_enemyObjList.size() >= 5) {
				message.rotation4 = _enemyObjList.get(4).getRotation();
				message.x4 = _enemyObjList.get(4).x;
				message.y4 =_enemyObjList.get(4).y;
				}
				
				if(_enemyObjList.size() >= 6) {
				message.rotation5 = _enemyObjList.get(5).getRotation();
				message.x5 = _enemyObjList.get(5).x;
				message.y5 =_enemyObjList.get(5).y;
				}
				
				if(_enemyObjList.size() >= 7) {
				message.rotation6 = _enemyObjList.get(6).getRotation();
				message.x6 = _enemyObjList.get(6).x;
				message.y6 =_enemyObjList.get(6).y;
				}
				*/
		}
	}
		

	
	
}
