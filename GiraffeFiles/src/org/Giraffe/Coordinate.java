package org.Giraffe;

public class Coordinate {
	
	public int x;
	public int y;
	private int width;
	private int height;
	
	
	
	public Coordinate (int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height= height;
		
	}
	
	
	public void setWidth (int width) {
			
		this.width = width;
		
	}
	
	public void setHeight (int height) {
		this.height = height;
	}
	
	public int getX () {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setX(int x){
		this.x=x;
	}
	
	

}
