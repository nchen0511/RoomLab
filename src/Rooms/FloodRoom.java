//Nick Chen
package Rooms;

import Game.Runner;
import People.Person;

public class FloodRoom extends Room {

	public FloodRoom(int x, int y)
	{
		super(x,y);
	}

	/**
	 * Person entering the flooded room will lose 25% of current HP (at least 1).
	 * @param x the Person entering
	 */
	public void enterRoom(Person x)
	{
		int hp = (int)(x.getHP()*.25)+1;
		System.out.println("This area is flooded! You trip and lose " + hp + " HP (25% of current hp).");
		occupant = x;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
		shown = true;
		x.setHP(x.getHP()-hp);
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
				return "F";
			} else {
				return "O";
			}
		} else {
			return " ";
		}
	}
}
