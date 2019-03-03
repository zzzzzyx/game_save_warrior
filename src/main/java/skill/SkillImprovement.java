package skill;

import gameboard.Buyable;
import unit.Player;

public class SkillImprovement extends Buyable {
    AbstractSkill skill;
    public SkillImprovement(AbstractSkill skill) {
        super(1000, "", skill.getEnhanceDescription());
        this.skill = skill;
        renewProductName();
    }

    private void renewProductName(){
        this.productName = "强化："+skill.getName();
    }

    @Override
    public void buy_sub(Player player) {
        skill.level += 1;
        renewProductName();
    }

    @Override
    public boolean canBuy() {
        return super.canBuy() && this.skill.level < 3;
    }
}
