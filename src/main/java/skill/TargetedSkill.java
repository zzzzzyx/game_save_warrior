package skill;

import unit.Monster;

public abstract class TargetedSkill implements AbstractSkill {
    public abstract void trigger(Monster m);

    @Override
    public Boolean needTarget() {
        return true;
    }
}
