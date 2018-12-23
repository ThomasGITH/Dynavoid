package gamesource.main;

import java.util.*;


// THIS CLASS HELPS OBTAINING DATA FROM THE GameManager.
public class Helper
{
	
	private static GameManager _GM = null;
	
	public static void SetGameManagerReference(GameManager ref)
	{
		_GM = ref;
	}
	
	/** Returns the first object of the given type that has collision with the given GameObject. */
	public static GameObject CheckCollisionRectangle(GameObject objA, Class type)
	{
		return _GM.CheckCollisionRectangle(objA, type);
	}
	
	
	public static void AddGameObject(GameObject obj)
	{
		_GM.getGameObjectsList().add(obj);
	}
	
	
	public static void RemoveGameObject(GameObject obj)
	{
		_GM.getGameObjectsList().remove(obj);
	}
	public static void RemoveGameObject(int index)
	{
		_GM.getGameObjectsList().remove(index);
	}
	
	public static ArrayList<GameObject> GetGameObjectList() {
		return _GM.getGameObjectsList();
	}
	
	/** Returns a list of GameObjects of the specified type. */
	public static ArrayList<GameObject> GetGameObjectsOfType(Class type)
	{
		ArrayList<GameObject> listOfObjects = new ArrayList<GameObject>();
		
		for (GameObject objB : _GM.getGameObjectsList())
		{
			// FIRST CHECK IF IT'S THE TYPE OF OBJECT WE'RE LOOKING FOR:
			if (type.isInstance(objB))
			{
				// ADD THE OBJECT TO THE RETURN LIST:
				listOfObjects.add(objB);
			}
		}
		
		return listOfObjects;
	}	
	
	

}