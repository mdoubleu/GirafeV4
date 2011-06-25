package org.Giraffe;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameViewOld extends SurfaceView implements SurfaceHolder.Callback{
	GameThread gameThread;
	int width;
	int height;
	GameModel gameModel;
	GameController gestureL;
	
	public GameViewOld(Context context, AttributeSet attrs) {
		super(context, attrs);
		//lets us stay updated about changes.
		SurfaceHolder holder=getHolder();
		holder.addCallback(this);
		
		//calls the inner class
		gameThread=new GameThread(holder, context,new Handler());

        setFocusable(true);
	}
	
	class GameThread extends Thread{
		SurfaceHolder G_surface;
		Handler G_handler;
		Context G_context;
		Bitmap background;
		int w_adapt;
		int h_adapt;
		boolean G_run;
		long time;
		long timeIn;
		
		
		   public GameThread(SurfaceHolder surfaceHolder, Context context,
	                Handler handler){
			   G_surface=surfaceHolder;
			   G_context=context;
			   G_handler=handler;
			   gameModel=new GameModel(G_context);
			   gameModel.loadLevel(1);
			   background=gameModel.getBackgrounds();
			   gestureL=new GameController(gameModel);

	            setFocusable(true);
	            
		   }
		   protected void onSizeChanged(int w, int h, int oldw, int oldh){
			   
		   }
		public void doDraw(Canvas canvas){
			//background stuff
			canvas.drawBitmap(background ,gameModel.getBChange(), 0, null);
			canvas.drawBitmap(background,gameModel.getB2Change(), 0, null);
			
			
			//giraffeStuff
			GiraffeEntity graff=(GiraffeEntity) gameModel.getLevel().get(0);
			
			//rotate neck
			Drawable g_neck=graff.getNeck();
			g_neck.setBounds(graff.neck_x1,graff.neck_y1,graff.neck_x2,graff.neck_y2);
			g_neck.draw(canvas);
			
			canvas.save();
			gameModel.getRotate();
			//canvas.rotate(gameModel.getRotate()[0],gameModel.getRotate()[1],gameModel.getRotate()[2]);
			g_neck.draw(canvas);
			canvas.restore();
			
			//draw body
			Drawable g_body=gameModel.getLevel().get(0).getImage();
			g_body.setBounds(graff.x1, graff.y1, graff.x2, graff.y2);
			g_body.draw(canvas);
			

			//Obstacles Stuff
			for(int x=0; x<gameModel.getEntityDraw().size(); x++){
				if(gameModel.getEntityDraw().get(x).doDraw()&&!gameModel.getEntityDraw().get(x).toString().equals("giraffe")){
					Drawable f=gameModel.getEntityDraw().get(x).getImage();
					f.setBounds(gameModel.getEntityDraw().get(x).getX(),
						gameModel.getEntityDraw().get(x).getY(),
						gameModel.getEntityDraw().get(x).getX2(),
						gameModel.getEntityDraw().get(x).getY2());
					gameModel.getEntityDraw().get(x).move();
					f.draw(canvas);
				}
			}
		}
		 
		public void setSurfaceSize(int width, int height) {
            synchronized (G_surface) {
                w_adapt = width;
                h_adapt = height;
                Log.d("TAG", "setSurfaceSize...");
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
	                Canvas c = null;
	                try {
	                    c = G_surface.lockCanvas(null);
	                    synchronized (G_surface) {
	                    	gameModel.alternateBackground();
	                    	gameModel.updateLevel();
	                    	gameModel.jump();
	                    	Log.d("TEST","RUNNING BEFORE GESTURE");
	                    	new GameController(gameModel);
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
	}
 
	
	/*public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()== MotionEvent.ACTION_DOWN)
		{
			Log.d("TREU?", "TOUHCEVENENENVENT");
			gameModel.setJump(true);
		/*try {
		  //jump(G_y1, G_y2);
		 } catch (InterruptedException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 }  
		}
		
			}
		return false;
		}*/


	public GameThread getThread(){
		return gameThread;
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		gameThread.setSurfaceSize(width, height);
		this.width=width;
		this.height=height;
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
