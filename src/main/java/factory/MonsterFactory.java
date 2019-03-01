package factory;

import unit.Monster;

public class MonsterFactory {
    public static Monster getMockMonster(){
        return new Monster("林间雪人",1,10000,1000,200,100,100);
    }
}
