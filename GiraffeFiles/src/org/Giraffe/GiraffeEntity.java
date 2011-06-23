package org.Giraffe;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class GiraffeEntity extends Entity{
	Context context;
	int legsMove;
	
	int neck_x1=98;
	int neck_x2=218;
	int neck_y1=107;
   int neck_y2=327;
 
	
	public GiraffeEntity(Context context, long time) {
		super(context, time);
		this.context=context;
		
		//old image
		/*this.x1=1;
		this.x2=150;
		this.y1=150;
		this.y2=450;*/
		
		  this.x1=16;
		   this.x2=165;
		   this.y1=280;
		   this.y2=430;
		
		this.image=context.getResources().getDrawable(
                R.drawable.body);
		legsMove=0;
		
		
	}	
	public void move() {
		if(legsMove<6){
			this.image=context.getResources().getDrawable(
					R.drawable.giraffe);
			legsMove++;
		}else{
			this.image=context.getResources().getDrawable(
	                R.drawable.giraffe2);
			legsMove++;
			if(legsMove>12){
				legsMove=0;
			}
		}
		
	}

	@Override
	public void collided(Entity otherEntity) {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		return "giraffe";
	}
	public Drawable getNeck(){
		return context.getResources().getDrawable(
                R.drawable.neck);
	}
}

