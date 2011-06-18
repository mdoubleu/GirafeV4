package org.Giraffe;


import org.Giraffe.GameView.GameThread;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;


public class GameCall extends Activity{

	GameView theGame;
	GameThread gameThread;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Log.d("TAG", "GameCall");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
        theGame=(GameView) findViewById(R.id.game1);
        Log.d("TAG", "GameCall2");
        gameThread=theGame.getThread();
        Log.d("TAG", "GameCall3");
       
    }

}