package org.Giraffe;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import android.os.Handler;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	GameThread gameThread;
	int G_y1;
	int G_y2;
	int G_x1;
	int G_x2;
	int b_round;
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//lets us stay updated about changes.
		SurfaceHolder holder=getHolder();
		holder.addCallback(this);
		
		//calls the inner class
		gameThread=new GameThread(holder, context,new Handler());
       
		G_y1=150;
        G_y2=450;
        G_x1=0;
        G_x2=150;
        setFocusable(true);
		
	}
	
	class GameThread extends Thread{
		SurfaceHolder G_surface;
		Handler G_handler;
		Context G_context;
		Bitmap background;
		Drawable giraffe;
		Rect rectangle;
		int w_adapt;
		int h_adapt;
		boolean G_run;
		long time;
		
		
		   public GameThread(SurfaceHolder surfaceHolder, Context context,
	                Handler handler){
			   G_surface=surfaceHolder;
			   G_context=context;
			   G_handler=handler;
			   b_round=0;
			   Resources res = G_context.getResources();
	            giraffe = context.getResources().getDrawable(
	                    R.drawable.giraffe);
	            background=BitmapFactory.decodeResource(res, R.drawable.a);	
	            setFocusable(true);
	            
		   }
		public void doDraw(Canvas canvas){
			Resources res = G_context.getResources();	
			canvas.drawBitmap(background, 0, 0, null);
			giraffe.setBounds(G_x1, G_y1, G_x2, G_y2);
			giraffe.draw(canvas);
			canvas.save();
			
			if(b_round==0){
				 background=BitmapFactory.decodeResource(res, R.drawable.a);
				 giraffe = G_context.getResources().getDrawable(
		                    R.drawable.giraffe2);
			 }else if(b_round==1){
				 background=BitmapFactory.decodeResource(res, R.drawable.a3);
				 giraffe = G_context.getResources().getDrawable(
		                    R.drawable.giraffe);
			 }else if(b_round==2){
				 background=BitmapFactory.decodeResource(res, R.drawable.a2);
			 }else{
				 Log.d("change", "Background");
			 }
			 time=System.currentTimeMillis();
			 while(time+500>System.currentTimeMillis()){
			 }
			
			canvas.restore();
			
		}
		 
		public void setSurfaceSize(int width, int height) {
            synchronized (G_surface) {
                w_adapt = width;
                h_adapt = height;

                // don't forget to resize the background image
                background = background.createScaledBitmap(
                        background, width, height, true);
            }
			
		}
		public void setRunning(boolean b) {
			G_run=b;
			
		}
		 public void run() {
			 	while (G_run) {
	                time=System.currentTimeMillis();
			 		Canvas c = null;
	                try {
	                    c = G_surface.lockCanvas(null);
	                    synchronized (G_surface) {
	                    	if(b_round==2){
	                    		b_round=-1;
	                    	}
	                    	b_round++;
	                    	doDraw(c);
	                    } 
	                } finally {
	                    // do this in a finally so that if an exception is thrown
	                    // during the above, we don't leave the Surface in an
	                    // inconsistent state
	                    if (c != null) {
	                        G_surface.unlockCanvasAndPost(c);
	                    }
	                }
	            }
	        }
		 private void changeBackground(int x){
			 Resources res = G_context.getResources();	
			 if(x==0){
				background=BitmapFactory.decodeResource(res, R.drawable.a2);
			 }else if(x==1){
				 background=BitmapFactory.decodeResource(res, R.drawable.a2);
				 invalidate();
			 }else if(x==2){
				 background=BitmapFactory.decodeResource(res, R.drawable.a3);
			 }else{
				 Log.d("change", "Background");
			 }
		}
	}
 
	
public boolean onKeyUp(int keyCode, KeyEvent msg) {
	    if(keyCode==KeyEvent.KEYCODE_DPAD_UP){
	    	try {
				jump(G_y1, G_y2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	    return false;
	    
	}
 public boolean jumping=false;
 long cTime;//current time
 long timer;//the timer as it goes along.
 public void down(int y1, int y2){
	 G_y1=y1+10;
	 G_y2=y2+10;
	 invalidate();
 }

	 public void jump(int y1, int y2) throws InterruptedException{
		 //redraws
		 invalidate();

		 jumping = true;
	      cTime = System.currentTimeMillis();
	      timer=System.currentTimeMillis();

	      //Giraffe Jump Part1
	      while((cTime+1000) > timer){  //moves 20 pixils up     
	          timer = System.currentTimeMillis(); //updates 
	          G_y1-=10;
	          G_y2-=10;
	          gameThread.sleep(50);
	          invalidate();
	      }
	          cTime = System.currentTimeMillis();
	      
	      while((cTime+1000) > timer){  //moves 20 pixils up      
	          timer = System.currentTimeMillis();
	          G_y1+=10;
	          G_y2+=10;
	          gameThread.sleep(50);
	          invalidate();
	           
	      }
	      jumping = false;
	      invalidate();
	     
	 }
	 
	public GameThread getThread(){
		return gameThread;
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		gameThread.setSurfaceSize(width, height);
	}


	public void surfaceCreated(SurfaceHolder holder) {
		 gameThread.setRunning(true);
	     gameThread.start();
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		 boolean retry = true;
	        gameThread.setRunning(false);
	        while (retry) {
	            try {
	                gameThread.join();
	                retry = false;
	            } catch (InterruptedException e) {
	            }
	        }

		}
	}
