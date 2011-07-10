package org.Giraffe;
import android.content.res.Resources;
import android.graphics.Bitmap;



public class Backgrounds2 {
	int level;
	float speed;
	Resources res;
	Graphic graphic;
	Coordinate coordinate;
	
	public Backgrounds2 (String s,int x,int y,float speed,Bitmap background){
		
		this.level=level;
		this.res=res;
		this.speed=speed;
		graphic = new Graphic (background);
		coordinate= new Coordinate (x,y,background.getWidth(), background.getHeight());

	}
	
	public Bitmap getBackground(){
	
		
		return graphic.getImg();
		
	}
	
	//if we want to change the background during gameplay, do it here-
	public void checkChangeBackground(){
		
	}
}