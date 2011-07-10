package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;



//Represents any element in the game
public abstract class Entity  implements Collidable{
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected float Cwidth;
	protected float Cheight;
	protected long timeIn; //when the enemy will appear on screen
	protected String imageName;
	protected Drawable image;
	boolean check=true;
	protected ArrayList<HitBox> hitBox=new ArrayList<HitBox>();
	protected int health=1;
	protected boolean cancollide=true;
	protected long delayOfTime;
	public boolean collidedWithGiraffe=false;
	protected float horizontalSpeed;

	public Entity(Context context, long timeIn, float Cwidth, float Cheight) {
		
		this.timeIn=timeIn;

	}
	public void delayObstacleImage(long timeIn){
		if(System.currentTimeMillis()-timeIn>300){
			this.setDraw(false);
		}
		
	}
	public int modelToViewX(float x){
		float gx=((x/800f)*Cwidth);
		return (int)gx;
	}
	public int modelToViewY(float y){
		float gx=((y/480f)*Cheight);
		return (int)gx;
	}
	public  ArrayList<HitBox> getHitBox(){
		return hitBox;
	}public int getHealth(){
		return health;
	}
	public void setHealth(int h){
		health=h;
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

	public boolean collidesWith(Entity thisE, Entity other) {
		//checks intersections of entities radii
		//ArrayList<HitBox> ohitbox=other.getHitBox();
		for (HitBox thisHitBox: thisE.getHitBox()){
			for(HitBox otherHitBox:other.getHitBox()){
				
				if(!thisHitBox.toString().equals(otherHitBox.toString())){
					int thisx2=(int)thisHitBox.x2();
					int thisx1=(int)thisHitBox.x1();
					
					int thisy1=(int)thisHitBox.y1();
					int thisy2=(int)thisHitBox.y2();
					
					int otherx1=(int)otherHitBox.x1();
					int otherx2=(int)otherHitBox.x2();
					
					int othery2=(int)otherHitBox.y2();
					int othery1=(int)otherHitBox.y1();	
					
					//Math.abs(thisx2-otherx1)<2
					
					
					 if((thisx1<otherx2 && thisx2>otherx1) && (thisy1<othery2 && thisy2>othery1)){
						// Log.d("C", ""+checkt+"  is  bigger than "+checko);
						if(thisHitBox.toString().equals("killbox")){
						 Log.d("POSITION", ""+thisHitBox.toString()+"  gy1 " +thisy1+ "  gy2 "+thisy2+" gx1 "+ thisx1+" gx2 "
								 +thisx2+"  "+otherHitBox.toString()+" oy1 "+othery1+ " oy2 "+othery2+" ox1 "+otherx1+ "  ox2 "+ otherx2 );
						}
						 this.collided(thisHitBox, otherHitBox);
						 other.collided(otherHitBox, thisHitBox);
						 return true;
					 }
					
				}
				
			}
		}
		   return false;
		  

	}
 
	public abstract void collided(HitBox thisHitBox, HitBox otherHitBox);
 
	public boolean canCollide() {
		return cancollide;
	}
	public abstract String toString();
}