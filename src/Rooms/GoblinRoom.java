//Nick Chen
package Rooms;

import Game.Runner;
import People.Person;

public class GoblinRoom extends Room{

	public GoblinRoom(int x, int y)
	{
		super(x,y);
	}

	/**
	 * When player enters the room, increases person's frag count by 1, around 70% of not taking damage.
	 * @param x the Person entering
	 */
	public void enterRoom(Person x)
	{
		if(Math.random()>.7){
			System.out.println("You run into a goblin and eliminate it after some struggling (You lost 1 HP).");
			x.setHP(x.getHP()-1);
		} else {
			System.out.println("You run into a goblin and eliminate it with no problem.");
		}
		occupant = x;
		x.setFrag(x.getFrag()+1);
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
		shown = true;
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
				return "G";
			} else {
				return "O";
			}
		} else {
			return " ";
		}
	}
}
