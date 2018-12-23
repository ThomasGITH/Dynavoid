package gamesource.collidables;

import java.util.ArrayList;

import gamesource.levels.MainGameLevel;
import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;

public class EnemyLightBeam extends GameObject{

	public EnemyLightBeam(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "Red_laser.png"));
	
		scaleX = 0.08;
		scaleY = 0.08;
	}
	
	private int speed = 26;
	public String lastDirection;
	
	public void Update() {
		switch(lastDirection) {
		case "LEFT" : setRotation(0); x -= speed; break;
		case "RIGHT" : setRotation(180); x += speed; break;
		case "UP" : setRotation(90); y -= speed; break;
		case "DOWN" : setRotation(-90); y += speed; break;
		}
		
		Wall rots = (Wall)Helper.CheckCollisionRectangle(this, Wall.class);
		EnemyLightBeam bullet = (EnemyLightBeam)Helper.CheckCollisionRectangle(this, EnemyLightBeam.class);
		Player player = (Player)Helper.CheckCollisionRectangle(this, Player.class);
		OtherPlayer playerTwo = (OtherPlayer)Helper.CheckCollisionRectangle(this, OtherPlayer.class);
		SpaceCity SC = (SpaceCity)Helper.CheckCollisionRectangle(this, SpaceCity.class);
		ForceField ff = (ForceField)Helper.CheckCollisionRectangle(this, ForceField.class);
		
		//GEEF PLAYER-INFORMATIE OP
		ArrayList<GameObject> playerList = Helper.GetGameObjectsOfType(Player.class);
		ArrayList<GameObject> playerTwoList = Helper.GetGameObjectsOfType(OtherPlayer.class);
		ArrayList<GameObject> SCList = Helper.GetGameObjectsOfType(SpaceCity.class);

		if(!playerList.isEmpty()) {
		GameObject myPlayer = playerList.get(0);
		
		// CHECK COLLISION WITH ENEMIES:
		
		if (player != null)
		{
			myPlayer.health -= 20;
			BulletImpact poof = new BulletImpact(myPlayer.x, myPlayer.y);
			Helper.AddGameObject(poof);
			poof.y -= 25;
			Helper.RemoveGameObject(bullet);
			if(myPlayer.health <= 0) {
				Explosion explosion = new Explosion(myPlayer.x, myPlayer.y);
				Helper.AddGameObject(explosion);
				if(!GameManager.isHost) {
					GameManager.EliminateClientPlayer = true;
				}else if(GameManager.isHost) {
					GameManager.EliminateHostPlayer = true;
				}
				Helper.RemoveGameObject(player);
				MainGameLevel.playerLives -= 1;
			}
		}
		}
		
		if(!playerTwoList.isEmpty()) {
		GameObject otherPlayer = playerTwoList.get(0);
		if (playerTwo != null)
		{
			otherPlayer.health -= 20;
			BulletImpact poof = new BulletImpact(otherPlayer.x, otherPlayer.y);
			Helper.AddGameObject(poof);
			poof.y -= 25;
			Helper.RemoveGameObject(bullet);
			if(otherPlayer.health <= 0) {
				Explosion explosion = new Explosion(otherPlayer.x, otherPlayer.y);
				Helper.AddGameObject(explosion);
				if(GameManager.isHost) {
					GameManager.EliminateClientPlayer = true;
				}else if(!GameManager.isHost) {
					GameManager.EliminateHostPlayer = true;
				}
				Helper.RemoveGameObject(playerTwo);
			}
		  }
		}
		
		// CHECK COLLISION WITH WALLS:
		if (rots != null)
		{
			rots.health -= 50;
			Helper.RemoveGameObject(bullet);
		}
		
		if (ff != null)
		{
			Helper.RemoveGameObject(bullet);
		}
		
		if(SC != null) {
			Helper.RemoveGameObject(bullet);
			SC.health -= 5;
			if(SC.health <= 0) {
			CityExplosion explosion = new CityExplosion(SC.x, SC.y);
			Helper.AddGameObject(explosion);
			Helper.RemoveGameObject(SC);
			}
		}
		
		//BULLET CLEANUP WHEN OUT OF GAME-VIEW
		if(((x <= 0)||(x >= 1165))||((y <= 0)||(y >= 800))) {
			Helper.RemoveGameObject(bullet);
		}
	}

}
