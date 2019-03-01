package factory;

import unit.Monster;

public class MonsterFactory {
    private static int count = 0;
    public static Monster getMockMonster(){
        count ++;
        return new Monster("林间雪人 "+count,1,3000,1000,200,100,100);
    }
}
