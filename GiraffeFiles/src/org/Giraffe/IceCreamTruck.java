package org.Giraffe;

import android.content.Context;

/**
 * Icecreamtruck rolls along and collides with giraffe.
 * @author mikedoubleyouu
 *
 */
public class IceCreamTruck extends Enemy{
	public IceCreamTruck(Context context, long time, float width, float height) {
		super(context, time, width, height);
		cancollide=true;
		
		x1=modelToViewX(1600, width);
		x2=modelToViewX(1800, width);
		y1=modelToViewY(210, height);
		y2=modelToViewY(270, height);
		
		speed=modelToViewX(16, width)*(width/800f);
		
		this.hitBox.add(new HitBox("icecream", x1, y1, x2, y2, true));
		this.image=context.getResources().getDrawable(
                R.drawable.creamtruck);
	}	
	public void move() {
		x1=moveLeft(x1, (int)speed, 5);
		x2=moveLeft(x2, (int)speed, 5);
		this.hitBox.get(0).changePosition(x1, y1, x2, y2);
	}

	@Override
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		if(otherHitBox.toString().equals("killbox")){
			image=context.getResources().getDrawable(R.drawable.kapow2);
			SoundManager.playSound(4);
			
			this.cancollide=false;
			collidedWithGiraffe=true;
			delayOfTime=System.currentTimeMillis()+0;
			delayObstacleImage(delayOfTime);
			
		}else if(otherHitBox.toString().equals("body")){
			image=context.getResources().getDrawable(R.drawable.kapow);
			SoundManager.playSound(3);
			this.cancollide=false;
			collidedWithGiraffe=true;
			delayOfTime=System.currentTimeMillis()+0;
			delayObstacleImage(delayOfTime);

		}else if(otherHitBox.toString().equals("head")){
			
		}
		
	}
	
	public boolean canCollide() {
		if(collidedWithGiraffe){
			delayObstacleImage(delayOfTime);
		}
		return cancollide;
	}
	public String toString(){
		return "icecream";
	}


}
