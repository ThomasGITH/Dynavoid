package gamesource.collidables;

import java.util.ArrayList;

import gamesource.main.*;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;


public class ForceField extends GameObject
{
	AudioClip force_field = new AudioClip(soundLoc + "force_field.wav");

	public ForceField(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "Shield.png"));
		
		scaleX = 0.335;
		scaleY = 0.335;
		
		timer = 600;
		
		force_field.play();
		
	}
	
	public void Update() {
		
		ArrayList<GameObject> playerList = Helper.GetGameObjectsOfType(Player.class);
		ForceField ff = (ForceField)Helper.CheckCollisionRectangle(this, ForceField.class);

		if(playerList.size() > 0) {
			GameObject myPlayer = playerList.get(0);
			x = myPlayer.x - 30;
			y = myPlayer.y - 30;
		}else {Helper.RemoveGameObject(ff);}
		
		timer -= 1;
	}
	
}