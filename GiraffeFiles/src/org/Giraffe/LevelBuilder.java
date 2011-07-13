package org.Giraffe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

public class LevelBuilder {
	String readLine = null;
	int numberForBackground;
	Context c;
	GameView g;
	public LevelBuilder(int level, Context c){
		this.c = c;
		String levelsource = "level" + level;
		Integer levelid = this.c.getResources().getIdentifier(levelsource, "raw", "org.Giraffe");
		InputStream is = c.getResources().openRawResource(levelid);
        Scanner br = new Scanner(new InputStreamReader(is));
        
		
		while (br.hasNextLine()) {
			//Log.d("BEFORE READING", readLine);
			readLine = br.nextLine();
			Log.d("THIS SHOULD BE A LEVEL STRING", readLine);
			numberForBackground=1;
		}
		
	}
	
	public int getNumForBackground(){
		return numberForBackground;
	}
	public String getLevel(){
		return readLine;
	}
	
}
