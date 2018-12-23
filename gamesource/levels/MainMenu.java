package gamesource.levels;

import java.util.ArrayList;

import gamesource.collidables.*;
import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import gamesource.main.Input;
import javafx.scene.media.AudioClip;

public class MainMenu {
	
	public MainMenu() {
		
	}

	ArrayList<GameObject> _gameObjList;
	
	MenuBackGround background;
	Logo logo;
	StartButton startbutton;
	ControllsButton controllsbutton;
	ControllsButton hostbutton;
	ControllsButton clientbutton;
	MultiplayerButton mpButton;
	
	AudioClip startbutton_press = new AudioClip(GameObject.soundLoc + "button.wav");
	AudioClip button_press = new AudioClip(GameObject.soundLoc + "button2.wav");

	public boolean hasNotInitialized = true;

	public void UpdateMenu() {
		
		//STARTKNOP HITBOX
		if(
		Input.MOUSE_X <= startbutton.getBBoxRight() &&
		Input.MOUSE_X >= startbutton.getBBoxLeft() &&
		Input.MOUSE_Y <= startbutton.getBBoxBottom() &&
		Input.MOUSE_Y >= startbutton.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				startbutton_press.play();
				GameManager.numberOfLevel = 1;
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
		
		if(
		Input.MOUSE_X <= controllsbutton.getBBoxRight() &&
		Input.MOUSE_X >= controllsbutton.getBBoxLeft() &&
		Input.MOUSE_Y <= controllsbutton.getBBoxBottom() &&
		Input.MOUSE_Y >= controllsbutton.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				button_press.play();
				GameManager.numberOfLevel = 2;
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
		
		if(
		Input.MOUSE_X <= mpButton.getBBoxRight() &&
		Input.MOUSE_X >= mpButton.getBBoxLeft() &&
		Input.MOUSE_Y <= mpButton.getBBoxBottom() &&
		Input.MOUSE_Y >= mpButton.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				button_press.play();
					GameManager.numberOfLevel = 5;
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
	}
	
	public void InitializeMenu() {
		
		_gameObjList = Helper.GetGameObjectList();
		_gameObjList.clear();
		
		background = new MenuBackGround(0,0);
		_gameObjList.add(background);
						
		logo = new Logo(263, 80);
		_gameObjList.add(logo);
		
		startbutton = new StartButton(425, 260);
		_gameObjList.add(startbutton);
		
		controllsbutton = new ControllsButton(394, 420);
		_gameObjList.add(controllsbutton);
		
		mpButton = new MultiplayerButton(375, 580);
		_gameObjList.add(mpButton);
		
	}
}
