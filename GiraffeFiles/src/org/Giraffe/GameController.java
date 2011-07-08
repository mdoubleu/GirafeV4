package org.Giraffe;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameController implements OnTouchListener{
GameModel gameModel;
float firstX;
float firstY;
public int state=0;
float delta;
float updateX;
float updateY;
float distance;
float previous=0f;



public GameController(GameModel gameModel){
		this.gameModel=gameModel;
		delta=10;
		state=1;
		
	}
	public void setSize(float w, float h){
		
	}

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized(gameModel){
		int action = event.getAction();
	      updateX=event.getX();
	      updateY=event.getY();
	       
		
		if (action == MotionEvent.ACTION_DOWN) {
	    	  
	           firstX = event.getRawX();
	           firstY = event.getRawY();
	           return true;
	       }else  if (action == MotionEvent.ACTION_MOVE ){
	    	   if(Math.abs(updateX-firstX)>delta ){
	    		   
	    		   distance = firstX - event.getX();
	    		  //previous=distance;
	    		  if (distance >= 100) {
	    			  gameModel.getOurGiraffe().setToPrime();
	    		  }
	    		  
	    	  }
	    	  
	           return true;
	           }
	       else if(action==MotionEvent.ACTION_UP ) {
	    	   if((firstX+20<event.getX() || firstX-20>event.getX())
	    			   && updateX<firstX){ 
		           gameModel.getOurGiraffe().setTime();
		           gameModel.getOurGiraffe().setToAttack();
	    		   /*makes sure giraffe isnt currently jumping so theres no double jump*/

	    		  
	    	   }else{
	    		   if(!gameModel.getOurGiraffe().currentlyJumping()||gameModel.getOurGiraffe().getDoubleJump()<2){
	    			   gameModel.getOurGiraffe().updateJumpCount();
	    			   gameModel.getOurGiraffe().setJump(true);
	    		   }
	    	   }
	    	   return true;
	       }
	       
	      
	       return false;
		}
	}



	
}
