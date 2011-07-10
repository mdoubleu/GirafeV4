package org.Giraffe;


import java.util.ArrayList;
import java.util.LinkedList;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class LevelMaker {
	Context context;
	/**Constructs a Level from a LevelString
	 * @param levelstring: tells the level where objects should
	 * be placed.
	 */
	LinkedList<Effects> levelLoad = new LinkedList<Effects>();
	ArrayList<Backgrounds2> backgrounds = new ArrayList<Backgrounds2>(); 
	String r="road";
	String sky="skyline";
	String tree="trees";
	
	
	public LevelMaker(String levelstring, Context context, float Cwidth, float Cheight){
		this.context=context;
		/**Splits the string into objects, with the # being the divider
		 * between each object. These are all represented by the array
		 */

		String[] objects = levelstring.split("#");
		long time = System.currentTimeMillis() + 0;
		for (int i = 0; i < objects.length; i++){
			String obj = objects[i];
			String[] objel = obj.split("%"); //object elements
			time += Long.parseLong(objel[0]);
			int type = Integer.parseInt(objel[1]);
			
			switch(type){
			case(0):levelLoad.add(new GiraffeEntity(context, time, Cwidth, Cheight)); break;
			case(1):levelLoad.add(new IceCreamTruck(context, time, Cwidth, Cheight)); break;
			case(2):levelLoad.add(new Helicopter(context,time, Cwidth, Cheight)); break;
			case(3):levelLoad.add(new Fruit(context,time, Cwidth, Cheight)); break;
			default:
			}
			
		}
		int j=0;
		int t=0;
	//	 background1.createScaledBitmap(background1, (int)width, (int)height, true);
		backgrounds.add(new Backgrounds2(sky, 0,0,.9f, getDrawable(sky).createScaledBitmap(getDrawable(sky), (int)800, (int)140, true)));
		for(int i=0; i<10; i++){
			for(int k=0; k<3; k++){
			backgrounds.add(new Backgrounds2(r, 65+j,(int)370,3, getDrawable(r)));
			j+=65;
			}
			backgrounds.add(new Backgrounds2(tree, 191+t,135,1, getDrawable(tree)));
			t+=191;
		}
		
	}
	public Bitmap getDrawable(String d)
	{
		Integer identifier = this.context.getResources().getIdentifier(d, "drawable", "org.Giraffe");
		return BitmapFactory.decodeResource(context.getResources(),identifier);
	}
	public LinkedList<Effects> getLevelObjects(){
			return levelLoad;
		}
	public ArrayList<Backgrounds2> getBacks(){
		return backgrounds;
	}

}
