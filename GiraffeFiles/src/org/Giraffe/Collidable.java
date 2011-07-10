package org.Giraffe;
/**
 * Defines objects that can collide with each other
 * @author mikedoubleyouu
 *
 */
	public interface Collidable {

		void collidesWithGiraffe(Enemy enemy,GiraffeEntity giraffe);
		void collided(HitBox thisHitBox, HitBox otherHitBox);
		boolean canCollide();

	}