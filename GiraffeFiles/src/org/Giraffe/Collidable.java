package org.Giraffe;

import java.util.ArrayList;


//Defines objects that can collide with each other
	public interface Collidable {

		boolean collidesWith(Entity thisE,Entity other);
		void collided(HitBox thisHitBox, HitBox otherHitBox);
		boolean canCollide();

	}