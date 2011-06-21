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

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	GameThread gameThread;
	int width;
	int height;
	GameModel gameModel;
	GiraffeEntity ourGiraffe;
	
	public GameView(Context context, AttributeSet attrs) {
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
	           
	           ourGiraffe=new GiraffeEntity(G_context, 0);
	            setFocusable(true);
	            
		   }
		   protected void onSizeChanged(int w, int h, int oldw, int oldh){
			   
		   }
		public void doDraw(Canvas canvas){
			canvas.drawBitmap(background ,gameModel.getBChange(), 0, null);
			canvas.drawBitmap(background,gameModel.getB2Change(), 0, null);
			GiraffeEntity graff=(GiraffeEntity) gameModel.getLevel().get(0);
			
			Drawable g_raff=gameModel.getLevel().get(0).getImage();
			g_raff.setBounds(graff.x1, graff.y1, graff.x2, graff.y2);
			g_raff.draw(canvas);
			graff.move();
			
			for(int x=0; x<gameModel.getEntityDraw().size(); x++){
				if(gameModel.getEntityDraw().get(x).doDraw()){
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
 
	
	public boolean onTouchEvent(MotionEvent event) {
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
		*/
			}
		return false;
		}
	
 public boolean jumping=false;
 long cTime;//current time
 long timer;//the timer as it goes along.
/*	 
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
	 */
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
