package gamesource.main;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import gamesource.collidables.*;
import gamesource.levels.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class GameManager
{
	private Pane _mainPane;
	private Canvas _mainCanvas;
	private GraphicsContext _context;
	
	// PROPERTY GAME OBJECT LIST:
	public ArrayList<GameObject> _gameObjList = new ArrayList<GameObject>();
	public ArrayList<GameObject> getGameObjectsList()
	{
		return _gameObjList;
	}
		
	public static int numberOfLevel = 0;
	public static URL imageDirectory;
	public static URL soundDirectory;
	public static boolean isHost = false;
	public static boolean EliminateClientPlayer = false;
	public static boolean EliminateHostPlayer = false;
	public static String IP = "localhost";
	
	public GameManager(Pane paneReference)
	{
		_mainPane = paneReference;
		_mainCanvas = (Canvas)_mainPane.getChildren().get(0);
		_context = _mainCanvas.getGraphicsContext2D();
		Helper.SetGameManagerReference(this);
		InitializeGame();
	}
	
	MainMenu menu = new MainMenu();
	MainGameLevel level1 = new MainGameLevel();
	ControllsScreen controlls = new ControllsScreen();
	GameOver gameOver = new GameOver();
	WinScreen winScreen = new WinScreen();
	Host host = new Host();
	Client client = new Client();
	MultiplayerMenu MPMenu = new MultiplayerMenu();
	WaitingScreen waitingscreen = new WaitingScreen();
	
	static Scanner scanner = new Scanner(System.in);
		
	public void Update()
	{
		
		// UPDATE ALL THE GAME OBJECTS:
		if (_gameObjList != null)
		{
			for (int i = 0; i < _gameObjList.size(); i++)
			{
				_gameObjList.get(i).Update();
			}
		}		
		
		if(numberOfLevel == 0) {
			if(menu.hasNotInitialized) {
				menu.InitializeMenu();
				menu.hasNotInitialized = false;
				level1.hasNotInitialized = true;
				controlls.hasNotInitialized = true;
				MPMenu.hasNotInitialized = true;
				gameOver.hasNotInitialized = true;
				winScreen.hasNotInitialized = true;
			}
			menu.UpdateMenu();
		}else if(numberOfLevel == 1) {
			if(level1.hasNotInitialized) {
			level1.initializeLevel();
			level1.hasNotInitialized = false;
			menu.hasNotInitialized = true;
			controlls.hasNotInitialized = true;
			gameOver.hasNotInitialized = true;
			MPMenu.hasNotInitialized = true;
			winScreen.hasNotInitialized = true;
			}
			level1.levelUpdate();
		}else if(numberOfLevel == 2) {
			if(controlls.hasNotInitialized) {
			controlls.InitializeControlls();
			controlls.hasNotInitialized = false;
			menu.hasNotInitialized = true;
			level1.hasNotInitialized = true;
			gameOver.hasNotInitialized = true;
			MPMenu.hasNotInitialized = true;
			winScreen.hasNotInitialized = true;
			}
			controlls.UpdateControlls();
		}else if(numberOfLevel == 3) {
			if(gameOver.hasNotInitialized) {
				gameOver.InitializeGameOver();
				gameOver.hasNotInitialized = false;
			menu.hasNotInitialized = true;
			controlls.hasNotInitialized = true;
			MPMenu.hasNotInitialized = true;
			level1.hasNotInitialized = true;
			winScreen.hasNotInitialized = true;
			}
			gameOver.UpdateGameOver();
		}else if(numberOfLevel == 4) {
			if(winScreen.hasNotInitialized) {
				winScreen.InitializeWinScreen();
				winScreen.hasNotInitialized = false;
			menu.hasNotInitialized = true;
			controlls.hasNotInitialized = true;
			level1.hasNotInitialized = true;
			MPMenu.hasNotInitialized = true;
			gameOver.hasNotInitialized = true;
			}
			winScreen.UpdateWinScreen();
		}else if(numberOfLevel == 5) {
			if(MPMenu.hasNotInitialized) {
				MPMenu.InitializeMenu();
				MPMenu.hasNotInitialized = false;
			client.hasNotInitialized = true;
			host.hasNotInitialized = true;
			winScreen.hasNotInitialized = true;
		menu.hasNotInitialized = true;
		controlls.hasNotInitialized = true;
		level1.hasNotInitialized = true;
		gameOver.hasNotInitialized = true;
		}
		MPMenu.UpdateMenu();
	}else if(numberOfLevel == 6) {
		if(waitingscreen.hasNotInitialized) {
			waitingscreen.initializeWait();
			waitingscreen.hasNotInitialized = false;
			host.hasNotInitialized = true;
			MPMenu.hasNotInitialized = true;
			winScreen.hasNotInitialized = true;
		menu.hasNotInitialized = true;
		controlls.hasNotInitialized = true;
		level1.hasNotInitialized = true;
		gameOver.hasNotInitialized = true;
		}
		waitingscreen.UpdateMenu();
	}else if(numberOfLevel == 7) {
			if(host.hasNotInitialized) {
				try {
					host.initializeLevel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				host.hasNotInitialized = false;
				MPMenu.hasNotInitialized = true;
				winScreen.hasNotInitialized = true;
			menu.hasNotInitialized = true;
			controlls.hasNotInitialized = true;
			level1.hasNotInitialized = true;
			gameOver.hasNotInitialized = true;
			}
			host.levelUpdate();
		}else if(numberOfLevel == 8) {
			if(client.hasNotInitialized) {
					try {
						client.initializeLevel();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				client.hasNotInitialized = false;
				host.hasNotInitialized = true;
				MPMenu.hasNotInitialized = true;
				winScreen.hasNotInitialized = true;
			menu.hasNotInitialized = true;
			controlls.hasNotInitialized = true;
			level1.hasNotInitialized = true;
			gameOver.hasNotInitialized = true;
			}
			client.levelUpdate();
		}
		
		//System.out.println("GAME OBJECTS:" + _gameObjList.size());
		
	      //System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
	}
	
	
	public void Draw()
	{
		// DRAW ALL THE GAME OBJECTS:
		if (_gameObjList != null)
		{
			for (int i = 0; i < _gameObjList.size(); i++)
			{
				_gameObjList.get(i).Draw(_context);
			}
		}
	}
	
	public void DrawGUI()
	{
		if(numberOfLevel == 1) {
		_context.setFill(Color.YELLOW);
		// DRAW INTERFACE HERE:
		_context.fillText("HIGHSCORE: " + MainGameLevel.oldHighScoreMinutes + ":" + MainGameLevel.oldHighScoreSeconds, 0, 15);
		_context.fillText("PLAYER LIVES: " + MainGameLevel.playerLives, 0, 45);
		_context.fillText("TIME: " + MainGameLevel.currentMinutes + ":" + MainGameLevel.currentSeconds, 0, 60);
		
		ArrayList<GameObject> playerList = Helper.GetGameObjectsOfType(Player.class);
		if(playerList.size() > 0) {
		_context.fillText("HEALTH: " + level1.playerRef.health, level1.playerRef.x + 10, level1.playerRef.y);
		}
		
		ArrayList<GameObject> spaceCityList = Helper.GetGameObjectsOfType(SpaceCity.class);
		if(spaceCityList.size() > 0) {
		_context.fillText("HEALTH: " + level1.SCRef.health, level1.SCRef.x + 30, level1.SCRef.y);
		}
		//_context.fillText("PLAYER X: 0",playerRef.x, playerRef.y);
		}
	}
	
	// PRIVATE FUNCTIONS, INTERNAL GAME STRUCTURE:
	private void InitializeGame()
	{			
		menu.InitializeMenu();
	}
	
	
	
	// PUBLIC FUNCTIONS:
	public GameObject CheckCollisionRectangle(GameObject objA, Class type)
	{
		for (GameObject objB : _gameObjList)
		{
			// FIRST CHECK IF IT'S THE TYPE OF OBJECT WE'RE LOOKING FOR:
			if (type.isInstance(objB))
			{
				// CHECK FOR COLLISION:
				if (
						objA.getBBoxLeft() <= objB.getBBoxRight() &&
						objA.getBBoxRight() >= objB.getBBoxLeft() &&
						objA.getBBoxTop() <= objB.getBBoxBottom() &&
						objA.getBBoxBottom() >= objB.getBBoxTop()
					)
				{
					// THERE IS COLLISION! RETURN THE OBJECT THAT HAS COLLISION WITH THE GIVEN OBJECT:
					// (The method stops executing after that.)
					return objB;
				}
			}
		}
		
		return null;
	}
	
	
}