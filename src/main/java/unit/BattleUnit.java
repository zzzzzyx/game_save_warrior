package unit;

public abstract class BattleUnit {
    public String name;
    public int blood;
    public int damage;
    public int defense;
    public int current_blood;

    public BattleUnit(String name, int blood, int damage, int defense) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
        this.defense = defense;
        this.current_blood = blood;
    }
}
