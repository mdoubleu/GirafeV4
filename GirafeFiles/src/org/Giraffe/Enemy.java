package org.Giraffe;

public abstract class Enemy extends Obstacle
{
	private int health;
	public Enemy (int x1, int y1, int x2, int y2, long timeIn, String imageName, int health)
	{
		super(x1,y1,x2,y2,timeIn,imageName);
		this.health = health;
	}
	public abstract void move(); 		
	public abstract void hit();
	public abstract void defeated();
}
