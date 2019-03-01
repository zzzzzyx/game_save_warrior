package skill;

import gameboard.GameModel;
import gui.HurtShower;
import unit.Monster;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class M_RandomAOE extends NonTargetSkill{
    private static Random random = new Random();

    private static final int targetNum = 3;
    @Override
    public void trigger() {
        GameModel gameModel = GameModel.getInstance();
        var monstersOnBoard = gameModel.getMonsters();
        List<Monster> selectedMonsterList;
        if(monstersOnBoard.size() <= targetNum){
            selectedMonsterList = monstersOnBoard;
        }else{
            selectedMonsterList = new ArrayList<>();
            while (selectedMonsterList.size() < targetNum){
                Monster m = monstersOnBoard.get(random.nextInt(monstersOnBoard.size()));
                if(!selectedMonsterList.contains(m)){
                    selectedMonsterList.add(m);
                }
            }
        }
        for(Monster m : selectedMonsterList){
            m.current_blood -= 800;
        }
        GameModel.getInstance().invokeObserver();
        for(Monster m : selectedMonsterList){
            HurtShower.showMonsterHurt(m,800);
        }

    }

    @Override
    public String getName() {
        return "神龙摆尾";
    }

    @Override
    public String getDescription() {
        return null;
    }
}
