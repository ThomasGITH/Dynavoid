package gamesource.main;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gamesource.levels.MainGameLevel;

public class SaveLoad{
	
	static String currentSoundLoc;

	  public static void saveGame(String saveGame)
	  {
		  		
			      varData e = new varData();
			      
			      if(saveGame == "SAVE") {
			      e.savedTimer = MainGameLevel.currentTimer;
			      
			      e.savedMinutesTimer = MainGameLevel.currentMinutes;
			      e.savedSecondsTimer = MainGameLevel.currentSeconds;
			      }else {						//VOOR HET RESETTEN VAN DE HIGHSCORE
			    	  e.savedTimer = 0;
			    	  e.savedMinutesTimer = 0;
			    	  e.savedSecondsTimer = 0;
			      }
			      try {
			         FileOutputStream fileOut = new FileOutputStream(GameObject.saveLoc);
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(e);
			         out.close();
			         fileOut.close();
			         System.out.printf("Save file overwritten");
			      } catch (IOException i) {
				     System.err.println("ERROR: Unable to create save file. Try running Eclipse as Administrator.");
			         i.printStackTrace();
			      }
			      			      
	  }
	  
	  public static void loadGame()
	  {
		  	
			      varData e = null;
			      try {
			         FileInputStream fileIn = new FileInputStream(GameObject.saveLoc);
			         ObjectInputStream in = new ObjectInputStream(fileIn);
			         e = (varData)
			         in.readObject();
			         in.close();
			         fileIn.close();
			      } catch (IOException i) {
			         System.err.println("WARNING: No save file found. Using first-recorded time-stamp instead.");
			          MainGameLevel.oldHighscore = MainGameLevel.currentTimer;
			         return;
			      } catch (ClassNotFoundException c) {
			    	  c.printStackTrace();
			         return;
			      } 
			      
			      MainGameLevel.oldHighscore = e.savedTimer;
			      
			      MainGameLevel.oldHighScoreMinutes = e.savedMinutesTimer;
			      MainGameLevel.oldHighScoreSeconds = e.savedSecondsTimer;
			      
			      System.out.println("Data loaded");
			      

	  }
}
