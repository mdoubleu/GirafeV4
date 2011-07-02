package org.Giraffe;

public class HitBox{
	
	private String name;
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	
	public HitBox(String name,float x1, float y1, float x2, float y2){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.name=name;
	}
	
	
	public String toString(){return name;}
	public float x1(){return x1;}
	public float x2(){return x2;}
	public float y1(){return y1;}
	public float y2(){return y2;}
	
	public void changePosition(float x1, float y1, float x2, float y2){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}


}
