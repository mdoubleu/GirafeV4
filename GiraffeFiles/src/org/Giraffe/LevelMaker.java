package org.Giraffe;

import java.util.LinkedList;

import android.content.Context;

public class LevelMaker {

	/**Constructs a Level from a LevelString
	 * @param levelstring: tells the level where objects should
	 * be placed.
	 */
	LinkedList<Entity> levelLoad = new LinkedList<Entity>();
	
	public LevelMaker(String levelstring, Context context, float Cwidth, float Cheight){
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
			case(1):levelLoad.add(new IceCreamTruck(context, time, Cwidth, Cheight)); break;
			case(2):levelLoad.add(new Helicopter(context,time, Cwidth, Cheight)); break;
			case(3):levelLoad.add(new Fruit(context,time, Cwidth, Cheight)); break;

			default:
			}
		}
	}

	public LinkedList<Entity> getLevel(){
			return levelLoad;
		}

}
