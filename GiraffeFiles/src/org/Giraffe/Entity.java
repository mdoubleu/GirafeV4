package org.Giraffe;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;


//Represents any element in the game
public abstract class Entity  extends View implements Collidable{
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected long timeIn; //when the enemy will appear on screen
	protected String imageName;
	protected Drawable image;
	boolean check=true;


	public Entity(Context context, long timeIn) {
		super(context);
		this.timeIn=timeIn;

	}
	public void setDraw(boolean check){
		this.check=check;
	}
	public boolean doDraw(){
		
		return check;
	}
	public int getX(){
		return x1;
	}
	public int getX2(){
		return x2;
	}
	public int getY(){
		return y1;
	}
	public int getY2(){
		return y2;
	}
	public abstract void move();
	
	public  long getTime(){
		return timeIn;
	}
	
	public  Drawable getImage(){
		return image;
	}

	public boolean collidesWith(Entity o) {
		//checks intersections of entities radii
		 double r1= Math.sqrt(.25*(x2-x1)*(x2-x1)+.25*(y2-y1)*(y2-y1));
		 double r2= Math.sqrt(.25*(o.x2-o.x1)*(o.x2-o.x1)+.25*(o.y2-o.y1)*(o.y2-o.y1));
		  //double colD = (r1+r2);
		  double cX1= ((x1+x2)/2);
		  double cY1= ((y1+y2)/2);
		  double cX2= ((o.x1+o.x2)/2);
		  double cY2= ((o.y1+o.y2)/2); 
		 if(this.toString().equals("giraffe")){
			 Log.d("X", ((r1+r2)*(r1+r2)) +"  " + (cX1-cX2)*(cX1-cX2)+(cY1-cY2)*(cY1-cY2));
		 }
		 double check1=((r1+r2)*(r1+r2));
		 double check2=(cX1-cX2)*(cX1-cX2)+(cY1-cY2)*(cY1-cY2);
		 if(check1>check2){
			 Log.d("WORKKKK!", "WORK GIRAFFE");
		 }
		 
		  //double eDistanceX= (disk1.getX()-disk2.getX());
		  //width is the same as height for a square
		  //double eDistanceY= (disk1.getY()-disk2.getY());
		  //double eDistanceX= (cX1-cX2);
		  //double eDistanceY= (cY1-cY2);
		 		 
		  if(((r1+r2)*(r1+r2))>((cX1-cX2)*(cX1-cX2)+(cY1-cY2)*(cY1-cY2)))
		  {
		  // this.x1=90;
		   //this.x2=300;
			  Log.d("LOOOK HEREE",this.toString()+ "  This has collided with "+ o.toString() );
		   return true;
		  }

		  else
		  {
		   return false;
		  }

	}
 
	public abstract void collided(Entity otherEntity);
 
	public boolean canCollide() {
		return true;
	}
}