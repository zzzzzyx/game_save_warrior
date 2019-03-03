package gameboard;

import gui.HurtShower;
import gui.MainFrame;
import observation.LevelChangeObserver;
import observation.SkillUseObserver;
import observation.ReloadDataObserver;
import skill.AbstractSkill;
import unit.Mage;
import unit.Monster;
import unit.Player;
import unit.Warrior;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static GameModel instance;

    private List<Monster> monsters;
    private Player player;
    private List<SkillUseObserver> skillUseObservers = new ArrayList<>();
    private List<ReloadDataObserver> reloadDataObservers = new ArrayList<>();
    private int currentLevel;
    private LevelChangeObserver levelChangeObserver;
    private boolean playerRound = true;
    private MainFrame mainFrame;

    private GameModel(){
        currentLevel = 1;
        monsters = MapFactory.getMap(currentLevel);
//        player = new Warrior();
    }

    public static void gameOver() {
        getInstance().mainFrame.gameOver();
    }

    public void initPlayer(Player player){
        this.player = player;
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

    public void setObserverAndInvoke(SkillUseObserver observer) {
        this.skillUseObservers.add(observer);
        observer.invoke(false);
    }

    public List<AbstractSkill> getSkills(){
        return player.getSkills();
    }

    public void invokeSkillUseObserver(boolean playerUseSkill){
        if(playerUseSkill){
            for(Monster m : monsters){
                if(m.current_blood <= 0){
                    GlobalLogger.log("您已击杀了 " + m.name);
                }
            }
            //检查有无怪兽死亡，如有死亡，则移除出列表
            monsters.removeIf(monster -> monster.current_blood <= 0);
            if(monsters.isEmpty()){
                //全部杀完了，本局游戏结束，开始结算
                player.levelComplete(currentLevel);
                levelChangeObserver.goToLevel(currentLevel==3?currentLevel:currentLevel+1);
                reloadData();
            }
        }
        for(SkillUseObserver o : skillUseObservers){
            o.invoke(playerUseSkill);
        }
    }

    public static void changeLevel(int level){
        GameModel gm = getInstance();
        gm.currentLevel = level;
        gm.getPlayer().current_blood = gm.getPlayer().blood;
        gm.monsters = MapFactory.getMap(level);
        gm.reloadData();
    }

    public static void playerRoundEnd(){
        GameModel gm = getInstance();
        if(!gm.playerRound){
            return;
        }
        gm.playerRound = false;
        int count = 0;
        for(Monster m : gm.monsters){
            int actual = m.damage-gm.player.getActualDefense();
            actual = actual>0?actual:0;
            gm.player.minusCurrentBlood(actual);
            HurtShower.showMonstersAttack(m,actual);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count ++;
            if(count == gm.monsters.size()){
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        monsterRoundEnd();
    }
    private static void monsterRoundEnd(){
        GameModel gm = getInstance();
        gm.invokeSkillUseObserver(false);
        gm.playerRound = true;
    }

    public void addMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
