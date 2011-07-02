package org.Giraffe;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
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
	public void doDraw(Canvas canvas){
		float width=canvas.getWidth();
		float height=canvas.getHeight();
		
		controller.setSize(width, height);
		//model.setSize(width, height);
		
		 background = background.createScaledBitmap(
                 background, (int)width, (int)height, true);
		
		canvas.drawBitmap(background, model.bLocation1, 0, null);
		canvas.drawBitmap(background, model.bLocation2, 0, null);
		
		//giraffeStuff
		GiraffeEntity graff=(GiraffeEntity) model.getEntities().get(0);
		if(model.notRotating){
			
		}
		canvas.save();
		//Obstacles Stuff
		for(int x=0; x<model.getEntities().size(); x++){
			model.getEntities().get(x).move();
			
			//model.getEntities().get(x).doDraw()&&
			if(!model.getEntities().get(x).toString().equals("body")&&model.getEntities().get(x).doDraw()){
				if(model.getEntities().get(x).toString().equals("giraffe")){
					int varHealth=20;
					for(int h=0; h<graff.getHealth(); h++){
						
						Drawable health=graff.healthImage();
						health.setBounds((int)width-varHealth, 0, (int)width-(varHealth/2), 20);
						health.draw(canvas);
						varHealth+=20;
					}
				}
						
				Drawable f=model.getEntities().get(x).getImage();
				f.setBounds(model.getEntities().get(x).getX(),
						model.getEntities().get(x).getY(),
						model.getEntities().get(x).getX2(),
						model.getEntities().get(x).getY2());
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
