package gameboard;

import factory.MapFactory;
import observation.MonstersRenewObserver;
import unit.Monster;

import java.util.Iterator;
import java.util.List;

public class GameModel {

    private static GameModel instance;

    List<Monster> monsters;
    MonstersRenewObserver observer;

    private GameModel(){
        monsters = MapFactory.getMockMap();
    }

    public static GameModel getInstance() {
        if(instance == null){
            instance =  new GameModel();
        }
        return instance;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setObserverAndInvoke(MonstersRenewObserver observer) {
        this.observer = observer;
        observer.monstersRenew(monsters);
    }

    public void invokeObserver(){
        //检查有无怪兽死亡，如有死亡，则移除出列表
        for(Monster m : monsters){
            if(m.current_blood <= 0){
                GlobalLogger.log("您已击杀了 " + m.name);
            }
        }
        monsters.removeIf(monster -> monster.current_blood <= 0);
        observer.monstersRenew(monsters);
    }
}
