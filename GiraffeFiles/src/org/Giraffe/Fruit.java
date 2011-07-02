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
		 
		Random rand = new Random();
		this.x1=800;
		this.x2=830;
		//These should be random
		int r = rand.nextInt(200)+100;
		this.y1= r;
		this.y2= r+35;
		this.image=context.getResources().getDrawable(
                R.drawable.fruit);
	}	
	public void move() {
		this.x1-=5;
		this.x2-=5;
	}
	public String toString(){
		return "fruit";
	}
	@Override
	public void collided(HitBox thisHitBox, HitBox otherHitBox) {
		if(otherHitBox.toString().equals("head")){
			this.setDraw(false);
		}
		
	}
}
