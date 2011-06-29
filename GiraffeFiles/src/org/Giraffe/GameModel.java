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
	GBody gBody;
	GHead gHead;
	
	//Bitmap background;
	
	//CollisionManager colisionCheck; !MERGE WITH ENTTITY
	
	//physics
	public static final float ACCELERATION=-.0075f;
	public static final float INITVELOCITY=10f;
	private long jumpTime;
	private int gVel;
	
	//public boolean canJump;
	//boolean iscanJump;
	
	//neck rotation/screen
	int state;
	float deg=0f;
	public int[] rotate=new int[3];
	
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
	
	public GameModel(Context context){
		this.context=context;
		ourGiraffe=new GiraffeEntity(context, 0, width, height);
        gBody=new GBody(context,0, width, height);
        gHead=new GHead(context, 0, width, height);
        timeFrozen=System.currentTimeMillis()+0;
        Log.d("TEST", "BEFOREROTATE");
        rotate[0]=0;rotate[1]=0;rotate[2]=0;
        Log.d("TEST", "BAFTEREROTATE");
        /*LOADS LEVEL 1 AS DEFUALT*/
        loadLevel(1);
	}
	public void setSize(float width, float height){
		this.width=width;
		this.height=height;
		bLocation1=0;
		bLocation2=800;
	}
	public void loadLevel(int act){
		LevelBuilder levels=new LevelBuilder(act);
		background= new Backgrounds(levels.getNumForBackground(),context.getResources());
        
		
		level= new LevelMaker(levels.getLevel(), context, height, width);
        
		entities=level.getLevel();
        entities.addFirst(ourGiraffe);
        entityDraw.addFirst(ourGiraffe);
        entities.add(gBody);
        entities.add(gHead);
        
	}
	public Bitmap getBkround(){
		return background.getBackground();
	}
	public void updateLevel(){
		//ourGiraffe.move();
		this.searchForCollision();
		this.alternateBackground();
		if(getJump()){
			this.jump();
		}
		
		entityDraw.clear();
		entityDraw.addFirst(ourGiraffe);
		timeIn=System.currentTimeMillis();
		for(int f=0; f<entities.size(); f++){
			if(entities.get(f).getX2()<-10){
				entities.remove(f);
			}
		}
		for(Entity e:entities){
				if(e.getTime()<timeIn){
					if(!e.toString().equals("body")){
						//e.setDraw(true);
						entityDraw.add(e);
					}
					
				}
			}
	}
	public LinkedList<Entity> getEntities(){
		return entityDraw;
	}
	 //********************//
	// move to controller //
   //********************//
	public void searchForCollision() {
	    for (Entity entity : entities) {
		for (Entity otherEntity : entities) {
			if(!entity.toString().equals(otherEntity.toString())){
				//entity.getX()!=otherEntity.getX()&&entity.getX2()!=otherEntity.getX2()){
				if (entity.toString().equals("body")&&otherEntity.toString().equals("giraffe")||
						entity.toString().equals("giraffe")&&otherEntity.toString().equals("body")){
				}else{
					if(entity.collidesWith(otherEntity)) {
						//Log.d("COLLISION", ""+entity.toString()+" collided with "+otherEntity.toString());
						entity.collided(otherEntity);
						otherEntity.collided(entity);
						}
				}
			}

			}
	    }
	  
	}
	public void alternateBackground(){
		
		if(bLocation2==0){
			bLocation1=0;
			//make constant
			bLocation2=800;
		}
		
		//Background changes based on time;
		if(System.currentTimeMillis()-timeFrozen>2){
			bLocation1-=5;
			bLocation2-=5;
			timeFrozen=System.currentTimeMillis();
			
		}
	}
	public void jump(){
		long timeMil=0;
	
			this.setCurrentJump(true);
			timeMil=System.currentTimeMillis()-jumpTime;
		
			gVel=(int)(INITVELOCITY+(ACCELERATION*timeMil));
			
			ourGiraffe.y1= (int) (GameController.viewToModelX(ourGiraffe.y1-gVel));
			ourGiraffe.y2= (int) GameController.viewToModelX(ourGiraffe.y2-gVel);
			ourGiraffe.neck_y1=(int) GameController.viewToModelX(ourGiraffe.neck_y1-gVel);
			ourGiraffe.neck_y2=(int) GameController.viewToModelX(ourGiraffe.neck_y2-gVel);
			gBody.y1=(int) GameController.viewToModelX(gBody.y1-gVel);
			gBody.y2=(int) GameController.viewToModelX(gBody.y2-gVel);
			
			gHead.y1=(int) (GameController.viewToModelX(gHead.y1-gVel));
			gHead.y2=(int) (GameController.viewToModelX(gHead.y2-gVel));

			//Log.d("Ok", "gVel= "+gVel+ " time="+timeMil+" tempY1: "+ tempY1 +"  ACCELERATION"+ACCELERATION+"   INIT"+INITVELOCITY+
				//	"   \n"+ ourGiraffe.y1+ "      "+ ourGiraffe.y2);
			
			
			if(ourGiraffe.y2>=398+p){
				setJump(false);
				this.setCurrentJump(false);
			}
		
			
	}
	//allows giraffe to jump
	public void setJump(boolean canJ){
		jumpTime=System.currentTimeMillis()+0;
		canJump=canJ;
	}
	//checks if the giraffe can jump.
	public boolean getJump(){
		return canJump;
	}
	//setcanJump sets whether the giraffe is currently canJump or not.
	public void setCurrentJump(boolean currentlyJumping){
		this.currentlyJumping=currentlyJumping;
	}
	//is canJump returns whethere the giraffe is currently canJump;
	public boolean currentlyJumping(){
		return currentlyJumping;
	}
	
	public void rotateNeck(int state){
		//Log.d("TEST","REACHED ROTATENECK");
		switch(state) {
		case 1:rotate[0]=0;
        case 2:
        	notRotating=false;
        	rotate[0]=90;
        	rotate[1]=130;
        	rotate[2]=350;
        break;
        case 3:
        	notRotating=false;
          	rotate[0]=(int)deg;
        	rotate[1]=140;
        	rotate[2]=350;
        break;
        }
	}
	public int[] getRotate(){
		return rotate;
	}
	public boolean levelOver(){
		return levelOver;
	}
	public void defaultRotate(){
		notRotating=true;
		rotate[0]=0;
	}
	
	
	
}
