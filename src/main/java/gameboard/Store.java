package gameboard;

import skill.AbstractSkill;
import skill.SkillImprovement;
import weapon.*;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static Store instance;
    private List<Buyable> products;

    private Store(){
        products = new ArrayList<>();
        products.addAll(List.of(new W_Shield(),new W_Sword(),new W_Balance()));
        products.add(new WeaponImprovement());
        GameModel gm = GameModel.getInstance();
        for(AbstractSkill skill : gm.getSkills()){
            products.add(new SkillImprovement(skill));
        }
    }

    public List<Buyable> getProducts() {
        return products;
    }

    public static Store getInstance() {
        if(instance == null){
            instance =  new Store();
        }
        return instance;
    }
}
