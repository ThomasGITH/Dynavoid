package gamesource.main;


import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.transform.*;
import javafx.geometry.*;


public class GameObject
{
	
	public int layer = 0;
	
	public double x = 0;
	public double y = 0;
	public double scaleX = 1.0;
	public double scaleY = 1.0;
	public int health = 0;
	public int timer;
	public double mov;
	public double spawnPoint;
	public boolean DetectsPlayer;
	
	public boolean isActive = true;
	public boolean isCollidable = false;
	public boolean isVisible = true;
	
	//VERANDER DE VOLGENDE DIRECTORY ALS HIJ NIET AANSLUIT OP DEZE COMPUTER
	
	public static String soundLoc = "FILE://" + GameObject.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/sounds/";
	public static String imageLoc = "FILE://" + GameObject.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "images/";
	public static String saveLoc = GameObject.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "Saves/save.ser";
	
	private float _rotation = 0;
	private Image _sprite;
	private Rectangle2D _boundingBox = new Rectangle2D(0, 0, 0, 0);
	
	
	
	// ------------------------------------------------- PROPERTIES (aka GETTERS/SETTERS) -------------------------------------------------
	public Image getSprite()
	{
		if (_sprite != null)
		{ return _sprite; }
		else
		{ return null; }
	}
	
	/** Based on the given image, it will automatically set the bounding box of the GameObject. */
	public void setSprite(Image newSprite)
	{
		_sprite = newSprite;
		_boundingBox = new Rectangle2D(0, 0, (double)_sprite.getWidth(), (double)_sprite.getHeight());
	}
	
	
	public float getRotation()
	{
		return _rotation;
	}
	public void setRotation(float rot)
	{
		_rotation = rot;
		_rotation %= 360; // Make sure the rotation does not surpass 360 degrees.
	
	}
	
	
	public double getBBoxTop()
	{
		return y + _boundingBox.getMinY();
	}
	public double getBBoxLeft()
	{
		return x + _boundingBox.getMinX();
	}
	public double getBBoxBottom()
	{
		return y + _boundingBox.getMaxY() * scaleY;
	}
	public double getBBoxRight()
	{
		return x + _boundingBox.getMaxX() * scaleX;
	}
	
	
	public double getWidth()
	{
		if (_sprite != null)
		{
			return _sprite.getWidth() * scaleX;
		}
		
		return 0.0;
	}
	public double getHeight()
	{
		if (_sprite != null)
		{
			return _sprite.getHeight() * scaleY;
		}
		
		return 0.0;
	}
	
	
	
	
	// -------------------------------------------------           CONSTRUCTORS           -------------------------------------------------
	public GameObject(double xPosition, double yPosition)
	{
		x = xPosition;
		y = yPosition;
				
	}
	
	
	
	// -------------------------------------------------              METHODS             ------------------------------------------------- 
	/** Override this method to insert your own game logic. */
	public void Update()
	{
		
	}
	
	
	/** This object will draw itself on the given GraphicsContext: */
	public void Draw(GraphicsContext gc)
	{
		if (_sprite != null)
		{
			// Save the current state of the canvas transformation.
			gc.save();
			
			// Use the center of the object sprite as rotation oriëntation:
			Rotate r = new Rotate(_rotation, x + (getWidth() * 0.5), y + (getHeight() * 0.5));
	        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
			
	        // Draw the image on the current transformation of the canvas.
			gc.drawImage(getSprite(), x, y, getSprite().getWidth() * scaleX, getSprite().getHeight() * scaleY);
			
			// Set the transformation back to the earlier saved state:
			gc.restore();
		}
	}
	
}