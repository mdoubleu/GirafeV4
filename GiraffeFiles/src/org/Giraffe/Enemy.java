package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Enemy extends Mechanics{
	protected boolean canCollide=true;
	protected int health;
	protected ArrayList<HitBox> hitBox=new ArrayList<HitBox>();
	protected ArrayList<Bitmap> deathImages=new ArrayList<Bitmap>();
	protected String name;
	protected boolean moveLeft=false;
	protected boolean moveRight=false;
	protected boolean moveUp=false;
	protected boolean moveDown=false;
	
	protected boolean canShoot = false;
	
	protected long delayOfTime;
	protected boolean delayImage=false;
		
	public Enemy (ArrayList<Bitmap> images, ArrayList<Bitmap> deathImages,
			Coordinate coordinate, float speed, String name) {
		super.images = images;
		this.deathImages=deathImages;
		super.coordinate=coordinate;
		super.speed=speed;
		this.name=name;
		setImageToDraw(images.get(0));

	}
	
	public void collided(){
		setImageToDraw(deathImages.get(0));
	}
	public void collidedWithKillbox(){
		setImageToDraw(deathImages.get(1));
	}
	public void canCollideSet(boolean canCollide){
		this.canCollide=canCollide;
	}
	public boolean canCollide(){
		return canCollide;
	}
	
	/**
	 * Delay of image to dissapear after collision. EX: helicopter to kapow to nothing.
	 */
	public void delayObstacleImage(long timeIn){
		if(System.currentTimeMillis()-timeIn>400){
			setImage(false);
		}
	}
	
	public void move(){
		if(delayImage){
			delayObstacleImage(delayOfTime);
		}else{
			setImageToDraw(animation(images, 200));
			if(moveLeft){
				coordinate.setX(moveLeft(coordinate.getX(), speed));
				for(int x=0; x<hitBox.size(); x++){
					hitBox.get(x).setX(coordinate.getX());
				}
			}if(moveRight){
			
			}if(moveDown){
				coordinate.setY((int)moveDown(coordinate.getY(), -speed));
				for(int x=0; x<hitBox.size(); x++){
					hitBox.get(x).setY(coordinate.getY());
				}
			}if (moveUp){
			
			}
		}
	}
	public void rangeAttack(){
		
	}
	public void moveHitBox(){
		for(int x=0; x<hitBox.size(); x++){
			hitBox.get(x);
		}
	}
	
	public  ArrayList<HitBox> getHitBox() {
		return hitBox;
		}
	public void setHealth(int h) {
		health=h;
		}
	public int getHealth() {
		return health;
	}
	public int loseHealth(int health, int minus){
		return health-minus;
	}
	public int addHealth(int health, int add){
		return health+add;
	}
	public  void shoot(String image, Coordinate c, float speed, int angle)
	{		
		
	}
	public  void moveParabola(){}
	public void fling(){}
	public void fall(){}
	
}
