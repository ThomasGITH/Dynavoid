package gamesource.levels;

import java.util.ArrayList;

import gamesource.collidables.*;
import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import gamesource.main.Input;
import javafx.scene.media.AudioClip;

public class ControllsScreen {

	ArrayList<GameObject> _gameObjList;
	
	ControllsBackGround background;
	BackButton backbutton;
	Controlls_LMB lmb;
	Controlls_W w;
	Controlls_A a;
	Controlls_S s;
	Controlls_D d;
	
	Controlls_SPACE space;
	Controlls_M m;
	Controlls_MUTE mute;
	
	Controlls_LEFT left;
	Controlls_RIGHT right;
	Controlls_UP up;
	Controlls_DOWN down;
	
	Controlls_MOVEMENT mov;
	Controlls_SHOOT shoot;
	
	AudioClip backbutton_press = new AudioClip(GameObject.soundLoc + "button2.wav");

	public boolean hasNotInitialized = true;
	
	public void UpdateControlls() {
		
		//BACKBUTTON HITBOX
		if(
		Input.MOUSE_X <= backbutton.getBBoxRight() &&
		Input.MOUSE_X >= backbutton.getBBoxLeft() &&
		Input.MOUSE_Y <= backbutton.getBBoxBottom() &&
		Input.MOUSE_Y >= backbutton.getBBoxTop()) {
			if(Input.MOUSE_PRESSED_LB) {
				backbutton_press.play();
				GameManager.numberOfLevel = 0;
				Input.MOUSE_PRESSED_LB = false; //VOORKOMT CLICK-SPAMM
			}
		}
		
	}
	
	public void InitializeControlls() {
		
		_gameObjList = Helper.GetGameObjectList();
		_gameObjList.clear();
		
		background = new ControllsBackGround(0,0);
		_gameObjList.add(background);
		
		backbutton = new BackButton(94, 70);
		_gameObjList.add(backbutton);
		
		lmb = new Controlls_LMB(950,390);
		_gameObjList.add(lmb);
		
		s = new Controlls_S(145, 277);
		_gameObjList.add(s);
		
		w = new Controlls_W(s.x, s.y - 85);
		_gameObjList.add(w);
		
		a = new Controlls_A(s.x - 85, s.y);
		_gameObjList.add(a);
		
		d = new Controlls_D(s.x + 85, s.y);
		_gameObjList.add(d);
		
		down = new Controlls_DOWN(s.x, s.y + 400);
		_gameObjList.add(down);
		
		left = new Controlls_LEFT(down.x - 85, down.y);
		_gameObjList.add(left);
		
		right = new Controlls_RIGHT(down.x + 85, down.y);
		_gameObjList.add(right);
		
		up = new Controlls_UP(down.x, down.y - 85);
		_gameObjList.add(up);
		
		space = new Controlls_SPACE(500,500);
		_gameObjList.add(space);
		
		m = new Controlls_M(850, 50);
		_gameObjList.add(m);
		
		mov = new Controlls_MOVEMENT(down.x - 100, down.y - 225);
		_gameObjList.add(mov);
		
		shoot = new Controlls_SHOOT(down.x + 575, down.y - 225);
		_gameObjList.add(shoot);
		
		mute = new Controlls_MUTE(m.x - 400, m.y + 25);
		_gameObjList.add(mute);
		
	}
}
