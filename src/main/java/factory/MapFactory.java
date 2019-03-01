package factory;

import unit.Monster;

import java.util.ArrayList;
import java.util.List;

public class MapFactory {
    public static List<Monster> getMockMap(){
        var monsterList = new ArrayList<Monster>();
        for(int i=0;i<6;i++){
            monsterList.add(MonsterFactory.getMockMonster());
        }
        return monsterList;
    }
}
