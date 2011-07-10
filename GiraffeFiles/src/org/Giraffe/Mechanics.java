package org.Giraffe;

/**
 * Mechanics allows entites to use different states. 
 */
public abstract class Mechanics {
	
	private boolean imageDraw=true;
	private long timeFroze=System.currentTimeMillis()+0;
	/**
	 * Enemies can shoot objects to the left
	 */
	public  void shoot(){}
	
	public int moveLeft(int value, int moveBy, int timeToWait){
		
		if(System.currentTimeMillis()-timeFroze>timeToWait){
			timeFroze=System.currentTimeMillis();
		}
		return value-moveBy;
	}
	public  void moveRight(){}
	public  void moveUp(){}
	public  void moveDown(){}
	public  void moveParabola(){}
	
	public  void speedUp(){}
	public  void slowDown(){}
	public  void setSpeed(){}
	
	/**
	 * controls health level
	 * @param h health level
	 */
	public int loseHealth(int health, int minus){
		return health-minus;
	}
	public int addHealth(int health, int add){
		return health+add;
	}
	
	/**
	 * Fling is a melee attack similiar to a hammer 
	 */
	public void fling(){}
	
	/**
	 * Transforms this object or enemy into something else via image
	 */
	public void changeImage(){}
	public boolean drawImage(){	return imageDraw;}
	public void setImage(boolean imageCheck){imageDraw=imageCheck;}
	public void animation(){}
	
	public int modelToViewX(float x, float width){
		float gx=((x/800f)*width);
		return (int)gx;
	}
	public int modelToViewY(float y, float height){
		float gx=((y/480f)*height);
		return (int)gx;
	}

}
