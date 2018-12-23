package gamesource.levels;

import java.util.ArrayList;

import gamesource.collidables.*;
import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import gamesource.main.Input;
import javafx.scene.media.AudioClip;

public class WaitingScreen {
	

	ArrayList<GameObject> _gameObjList;
	
	MenuBackGround background;
	WaitingLogo waiting;
	
	AudioClip startbutton_press = new AudioClip(GameObject.soundLoc + "button.wav");
	AudioClip button_press = new AudioClip(GameObject.soundLoc + "button2.wav");

	public boolean hasNotInitialized = true;

	public void UpdateMenu() {
	}
	
	public void initializeWait() {
		
		_gameObjList = Helper.GetGameObjectList();
		_gameObjList.clear();
		
		background = new MenuBackGround(0,0);
		_gameObjList.add(background);
		
		waiting = new WaitingLogo(190, 350);
		_gameObjList.add(waiting);
		
		GameManager.numberOfLevel = 7;
						
		
	}
}
