package org.Giraffe;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Tank extends Enemy
{	
	int T_x1;
	int T_x2;
	int T_y1;
	int T_y2;
	long time;
	Drawable tank;
	
	public Tank (Context context, long time, float width, float height){
		super(context, time, width, height);
		this.time=time;
		tank = context.getResources().getDrawable(
                R.drawable.g3);

		this.time=time;
	}
	public void move(){
		
	}
	public  long getTime(){
		return time;
	}
	public Drawable getImage(){
		return tank;
	}
	//At a certain time, the tank will fire


	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void defeated() {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		return "tank";
	}
	@Override
	public void collided(Entity otherEntity) {
		// TODO Auto-generated method stub
		
	}
}
