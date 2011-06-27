package org.Giraffe;

import android.util.Log;

public class GameThread extends Thread{
	GameModel model;
	GameView view;
	GameController controller;
	private final boolean NOTRUNNING=false;
	private final boolean ISRUNNING=true;
	private boolean running;
	
	public GameThread(GameModel model, GameView view, GameController controller){
		this.model=model;
		this.view=view;
		this.controller=controller;
		running=true;
	}


	public void run() {
		while(running){
			if(GameCall.getmPaused()==true)
        	{
        		Log.d("Paused", "Game should be paused.");
        		
        	}
			else{
					synchronized(view){
						view.draw();
					}
				 if (!model.levelOver()){
					synchronized(model){
					     model.updateLevel();
					}
				 }
			}
		}
	}
	
	
	public void isRunning(boolean running){
		if(running==false){
			this.running= NOTRUNNING;
		}else{
			 this.running=ISRUNNING;
		}
	}

}
