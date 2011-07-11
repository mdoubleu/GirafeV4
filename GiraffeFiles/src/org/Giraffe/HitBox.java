package org.Giraffe;

public class HitBox{
	
	private String name;
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	protected boolean collide;
	
	public HitBox(String name,float x1, float y1, float x2, float y2, boolean collide){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.name=name;
		this.collide=collide;
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
	public void collide(boolean collide){
		this.collide=collide;
	}
	
	public boolean collidesWith(HitBox other){
		
		if(!this.toString().equals(other.toString())){
			int otherx1=(int)other.x1();
			int otherx2=(int)other.x2();
			int othery2=(int)other.y2();
			int othery1=(int)other.y1();	
			
			 if((x1<otherx2 && x2>otherx1) && (y1<othery2 && y2>othery1)){
				 return true;
			 }
		}
		return false;
		
	}



}
