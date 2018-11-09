//Nick Chen
package People;

import Game.Runner;
import Items.Items;

/**
 * Person represents the player as they move through the game.
 */
public class Person {
	int xLoc, yLoc, scout, HP, hp;
	int frag = 0;
	public Items[] inventory = new Items[3];


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

	public void setHP(int hp){
		this.hp = hp;
		if(hp<1){
			Runner.gameOff();
		}

		if(hp>HP){
			this.hp = HP;
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
		return hp;
	}

	public int getMaxHP(){
		return HP;
	}

	public void printInventory(){
		String temp="";
		for(int i=0;i<inventory.length;i++){
			if(inventory[i]==null){
				temp+="[ Empty ]";
			} else {
				temp+="[ " + inventory[i] + " ]";
			}
		}
		System.out.println(temp);
	}

	public void addItem(Items item){
		for(int i=0;i<inventory.length;i++){
			if(inventory[i]==null){
				inventory[i] = item;
				break;
			}
		}
	}

	public void use(int num){
		inventory[num].use(this);
		inventory[num] = null;
	}

	public Person (int xLoc, int yLoc, int scout, int HP)
	{
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.scout = scout;
		this.HP = HP;
		this.hp = HP;
	}


}
