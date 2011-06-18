package org.Giraffe;

import java.util.LinkedList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class GameModel extends View {
	LinkedList<Entity> entities=new LinkedList<Entity>();
	LinkedList<Entity>entityDraw=new LinkedList<Entity>();
	LevelMaker level;
	public int b_change;
	public int b2_change;
	int check2=0;
	Context context;
	long timeIn;
	GiraffeEntity ourGiraffe;
	Bitmap background;
	
	public GameModel(Context context) {
		super(context);
		this.context=context;
        b_change=0;
        b2_change=800;
		// TODO Auto-generated constructor stub
	}
	public Bitmap getBackgrounds(){
		Resources res = context.getResources();	
		return BitmapFactory.decodeResource(res, R.drawable.bakroundcopy);
	}
	
	public void updateLevel(){
		timeIn=System.currentTimeMillis();
		//LinkedList<Entity>cloneEnt=(LinkedList<Entity>)entities.clone();
		for(Entity e:entities){
				if(e.getTime()<timeIn){
					entityDraw.add(e);
				}
			}
	}
	public int getBChange(){
		return b_change;
	}
	public int getB2Change(){
		return b2_change;
	}

	public void alternateBackground(){
		if(b2_change==0){
			b_change=0;
			b2_change=800;
		}
		b_change-=10;
		b2_change-=10;
		
	}

	//puts in and gets rid of enemies on screen-
	public void alternateEntity(){
		for(int x=0; x<entityDraw.size()&&entityDraw.size()>0; x++){
			if(entityDraw.get(x).getX2()<-10){
				entityDraw.remove(x);
			}
		}
	}
	public LinkedList<Entity> getEntityDraw(){
		return entityDraw;
	}
	
	public void loadLevel(int act){
		LevelBuilder levels=new LevelBuilder(act);
	
        level= new LevelMaker(levels.getLevel(), context);
        entities=level.getLevel();
	}
	
	public LinkedList<Entity> getLevel(){
		return entities;
	}
	
	public void jump(){
		
	}
	public void updatePhysics(){
		
	}
	
}
