package org.Giraffe;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class GiraffeEntity extends Entity{
	Context context;
	int legsMove;
	
	int neck_x1=57;
	int neck_x2=220;
	int neck_y1=110+GameModel.p;
   int neck_y2=295+GameModel.p;
 
	
	public GiraffeEntity(Context context, long time, float width, float height) {
		super(context, time, width, height);
		this.context=context;
		
		//old image
		/*this.x1=1;
		this.x2=150;
		this.y1=150;
		this.y2=450;*/
		
		  this.x1=1;
		   this.x2=150;
		   this.y1=250+GameModel.p;
		   this.y2=400+GameModel.p;
		
		this.image=context.getResources().getDrawable(
                R.drawable.body);
		legsMove=0;
		
		
	}	
	public void move() {
		if(legsMove<6){
			this.image=context.getResources().getDrawable(
					R.drawable.body);
			legsMove++;
		}else{
			this.image=context.getResources().getDrawable(
	                R.drawable.body);
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

