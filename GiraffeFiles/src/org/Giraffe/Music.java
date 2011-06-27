package org.Giraffe;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
	private static MediaPlayer mp= null;
	
	/** Stop old song and start playing new one */
	public static void create(Context context, int resource)
	{
		//stop(context);
		mp = MediaPlayer.create(context, resource);
		mp.setLooping(true);
		//mp.start();
	}
	public static void stop(Context context)
	{
		if(mp != null)
		{
			mp.stop();
			mp.release();
			mp = null;
		}
	}
	public static void pause(Context context)
	{
		mp.pause();
	}
	public static void start(Context context)
	{
		mp.start();
	}

}
