package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;

public class IceCreamTruck extends Entity{
	Context context;
	public IceCreamTruck(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.context=context;
		this.x1=800;
		this.x2=950;
		this.y1=250+GameModel.p;
		this.y2=400+GameModel.p;
		this.hitBox.add(new HitBox("icecream",this.x1, y1, x2, y2));
		this.image=context.getResources().getDrawable(
                R.drawable.creamtruck);
	}	
	public void move() {
		this.x1-=5;
		this.x2-=5;
		this.hitBox.get(0).changePosition(this.x1, y1, x2, y2);
	}

	@Override
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		if(otherHitBox.toString().equals("head")){
			image=context.getResources().getDrawable(R.drawable.kapow);
			
		}else if(otherHitBox.toString().equals("body")){
			image=context.getResources().getDrawable(R.drawable.helicopter);
		}
		/*if(otherEntity.toString().equals("body")){
			image=context.getResources().getDrawable(R.drawable.kapow);
			
		}else if(otherEntity.toString().equals("head")){
			this.setDraw(false);
		}*/
		
	}
	public String toString(){
		return "icecream";
	}
	@Override
	public ArrayList<HitBox> getHitBox() {
		// TODO Auto-generated method stub
		return this.hitBox;
	}



}
