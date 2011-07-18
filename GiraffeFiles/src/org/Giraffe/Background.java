package org.Giraffe;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Background extends Mechanics {
	private int repeat;
	protected ArrayList<Background> repeats=new ArrayList<Background>();
	private long time;
	
	public Background (ArrayList<Bitmap> images, Coordinate coordinate, float speed, int repeat) {
		super.images = images;
		super.coordinate=coordinate;
		super.speed=speed;
		this.repeat=repeat;
		time=System.currentTimeMillis()+0;
		setImageToDraw(images.get(0));
		
		if(repeat>0){
			backgroundFactory(repeat, coordinate.getWidth());
		}
	}
	
	public void backgroundFactory(int repeat, int width){
		int newX=0;
		for(int x=0; x<repeat; x++){
			newX+=width-10;
			Coordinate newC=new Coordinate(newX, coordinate.getY(), coordinate.getWidth(), coordinate.getHeight());
			repeats.add(new Background(images, newC, speed, 0));
		}
	}
	public ArrayList<Background> getRepeats(){
		return repeats;
	}

	@Override
	public void move() {
		coordinate.setX(moveLeft(coordinate.getX(), speed));
		if(System.currentTimeMillis()-time>50){
			
			time=System.currentTimeMillis()+0;
		}
		
	}
	
	
}
