package org.Giraffe;

import android.content.Context;
/**
 * The enemy class allows for an object other than the giraffe who can negatively affect the giraffe 
 * to be created
 * @author mikedoubleyouu
 *
 */
public abstract class Enemy extends Effects{
	/**
	 * Delay of time from hitting giraffe to disapearing 
	 */
	protected long delayOfTime;
	/**
	 * checks if object collided with giraffe and delays
	 */
	public boolean collidedWithGiraffe=false;
	
	public Enemy (Context context, long timeIn, float canvasWidth, float canvasHeight){
		this.context=context;
		this.timeIn=timeIn;
		this.canvasWidth=canvasWidth;
		this.canvasHeight=canvasHeight;
		cancollide=true;
	}
	/**
	 * Just going to compare enemy and giraffe's hitboxes
	 */
	public void collidesWithGiraffe(Enemy enemy, GiraffeEntity giraffe){//<----giraffe here 
		for (HitBox thisHitBox: enemy.getHitBox()){
			for(HitBox otherHitBox:giraffe.getHitBox()){
				if(thisHitBox.collidesWith(otherHitBox)){
					collided(thisHitBox, otherHitBox);
					giraffe.collided(otherHitBox, thisHitBox);
				}
			}
		}
	}
	
	/**
	 * Collision elements of enemy
	 */
	public abstract void collided(HitBox thisHitBox, HitBox otherHitBox); 
	public boolean canCollide() {return cancollide;}
	
	/**
	 * Delay of image to dissapear after collision. EX: helicopter to kapow to nothing.
	 */
	public void delayObstacleImage(long timeIn){
		if(System.currentTimeMillis()-timeIn>300){
			//this.setDraw(false);
		}
	}
	
	
}
