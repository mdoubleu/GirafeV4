package org.Giraffe;
/*
 * In this class you have the progress of the game, and the next level method
 */
public class GameState 
{
	private static int progress=1;
	
	//returns the level of the state to the gamemodel to load rawr
	public static int getLevel()
	{
		return progress;
	}
	//goes to the next level
	public static void nextLevel()
	{
		progress+=1;
	}
}
