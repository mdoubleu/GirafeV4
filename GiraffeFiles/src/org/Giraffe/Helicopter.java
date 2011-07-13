package org.Giraffe;

import java.util.ArrayList;
import java.util.LinkedList;

import android.content.Context;
import android.graphics.drawable.Drawable;
public class Helicopter extends Enemy{


	private int hasNet;
	private boolean payload;

	
	private LinkedList <Drawable> helicopterPics = new LinkedList <Drawable>();

	public Helicopter(Context context, long time, float width, float height){
		super(context, time, width, height);
		cancollide=true;
		
		x1=modelToViewX(1600, width);
		x2=modelToViewX(1800, width);
		y1=modelToViewY(50, height);
		y2=modelToViewY(98, height);
		
		helicopterPics.add(context.getResources().getDrawable( R.drawable.helicopter1));
		helicopterPics.add(context.getResources().getDrawable( R.drawable.helicopter2));

		image = helicopterPics.get(0);
		
		speed=modelToViewX(14, width)*(width/800f);

		this.hitBox.add(new HitBox("helicopter",x1,y1,x2,y2,true));
		
		this.image=context.getResources().getDrawable(
                R.drawable.helicopter1);

		this.hasNet = GameModel.r.nextInt(2);

		if(hasNet > 0)
		{
			 payload = true;
		}
	}	
	public void move() {
		x1=moveLeft(x1, (int)speed, 3);
		x2=moveLeft(x2, (int)speed, 3);
		
		if(collidedWithGiraffe == false)
		{
			image = animation(helicopterPics,100);
		}
		
		this.hitBox.get(0).changePosition(this.x1, y1, x2, y2);

		if(x1 < 110 && payload == true && this.cancollide==true)
		{
			NetV netv = new NetV(context, delayOfTime, canvasHeight, canvasHeight);
			GameModel.levelObjects.add(netv);
			payload = false;
		}
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
