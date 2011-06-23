package org.Giraffe;

import android.content.Context;
public class Helicopter extends Entity{
	Context context;
	public Helicopter(Context context, long time) {
		super(context, time);
		this.context=context;

		this.x1=800;
		this.x2=950;
		this.y1=6;
		this.y2=66;
		this.image=context.getResources().getDrawable(
                R.drawable.helicopter);
		

	}	
	public void move() {
		this.x1-=5;
		this.x2-=5;
	}

	public boolean collidesWith(Collidable otherEntity) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void collided(Entity otherEntity) {
		if(otherEntity.toString().equals("body")){
			image=context.getResources().getDrawable(R.drawable.helicopter);
			this.setDraw(false);
		}
	}
	public String toString(){
		return "helicopter";
	}


}

