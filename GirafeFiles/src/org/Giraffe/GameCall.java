package org.Giraffe;


import org.Giraffe.GameView.GameThread;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameCall extends Activity implements OnClickListener{

	GameView theGame;
	GameThread gameThread;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
        theGame=(GameView) findViewById(R.id.game1);
        gameThread=theGame.getThread();
        
    //View playButton=findViewById(R.id.play_button);
    //playButton.setOnClickListener(this);

    }
    public void onClick(View v) {

	}

}