package unit;

import gameboard.GlobalLogger;
import skill.*;

import java.util.List;

public class Warrior extends Player {
    private double damage_next = 1;
    public Warrior(){
        super("战士",10000,1000, 600,100,300,1,1000);
    }

    @Override
    public List<AbstractSkill> getSkills() {
        if(this.skills == null){
            this.skills = List.of(new W_NormalAttack(), new W_Collision(), new W_Hold());
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
