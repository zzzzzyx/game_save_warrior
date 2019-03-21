package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;
import unit.Player;

import java.util.HashMap;
import java.util.List;

public class M_WholeAOE extends NonTargetSkill{
    double[] damage_coefficient ={0,0.9,1.1,1.3};
    double[] sacrifice_coefficient= {0,0.2,0.15,0.1};
    @Override
    public void trigger() {
        GameModel gameModel = GameModel.getInstance();
        List<Monster> monstersOnBoard = gameModel.getMonsters();
        Player player = gameModel.getPlayer();
        player.minusCurrentBlood((int)(player.current_blood*sacrifice_coefficient[level]));

        HashMap<Monster,Integer> damage_list = new HashMap<>();
        for(Monster monster : monstersOnBoard){
            int damage_cause = player.getActualDamageCauseToMonster(monster);
            damage_cause *= damage_coefficient[level];
            damage_cause = damage_cause>0?damage_cause:0;
            monster.current_blood -= damage_cause;
            damage_list.put(monster,damage_cause);
        }
        GameModel.getInstance().invokeSkillUseObserver(true);
        for(Monster monster : monstersOnBoard){
            boolean showSuccess = HurtShower.showMonsterHurt(monster,damage_list.get(monster));
            if(!showSuccess){
                new Thread(GameModel::playerRoundEnd).start();
            }
        }
    }

    @Override
    public String getSkillName() {
        return "因果轮回";
    }

    @Override
    public String getDescription() {
        return "对所有目标造成伤害,但牺牲自身少量血量";
    }

    @Override
    public String getEnhanceDescription() {
        return "1:对所有目标造成0.9倍额定伤害,但扣除自身20%当前血量<br>" +
                "2:对所有目标造成1.1倍额定伤害,但扣除自身15%当前血量<br>" +
                "3:对所有目标造成1.3倍额定伤害,但扣除自身10%当前血量";
    }

}
