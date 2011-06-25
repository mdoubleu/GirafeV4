package org.Giraffe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;



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
	public void doDraw(Canvas canvas){
		int width=canvas.getWidth();
		int height=canvas.getHeight();
		controller.setSize(width, height);
		
		
		canvas.drawBitmap(background,gameModel.getBChange(), 0, null);
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
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		try {
			thread.isRunning(false);
		} finally {
			thread = null;
		}
		
	}
	public void getBackground(){
		
	}

}
