package org.Giraffe;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/*
 * In this class you have the progress of the game, and the next level method
 */
public class GameState 
{
	private static int progress=1;
	
	//returns the level of the state to the gamemodel to load rawr
	public static int getLevel(Context context)
	{
		return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context)
				.getString("levelselect", progress+""));
	}
	//goes to the next level
	public static void nextLevel(Context context)
	{
		progress+=1;
		SharedPreferences.Editor editor = 
			PreferenceManager.getDefaultSharedPreferences(context)
				.edit();
				editor.putString("levelselect", progress+"");
				editor.commit();
	}
}
