package org.Giraffe;

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
	}


	public void run() {
		while(running){
			try{
				synchronized(view){
					view.draw();

				}
			 if (!model.levelOver)
				synchronized(model){
				     model.updateGame(System.currentTimeMillis());
				     gameModel.alternateBackground();
                 	gameModel.updateLevel();
                 	gameModel.jump();
                 	Log.d("TEST","RUNNING BEFORE GESTURE");
                 	new GameController(gameModel);
                 	doDraw(c);
					}
				else controller.levelOver();
			}catch(InterruptedException e){
				isRunning(false);
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
