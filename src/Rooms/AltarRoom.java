//Nick Chen
package Rooms;

import Game.Runner;
import Items.Elixir;
import Items.Scroll;
import People.Person;

import static Game.Runner.flood;
import static Game.Runner.goal;

public class AltarRoom extends Room{

	public AltarRoom(int x, int y)
	{
		super(x,y);
	}

	/**
	 * When player enters the room, a random event will occur (may be good or bad).
	 * @param x the Person entering
	 */
	public void enterRoom(Person x)
	{
		System.out.println("You come across an altar and pay your respects.");
		double random = Math.random();
		if (random<.1) {
			System.out.println("The gods smile upon you. You have received an elixir.");
			x.addItem(new Elixir());
		} else if (random<.2) {
			System.out.println("The gods are angry with you. You are smited for 20% of your health.");
			x.setHP(x.getHP()-(int)(x.getMaxHP()*0.2));
		} else if (random<.3) {
			System.out.println("The gods sees your potential. You have gained 5 more scouts.");
			x.setScout(x.getScout()+5);
		} else if (random<.4) {
			System.out.println("The gods senses your ambition. Your frag counter is filled by 10%.");
			x.setFrag(x.getFrag()+(int)(goal*.1));
		} else if (random<.5) {
			System.out.println("The gods smile upon you. You are healed for 25% of your health.");
			x.setHP(x.getHP()+(int)(x.getMaxHP()*0.25));
		} else if (random<.6) {
			System.out.println("The gods trusts you. You are given a flood scroll!");
			x.addItem(new Scroll());
		} else if (random<.7) {
			System.out.println("The gods are angered and floods the forest.");
			Runner.flood();
		} else if (random<.8) {
			System.out.println("The gods feel generous. You inventory is filled with elixirs");
			x.addItem(new Elixir());
			x.addItem(new Elixir());
			x.addItem(new Elixir());
		} else if (random<.9) {
			System.out.println("The gods laugh at your skills. You lose all your scouts!");
			x.setScout(0);
		} else {
			System.out.println("The gods ignore you and nothing happens.");
		}
		occupant = x;
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
				return "A";
			} else {
				return "O";
			}
		} else {
			return " ";
		}
	}
}
