//Nick Chen
package Rooms;

import People.Person;

public class AmbushRoom extends Room {

	public AmbushRoom(int x, int y)
	{
		super(x,y);
	}

	/**
	 * Method controls the results when a person enters this room.
	 * @param x the Person entering
	 */
	public void enterRoom(Person x)
	{
		int random = (int)(Math.random()*3)+1;
		System.out.println("You walk into an ambush! A group of small goblins attack you and you lost " + random + " HP.");
		occupant = x;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
		shown = true;
		x.setHP(x.getHP()-random);
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
