package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class GiraffeEntity extends Entity{
	Context context;
	int legsMove;
	public static final int p=80;
	//physics
	public static final float ACCELERATION=-.0075f;
	public static final float INITVELOCITY=10f;
	private enum gState {NORMAL, JUMPING, PRIMED, ATTACKING, ATTACKING2}
	//jump stuff
	private long jumpTime;
	private int gVel;
	private boolean canJump;
	private boolean currentlyJumping=false;
	gState myState;
	//neck stuff
	long difference=0;
	long primeTime=0;
	private boolean currentlyAttacking=false;
	
	//pictures of giraffes
	public ArrayList <Drawable> cuteGiraffePics = new ArrayList <Drawable>();
	
	int neck_x1=57;
	int neck_x2=220;
	int neck_y1=110+GameModel.p;
   int neck_y2=295+GameModel.p;
 
   /*Controls the jump*/
	long jumpTimeFrozen;
   /*Controls the neck-attack*/
	long NeckTimeFrozen;

	private Drawable getDrawable(String d)
	{
		Integer identifier = context.getResources().getIdentifier(d, "drawable", "org.Giraffe");
		Log.d("I GOT HERE",identifier.toString());
		return context.getResources().getDrawable(identifier);
	}
	
	public GiraffeEntity(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.context=context;
		myState = gState.NORMAL;
		
		for (int i =1; i<=5; i++){
			
			String s = "g"+i;
			
			Log.d("STRING S", s);
			
			cuteGiraffePics.add(getDrawable(s));
		}
		
		   this.x1=1;
		   this.x2=150;
		   this.y1=250+GameModel.p;
		   this.y2=400+GameModel.p;
		
		setPic();/*context.getResources().getDrawable(
                R.drawable.giraffe);*/
		legsMove=0;
		
		
	}	
	public void move() {
		if(legsMove<6){
			setPic();/*context.getResources().getDrawable(
					R.drawable.giraffe);*/
			legsMove++;
		}else{
			setPic();//this.image=cuteGiraffePics.get(2);/*context.getResources().getDrawable(
	    //            R.drawable.giraffe);*/
			legsMove++;
			if(legsMove>12){
				legsMove=0;
			}
		}
		
	}

	@Override
	public void collided(Entity otherEntity) {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		return "giraffe";
	}
	public Drawable getNeck(){
		return context.getResources().getDrawable(
                R.drawable.neck);
	}
	
	
	
	public void jump(){
		long timeMil=0;			
		
			this.setCurrentJump(true);
			timeMil=System.currentTimeMillis()-jumpTime;
		
			gVel=(int)(INITVELOCITY+(ACCELERATION*timeMil));
			
			y1= (y1-gVel);
			y2= (y2-gVel);
			

			//Log.d("Ok", "gVel= "+gVel+ " time="+timeMil+" tempY1: "+ tempY1 +"  ACCELERATION"+ACCELERATION+"   INIT"+INITVELOCITY+
				//	"   \n"+ ourGiraffe.y1+ "      "+ ourGiraffe.y2);
			
			/*This occurs when the Giraffe gets back to the bottom of the screen*/
			if(y2>=398+p){
				setJump(false);
				this.setCurrentJump(false);
			}
	}
	
	
	//allows giraffe to jump
	public void setJump(boolean canJ){
		jumpTime=System.currentTimeMillis()+0;
		canJump=canJ;
	}
	//checks if the giraffe can jump.
	public boolean getJump(){
		return canJump;
	}
	//setcanJump sets whether the giraffe is currently canJump or not.
	public void setCurrentJump(boolean currentlyJumping){
		this.currentlyJumping=currentlyJumping;
	}
	//is canJump returns whethere the giraffe is currently canJump;
	public boolean currentlyJumping(){
		return currentlyJumping;
	}
	
	public void setPic() {		
		switch(myState) {
		case ATTACKING:
			
				this.image=cuteGiraffePics.get(4);
				myState=gState.NORMAL;
				
			
		break;
		case ATTACKING2:
			
				this.image=cuteGiraffePics.get(3);
			
		case NORMAL:
			if (difference>=600 || difference==0) 
				this.image=cuteGiraffePics.get(2);
		break;
		case PRIMED:
			this.image=cuteGiraffePics.get(0);
			
			break;
			
		}
		
		
		/*
		if (myState == gState.ATTACKING) {
			this.image=cuteGiraffePics.get(i);
			if (System.currentTimeMillis()-attackTime == 100 && i < 5) {
				i++;
				attackTime = System.currentTimeMillis();
				
			} else if (i >= 5 ) {
				setToNormal();
				attackTime = System.currentTimeMillis();
				
			}
		}
		if (myState == gState.NORMAL) {
			this.image=cuteGiraffePics.get(i);
			if (System.currentTimeMillis()-attackTime == 100 && i >=3) {
				i--;
				attackTime = System.currentTimeMillis();
			}
		}
		
		if (myState == gState.PRIMED) {
			this.image=cuteGiraffePics.get(i);
			if (System.currentTimeMillis()-attackTime == 100 && i >= 0) {
				i--;
				attackTime = System.currentTimeMillis();
			}
		}*/
		
		
	}
	
	public void setToAttack() {
		myState = gState.ATTACKING;
		
		
	}
	
	public void setToJump() {
		myState = gState.JUMPING;
	}
	
	public void setToPrime () {
		myState = gState.PRIMED;
		
	}
	
	public void setToNormal () {
		myState = gState.NORMAL;
	}
	
	public void setTime () {
		primeTime = System.currentTimeMillis()+0;
		Log.d("Look HERE!", ""+primeTime);
	}
	public void updateTime() {
		difference=System.currentTimeMillis()-primeTime;
	}
}

