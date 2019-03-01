package unit;

public abstract class BattleUnit {
    public String name;
    public int level;
    public int blood;
    public int damage;
    public int defense;
    public int current_blood;

    public BattleUnit(String name, int level, int blood, int damage, int defense) {
        this.name = name;
        this.level = level;
        this.blood = blood;
        this.damage = damage;
        this.defense = defense;
        this.current_blood = blood;
    }
}
