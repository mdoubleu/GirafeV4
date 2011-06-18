package org.Giraffe;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;


//Represents any element in the game
public abstract class Entity  extends View implements Collidable{
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected long timeIn; //when the enemy will appear on screen
	protected String imageName;
	protected Drawable image;


	public Entity(Context context, long timeIn) {
		super(context);
		this.timeIn=timeIn;

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

	public boolean collidesWith(Entity otherEntity) {

		return true;

	}
 
	public void collided(Collidable otherEntity) {
	}
 
	public boolean canCollide() {

		return true;

	}
}