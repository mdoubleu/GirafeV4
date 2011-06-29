package org.Giraffe;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameController implements OnTouchListener{
GameModel gameModel;
float firstX;
float firstY;
int state;
float delta;
float updateX;
float updateY;
private static int width;
private static int height;

public GameController(GameModel gameModel){
		this.gameModel=gameModel;
		delta=10;
		state=1;
		//Log.d("TEST","REACHED ONDOWN");
		
	}
	public void setSize(int w, int h){
			width = w;
			height = h;
	}
	public static float viewToModelX(float x)
	{
		float qX= ((x*800f)/width);
		return qX;
	}
	public static float viewToModelY(float y)
	{
		float qY= (height+(y*450f)/height);
		return qY;
	}
	public float modelToViewX(float x)
	{
		float qX=((x/800f)*width);
		return qX;
	}
	public float modelToViewY(float y)
	{
		float qY=((y/450f)*height);
		return qY;
	}

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized(gameModel){
		int action = event.getAction();
	      updateX=viewToModelX(event.getX());
	      updateY=viewToModelY(event.getY());
	       
		
		if (action == MotionEvent.ACTION_DOWN) {
	    	   Log.d("TEST","REACHED PRESSDOWn");
	    	  
	           firstX = viewToModelX(event.getX());
	           firstY = viewToModelY(event.getY());
	           return true;
	       }else  if (action == MotionEvent.ACTION_MOVE){
	    	  if(Math.abs(updateX-firstX)>delta ){
		           if (gameModel.deg>-40) {
		               gameModel.deg -=3;
		               gameModel.rotateNeck(3); 
		           }
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
	    	   
	    	   if(firstX+20<updateX || firstX-20>updateX
	    			  ){ //&&firstY+20<event.getY() &&firstY-20>event.getY())
	    		   gameModel.rotateNeck(2); 
		           gameModel.deg=0;
		           Log.d("TEST", "MOTIONSWIPE");
	    		   /*makes sure giraffe isnt currently jumping so theres no double jump*/
		    	   Log.d("TESTING", ""+firstX+"  "+event.getRawX()+"  "+firstY+ "   "+ event.getRawY());

	    		  
	    	   }else{
	    		   if(!gameModel.currentlyJumping()){
	    			   gameModel.setJump(true);
	    		   }
	    	   }
	    	   return true;
	       }
	       
	      
	       return false;
		}
	}



	
}
