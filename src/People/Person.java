//Nick Chen
package People;

import Game.Runner;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
	int xLoc, yLoc, HP;


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
	}

	public int getHP(){
		return HP;
	}

	public Person (int xLoc, int yLoc, int HP)
	{
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.HP = HP;
	}


}
