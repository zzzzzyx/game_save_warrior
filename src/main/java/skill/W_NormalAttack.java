package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;
import unit.Player;
import unit.Warrior;

public class W_NormalAttack extends TargetedSkill{
    double[] damage_coefficient ={0,1,1.2,1.5};
    double[] recover_coefficient = {0,0.05,0.08,0.1};
    @Override
    public void trigger(Monster m) {
        GameModel gm = GameModel.getInstance();
        Warrior player = (Warrior)gm.getPlayer();
        int damage_cause = player.getActualDamageCauseToMonster(m);
        damage_cause *= 2*damage_coefficient[level];
        damage_cause = damage_cause>0?damage_cause:0;
        m.current_blood -= damage_cause;
        player.addCurrentBlood((int)(player.blood*recover_coefficient[level]));
        player.resetDamageNext();
        gm.invokeSkillUseObserver(true);
        gm.reloadData();
        boolean showSuccess = HurtShower.showMonsterHurt(m,damage_cause);
        if(!showSuccess){
            new Thread(GameModel::playerRoundEnd).start();
        }
    }

    @Override
    public String getSkillName() {
        return "嗜血若狂";
    }

    @Override
    public String getDescription() {
        return "对目标造成大量额定伤害，并回复自身血量";
    }

    @Override
    public String getEnhanceDescription() {
        return "1:对目标造成两倍额定伤害，并回复5%血量<br>2:伤害提升20%，恢复8%血量<br>3:伤害提升50%，恢复10%血量";
    }

}
