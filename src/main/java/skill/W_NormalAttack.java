package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;

public class W_NormalAttack extends TargetedSkill{
    @Override
    public void trigger(Monster m) {
        m.current_blood -= 1000;
        GameModel.getInstance().invokeObserver();
        HurtShower.showMonsterHurt(m,1000);
    }

    @Override
    public String getSkillName() {
        return "磨牙吮血";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getEnhanceDescription() {
        return "第一级:平砍并恢复5%血量<br>第二级：伤害提升20%，恢复10%血量<br>第三级：伤害提升50%，恢复20%血量";
    }

    @Override
    public void enhance() {

    }
}
