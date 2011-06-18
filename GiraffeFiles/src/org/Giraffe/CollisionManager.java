package org.Giraffe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollisionManager {

	List<Collidable> entities;
 
	public CollisionManager() {
	    entities = new ArrayList<Collidable>();
	}
 
	public CollisionManager(Collidable ... entities) {
	    this();
	    addEntities(entities);
	}
 
	public void addEntities(Collidable[] entities) {
	    this.entities.addAll(Arrays.asList(entities));
	}
 
	/**
	 * Compares each entity with every other entity. If they collide with
	 * each other then both are notified
	 * 
	 * @return if a collision occurred
	 */
	public boolean searchForCollision() {
	    for (Collidable entity : entities) {
		for (Collidable otherEntity : entities) {
		    if (entity == otherEntity)
			continue;
 
		    if (entity.canCollide() && entity.collidesWith(otherEntity)) {
			System.out.println(entity.getClass().getSimpleName()
				+ " collided with "
				+ otherEntity.getClass().getSimpleName());
			entity.collided(otherEntity);
			otherEntity.collided(entity);
			return true;
		    }
		}
	    }
	    return false;
	}
    }