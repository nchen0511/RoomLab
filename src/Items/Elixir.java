package Items;

import People.Person;

public class Elixir implements Items {
    public void use(Person p){
        p.setHP(p.getHP()+(int)(p.getMaxHP()*.15));
        System.out.println("You are healed for " + (int)(p.getMaxHP()*.15) + " HP! (15% of max HP)");
    }
    public String toString(){
        return "Elixir";
    }
}
