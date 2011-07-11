package org.Giraffe;

import java.util.ArrayList;
import java.util.LinkedList;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class GiraffeEntity extends Effects{
	
	public final float ACCELERATION=-.0075f;
	public float INITVELOCITY;
	private enum gState {NORMAL, JUMPING, PRIMED, ATTACKING, ATTACKING2}
	/**
	 * Jumptime occuring while jump happens/
	 */
	private long jumpTime;
	/**
	 * Giraffes velocity used for physics jump
	 */
	private int gVel;
	private boolean canJump;
	private boolean currentlyJumping=false;
	/**State for giraffes neck position */
	gState myState;
	/**Difference in time for neck to go back to position*/
	long difference=0;
	long primeTime=0;
	private long delayOneSecond;
	private int doubleJumpCount;
	
	//pictures of giraffes
	private ArrayList <Drawable> cuteGiraffePics = new ArrayList <Drawable>();
	private LinkedList<Drawable>giraffeRun=new LinkedList<Drawable>();
   /**Controls the jump*/
	long jumpTimeFrozen;
   /**Controls the neck-attack*/
	long NeckTimeFrozen;
	
	int score;

	private Drawable getDrawable(String d)
	{
		Integer identifier = context.getResources().getIdentifier(d, "drawable", "org.Giraffe");
		return context.getResources().getDrawable(identifier);
	}
	
	public GiraffeEntity(Context context, long time, float width, float height) {
		this.context=context;
		cancollide=true;
		
		myState = gState.NORMAL;
		/**
		 * Sets the giraffe's pictures that will be used for the neck movement.
		 */

		cuteGiraffePics.add(getDrawable("giraffe"));
		cuteGiraffePics.add(getDrawable("giraffe90b"));
		cuteGiraffePics.add(getDrawable("giraffe90f"));
		giraffeRun.add(getDrawable("giraffe"));
		giraffeRun.add(getDrawable("grunear"));
		
		x1=modelToViewX(.3f, width);
		x2=modelToViewX(420, width);
		y1=modelToViewY(140, height);
		y2=modelToViewY(280, height);
		
		this.canvasWidth=width;
		this.canvasHeight=height;
		
		/**
		 * this is the velocity for the giraffe's jump.
		 */
		INITVELOCITY=modelToViewY(6f, height)*(width/800f);
		
		/**
		 * adds 3 hitboxes, head body and killbox.
		 */
		hitBox.add(new HitBox("body",modelToViewX(120, width),modelToViewY(230, height),modelToViewX(240, width),y2, true));
		hitBox.add(new HitBox("head",modelToViewX(240, width), modelToViewY(150, height),
				modelToViewX(310, width), modelToViewY(170, height), true));
		hitBox.add(new HitBox("killbox",x1,y1,x2,y2,false));
		
		
		setPic();
		doubleJumpCount=0;
		
		
	}
	/**
	 * Animates leg movement
	 */
	public void move(){
		if(myState==gState.NORMAL){
			image=animation(giraffeRun, 400);
		}
	}
	

	/**
	 * This method makes the giraffe disapear and reapear for 1.5 seconds only when it gets hit by an 
	 * enemy. After 1.5 seconds, the giraffe can collide again/can get hit.
	 * @param timeIn is used to check when the delay should start and end.
	 */
	public void delayCollideOneSecond(long timeIn){
		
		if(System.currentTimeMillis()-timeIn>1500){
			cancollide=true;
			setImage(true);
		}else if(System.currentTimeMillis()-timeIn>1250){
			setImage(false);
		}else if(System.currentTimeMillis()-timeIn>1000){
			setImage(true);	
		}else if(System.currentTimeMillis()-timeIn>600){
			setImage(false);
		}else if(System.currentTimeMillis()-timeIn>300){
			setImage(true);
		}else if(System.currentTimeMillis()-timeIn>0){
			setImage(false);
		}
		
	}
	public boolean canCollide() {
		delayCollideOneSecond(delayOneSecond);
		return cancollide;
	}
	
	/**
	 * This returns the image for the health to appear on screen and control gameover 
	 * @return strawberry image for health
	 */
	public Drawable healthImage(){
		return context.getResources().getDrawable(
                R.drawable.strawberry);
	}
	
	
	public void jump(){
		if(currentlyJumping==false){SoundManager.playSound(1);}
		long timeMil=0;		
		this.setCurrentJump(true);
		timeMil=System.currentTimeMillis()-jumpTime;
			
			gVel=(int)(INITVELOCITY+(ACCELERATION*timeMil));
			
			y1= y1-gVel;
			y2= y2-gVel;
					
			
			this.hitBox.get(0).changePosition(modelToViewX(120, canvasWidth), hitBox.get(0).y1()-gVel, modelToViewX(240, canvasWidth), hitBox.get(0).y2()-gVel);
			this.hitBox.get(1).changePosition(modelToViewX(240, canvasWidth), hitBox.get(1).y1()-gVel,modelToViewX(310, canvasWidth), hitBox.get(1).y2()-gVel);
			this.hitBox.get(2).changePosition(1, Y()-gVel, 230, Y2()-gVel);
			
			/**
			 * This occurs when the Giraffe gets back to the bottom of the screen and stops moving*/
			if(y2>=modelToViewY(280, canvasHeight)){
				doubleJumpCount=0;
				setJump(false);
				this.setCurrentJump(false);
				//resets giraffe coordinates
				y1=modelToViewY(140, canvasHeight);
				y2=modelToViewY(280, canvasHeight);
				this.hitBox.get(0).changePosition(modelToViewX(120, canvasWidth),modelToViewY(230, canvasHeight),modelToViewX(240, canvasWidth),y2);
				this.hitBox.get(1).changePosition(modelToViewX(240, canvasWidth), modelToViewY(150, canvasHeight),
						modelToViewX(310, canvasWidth), modelToViewY(170, canvasHeight));
			}
	}
	public void updateJumpCount(){
		if(doubleJumpCount==2){
			doubleJumpCount=0;
		}
		doubleJumpCount++;
	}
	public int getDoubleJump(){return doubleJumpCount;}
	
	/**
	 * allows giraffe to jump and creates a jumptime for the physics.
	 * @param canJ
	 */
	public void setJump(boolean canJ){
		jumpTime=System.currentTimeMillis()+0;
		canJump=canJ;
	}
	/**
	 * checks if the giraffe can jump.
	 * @return
	 */
	public boolean getJump(){return canJump;}
	/**
	 * setcanJump sets whether the giraffe is currently canJump or not.
	 * @param currentlyJumping
	 */
	public void setCurrentJump(boolean currentlyJumping){this.currentlyJumping=currentlyJumping;}
	/**
	 * is canJump returns whethere the giraffe is currently canJump;
	 * @return
	 */
	public boolean currentlyJumping(){return currentlyJumping;}
	
	/**
	 * Sets picture based on attacking, primed or normal. Primed is neck back, and attacking is neck forward.
	 */
	public void setPic() {		
		updateTime();
		switch(myState) {
		case ATTACKING:
				image=cuteGiraffePics.get(2);
				hitBox.get(2).collide(true);
				if (difference>=600 || difference==0){
					myState=gState.NORMAL;
				}
			
		break;
		case ATTACKING2:
				//this.image=cuteGiraffePics.get(3);
		case NORMAL:
			hitBox.get(2).collide(false);
			image=cuteGiraffePics.get(0);
		break;
		case PRIMED:
			this.image=cuteGiraffePics.get(1);
			break;
		}
	}
	
	public void setToAttack() {myState = gState.ATTACKING;}
	public void setToJump() {myState = gState.JUMPING;}
	public void setToPrime () {myState = gState.PRIMED;}
	public void setToNormal () {myState = gState.NORMAL;}
	public void setTime () {primeTime = System.currentTimeMillis()+0;}
	public void updateTime() {difference=System.currentTimeMillis()-primeTime;}
	public String toString(){return "giraffe";}
	public String getScore(){return ""+score;}

	public void collidesWithGiraffe(Enemy enemy, GiraffeEntity giraffe) {
		
	}
	/**
	 * When collision occurs, the giraffe reacts accordingly.
	 */
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		if(otherHitBox.toString().equals("icecream")&&thisHitBox.toString().equals("killbox")){
			score+=10;
		}else if(otherHitBox.toString().equals("helicopter")&&thisHitBox.toString().equals("killbox")){
			score+=20;
		}
		else if(otherHitBox.toString().equals("icecream")&&thisHitBox.toString().equals("body")
				){
			health=loseHealth(health, 1);
			this.cancollide=false;
			delayOneSecond=System.currentTimeMillis()+0;
			/**
			 * Makes the giraffe blink, and after 1.5 seconds, cancollide is true
			 */
			delayCollideOneSecond(delayOneSecond);
		}
		else if(otherHitBox.toString().equals("helicopter")&&thisHitBox.toString().equals("body")
					||otherHitBox.toString().equals("helicopter")&&thisHitBox.toString().equals("head"))
					{
			health=loseHealth(health, 1);
			this.cancollide=false;
			delayOneSecond=System.currentTimeMillis()+0;
			delayCollideOneSecond(delayOneSecond);
					}
		else if(otherHitBox.toString().equals("netv")&&thisHitBox.toString().equals("body")
				||otherHitBox.toString().equals("netv")&&thisHitBox.toString().equals("head"))
				{
		health=loseHealth(health, 1);
		this.cancollide=false;
		delayOneSecond=System.currentTimeMillis()+0;
		delayCollideOneSecond(delayOneSecond);
				}
		else if(otherHitBox.toString().equals("fruit")&&thisHitBox.toString().equals("head")){
			health=addHealth(health, 1);
		}

	}

}

