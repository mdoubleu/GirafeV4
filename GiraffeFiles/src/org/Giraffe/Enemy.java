package org.Giraffe;

import android.content.Context;
//this needs to be changed a bit;
public abstract class Enemy extends Entity
{
	private int health;
	public Enemy (Context context, long time)
	{
		super(context, time);
		this.health = health;
	}
	public abstract void move(); 		
	public abstract void hit();
	public abstract void defeated();
}
