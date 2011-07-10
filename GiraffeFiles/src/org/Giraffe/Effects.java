package org.Giraffe;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.drawable.Drawable;
/**
 * An Effect is something that allows an object to be created. For example, tank-moves at speed, fires a net etc.
 * @author mikedoubleyouu
 *
 */
public abstract class Effects extends Mechanics implements Collidable {
	/**
	 * Coordinates for placement of object
	 */
	protected int x1;
	protected int x2;
	protected int y1;
	protected int y2;
	
	/**
	 * Uses canvas width and height to scale
	 */
	protected float canvasWidth;
	protected float canvasHeight;
	
	protected int health;
	/**
	 * Speed of object
	 */
	protected float speed;
	/**
	 * Image of object
	 */
	protected Drawable image;
	/**
	 * List of objects hitboxes
	 */
	protected LinkedList<HitBox> hitBox=new LinkedList<HitBox>();
	/**
	 * Is the time that an abject arrives on screen at
	 */
	protected long timeIn;
	/**
	 * The context of the game in order to get the images
	 */
	protected Context context;
	
	protected boolean cancollide;

	public int X(){return x1;}
	public int X2(){return x2;}
	public int Y(){return y1;}
	public int Y2(){return y2;}
	public  long getTime(){return timeIn;}
	public  Drawable getImage(){return image;}	
	public  LinkedList<HitBox> getHitBox(){return hitBox;}
	public void setHealth(int h){health=h;}
	public int getHealth(){return health;}
	public abstract void move();
}
