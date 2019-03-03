package gameboard;

import observation.LevelChangeObserver;
import observation.MonstersRenewObserver;
import observation.ReloadDataObserver;
import skill.AbstractSkill;
import unit.Mage;
import unit.Monster;
import unit.Player;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static GameModel instance;

    List<Monster> monsters;
    Player player;
    MonstersRenewObserver observer;
    List<ReloadDataObserver> reloadDataObservers = new ArrayList<>();
    int currentLevel;
    LevelChangeObserver levelChangeObserver;

    private GameModel(){
        currentLevel = 1;
        monsters = MapFactory.getMap(currentLevel);
        player = new Mage();
    }

    public static GameModel getInstance() {
        if(instance == null){
            instance =  new GameModel();
        }
        return instance;
    }

    public void addReloadDataObservers(ReloadDataObserver r){
        reloadDataObservers.add(r);
    }

    public void addLevelChangeObserver (LevelChangeObserver levelChangeObserver){
        this.levelChangeObserver = levelChangeObserver;
    }
    public void reloadData(){
        for(ReloadDataObserver r : reloadDataObservers){
            r.reloadData();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setObserverAndInvoke(MonstersRenewObserver observer) {
        this.observer = observer;
        observer.monstersRenew(monsters);
    }

    public List<AbstractSkill> getSkills(){
        return player.getSkills();
    }

    public void invokeObserver(){
        //检查有无怪兽死亡，如有死亡，则移除出列表
        for(Monster m : monsters){
            if(m.current_blood <= 0){
                GlobalLogger.log("您已击杀了 " + m.name);
            }
        }
        monsters.removeIf(monster -> monster.current_blood <= 0);
        if(monsters.isEmpty()){
            //全部杀完了，本局游戏结束，开始结算
            player.levelComplete(currentLevel);
            levelChangeObserver.goToLevel(currentLevel==3?currentLevel:currentLevel+1);
            reloadData();
        }else{
            observer.monstersRenew(monsters);
        }
    }

    public static void changeLevel(int level){
        GameModel gm = getInstance();
        gm.currentLevel = level;
        gm.getPlayer().current_blood = gm.getPlayer().blood;
        gm.monsters = MapFactory.getMap(level);
        gm.reloadData();
    }
}
