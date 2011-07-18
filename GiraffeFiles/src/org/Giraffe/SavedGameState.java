package org.Giraffe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class SavedGameState extends Activity {
	String FILENAME = "saved_game";
	String DATA = "6 120i420dfm";
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			FileOutputStream gamesave = openFileOutput (FILENAME, Context.MODE_PRIVATE);
			gamesave.write(DATA.getBytes());
			gamesave.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream input = openFileInput(FILENAME);
			
			byte[] buffer=new byte[input.available()];

			input.read(buffer);
			String decode=new String(buffer);
			Log.d("TESTEINGINGAGNIG", " " + decode);
			
			
			input.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
