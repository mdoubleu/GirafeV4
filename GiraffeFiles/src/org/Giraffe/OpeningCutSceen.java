package org.Giraffe;






import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.AdapterView.OnItemClickListener;

public class OpeningCutSceen extends Activity implements OnClickListener
{
	private Button replay;
	private Button skip;
	long cTime;
	long startTime;
	Intent o;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        
	        setContentView(R.layout.cutsceen1);
	        replay= (Button) findViewById(R.id.replay);
	        replay.setOnClickListener(this);
	        skip= (Button) findViewById(R.id.skip);
	        skip.setOnClickListener(this);
	        //Fill view from resource
			o=new Intent(this, GameCall.class);
			
	        VideoView video = (VideoView) findViewById(R.id.videoView1);
	        //video.setVideoPath("/raw/cutscene1.mp4");
	        Uri videoPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cutscene1);
	       video.setVideoURI(videoPath);
	       video.requestFocus();
	       //video.setOnCompletionListener(null); 
	       video.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			finish();
			startActivity(o);
			
			
		} });
	       video.start();
	        /*
	        if (!video.canSeekBackward())
	        {
	        	finish();
	        }
	        */
	        //Music.create(this,R.raw.cutscene1);
	        //mediaPlayer.start(); // no need to call prepare(); create() does that for you
	       
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

	        setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        //Music.create(this, R.raw.jeremythegiraffetheme);
	       // Music.setLooping(this, R.raw.jeremythegiraffetheme);
	    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId())
		{
		case R.id.replay:
			finish();
			Intent x=new Intent(this, OpeningCutSceen.class);
			startActivity(x);
			break;
		case R.id.skip:
			finish();
			Intent z=new Intent(this, GameCall.class);
			startActivity(z);
			break;
		}
		

			 
		
			
	}

	
	
}
