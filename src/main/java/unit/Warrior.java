package unit;

import skill.AbstractSkill;
import skill.M_RandomAOE;
import skill.W_NormalAttack;

import java.util.List;

public class Warrior extends Player {
    public Warrior(String name, int blood, int damage, int defense, int exp, int money, int level, int levelExp) {
        super(name, blood, damage, defense, exp, money, level, levelExp);
    }

    @Override
    public List<AbstractSkill> getSkills() {
        if(this.skills == null){
            this.skills = List.of(new W_NormalAttack(), new M_RandomAOE(), new M_RandomAOE());
        }
        return this.skills;
    }
}
