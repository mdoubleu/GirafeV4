package org.Giraffe;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class GameModel {
	Context context;
	Giraffe jeremy;
	
	public boolean levelOver=false;
	protected boolean projectileDraw = true;
	
	ArrayList<Enemy>enemies=new ArrayList<Enemy>();
	ArrayList<Enemy>enemiesToDraw=new ArrayList<Enemy>();
	
	ArrayList<Background>backgrounds=new ArrayList<Background>();
	ArrayList<Background>backgroundsToDraw=new ArrayList<Background>();
	
	public GameModel(Context context){
		this.context=context;
		LevelBuilder level=new LevelBuilder(1, context);
		enemies=level.getEnemies();
		backgrounds=level.getBackgrounds();
		jeremy = new Giraffe(context);
	}
	
	public void updateLevel()
	{
		jeremy.setPic();
		searchForCollision();
		
		for(Enemy enemy: enemies){
			if(enemy.toString().equals("netv") && enemy.getX() < 300 && projectileDraw)
			{	
					enemy.setImage(true);
					enemy.moveDown = true;
			}
		}
		
	}
	
	public void searchForCollision() {
	    //one loop for all enemies on screen
		
		//then one loop for enemies v giraffe
		
		for(Enemy enemy: enemies){
			if(enemy.canCollide()&&jeremy.canCollide()){
				
				if(enemy.hitBox.get(0).collidesWith(jeremy.getHitBox().get(0))
				|| enemy.hitBox.get(0).collidesWith(jeremy.getHitBox().get(1))){
					enemy.collided();
					enemy.delayOfTime=System.currentTimeMillis()+0;
					enemy.delayImage=true;
					enemy.canCollideSet(false);
					if(enemy.toString().equals("netv")){
						projectileDraw = false;
					}
					
					
					jeremy.delayCollideTime=System.currentTimeMillis()+0;
					jeremy.delayCollide=true;
				}else if(enemy.hitBox.get(0).collidesWith(jeremy.getHitBox().get(2))
						&&jeremy.getHitBox().get(2).collide){
					enemy.collidedWithKillbox();
					enemy.delayOfTime=System.currentTimeMillis()+0;
					enemy.delayImage=true;
					enemy.canCollideSet(false);
					if(enemy.toString().equals("netv")){
						projectileDraw = false;
					}
				}
			}
		}
	  
	}
	public ArrayList<Background> getBackgrounds(){
		return backgrounds;
	}
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public void gameOver(){
		
	}
	public Giraffe getGiraffe()
	{
		return jeremy;
	}
}
