package org.Giraffe;

import java.util.LinkedList;

import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Mechanics allows entites to use different states. 
 */
public abstract class Mechanics {
	
	private boolean imageDraw=true;
	private long moveLeftTime=System.currentTimeMillis()+0;
	private long animationTime=System.currentTimeMillis()+0;
	private int animationCount=-1;
	/**
	 * Enemies can shoot objects to the left
	 */
	public  void shoot(){}
	
	public int moveLeft(int value, int moveBy, int timeToWait){
		
		if(System.currentTimeMillis()-moveLeftTime>timeToWait){
			moveLeftTime=System.currentTimeMillis();
		}
		return value-moveBy;
	}
	public  void moveRight(){}
	public  void moveUp(){}
	
	public int moveDown(int value, int moveBy, int timeToWait)
	{
		return moveLeft(value,moveBy,timeToWait);
	}
	
	public  void moveParabola(){}
	
	public  void speedUp(){}
	public  void slowDown(){}
	public  void setSpeed(){}
	
	/**
	 * controls health level
	 * @param h health level
	 */
	public int loseHealth(int health, int minus){
		return health-minus;
	}
	public int addHealth(int health, int add){
		return health+add;
	}
	
	/**
	 * Fling is a melee attack similiar to a hammer 
	 */
	public void fling(){}
	
	/**
	 * Transforms this object or enemy into something else via image
	 */
	public void changeImage(){}
	public boolean drawImage(){	return imageDraw;}
	public void setImage(boolean imageCheck){imageDraw=imageCheck;}
	
	public Drawable animation(LinkedList<Drawable> images, int timeToWait){
		
		
		if(System.currentTimeMillis()-animationTime>timeToWait){
			if(animationCount+1==images.size()){
				animationCount=-1;
			}
			if(animationCount+1<images.size()){
				animationCount++;
			}
			animationTime=System.currentTimeMillis();
		}
		
		if(animationCount<0){
			animationCount=0;
		}
		return images.get(animationCount);
	}
	
	public int modelToViewX(float x, float width){
		float gx=((x/800f)*width);
		return (int)gx;
	}
	public int modelToViewY(float y, float height){
		float gx=((y/480f)*height);
		return (int)gx;
	}

}
