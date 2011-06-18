package org.Giraffe;

import android.content.Context;
import android.content.res.Resources;

public class GiraffeEntity extends Entity{
	Context context;
	int legsMove;
	public GiraffeEntity(Context context, long time) {
		super(context, time);
		this.context=context;
		Resources res = context.getResources();

		this.x1=0;
		this.x2=150;
		this.y1=150;
		this.y2=450;
		this.image=context.getResources().getDrawable(
                R.drawable.giraffe);
		legsMove=0;
	}	
	public void move() {
		if(legsMove==0){
			this.image=context.getResources().getDrawable(
					R.drawable.giraffe2);
			legsMove=1;
		}else{
			this.image=context.getResources().getDrawable(
	                R.drawable.giraffe);
			legsMove=0;
		}
		
		
	}
	public void moveFeet(){
		
	}

	public boolean collidesWith(Collidable otherEntity) {
		
		return false;
	}
}

