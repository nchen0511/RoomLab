//Nick Chen
package Rooms;

import Game.Runner;
import People.Person;

public class AmbushRoom extends Room {

	public AmbushRoom(int x, int y)
	{
		super(x,y);
	}

	/**
	 * Person entering the room will lose a random amount of HP (up to around 20% of max hp, at least 1).
	 * @param x the Person entering
	 */
	public void enterRoom(Person x)
	{
		int random = (int)(Math.random()*(x.getMaxHP()*.2))+1;
		System.out.println("You walk into an ambush! A group of small goblins attack you and you lost " + random + " HP.");
		occupant = x;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
		shown = true;
		x.setHP(x.getHP()-random);
		Runner.normalize(this.xLoc,this.yLoc,x);
	}

	/**
	 * Removes the player from the room.
	 * @param x
	 */
	public void leaveRoom(Person x)
	{
		occupant = null;
	}

	public void show(){
		this.shown = true;
	}

	public String toString(){
		if(shown) {
			if(occupant==null) {
				return "Z";
			} else {
				return "O";
			}
		} else {
			return " ";
		}
	}
}
