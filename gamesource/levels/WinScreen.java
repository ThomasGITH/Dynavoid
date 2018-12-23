package gamesource.levels;

import java.util.ArrayList;

import gamesource.collidables.HighScoreBeaten;
import gamesource.collidables.ResetHighscore;
import gamesource.collidables.Retry;
import gamesource.collidables.VictoryLogo;
import gamesource.collidables.WinBackground;
import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import gamesource.main.Input;
import gamesource.main.SaveLoad;
import javafx.scene.media.AudioClip;

public class WinScreen {
	
	ArrayList<GameObject> _gameObjList;
	
	public boolean hasNotInitialized = true;
	AudioClip button_press = new AudioClip(GameObject.soundLoc + "button.wav");
	
	public void UpdateWinScreen() {
				
		if(
		Input.MOUSE_X <= retry.getBBoxRight() &&
		Input.MOUSE_X >= retry.getBBoxLeft() &&
		Input.MOUSE_Y <= retry.getBBoxBottom() &&
		Input.MOUSE_Y >= retry.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				button_press.play();
				GameManager.numberOfLevel = 0;
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
		
		//RESET HIGHSCORE KNOP
		if(
		Input.MOUSE_X <= rhs.getBBoxRight() &&
		Input.MOUSE_X >= rhs.getBBoxLeft() &&
		Input.MOUSE_Y <= rhs.getBBoxBottom() &&
		Input.MOUSE_Y >= rhs.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				button_press.play();
				SaveLoad.saveGame("RESET");
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
		
	}
	
	Retry retry;
	ResetHighscore rhs;
	public void InitializeWinScreen() {
		
		_gameObjList = Helper.GetGameObjectList();
		_gameObjList.clear();
		
		WinBackground dbg = new WinBackground(0,0);
		_gameObjList.add(dbg);
		
		VictoryLogo vl = new VictoryLogo(350,200);
		_gameObjList.add(vl);
		
		HighScoreBeaten hsb = new HighScoreBeaten(vl.x, vl.y + 150);
		_gameObjList.add(hsb);
				
		retry = new Retry(475, vl.y + 230);
		_gameObjList.add(retry);
		
		rhs = new ResetHighscore(420, retry.y + 130);
		_gameObjList.add(rhs);

	}
}
