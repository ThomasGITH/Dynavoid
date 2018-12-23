package gamesource.collidables;

import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;

public class LightBeam extends GameObject{

	public LightBeam(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "Green_laser.png"));
	
		scaleX = 0.16;
		scaleY = 0.16;
	}
	
	private int speed = 26;
	public int lastDirection;
	public int explosionTimer = 1;
	
	public void Update() {
		switch(lastDirection) {
		case 1 : setRotation(0); x -= speed; break;
		case 2 : setRotation(180); x += speed; break;
		case 3 : setRotation(90); y -= speed; break;
		case 4 : setRotation(-90); y += speed; break;
		}
		
		Wall asteroid = (Wall)Helper.CheckCollisionRectangle(this, Wall.class);
		LightBeam bullet = (LightBeam)Helper.CheckCollisionRectangle(this, LightBeam.class);
		Enemy evilShip = (Enemy)Helper.CheckCollisionRectangle(this, Enemy.class);
		NetworkingEnemy evilNetShip = (NetworkingEnemy)Helper.CheckCollisionRectangle(this, NetworkingEnemy.class);
		Destroyer destroyer = (Destroyer)Helper.CheckCollisionRectangle(this, Destroyer.class);
		SpaceCity SC = (SpaceCity)Helper.CheckCollisionRectangle(this, SpaceCity.class);

		// CHECK COLLISION WITH WALLS:
		if (asteroid != null)
		{
			asteroid.health -= 50;
			Helper.RemoveGameObject(bullet);
		}
		
		// CHECK COLLISION WITH ENEMIES:
		if (evilShip != null)
		{
			Helper.RemoveGameObject(bullet);
			BulletImpact poof = new BulletImpact(evilShip.x, evilShip.y);
			poof.y -= 30;
			Helper.AddGameObject(poof);
			evilShip.health -= 50;
			if(evilShip.health <= 0) {
			double explosionX = evilShip.x, explosionY = evilShip.y;
			Explosion explosion = new Explosion(explosionX, explosionY);
			Helper.AddGameObject(explosion);
			Helper.RemoveGameObject(evilShip);
			evilShip.enemyCounter -= 1;
			}
		}
		
		if (destroyer != null)
		{
			Helper.RemoveGameObject(bullet);
			BulletImpact poof = new BulletImpact(destroyer.x, destroyer.y);
			Helper.AddGameObject(poof);
			destroyer.health -= 14.28571428571429;
			if(destroyer.health <= 0) {
			double explosionX = destroyer.x, explosionY = destroyer.y;
			Explosion explosion = new Explosion(explosionX, explosionY);
			explosion.scaleX = 1; explosion.scaleY = 1;
			explosion.x -= 20; explosion.y -= 5;
			Helper.AddGameObject(explosion);
			Helper.RemoveGameObject(destroyer);
			Destroyer.enemyCounter -= 1;
			}
		}
		
		if (evilNetShip != null)
		{
			Helper.RemoveGameObject(bullet);
			BulletImpact poof = new BulletImpact(evilNetShip.x, evilNetShip.y);
			poof.y -= 30;
			Helper.AddGameObject(poof);
			evilNetShip.health -= 50;
			if(evilNetShip.health <= 0) {
			double explosionX = evilNetShip.x, explosionY = evilNetShip.y;
			Explosion explosion = new Explosion(explosionX, explosionY);
			Helper.AddGameObject(explosion);
			Helper.RemoveGameObject(evilNetShip);
			evilNetShip.enemyCounter -= 1;
			}
		}
		
		//BULLET CLEANUP WHEN OUT OF GAME-VIEW
		if(((x <= 0)||(x >= 1165))||((y <= 0)||(y >= 800))) {
			Helper.RemoveGameObject(bullet);
		}
	}

}
