package org.Giraffe;

import android.content.Context;
import android.content.res.Resources;

public class Box extends Entity {

	int bX1=570;
	int bX2=650;
	int bY1=240;
	int bY2=415;

	public Box(Context context, long time) {
		super(context, time);
		this.timeIn=time;
		Resources res = context.getResources();

		this.x1=300;
		this.x2=450;
		this.y1=225;
		this.y2=450;
		this.image=context.getResources().getDrawable(
                R.drawable.icecream);
	}



	@Override
	public void move() {
		this.x1-=10;
		this.x1-=10;
		
	}

	@Override
	public long getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void collided(Entity otherEntity) {
		// TODO Auto-generated method stub
		
	}


}