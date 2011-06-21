package org.Giraffe;


//Defines objects that can collide with each other
	public interface Collidable {

		boolean collidesWith(Entity otherEntity);
		void collided(Entity otherEntity);
		boolean canCollide();

	}