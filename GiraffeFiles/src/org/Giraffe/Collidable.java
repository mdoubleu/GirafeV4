package org.Giraffe;


//Defines objects that can collide with each other
	public interface Collidable {

		boolean collidesWith(Collidable otherEntity);
		void collided(Collidable otherEntity);
		boolean canCollide();

	}