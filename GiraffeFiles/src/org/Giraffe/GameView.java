package org.Giraffe;


import java.util.LinkedList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.widget.ImageSwitcher;



public class GameView implements Callback{
	private GameController controller;
	private SurfaceHolder holder;
	private GameModel model;
	private GameThread thread;
	private Context context;
	public Resources Gres;
	private LinkedList<Bitmap> background;
	private float width;
	private float height;

	
	public GameView(GameController controller,SurfaceHolder holder,GameModel model, Context context){
		this.controller=controller;
		this.holder = holder;
		this.model=model;
		this.context=context;
		Gres=context.getResources();
		
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holdr, int format, int width, int height) {
		this.width=width;
		this.height=height;
		model.setSize(width, height);
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (thread == null) {
			thread = new GameThread(model,this,controller);
			thread.start();
			}
	}

	public int modelToViewX(float x){
		float gx=((x/800f)*width);
		return (int)gx;
	}
	public int modelToViewY(float y){
		float gx=((y/480f)*height);
		return (int)gx;
	}
	
	
	public void draw() {
		Canvas c = null;
		try {
			c = holder.lockCanvas();

			if (c != null) {
				doDraw(c);
			}
		} finally {
			if (c != null) {
				holder.unlockCanvasAndPost(c);
			}
		}
	}
	public void doDraw(Canvas canvas){
		float width=canvas.getWidth();
		float height=canvas.getHeight();
		
    	for (Backgrounds2 backround : model.getBkround()) {
    		if(backround.coordinate.getX()<=-width){
    			backround.coordinate.setX((int)width);
    			
    		}
    		canvas.drawBitmap(backround.getBackground(), 
    				backround.coordinate.getX(),
    				backround.coordinate.getY(), null);
    		
    		backround.coordinate.x-=backround.speed;
    		 
    	}
    	
		/*Bitmap background1=background.get(0); 
		 
		 background1.createScaledBitmap(background1, (int)width, (int)height, true);
		
		canvas.drawBitmap(background1, model.bLocation1, 0, null);
		canvas.drawBitmap(background1, model.bLocation2, 0, null);
		*/
		Paint v = new Paint();
		v.setColor(Color.BLACK);
		//canvas.drawRect(1,230, 230, 490, v);
		
		
		//giraffeStuff
		GiraffeEntity graff=(GiraffeEntity) model.allEffects().get(0);
		
		/*if(graff.getHitBox().size()>2){
			canvas.drawRect(graff.getHitBox().get(2).x1(),graff.getHitBox().get(2).y1(),
					graff.getHitBox().get(2).x2(),graff.getHitBox().get(2).y2(), v);
			
			
			canvas.drawRect(graff.getHitBox().get(0).x1(),graff.getHitBox().get(0).y1(),
					graff.getHitBox().get(0).x2(),graff.getHitBox().get(0).y2(), v);
			
			canvas.drawRect(graff.getHitBox().get(1).x1(),graff.getHitBox().get(1).y1(),
			graff.getHitBox().get(1).x2(),graff.getHitBox().get(1).y2(), v);
			
		}*/
		
		//Obstacles Stuff
		for(Effects e: model.getObjects()){
			e.move();
			
			//model.getEntities().get(x).doDraw()&&
			if(e.drawImage()){
				if(e.toString().equals("giraffe")){
					for(int h=0; h<graff.getHealth(); h++){
						Drawable health=graff.healthImage();
						health.setBounds((int)width-(40 * (h+1)), 0, (int)width-(40 * h), 40);
						health.draw(canvas);
					}
				}
				Drawable f=e.getImage();
				f.setBounds(e.X(),e.Y(),e.X2(),e.Y2());
				f.draw(canvas);
			}
		}
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		try {
			thread.isRunning(false);
		} finally {
			thread = null;
		}
		
	}

}
