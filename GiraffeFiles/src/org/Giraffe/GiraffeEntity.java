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
	private long delayOneSecond;
	
	//pictures of giraffes
	public ArrayList <Drawable> cuteGiraffePics = new ArrayList <Drawable>();
   /*Controls the jump*/
	long jumpTimeFrozen;
   /*Controls the neck-attack*/
	long NeckTimeFrozen;

	private Drawable getDrawable(String d)
	{
		Integer identifier = context.getResources().getIdentifier(d, "drawable", "org.Giraffe");
		return context.getResources().getDrawable(identifier);
	}
	
	public GiraffeEntity(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.context=context;
		myState = gState.NORMAL;
		
		for (int i =1; i<=5; i++){
			String s = "g"+i;
			cuteGiraffePics.add(getDrawable(s));
		}
		
		   this.x1=1;
		   this.x2=150;
		   this.y1=230;
		   this.y2=490;
		
		   this.hitBox.add(new HitBox("body",1,350,100,y2));
		   this.hitBox.add(new HitBox("head",70, 240,160, 310));
		
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
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		
		if(otherHitBox.toString().equals("icecream")&&thisHitBox.toString().equals("body")){
			setHealth(this.getHealth()-1);
			this.cancollide=false;
			delayOneSecond=System.currentTimeMillis()+0;
			delayCollideOneSecond(delayOneSecond);
			//call method that shall check how long it is i say 1 second and then sets true;
			
		}else if(otherHitBox.toString().equals("icecream")&&thisHitBox.toString().equals("head")){
			if(getHealth()==0){
			//endlevelYOULOSE!
			}
		}else if(otherHitBox.toString().equals("helicopter")&&thisHitBox.toString().equals("body")){
			
		}
		
	}
	public void delayCollideOneSecond(long timeIn){
		
		if(System.currentTimeMillis()-timeIn>1000){
			this.cancollide=true;
			this.setDraw(true);
		}else if(System.currentTimeMillis()-timeIn>600){
			this.setDraw(false);
		}else if(System.currentTimeMillis()-timeIn>300){
			this.setDraw(true);
		}else if(System.currentTimeMillis()-timeIn>0){
			this.setDraw(false);
		}
		
	}
	public boolean canCollide() {
		delayCollideOneSecond(delayOneSecond);
		return cancollide;
	}
	public String toString(){
		return "giraffe";
	}
	public Drawable healthImage(){
		return context.getResources().getDrawable(
                R.drawable.strawberry);
		
	}
	
	
	public void jump(){
		long timeMil=0;			
		
			this.setCurrentJump(true);
			timeMil=System.currentTimeMillis()-jumpTime;
		
			gVel=(int)(INITVELOCITY+(ACCELERATION*timeMil));
			
			y1= (y1-gVel);
			y2= (y2-gVel);
			   
			this.hitBox.get(0).changePosition(1, getY()-gVel, 100, getY2()-gVel);
			this.hitBox.get(1).changePosition(70, getY()-gVel, 160, getY2()-gVel);
			
			
			if(this.hitBox.size()>2){
				this.hitBox.get(2).changePosition(1, getY()-gVel, 230, getY2()-gVel);
			}
	

			//Log.d("Ok", "gVel= "+gVel+ " time="+timeMil+" tempY1: "+ tempY1 +"  ACCELERATION"+ACCELERATION+"   INIT"+INITVELOCITY+
				//	"   \n"+ ourGiraffe.y1+ "      "+ ourGiraffe.y2);
			
			/*This occurs when the Giraffe gets back to the bottom of the screen*/
			if(y2>=410+p){
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
		updateTime();
		switch(myState) {
		case ATTACKING:
			
				this.image=cuteGiraffePics.get(4);
				myState=gState.NORMAL;
				this.hitBox.add(new HitBox("killbox",1,230, 230, 490));
				
			
		break;
		case ATTACKING2:
			
				this.image=cuteGiraffePics.get(3);
			
		case NORMAL:
			if(this.hitBox.size()>2 &&difference>=500 ){
				this.hitBox.remove(2);
			}
			if (difference>=600 || difference==0) 
				this.image=cuteGiraffePics.get(2);
		break;
		case PRIMED:
			this.image=cuteGiraffePics.get(0);
			
			break;
			
		}
		
		
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
	}
	public void updateTime() {
		difference=System.currentTimeMillis()-primeTime;
	}
}

