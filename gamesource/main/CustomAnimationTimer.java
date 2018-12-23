package gamesource.main;


import javafx.animation.*;
import java.util.function.*;

public class CustomAnimationTimer extends AnimationTimer
{
	
	private Consumer<Long> handleMethod = null;
	
	
	// GEEF DE REFERENTIE VAN DE UIT TE VOEREN UPDATE-METHODE MEE:
	public CustomAnimationTimer(Consumer<Long> methodReference)
	{
		handleMethod = methodReference;
	}
	
	
	// VOERT DE MEEGEGEVEN UPDATE-METHODE (60 KEER PER SECONDE) UIT.
	public void handle(long currentNanoTime)
	{
		handleMethod.accept(currentNanoTime);
	}
	
	
	// VERVANG DE HUIDIGE UIT TE VOEREN UPDATE-METHODE MET EEN ANDERE METHODE:
	public void setHandle(Consumer<Long> methodReference) 
	{
		handleMethod = null;
		handleMethod = methodReference;
	}
	
}