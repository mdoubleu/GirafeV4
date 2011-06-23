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
	GBody gBody;
	Bitmap background;
	CollisionManager colisionCheck;
	public static final float ACCELERATION=-.0075f;
	public static final float INITVELOCITY=7f;
	public boolean jumping;
	private long time;
	private int gVel;
	boolean isJumping;
	int state;
	float deg=0f;
	public int[] rotate=new int[3];
	
	public GameModel(Context context) {
		super(context);
		this.context=context;
        b_change=0;
        b2_change=800;
        ourGiraffe=new GiraffeEntity(context, 0);
        gBody=new GBody(context,0);
        isJumping=false;
        state=1;
        rotate[0]=0;
        rotate[1]=100; 
        rotate[2]=100;
	}
	public Bitmap getBackgrounds(){
		Resources res = context.getResources();	
		return BitmapFactory.decodeResource(res, R.drawable.bakroundcopy);
	}
	
	public void updateLevel(){
		//ourGiraffe.move();
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
		b_change-=5;
		b2_change-=5;
		
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
        entities.add(gBody);
	}
	
	public LinkedList<Entity> getLevel(){
		return entities;
	}
	
	public void jump(){
		long timeMil=0;
		
		if(getJump()){
			this.setCurrentJump(true);
			timeMil=System.currentTimeMillis()-time;
		
			gVel=(int)(INITVELOCITY+(ACCELERATION*timeMil));
			
			ourGiraffe.y1= (ourGiraffe.y1-gVel);
			ourGiraffe.y2= (ourGiraffe.y2-gVel);
			gBody.y1=(gBody.y1-gVel);
			gBody.y2=(gBody.y2-gVel);
			

			//Log.d("Ok", "gVel= "+gVel+ " time="+timeMil+" tempY1: "+ tempY1 +"  ACCELERATION"+ACCELERATION+"   INIT"+INITVELOCITY+
				//	"   \n"+ ourGiraffe.y1+ "      "+ ourGiraffe.y2);
			
			
			if(ourGiraffe.y2>=450){
				setJump(false);
				this.setCurrentJump(false);
			}
		}
			
	}
	//setJumping sets whether the giraffe is currently jumping or not.
	public void setCurrentJump(boolean isJumping){
		this.isJumping=isJumping;
	}
	//is jumping returns whethere the giraffe is currently jumping;
	public boolean currentlyJumping(){
		return isJumping;
	}
	//sets the giraffe to start jump
	public void setJump(boolean canJ){
		time=System.currentTimeMillis()+0;
		jumping=canJ;
	}
	//checks if the giraffe can jump.
	public boolean getJump(){
		return jumping;
	}
	
	public void rotateNeck(int state){
		Log.d("TEST","REACHED ROTATENECK");
		switch(state) {

        case 2:
        	rotate[0]=90;
        	rotate[1]=130;
        	rotate[2]=350;
        break;
        case 3:
          	rotate[0]=(int)deg;
        	rotate[1]=140;
        	rotate[2]=350;
        break;
        }
	}
	public int[] getRotate(){
		return rotate;
	}
	
}
