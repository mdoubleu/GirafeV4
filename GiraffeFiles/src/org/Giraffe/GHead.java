package org.Giraffe;

import android.content.Context;

public class GHead extends Entity{

	public GHead(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.x1=97;
		this.x2=218;
		this.y1=140+GameModel.p;
		this.y2=220+GameModel.p;
		this.image=context.getResources().getDrawable(
                R.drawable.body);
		this.setDraw(false);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collided(Entity otherEntity) {
		
	}

	@Override
	public String toString() {
		return "head";
	}

}
