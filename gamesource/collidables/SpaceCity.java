package gamesource.collidables;

import gamesource.main.GameManager;
import gamesource.main.GameObject;
import gamesource.main.Helper;
import javafx.scene.image.Image;

public class SpaceCity extends GameObject{

	public SpaceCity(double xPosition, double yPosition) {
		super(xPosition, yPosition);
		
		setSprite(new Image(imageLoc + "spaceCity.png"));
	
		scaleX = 0.375; //0.375
		scaleY = 0.375;
		
		x = 550;

		health = 1200;
	}
	
	public void Update() {
			
			// CHECK COLLISION WITH ENEMY LIGHTBEAMS
		DestroyerLightBeam DLB = (DestroyerLightBeam)Helper.CheckCollisionRectangle(this, DestroyerLightBeam.class);
		SpaceCity SC = (SpaceCity)Helper.CheckCollisionRectangle(this, SpaceCity.class);

			if (DLB != null)
			{
				Helper.RemoveGameObject(DLB);
			}
			

	}

}
