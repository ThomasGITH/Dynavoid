package gamesource.levels;

import java.util.ArrayList;
import java.util.Scanner;

import gamesource.collidables.*;
import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import gamesource.main.Input;
import javafx.scene.media.AudioClip;

public class MultiplayerMenu {

	ArrayList<GameObject> _gameObjList;
	
	MenuBackGround background;
	BackButton backbutton;
	HostButton hostbutton;
	ClientButton clientbutton;
	
	Scanner scanner = new Scanner(System.in);
	
	AudioClip startbutton_press = new AudioClip(GameObject.soundLoc + "button.wav");
	AudioClip button_press = new AudioClip(GameObject.soundLoc + "button2.wav");

	public boolean hasNotInitialized = true;

	public void UpdateMenu() {
		
		if(
		Input.MOUSE_X <= backbutton.getBBoxRight() &&
		Input.MOUSE_X >= backbutton.getBBoxLeft() &&
		Input.MOUSE_Y <= backbutton.getBBoxBottom() &&
		Input.MOUSE_Y >= backbutton.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				button_press.play();
				GameManager.numberOfLevel = 0;
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
		
		if(
		Input.MOUSE_X <= hostbutton.getBBoxRight() &&
		Input.MOUSE_X >= hostbutton.getBBoxLeft() &&
		Input.MOUSE_Y <= hostbutton.getBBoxBottom() &&
		Input.MOUSE_Y >= hostbutton.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				startbutton_press.play();
					GameManager.numberOfLevel = 6;
					GameManager.isHost = true;
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
		
		if(
		Input.MOUSE_X <= clientbutton.getBBoxRight() &&
		Input.MOUSE_X >= clientbutton.getBBoxLeft() &&
		Input.MOUSE_Y <= clientbutton.getBBoxBottom() &&
		Input.MOUSE_Y >= clientbutton.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				startbutton_press.play();
				System.out.println("Enter the server IP adress.");
				String ip = scanner.nextLine();
				if(ip == "") {
					ip = "localhost";
				}else {
					GameManager.IP = ip;
				}
					GameManager.numberOfLevel = 8;
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
	}
	
	public void InitializeMenu() {
		
		_gameObjList = Helper.GetGameObjectList();
		_gameObjList.clear();
		
		background = new MenuBackGround(0,0);
		_gameObjList.add(background);
		
		backbutton = new BackButton(94, 70);
		_gameObjList.add(backbutton);
		
		hostbutton = new HostButton(400, 260);
		_gameObjList.add(hostbutton);
		
		clientbutton = new ClientButton(400, 480);
		_gameObjList.add(clientbutton);
		
	}
}
