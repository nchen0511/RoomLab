package Items;

import People.Person;

public class Elixir implements Items {
    public void use(Person p){
        p.setHP(p.getHP()+(int)(p.getMaxHP()*.2));
        System.out.println("You are healed for " + (int)(p.getMaxHP()*.2) + " HP! (20% of max HP)");
    }
    public String toString(){
        return "Elixir";
    }
}
