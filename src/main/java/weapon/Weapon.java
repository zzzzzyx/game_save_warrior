package weapon;

import gameboard.Buyable;
import unit.Player;

public abstract class Weapon extends Buyable implements Cloneable{
    public String name;
    public int level;
    public int damage;
    public int defense;

    public Weapon(int money, String name, String description, int level, int damage, int defense) {
        super(money, name, description);
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
    }

    @Override
    public Weapon clone() {
        Weapon stu = null;
        try{
            stu = (Weapon)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }

    @Override
    public void buy_sub(Player player) {
        player.weapon = this.clone();
    }

}
