package org.Giraffe;

import org.Giraffe.About;
import org.Giraffe.HTP;
import org.Giraffe.GameCall;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;

public class Giraffe extends Activity implements OnClickListener
{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        View continueButton = findViewById(R.id.play_button);
        continueButton.setOnClickListener(this);
        View newButton = findViewById(R.id.level_select_button);
        newButton.setOnClickListener(this);
        View buyButton = findViewById(R.id.how_to_play_button);
        buyButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(this);
        
    }
    public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.play_button:

				Intent x=new Intent(this, GameCall.class);
				startActivity(x);

				break;
			case R.id.about_button:
			Intent a = new Intent(this, About.class);

			startActivity(a);
			break;
			case R.id.how_to_play_button:
			Intent b= new Intent(this, HTP.class);
			startActivity(b);
			break;

		}
		//more buttons go here(if any)

	}
}