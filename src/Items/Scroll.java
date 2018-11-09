package Items;

import Game.Runner;
import People.Person;

public class Scroll implements Items {
    public void use(Person p){
        Runner.flood();
        p.setHP(p.getHP()-(int)(p.getMaxHP()*.5));
        System.out.println("You used " + (int)(p.getMaxHP()*.5) + " HP (50% of max HP) in order to summon a flood.");
    }
    public String toString(){
        return "Scroll";
    }
}
