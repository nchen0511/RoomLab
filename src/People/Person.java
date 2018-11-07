//Nick Chen
package People;

import Game.Runner;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
	int xLoc, yLoc, scout, HP;
	int frag = 0;


	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public void setHP(int HP){
		this.HP = HP;
		if(HP<1){
			Runner.gameOff();
		}

		if(HP>10){
			this.HP = 10;
		}
	}

	public int getScout(){
		return scout;
	}

	public void setScout(int scout){
		this.scout = scout;
	}

	public int getFrag(){
		return frag;
	}

	public void setFrag(int frag){
		this.frag = frag;
	}

	public int getHP(){
		return HP;
	}

	public Person (int xLoc, int yLoc, int scout, int HP)
	{
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.scout = scout;
		this.HP = HP;
	}


}
