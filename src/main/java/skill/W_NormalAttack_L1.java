package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;

public class W_NormalAttack_L1 extends TargetedSkill{
    @Override
    public void trigger(Monster m) {
        m.current_blood -= 1000;
        GameModel.getInstance().invokeObserver();
        HurtShower.showMonsterHurt(m,1000);
    }

    @Override
    public String getName() {
        return "磨牙吮血";
    }

    @Override
    public String getDescription() {
        return null;
    }
}
