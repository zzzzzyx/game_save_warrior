package gameboard;

import unit.Monster;

import java.util.ArrayList;
import java.util.List;

public class MapFactory {

    public static int[] LEVEL_EXP = {0,1500,5000,20000};
    public static int[] LEVEL_MONEY = {0,2000,6000,18000};

    public static List<Monster> getMap(int level){
        List<Monster> l = new ArrayList<>();
        if(level == 1){
            l.add(getSmallMonster());
            l.add(getSmallMonster());
            l.add(getSmallMonster());
            l.add(getSmallMonster());
            l.add(getMediumMonster());
            l.add(getMediumMonster());
        }else if(level == 2){
            l.add(getSmallMonster());
            l.add(getSmallMonster());
            l.add(getMediumMonster());
            l.add(getMediumMonster());
            l.add(getMediumMonster());
            l.add(getMediumMonster());
            l.add(getMediumMonster());
        }else if(level == 3){
            l.add(getSmallMonster());
            l.add(getMediumMonster());
            l.add(getMediumMonster());
            l.add(getMediumMonster());
            l.add(getMediumMonster());
            l.add(getLargeMonster());
            l.add(getLargeMonster());
            l.add(getLargeMonster());
        }
        return l;
    }

    private static int count = 0;
    public static Monster getSmallMonster(){
        count ++;
        return new Monster("林间雪怪 "+count,4000,1000,200);
    }

    public static Monster getMediumMonster(){
        count ++;
        return new Monster("嘶吼猛狮 "+count,6000,1500,500);
    }

    public static Monster getLargeMonster(){
        count ++;
        return new Monster("钢铁暴龙 "+count,10000,2500,1000);
    }

}
