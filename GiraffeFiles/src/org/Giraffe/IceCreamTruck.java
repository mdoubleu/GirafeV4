package org.Giraffe;
import android.content.Context;

public class IceCreamTruck extends Entity{
	Context context;
	public IceCreamTruck(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.context=context;
		this.x1=800;
		this.x2=950;
		this.y1=380;
		this.y2=490;
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
