package org.Giraffe;

import android.graphics.Bitmap;

public class Scaling {
	float width;
	float height;
	float scaledX;
	float scaledY;
	public float deltaX;
	public float deltaY;
	
	public Scaling (float width,float height) {
		this.width=width;
		this.height=height;
		deltaX = width/800;
		deltaY = height/480;
	}
	
	
	
	
	public Bitmap scaleImg (Backgrounds2 b) {
		return b.getBackground().createScaledBitmap(b.getBackground(),
				(int)(b.getBackground().getWidth()*deltaY),
				(int)(b.getBackground().getHeight()*deltaY), 
				true);
		
	}
	
	
	public float scaledX (Backgrounds2 b) {
		scaledX=deltaX*b.getX();
		return scaledX;
	}
	
	public float scaledY (Backgrounds2 b) {
		scaledY=deltaY*b.getY();
		return scaledY;
	}
	
	
	

}
