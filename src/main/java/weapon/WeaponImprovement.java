package weapon;

import gameboard.Buyable;
import unit.Player;

public class WeaponImprovement extends Buyable {
    double damage_add_ratio = 0.5;
    double defense_add_ratio = 0.5;

    public WeaponImprovement() {
        super(300, "武器强化", "将武器的攻击或防御提升50%");
    }

    public void improve(Weapon w){
        w.damage += w.damage*damage_add_ratio;
        w.defense += w.defense*defense_add_ratio;
        w.level += 1;
    }

    @Override
    public void buy_sub(Player player) {
        improve(player.weapon);
    }
}
