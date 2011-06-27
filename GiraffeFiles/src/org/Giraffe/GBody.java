package org.Giraffe;

import android.content.Context;

public class GBody extends Entity {

	public GBody(Context context, long time, float width, float height){
		super(context, time, width, height);
		this.x1=1;
		this.x2=150;
		this.y1=300+GameModel.p;
		this.y2=400+GameModel.p;
		this.setDraw(false);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	public boolean canCollide() {
		return false;
	}

	@Override
	public void collided(Entity otherEntity) {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		return "body";
	}

}
