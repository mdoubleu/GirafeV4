package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;
public class Helicopter extends Entity{
	Context context;
	public Helicopter(Context context, long time, float width, float height){
		super(context, time, width, height);
		this.context=context;
		this.hitBox.add(new HitBox("icecream",800,70,950,150));
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
		this.hitBox.get(0).changePosition(this.x1, 210, 950, this.x2);
	}

	@Override
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		
		/*if(otherEntity.toString().equals("body")){
			image=context.getResources().getDrawable(R.drawable.icecream);
			
		}else if(otherEntity.toString().equals("head")){
			image=context.getResources().getDrawable(R.drawable.kapow);
			//this.setDraw(false);
		}*/
	}
	public String toString(){
		return "helicopter";
	}
	@Override
	public ArrayList<HitBox> getHitBox() {
		// TODO Auto-generated method stub
		return this.hitBox;
	}


}

