package org.Giraffe;

import java.util.LinkedList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

public class GameModel extends View {
	LinkedList<Entity> entities=new LinkedList<Entity>();
	LinkedList<Entity>entityDraw=new LinkedList<Entity>();
	LevelMaker level;
	public int b_change;
	public int b2_change;
	Context context;
	long timeIn;
	GiraffeEntity ourGiraffe;
	Bitmap background;
	CollisionManager colisionCheck;
	public static final float GRAVITY=-9.8f;
	public boolean jumping;
	long time;
	double tempY1;
	double tempY2;
	public GameModel(Context context) {
		super(context);
		this.context=context;
        b_change=0;
        b2_change=800;
        ourGiraffe=new GiraffeEntity(context, 0);
		// TODO Auto-generated constructor stub
	}
	public Bitmap getBackgrounds(){
		Resources res = context.getResources();	
		return BitmapFactory.decodeResource(res, R.drawable.bakroundcopy);
	}
	
	public void updateLevel(){
		colisionCheck=new CollisionManager(entities);
		entityDraw.clear();
		timeIn=System.currentTimeMillis();
		for(int f=0; f<entities.size(); f++){
			if(entities.get(f).getX2()<-10){
				entities.remove(f);
			}
		}
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

	public LinkedList<Entity> getEntityDraw(){
		return entityDraw;
	}
	
	public void loadLevel(int act){
		LevelBuilder levels=new LevelBuilder(act);
	
        level= new LevelMaker(levels.getLevel(), context);
        entities=level.getLevel();
        entities.addFirst(ourGiraffe);
	}
	
	public LinkedList<Entity> getLevel(){
		return entities;
	}
	
	public void jump(){
		double gVel;
		long timeSec=0;
		
		if(getJump()){
			
			timeSec=(System.currentTimeMillis()-time)/1000;
			//gVel=1+(GRAVITY*timeSec);
			gVel=(1*timeSec)+(.5*GRAVITY*Math.pow(timeSec, 2))+450;
			Log.d("Ok", "gVel= "+gVel+ " time="+timeSec+ "   \n"+ ourGiraffe.y1+ "      "+ ourGiraffe.y2);
			ourGiraffe.y1=(int) (tempY1+gVel);
			ourGiraffe.y2=(int) (tempY2+gVel);
			
			if(ourGiraffe.y2>450&&ourGiraffe.y1>=0){
				setJump(false);
			}
		}
			
	}
	public void setJump(boolean canJ){
		time=System.currentTimeMillis()+0;
		tempY1=ourGiraffe.y1;
		tempY2=ourGiraffe.y2;
		jumping=canJ;
	}
	public boolean getJump(){
		return jumping;
	}
	
	public void updatePhysics(){
		
	}
	
}
