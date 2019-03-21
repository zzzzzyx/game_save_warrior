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

        products.add(new W_Shield());
        products.add(new W_Sword());
        products.add(new W_Balance());
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
