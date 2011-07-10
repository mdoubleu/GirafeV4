package org.Giraffe;

import java.util.LinkedList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Backgrounds extends Mechanics {
	int level;
	Resources res;
	LinkedList<Bitmap> backgrounds;
	public Backgrounds(int level, Resources res){
		this.level=level;
		this.res=res;
		backgrounds=new LinkedList<Bitmap>();
		
	}
	
	public LinkedList<Bitmap> getBackground(){
		switch(level){
		case(1):backgrounds.add(BitmapFactory.decodeResource(res, R.drawable.bakroundcopy));
		return backgrounds;
		}
		return null;
	}
	
	public void alternateBackground(int bLocation1, int bLocation2, int canvasWidth){
		float speed=modelToViewX(5, canvasWidth)*(canvasWidth/800);
		if(bLocation2<=0){
			bLocation1=0;
			bLocation2=canvasWidth;
		}
		bLocation1=moveLeft(bLocation1, (int)speed, 3);
		bLocation2=moveLeft(bLocation2, (int)speed, 3);	
	}
}
