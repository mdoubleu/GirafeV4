package org.Giraffe;

public abstract class Obstacle  
{
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected long timeIn; //when the enemy will appear on screen
	protected String imageName;
	
	public Obstacle (int x1, int y1, int x2, int y2, long timeIn, String imageName)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.timeIn = timeIn;
		this.imageName = imageName;
	}
	public abstract void move();
}
