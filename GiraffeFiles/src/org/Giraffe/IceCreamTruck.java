package org.Giraffe;

import android.content.Context;

public class IceCreamTruck extends Entity{
	Context context;
	public IceCreamTruck(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.context=context;

		this.x1=800;
		this.x2=950;
		this.y1=280;
		this.y2=400;
		this.image=context.getResources().getDrawable(
                R.drawable.creamtruck);
	}	
	public void move() {
		this.x1-=5;
		this.x2-=5;
	}

	@Override
	public void collided(Entity otherEntity) {
		if(otherEntity.toString().equals("body")){
			image=context.getResources().getDrawable(R.drawable.helicopter);
			
		}else if(otherEntity.toString().equals("head")){
			this.setDraw(false);
		}
		
	}
	public String toString(){
		return "icecream";
	}


}
