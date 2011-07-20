package org.Giraffe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.ClassLoader;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


public class LevelBuilder {
	String readLine = null;
	int numberForBackground;
	Context context;
	GameView g;
	ArrayList<Background> backgrounds = new ArrayList<Background>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public LevelBuilder(int level, Context context){

		this.context = context;
		String levelsource = "level" + level;
		Integer levelid = context.getResources().getIdentifier(levelsource, "raw", "org.Giraffe");
		InputStream is = context.getResources().openRawResource(levelid);
        Scanner br = new Scanner(new InputStreamReader(is));
        levelMaker(br);
        if(level==1)
        {
        	Music.create((Activity)context, R.raw.newcentralpark);
			Music.start((Activity)context);
			Music.setLooping((Activity)context, R.raw.newcentralpark);
        }

	}
	
	public void levelMaker(Scanner level){
		while (level.hasNextLine()) {
			readLine = level.nextLine();
			processLine(readLine);
		}
	}
	
	public void processLine(String levelstring){
		 String[] categories = levelstring.split(" % ");
		 String rstring = categories[0];
		 String[] resource = rstring.split(" ; ");
		/**This integer represents the number that the resource must be repeated for creation **/
		 int repeat = Integer.parseInt(resource[0]);
		 /**Name to create a class**/
		 String classname = resource[1];
		 ArrayList<Bitmap> images=new ArrayList<Bitmap>();
		 ArrayList<Bitmap> deathImages=new ArrayList<Bitmap>();
		 
		 /**This is the first image file, and its name will be the string for the enemy**/
		 String imageName=resource[2];
		 Bitmap image=getDrawable(resource[2]);
		 
		 if(classname.equals("Enemy")){
			 /**this loop adds images for the animation work**/
			 for(int v=2; v<resource.length; v++){
				 if(v>repeat){
					 deathImages.add(getDrawable(resource[v]));
				 }else{
					 images.add(getDrawable(resource[v]));
				 }
			 }
		 }else{
			 for(int v=2; v<resource.length; v++){
				 images.add(getDrawable(resource[v]));
			 }
		 }
		 int bitmapwidth=image.getWidth();
		 int bitmapheight=image.getHeight();
		 
		 String istring = categories[1];
		 String[] instances = istring.split(" ; ");
		 for (String instance: instances){
			 String[] instel = instance.split(", ");
			 float speed = Float.parseFloat(instel[0]);
			// int x = Integer.parseInt(instel[1]);
			 int x=Integer.parseInt(instel[1]);
			 int y = Integer.parseInt(instel[2]);
			 Coordinate coordinate=new Coordinate(x,y,bitmapwidth,bitmapheight);
			 if (classname.equals("StaticImage")){
				 Background hold=new Background(images, coordinate,speed, repeat);
				 backgrounds.add(hold); 
				 if(repeat>0){
					backgrounds.addAll(hold.getRepeats());
				 }
			 }
			 else if (classname.equals("Enemy")){
				GenericEnemy hold =new GenericEnemy(images, deathImages,coordinate,speed,imageName);
				if(imageName.equals("helicopter")||imageName.equals("bhelicopter1")){
					hold.moveLeft(true);
					hold.getHitBox().add(new HitBox("helicopter", coordinate, true));
				}
				else if(imageName.equals("netv")){
					hold.moveLeft(true);
					hold.setImage(false);
					hold.getHitBox().add(new HitBox("netv", coordinate, true));
				}else if(imageName.equals("vender") || imageName.equals("cbus") ||imageName.equals("icecream1") ){
					hold.moveLeft(true);
					hold.getHitBox().add(new HitBox("vender", coordinate, true));
					hold.setLandOn(true);
				}else if(imageName.equals("ninja") || imageName.equals("ninjaglide") || imageName.equals("nstar1")
						|| imageName.equals("ninjaglide2")){
					hold.moveLeft(true);
					hold.getHitBox().add(new HitBox(imageName, coordinate, true));
					hold.jumping(true);
					if(imageName.equals("ninjaglide2") || imageName.equals("nstar1") ){
						hold.setImage(false);
					}
					
				}else if(imageName.equals("cdragon")){
					hold.moveLeft(true);
					hold.getHitBox().add(new HitBox("cdragon", coordinate, true));
					hold.setLandOn(true);
					
				}else if(imageName.equals("kapow")){
					hold.moveLeft(true);
					hold.getHitBox().add(new HitBox("kapow", coordinate, true));
					hold.canCollide=false;
					hold.setImage(false);	
				}
				
				 enemies.add(hold); 
			 }
		 }
	}
	public Bitmap getDrawable(String d)
	 {
	  Integer identifier = this.context.getResources().getIdentifier(d, "drawable", "org.Giraffe");
	  return BitmapFactory.decodeResource(context.getResources(),identifier);
	 }
	public ArrayList<Background> getBackgrounds(){
		return backgrounds;
	}
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public String getLevel(){
		return readLine;
	}
	
}
