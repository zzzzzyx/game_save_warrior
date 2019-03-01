package factory;

import skill.AbstractSkill;
import skill.M_RandomAOE;
import skill.W_NormalAttack_L1;

import java.util.List;

public class SkillFactory {
    public static List<AbstractSkill> getSkills(){
        return List.of(new W_NormalAttack_L1(), new M_RandomAOE(), new M_RandomAOE());
    }
}
