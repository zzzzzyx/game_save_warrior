package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.List;

public class M_RandomAOE extends NonTargetSkill{
    private static Random random = new Random();
    double[] damage_coefficient ={0,1,1.3,1.5};
    double[] target_num = {0,3,3,4};
    @Override
    public void trigger() {
        GameModel gameModel = GameModel.getInstance();
        var monstersOnBoard = gameModel.getMonsters();
        var player = gameModel.getPlayer();

        List<Monster> selectedMonsterList;
        if(monstersOnBoard.size() <= target_num[level]){
            selectedMonsterList = monstersOnBoard;
        }else{
            selectedMonsterList = new ArrayList<>();
            while (selectedMonsterList.size() < target_num[level]){
                Monster m = monstersOnBoard.get(random.nextInt(monstersOnBoard.size()));
                if(!selectedMonsterList.contains(m)){
                    selectedMonsterList.add(m);
                }
            }
        }
        var damage_list = new HashMap<Monster,Integer>();
        for(Monster monster : selectedMonsterList){
            int damage_cause = player.getActualDamageCauseToMonster(monster);
            damage_cause *= 1.2*damage_coefficient[level];
            damage_cause = damage_cause>0?damage_cause:0;
            monster.current_blood -= damage_cause;
            damage_list.put(monster,damage_cause);
        }
        GameModel.getInstance().invokeSkillUseObserver(true);
        for(Monster monster : selectedMonsterList){
            var showSuccess = HurtShower.showMonsterHurt(monster,damage_list.get(monster));
            if(!showSuccess){
                new Thread(GameModel::playerRoundEnd).start();
            }
        }
    }

    @Override
    public String getSkillName() {
        return "神龙摆尾";
    }

    @Override
    public String getDescription() {
        return "对随机三个目标造成中量伤害";
    }

    @Override
    public String getEnhanceDescription() {
        return "1:对随机三个目标造成1.2倍额定伤害<br>2:伤害提升30%<br>3:伤害提升50%，对4个目标造成伤害";
    }

}
