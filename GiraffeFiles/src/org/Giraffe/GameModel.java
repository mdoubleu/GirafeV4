package org.Giraffe;

import java.util.LinkedList;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class GameModel {
	/*list of enenmies and images +giraffe, one list has all, one has things to draw in real time*/
	public static final int p=80;
	LinkedList<Entity> entities=new LinkedList<Entity>();
	private LinkedList<Entity>entityDraw=new LinkedList<Entity>();
	
	//creates level
	LevelMaker level;
	//public int b_change;
	//public int b2_change;
	Context context;
	Backgrounds background;

	//this checks when enemies or obstacles appear and disapear. 
	long timeIn;
	
	//creates giraffe object --body --neck
	GiraffeEntity ourGiraffe;

	//neck rotation/screen
	int state;
	float deg=0f;
	float pix=0f;
	//size
	float width;
	float height;
	
	float bLocation1;
	float bLocation2;
	
	long timeFrozen;
	
	private boolean canJump;
	private boolean currentlyJumping=false;
	public boolean notRotating=true;
	
	private boolean levelOver=false;
	private boolean levelLose=false;
	
	public GameModel(Context context){
		this.context=context;
		ourGiraffe=new GiraffeEntity(context, 0, width, height);
		ourGiraffe.setHealth(2);
        timeFrozen=System.currentTimeMillis()+0;
        /*LOADS LEVEL 1 AS DEFUALT*/
        loadLevel(1);
	}
	public void setSize(float width, float height){
		this.width=width;
		this.height=height;
		bLocation1=0;
		bLocation2=width;
	}
	public void loadLevel(int act){
		LevelBuilder levels=new LevelBuilder(act);
		background= new Backgrounds(levels.getNumForBackground(),context.getResources());
        
		
		level= new LevelMaker(levels.getLevel(), context, height, width);
        
		entities=level.getLevel();
        entities.addFirst(ourGiraffe);
        entityDraw.addFirst(ourGiraffe);
        
	}
	public Bitmap getBkround(){
		return background.getBackground();
	}
	public void gameOver(long t){
		if(levelLose){
			entities.clear();
			entityDraw.clear();
			background=new Backgrounds(2,context.getResources());
			updateLevel();
		}else{
			entities.clear();
			entityDraw.clear();
			background=new Backgrounds(3,context.getResources());
			updateLevel();
		}
		
	}
	public void updateLevel(){
		//ourGiraffe.move();
		this.searchForCollision();
		this.alternateBackground();
		if(ourGiraffe.getJump()){
			ourGiraffe.jump();
		}
		if(ourGiraffe.health==0){
			levelLose=true;
			levelOver=true;
		}
		
		entityDraw.clear();
		ourGiraffe.updateTime();
		ourGiraffe.setPic();
		timeIn=System.currentTimeMillis();
		entityDraw.addFirst(ourGiraffe);
		
		if(entities.size()<2){
			//levelWin=true;//this is winning the level
			levelOver=true;
			
		}
		for(int f=0; f<entities.size(); f++){
			if(entities.get(f).getX2()<-10){
				entities.remove(f);
			}
		}
		for(Entity e:entities){
				if(e.getTime()<timeIn){
					if(!e.toString().equals("body")){
						entityDraw.add(e);
					}
					
				}
			}
	}
	public LinkedList<Entity> getEntities(){
		return entityDraw;
	}
	
	public void searchForCollision() {
	    for (Entity entity : entities) {
			
	    	for (Entity otherEntity : entities) {
			
			if(entity.canCollide()&&otherEntity.canCollide()&&!entity.toString().equals(otherEntity.toString())){
				entity.collidesWith(entity,otherEntity);
				//Log.d("COLLISION", ""+entity.toString()+" collided with "+otherEntity.toString());
					}
				}

			}
	  
	}
	public void alternateBackground(){
		
		if(bLocation2==0){
			bLocation1=0;
			bLocation2=width;
		}
		
		//Background changes based on time;
		if(System.currentTimeMillis()-timeFrozen>2){
			bLocation1-=5;
			bLocation2-=5;
			timeFrozen=System.currentTimeMillis();
			
		}
	}

	
	public boolean levelOver(){
		return levelOver;
	}
	public GiraffeEntity getOurGiraffe()
	{
		return ourGiraffe;
	}
	
	
}
