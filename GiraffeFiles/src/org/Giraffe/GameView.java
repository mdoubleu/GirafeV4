package org.Giraffe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

//View needs the folowing from model.
//model. 
// blocation1
//blocation 2
//.get entities()
//potential add (.graff)


public class GameView implements Callback{
	private GameController controller;
	private SurfaceHolder holder;
	private GameModel model;
	private GameThread thread;
	private Context context;
	public Resources Gres;
	private Bitmap background;

	
	public GameView(GameController controller,SurfaceHolder holder,GameModel model, Context context){
		this.controller=controller;
		this.holder = holder;
		this.model=model;
		this.context=context;
		Gres=context.getResources();
		getBackground();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holdr, int format, int width, int height) {
		controller.setSize(width, height);
		model.setSize(width, height);
		
	}
	

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (thread == null) {
			thread = new GameThread(model,this,controller);
			thread.start();
			}
		
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
	private void drawGraff(Canvas canvas, GiraffeEntity graff)
	{
		//rotate neck
		Drawable g_neck=graff.getNeck();
		g_neck.setBounds((int)controller.modelToViewX(graff.neck_x1),
			 			 (int)controller.modelToViewX(graff.neck_y1),
						 (int)controller.modelToViewX(graff.neck_x2),
						 (int)controller.modelToViewX(graff.neck_y2));
		if(model.notRotating){
			g_neck.draw(canvas);
		}
		canvas.save();
		model.getRotate();
		canvas.rotate(model.getRotate()[0],model.getRotate()[1],model.getRotate()[2]);
		g_neck.draw(canvas);
		canvas.restore();
		model.defaultRotate();

	}
	private void drawBG(Canvas canvas)
	{
		int width=canvas.getWidth();
		int height=canvas.getHeight();
		background = background.createScaledBitmap(
        background, (int)width, (int)height, true);	
		controller.setSize(width, height);
		canvas.drawBitmap(background, controller.modelToViewX(model.bLocation1), 0, null);
		canvas.drawBitmap(background, controller.modelToViewX(model.bLocation2), 0, null);
		
		
	}
	private void doDraw(Canvas canvas){
		
		//model.setSize(width, height);
		drawBG(canvas);
		
		//giraffeStuff
		GiraffeEntity graff=(GiraffeEntity) model.getEntities().get(0);
		drawGraff(canvas, graff);
		
		
		//Obstacles Stuff
		for(Entity e:model.getEntities()){
			//why move?
			e.move();
			
			//model.getEntities().get(x).doDraw()&&
			if(!e.toString().equals("body")&&e.doDraw()){
				Drawable f=e.getImage();
				f.setBounds((int)controller.modelToViewX(e.getX()),
							(int)controller.modelToViewY(e.getY()),
							(int)controller.modelToViewX(e.getX2()),
							(int)controller.modelToViewY(e.getY2()));
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
	public void getBackground(){
		background=model.getBkround();
	}

}
