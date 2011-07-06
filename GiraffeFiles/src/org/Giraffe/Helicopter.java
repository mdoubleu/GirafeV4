package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;
public class Helicopter extends Entity{
	Context context;
	public Helicopter(Context context, long time, float width, float height){
		super(context, time, width, height);
		this.context=context;
		this.x1=800;
		this.x2=950;
		this.y1=86;
		this.y2=146;
		this.hitBox.add(new HitBox("helicopter",x1,y1,x2,y2));
		
		this.image=context.getResources().getDrawable(
                R.drawable.helicopter);
		

	}	
	public void move() {
		this.x1-=5;
		this.x2-=5;
		this.hitBox.get(0).changePosition(this.x1, y1, x2, y2);
	}

	@Override
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		if(otherHitBox.toString().equals("killbox")){
			image=context.getResources().getDrawable(R.drawable.kapow2);
			
			this.cancollide=false;
			collidedWithGiraffe=true;
			delayOfTime=System.currentTimeMillis()+0;
			delayObstacleImage(delayOfTime);
			
			
		}else if(otherHitBox.toString().equals("head")){
			image=context.getResources().getDrawable(R.drawable.kapow);
			
			this.cancollide=false;
			collidedWithGiraffe=true;
			delayOfTime=System.currentTimeMillis()+0;
			delayObstacleImage(delayOfTime);

		}
	}
	public boolean canCollide() {
		if(collidedWithGiraffe){
			delayObstacleImage(delayOfTime);
		}
		
		return cancollide;
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

