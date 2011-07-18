package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;

public class GenericEnemy extends Enemy {
	
	public GenericEnemy(ArrayList<Bitmap> images, ArrayList<Bitmap> deathImages,
			Coordinate coordinate, float speed, String name) {
		super(images, deathImages,coordinate, speed, name);
		
	}
	
	public void moveLeft(boolean move){
		super.moveLeft=move;
	}public void moveRight(boolean move){
		super.moveRight=move;
	}public void moveUp(boolean move){
		super.moveUp=move;
	}public void moveDown(boolean move){
		super.moveDown=move;
	}
	
	
	public String toString(){
		return name;
	}
	
	
	
	

}
