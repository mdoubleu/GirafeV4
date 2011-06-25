package org.Giraffe;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Backgrounds {
	int level;
	Resources res;
	
	public Backgrounds(int level, Resources res){
		this.level=level;
		this.res=res;
	}
	
	public Bitmap getBackground(){
		switch(level){
		case(1):return BitmapFactory.decodeResource(res, R.drawable.bakroundcopy);
		case(2)://level two backround?
		
		}
		return null;
		
	}
	
	//if we want to change the background during gameplay, do it here-
	public void checkChangeBackground(){
		
	}
	
	
}
