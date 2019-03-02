package skill;

import unit.Monster;

public abstract class TargetedSkill extends AbstractSkill {
    public abstract void trigger(Monster m);
}
