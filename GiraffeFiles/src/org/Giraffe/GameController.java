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
		//Log.d("TEST","REACHED ONDOWN");
		
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
	       }else  if (action == MotionEvent.ACTION_MOVE){
	    	   if(Math.abs(updateX-firstX)>delta ){
	    		   
	    		   distance = firstX - event.getX();
	    		  //previous=distance;
	    		  if (distance >= 100) {
	    			  gameModel.getOurGiraffe().setToPrime();
	    			  Log.d("Look Here!", ""+gameModel.getOurGiraffe().primeTime);
	    		  }
		          /* 
	    		  if (gameModel.deg>-40) {
		               gameModel.deg -=3;
		               gameModel.rotateNeck(3); 
		           }*/
	    		  
	    	  }
	    	  
	           return true;
	           }
	       /*else if(action==MotionEvent.ACTION_UP&&gameModel.deg!=0){
		    	   gameModel.rotateNeck(2); 
		           gameModel.deg=0;
		    	   return true;
		       }*/
	       else if(action==MotionEvent.ACTION_UP) {
	    	   Log.d("TEST","REACHED PRESSED UP");
	    	   Log.d("TESTING", ""+firstX+"  "+updateX+"  "+firstY+ "   "+ updateY);
	    	   
	    	   if(firstX+10<event.getX() || firstX-10>event.getX()
	    			  ){ //&&firstY+20<event.getY() &&firstY-20>event.getY())
	    		 //  gameModel.rotateNeck(2); 
		           gameModel.deg=0;
		        //   Log.d("TEST", "MOTIONSWIPE");
		           gameModel.getOurGiraffe().setTime();
		           gameModel.getOurGiraffe().setToAttack();
		          
		           Log.d("LOOK HERE", ""+gameModel.getOurGiraffe().primeTime);
	    		   /*makes sure giraffe isnt currently jumping so theres no double jump*/

	    		  
	    	   }else{
	    		   if(!gameModel.getOurGiraffe().currentlyJumping()){
	    			   gameModel.getOurGiraffe().setJump(true);
	    		   }
	    	   }
	    	   return true;
	       }
	       
	      
	       return false;
		}
	}



	
}
