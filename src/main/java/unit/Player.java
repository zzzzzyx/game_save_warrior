package unit;

import gameboard.GlobalLogger;
import gameboard.MapFactory;
import skill.AbstractSkill;
import weapon.W_None;
import weapon.Weapon;

import java.util.List;

public abstract class Player extends BattleUnit {

    public int exp;
    public int money;
    public int level;
    public int levelExp;
    public Weapon weapon;

    public Player(String name, int blood, int damage, int defense,
                  int exp, int money, int level, int levelExp) {
        super(name, blood, damage, defense);
        this.exp = exp;
        this.money = money;
        this.level = level;
        this.levelExp = levelExp;
        weapon = new W_None();
    }

    public void levelComplete(int currentLevel){
        this.money+= MapFactory.LEVEL_MONEY[currentLevel];
        this.exp+=MapFactory.LEVEL_EXP[currentLevel];
        GlobalLogger.log("您已成功挑战第"+currentLevel+"层" +
                "，获得"+MapFactory.LEVEL_MONEY[currentLevel]+"金钱和"+
                MapFactory.LEVEL_EXP[currentLevel]+"经验");
        if(this.exp > this.levelExp){
            this.exp -= this.levelExp;
            this.level += 1;
            GlobalLogger.log("您已升级！恭喜您升到第"+this.level+"级！");
            GlobalLogger.log("您的血量增加了"+this.blood/2+"点; "
                    +"您的攻击增加了"+this.damage/2+"点; "
                    +"您的防御增加了"+this.defense/2+"点");
            this.current_blood += this.blood/2;
            this.blood *= 1.5;
            this.damage *= 1.5;
            this.defense *= 1.5;
        }
    }

    protected List<AbstractSkill> skills;
    public abstract List<AbstractSkill> getSkills();
}
