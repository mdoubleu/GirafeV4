package org.Giraffe;

import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameController implements OnGestureListener, OnTouchListener{
GameModel gameModel;
float firstX;
float firstY;
int state;
float delta;

public GameController(GameModel gameModel){
		this.gameModel=gameModel;
		delta=10;
		state=1;
		
	}
	   
	public boolean onDown(MotionEvent event) {
		Log.d("TEST","REACHED ONDOWN");
		gameModel.setJump(true);
		int action = event.getAction();
	       
	       if (action == MotionEvent.ACTION_DOWN) {

	           firstX = event.getX();
	           firstY = event.getY();
	       }
	       if (action == MotionEvent.ACTION_UP && gameModel.deg != 0) {
	    	   gameModel.rotateNeck(2); 
	           gameModel.deg=0;
	         //  gameModel.ourGiraffe.attacked(true);

	       }
	       if (action == MotionEvent.ACTION_MOVE && Math.abs(event.getX()-firstX)>delta && firstX>event.getX()) {
	           gameModel.rotateNeck(3); 
	           if (gameModel.deg>-40) {
	               gameModel.deg -=3;
	           }
	           firstX = event.getX();
	           firstY = event.getY();
	           
	           }
	       return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//this method on single tap, puts giraffe into jump mode just once.
	public boolean onSingleTapUp(MotionEvent e) {
		gameModel.setJump(true);
		if(gameModel.currentlyJumping()==false){
			gameModel.setJump(true);
		}
		
		return false;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		gameModel.setJump(true);
		return false;
	}

	
}
