package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;
import unit.Warrior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class W_Hold extends NonTargetSkill{
    private static Random random = new Random();
    double[] recover_coefficient ={0,0.05,0.08,0.1};
    double[] damage_next = {0,1.6,1.7,1.8};
    @Override
    public void trigger() {
        GameModel gm = GameModel.getInstance();
        Warrior warrior = (Warrior)gm.getPlayer();
        warrior.hold(recover_coefficient[level],damage_next[level]);
    }

    @Override
    public String getSkillName() {
        return "屏息凝神";
    }

    @Override
    public String getDescription() {
        return "回复自身血量，且下个技能造成加倍伤害";
    }

    @Override
    public String getEnhanceDescription() {
        return "1:回复5%血量，且下个技能造成1.6倍伤害<br>" +
                "2:回复8%血量，且下个技能造成1.7倍伤害<br>" +
                "3:回复10%血量，且下个技能造成1.8倍伤害";
    }

}
