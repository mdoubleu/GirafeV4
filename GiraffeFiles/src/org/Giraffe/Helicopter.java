package org.Giraffe;

import android.content.Context;
public class Helicopter extends Entity{
	Context context;
	public Helicopter(Context context, long time, float width, float height){
		super(context, time, width, height);
		this.context=context;

		this.x1=800;
		this.x2=950;
		this.y1=6+GameModel.p;
		this.y2=66+GameModel.p;
		this.image=context.getResources().getDrawable(
                R.drawable.helicopter);
		

	}	
	public void move() {
		this.x1-=5;
		this.x2-=5;
	}

	@Override
	public void collided(Entity otherEntity) {
		if(otherEntity.toString().equals("body")){
			image=context.getResources().getDrawable(R.drawable.icecream);
			
		}else if(otherEntity.toString().equals("head")){
			this.setDraw(false);
		}
	}
	public String toString(){
		return "helicopter";
	}


}

