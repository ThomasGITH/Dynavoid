package gamesource.main;


import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.*;


public class Input
{
	
	public static ArrayList<String> KEYS_PRESSED = new ArrayList<String>();
	public static ArrayList<String> KEYS_RELEASED = new ArrayList<String>();
	
	public static double MOUSE_X = 0.0;
	public static double MOUSE_Y = 0.0;
	
	public static boolean MOUSE_PRESSED_LB = false;
	
	
	
	public static EventHandler<KeyEvent> OnKeyPressed = Input::OnKeyPressed;
	public static void OnKeyPressed(KeyEvent event)
	{
		String code = event.getCode().toString();
		 
		if ( !KEYS_PRESSED.contains(code) )
		{
			KEYS_PRESSED.add( code );
		}
	}
	
	public static EventHandler<KeyEvent> OnKeyReleased = Input::OnKeyReleased;
	public static void OnKeyReleased(KeyEvent event)
	{
		String code = event.getCode().toString();
		 
		if ( !KEYS_RELEASED.contains(code) )
		{
			KEYS_RELEASED.add( code );
		}
		
		KEYS_PRESSED.remove( code );
	}
	
	
	
	
	public static EventHandler<MouseEvent> OnMousePressed = Input::OnMousePressed;
	public static void OnMousePressed(MouseEvent event)
	{
		if (event.getButton() == MouseButton.PRIMARY)
		{
			MOUSE_PRESSED_LB = true;
		}
	}
	
	public static EventHandler<MouseEvent> OnMouseReleased = Input::OnMouseReleased;
	public static void OnMouseReleased(MouseEvent event)
	{
		if (event.getButton() == MouseButton.PRIMARY)
		{
			MOUSE_PRESSED_LB = false;
		}
	}
	
	public static EventHandler<MouseEvent> OnMouseMove = Input::OnMouseMove;
	public static void OnMouseMove(MouseEvent event)
	{
		MOUSE_X = event.getX();
		MOUSE_Y = event.getY();
	}
	
	
}