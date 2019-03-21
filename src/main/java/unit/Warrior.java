package unit;

import gameboard.GlobalLogger;
import skill.*;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends Player {
    public static final String INTRINSIC_DESCRIPTION = "每个自己的回合结束后有50%几率使自身下个技能伤害翻倍";
    private double damage_next = 1;
    public Warrior(){
        super("战士");
    }

    @Override
    public List<AbstractSkill> getSkills() {
        if(this.skills == null){
            this.skills = new ArrayList<>();
            this.skills.add(new W_NormalAttack());
            this.skills.add(new W_Collision());
            this.skills.add(new W_Hold());
        }
        return this.skills;
    }

    @Override
    public int getActualDamageCauseToMonster(Monster m){
        return (int)(damage_next*super.getActualDamageCauseToMonster(m));
    }
    public void resetDamageNext(){
        this.damage_next = 1;
    }
    public void hold(double recover_coefficient, double damage_next){
        this.damage_next = damage_next;
        GlobalLogger.log("您的下一个技能的伤害将会是"+damage_next+"倍");
        this.addCurrentBlood((int)(recover_coefficient*blood));
    }
}
