package unit;

public class Monster extends BattleUnit {
    public int moneyAfterDefeat;
    public int expAfterDefeat;

    public Monster(String name, int level, int blood, int damage, int defense, int moneyAfterDefeat, int expAfterDefeat) {
        super(name, level, blood, damage, defense);
        this.moneyAfterDefeat = moneyAfterDefeat;
        this.expAfterDefeat = expAfterDefeat;
    }
}
