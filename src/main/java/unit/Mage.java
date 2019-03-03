package unit;

import skill.*;

import java.util.List;

public class Mage extends Player {
    public static final String INTRINSIC_DESCRIPTION = "每个自己的回合结束后有50%几率使自身回复20%血量";
    @Override
    public List<AbstractSkill> getSkills() {
        if(this.skills == null){
            this.skills = List.of(new M_NormalAttack(), new M_RandomAOE(), new M_WholeAOE());
        }
        return this.skills;
    }

    public Mage() {
        super("法师");
    }
}
