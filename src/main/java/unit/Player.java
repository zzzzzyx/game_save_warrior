package unit;

import gameboard.GameModel;
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

    public Player(String name) {
        super(name, 5000, 450, 150);
        this.exp = 100;
        this.money = 300;
        this.level = 1;
        this.levelExp = 1000;
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
            this.blood *= 1.2;
            this.current_blood = this.blood;
            this.damage *= 1.2;
            this.defense *= 1.2;
            this.levelExp *= 1.2;
        }
    }
    public void addCurrentBlood(int addant){
        int mirror_current_blood = current_blood;
        current_blood += addant;
        current_blood = current_blood>blood?blood:current_blood;
        int change = current_blood - mirror_current_blood;
        if(change > 0){
            GlobalLogger.log("您回复了"+addant+"点血量");
        }
    }
    public void minusCurrentBlood(int minus){
        current_blood -= minus;
        GameModel.getInstance().reloadData();
        if(current_blood <= 0){
            GlobalLogger.log("您死了，游戏结束，大侠请重新来过。");
            GameModel.gameOver();
        }
    }

    public int getActualDamageCauseToMonster(Monster monster){
        return damage + weapon.damage - monster.defense;
    }

    public int getActualDefense(){
        return defense + weapon.defense;
    }

    protected List<AbstractSkill> skills;
    public abstract List<AbstractSkill> getSkills();
}
