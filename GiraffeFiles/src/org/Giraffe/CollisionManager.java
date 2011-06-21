package org.Giraffe;

import java.util.LinkedList;

import android.util.Log;

public class CollisionManager {

	LinkedList<Entity> entities;
 
	public CollisionManager(LinkedList<Entity> entities) {
	    this.entities = entities;
	    this.searchForCollision();
	}
 
	/**
	 * Compares each entity with every other entity. If they collide with
	 * each other then both are notified
	 * 
	 * @return if a collision occurred
	 */
	public void searchForCollision() {
	    for (Entity entity : entities) {
		for (Entity otherEntity : entities) {
			if(!entity.toString().equals(otherEntity.toString())){
				//entity.getX()!=otherEntity.getX()&&entity.getX2()!=otherEntity.getX2()){
				
				if (entity.collidesWith(otherEntity)) {
					if(entity.toString().equals("giraffe")){
						Log.d("GiraffeCRASH", "CRASH");
					}
					entity.collided(otherEntity);
					otherEntity.collided(entity);
					}
				}

			}
	    }
	}
}