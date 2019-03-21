package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;
import unit.Player;

import java.util.Random;

public class M_NormalAttack extends TargetedSkill{
    private static Random random = new Random();
    double[] damage_coefficient ={2,3,5};
    @Override
    public void trigger(Monster m) {
        GameModel gm = GameModel.getInstance();
        Player player = gm.getPlayer();
        int damage_cause = player.getActualDamageCauseToMonster(m);
        double efficient = damage_coefficient[level];
        if(random.nextBoolean()){
            efficient += 2.5;
        }
        damage_cause *= efficient;
        damage_cause = damage_cause>0?damage_cause:0;
        m.current_blood -= damage_cause;
        gm.invokeSkillUseObserver(true);
        gm.reloadData();
        boolean showSuccess = HurtShower.showMonsterHurt(m,damage_cause);
        if(!showSuccess){
            new Thread(GameModel::playerRoundEnd).start();
        }
    }

    @Override
    public String getSkillName() {
        return "会心一击";
    }

    @Override
    public String getDescription() {
        return "对目标造成大量伤害，有几率造成极大伤害";
    }

    @Override
    public String getEnhanceDescription() {
        return "1:对目标造成两倍伤害，且有50%几率额外造成2.5倍伤害<br>" +
                "2:对目标造成三倍伤害，且有50%几率额外造成2.5倍伤害<br>" +
                "3:对目标造成五倍伤害，且有50%几率额外造成2.5倍伤害";
    }

}
