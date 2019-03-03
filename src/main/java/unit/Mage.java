package unit;

import skill.*;

import java.util.List;

public class Mage extends Player {
    @Override
    public List<AbstractSkill> getSkills() {
        if(this.skills == null){
            this.skills = List.of(new M_NormalAttack(), new M_RandomAOE(), new M_WholeAOE());
        }
        return this.skills;
    }

    public Mage() {
        super("术士", 10000,1000, 600,100,300,1,1000);
    }
}
