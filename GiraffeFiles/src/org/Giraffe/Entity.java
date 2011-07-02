package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;



//Represents any element in the game
public abstract class Entity  implements Collidable{
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected long timeIn; //when the enemy will appear on screen
	protected String imageName;
	protected Drawable image;
	boolean check=true;
	protected ArrayList<HitBox> hitBox=new ArrayList<HitBox>();
	protected int health=1;
	protected boolean cancollide=true;

	public Entity(Context context, long timeIn, float Cwidth, float Cheight) {

		this.timeIn=timeIn;

	}
	public  ArrayList<HitBox> getHitBox(){
		return hitBox;
	}public int getHealth(){
		return health;
	}
	public void setHealth(int h){
		health=h;
	}
	
	public void setDraw(boolean check){
		this.check=check;
	}
	public boolean doDraw(){
		
		return check;
	}
	public int getX(){
		return x1;
	}
	public int getX2(){
		return x2;
	}
	public int getY(){
		return y1;
	}
	public int getY2(){
		return y2;
	}
	public abstract void move();
	
	public  long getTime(){
		return timeIn;
	}
	
	public  Drawable getImage(){
		return image;
	}

	public boolean collidesWith(Entity thisE, Entity other) {
		//checks intersections of entities radii
		//ArrayList<HitBox> ohitbox=other.getHitBox();
		for (HitBox thisHitBox: thisE.getHitBox()){
			for(HitBox otherHitBox:other.getHitBox()){
				
				if(!thisHitBox.toString().equals(otherHitBox.toString())){
					int checkt=(int)thisHitBox.x2();
					int checko=(int)otherHitBox.x1();
				
					 if(Math.abs(checkt-checko)<10){
						 Log.d("C", ""+checkt+"  is  bigger than "+checko);
						 this.collided(thisHitBox, otherHitBox);
						 other.collided(otherHitBox, thisHitBox);
						 return true;
					 }
					
				}
				
			}
		}
		   return false;
		  

	}
 
	public abstract void collided(HitBox thisHitBox, HitBox otherHitBox);
 
	public boolean canCollide() {
		return cancollide;
	}
	public abstract String toString();
}