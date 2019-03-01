package gameboard;

import factory.SkillFactory;
import skill.AbstractSkill;

import java.util.List;

public class SkillModel {

    private static SkillModel instance;

    public List<AbstractSkill> getSkills() {
        return skills;
    }

    List<AbstractSkill> skills;
    private SkillModel(){
        skills = SkillFactory.getSkills();
    }

    public static SkillModel getInstance() {
        if(instance == null){
            instance =  new SkillModel();
        }
        return instance;
    }
}
