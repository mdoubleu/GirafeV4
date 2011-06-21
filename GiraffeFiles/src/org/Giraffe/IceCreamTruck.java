package org.Giraffe;

import android.content.Context;
import android.content.res.Resources;

public class IceCreamTruck extends Entity{
	Context context;
	public IceCreamTruck(Context context, long time) {
		super(context, time);
		this.context=context;
		Resources res = context.getResources();

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
		if(otherEntity.toString().equals("giraffe")){
			image=context.getResources().getDrawable(R.drawable.helicopter);
			this.setDraw(false);
		}
		
	}
	public String toString(){
		return "icecream";
	}


}
