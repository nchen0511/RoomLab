package Rooms;

import People.Person;
import Game.Runner;

public class TPRoom extends Room{
	Person occupant;
	int xLoc,yLoc;

	public TPRoom(int x, int y)
	{
		super(x,y);
	}

	/**
	 * Method controls the results when a person enters this room.
	 * @param x the Person entering
	 */
	public void enterRoom(Person x)
	{
		System.out.println("You run into a wizard. He teleports you into a random room.");
		occupant = x;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
		Runner.teleport(this.xLoc,this.yLoc);
	}

	/**
	 * Removes the player from the room.
	 * @param x
	 */
	public void leaveRoom(Person x)
	{
		occupant = null;
	}
	
}
