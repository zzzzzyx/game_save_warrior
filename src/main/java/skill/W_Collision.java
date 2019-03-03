package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;
import unit.Warrior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class W_Collision extends TargetedSkill{
    private static Random random = new Random();
    double[] damage_coefficient ={0,1,1.3,1.5};
    double[] extra_target_num = {0,2,2,3};
    @Override
    public void trigger(Monster m) {
        var gm = GameModel.getInstance();
        var player = (Warrior)gm.getPlayer();

        var monstersOnBoard = gm.getMonsters();
        List<Monster> selectedMonsterList;
        if(monstersOnBoard.size() <= extra_target_num[level]+1){
            selectedMonsterList = monstersOnBoard;
        }else{
            selectedMonsterList = new ArrayList<>();
            selectedMonsterList.add(m);
            while (selectedMonsterList.size() < extra_target_num[level]+1){
                Monster monster = monstersOnBoard.get(random.nextInt(monstersOnBoard.size()));
                if(!selectedMonsterList.contains(monster)){
                    selectedMonsterList.add(monster);
                }
            }
        }
        var damage_list = new HashMap<Monster,Integer>();
        for(Monster monster : selectedMonsterList){
            int damage_cause = player.getActualDamageCauseToMonster(monster);
            damage_cause *= damage_coefficient[level];
            damage_cause = damage_cause>0?damage_cause:0;
            monster.current_blood -= damage_cause;
            damage_list.put(monster,damage_cause);
        }
        player.resetDamageNext();
        gm.invokeSkillUseObserver(true);
        for(Monster monster : selectedMonsterList){
            var showSuccess = HurtShower.showMonsterHurt(monster,damage_list.get(monster));
            if(!showSuccess){
                new Thread(GameModel::playerRoundEnd).start();
            }
        }
    }

    @Override
    public String getSkillName() {
        return "野性冲撞";
    }

    @Override
    public String getDescription() {
        return "对目标及多个随机其他目标造成少量伤害";
    }

    @Override
    public String getEnhanceDescription() {
        return "1:对目标及两个随机目标造成额定伤害<br>2:伤害提升30%<br>3:伤害提升50%，增加一个额外目标";
    }

}
