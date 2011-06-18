package org.Giraffe;

import android.content.Context;
import android.content.res.Resources;

public class Helicopter extends Entity{
	
	public Helicopter(Context context, long time) {
		super(context, time);
		//this.timeIn=time;
		Resources res = context.getResources();

		this.x1=800;
		this.x2=950;
		this.y1=6;
		this.y2=66;
		this.image=context.getResources().getDrawable(
                R.drawable.helicopter);
		

	}	
	public void move() {
		this.x1-=1;
		this.x2-=1;
	}

	public boolean collidesWith(Collidable otherEntity) {
		// TODO Auto-generated method stub
		return false;
	}


}

