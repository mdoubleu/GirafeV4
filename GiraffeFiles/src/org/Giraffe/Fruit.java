package org.Giraffe;

import java.util.Random;
import android.content.Context;

public class Fruit extends Enemy 
{

	Context context;
	public Fruit(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.context=context;
		 
		speed=modelToViewX(5f, width)*(width/800);
		Random rand = new Random();
		this.x1=modelToViewX(1600, width);
		this.x2=modelToViewX(1625, width);
		//These should be random
		int r = rand.nextInt(100)+150;
		this.y1= modelToViewX(r, height);;
		this.y2= modelToViewX(r+35, height);;
		this.image=context.getResources().getDrawable(
                R.drawable.fruit);
		this.hitBox.add(new HitBox("fruit",x1,y1,x2,y2,true));
	}	
	public void move() {
		x1=moveLeft(x1, (int)speed, 5);
		x2=moveLeft(x2, (int)speed, 5);
		this.hitBox.get(0).changePosition(x1,y1,x2,y2);
	}
	public String toString(){
		return "fruit";
	}
	@Override
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		if(otherHitBox.toString().equals("head")){
			setImage(false);
			this.cancollide=false;
		}
	}
}
