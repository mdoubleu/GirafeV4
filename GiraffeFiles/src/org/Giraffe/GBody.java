package org.Giraffe;

import android.content.Context;

public class GBody extends Entity {

	public GBody(Context context, long timeIn) {
		super(context, timeIn);
		this.x1=1;
		this.x2=150;
		this.y1=300;
		this.y2=400;
		this.setDraw(false);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collided(Entity otherEntity) {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		return "body";
	}

}
