package org.Giraffe;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;

public class Fruit extends Entity 
{

	Context context;
	public Fruit(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.context=context;
		Resources res = context.getResources();
		 
		horizontalSpeed=modelToViewX(7f);
		Random rand = new Random();
		this.x1=modelToViewX(1600);
		this.x2=modelToViewX(1620);
		//These should be random
		int r = rand.nextInt(200)+100;
		
		this.y1= r;
		this.y2= r+35;
		this.image=context.getResources().getDrawable(
                R.drawable.fruit);
		this.hitBox.add(new HitBox("fruit",x1,y1,x2,y2));
	}	
	public void move() {
		this.x1=this.x1-(int)horizontalSpeed;
		this.x2=this.x2-(int)horizontalSpeed;
		this.hitBox.get(0).changePosition(x1,y1,x2,y2);
	}
	public String toString(){
		return "fruit";
	}
	@Override
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		if(otherHitBox.toString().equals("head")){
			this.setDraw(false);
			this.cancollide=false;
		}
		
	}
}
