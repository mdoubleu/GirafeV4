package org.Giraffe;

import android.content.Context;
public class Helicopter extends Enemy{
	public Helicopter(Context context, long time, float width, float height){
		super(context, time, width, height);
		cancollide=true;
		
		x1=modelToViewX(1600, width);
		x2=modelToViewX(1800, width);
		y1=modelToViewY(50, height);
		y2=modelToViewY(98, height);
		
		speed=modelToViewX(14, width)*(width/800f);

		this.hitBox.add(new HitBox("helicopter",x1,y1,x2,y2));
		this.image=context.getResources().getDrawable(
                R.drawable.helicopter);
	}	
	public void move() {
		x1=moveLeft(x1, (int)speed, 3);
		x2=moveLeft(x2, (int)speed, 3);
		this.hitBox.get(0).changePosition(this.x1, y1, x2, y2);
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
			
			
		}else if(otherHitBox.toString().equals("head")){
			image=context.getResources().getDrawable(R.drawable.kapow);
			SoundManager.playSound(3);
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

}

