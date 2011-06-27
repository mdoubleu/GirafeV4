package org.Giraffe;

import org.Giraffe.About;
import org.Giraffe.HTP;
import org.Giraffe.GameCall;


import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Giraffe extends Activity implements OnClickListener
{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
  
        //View settingsButton = findViewById(R.id.settings_button);
        //settingsButton.setOnClickListener(this);
        View mStartButton = findViewById(R.id.startButton);
        mStartButton.setOnClickListener(this);
        View mExtrasButton = findViewById(R.id.extrasButton);
        mExtrasButton.setOnClickListener(this);
        View mOptionsButton = findViewById(R.id.optionButton);
        mOptionsButton.setOnClickListener(this);
        View mBackground = findViewById(R.id.mainMenuBackground);
        
      
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        Music.create(this, R.raw.jeremythegiraffetheme);
    }
    public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.startButton:

				Intent x=new Intent(this, GameCall.class);
				startActivity(x);
				Log.d("Far", "Do i get this far?");

				break;
			/*
			case R.id.about_button:
			Intent a = new Intent(this, About.class);

			startActivity(a);
			break;
			case R.id.how_to_play_button:
			Intent b= new Intent(this, HTP.class);
			startActivity(b);
			break;
			*/
		}
		//more buttons go here(if any)

	}
    @Override
    protected void onResume()
    {
    	super.onResume();
    	Music.start(this);
    	
    }
    @Override
    protected void onPause()
    {
    	super.onPause();
    	//Music.stop(this);
    }
    @Override
    protected void onDestroy()
    {
    	super.onDestroy();
    	Music.stop(this);
    }
}