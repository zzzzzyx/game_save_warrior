package gameboard;

import unit.Player;

public abstract class Buyable {
    public int money;
    public String productName;
    public String description;

    public Buyable(int money, String productName, String description) {
        this.money = money;
        this.productName = productName;
        this.description = description;
    }

    public void buy(){
        GameModel gm = GameModel.getInstance();
        Player player = gm.getPlayer();
        player.money -= this.money;
        buy_sub(player);
        GlobalLogger.log("您已购买【"+productName+"】,花费"+money+"金币，剩余金币为"+player.money);
        gm.reloadData();
    }

    public abstract void buy_sub(Player player);

    public boolean canBuy() {
        int player_money = GameModel.getInstance().getPlayer().money;
        return player_money>=this.money;
    }
}
