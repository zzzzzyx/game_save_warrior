package skill;

import unit.Monster;

public class W_NormalAttack_L1 extends TargetedSkill{
    @Override
    public void trigger(Monster m) {
        m.blood -= 1000;
    }

}
