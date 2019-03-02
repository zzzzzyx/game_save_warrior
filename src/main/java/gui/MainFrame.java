package gui;

import gameboard.GameModel;
import gameboard.GlobalLogger;
import observation.LevelChangeObserver;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements LevelChangeObserver {

    private BattlePanel battlePanelShow;
    private SkillPanel skillPanel;
    private StorePanel storePanel;

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.initLevel();
    }

    private void initLevel(){
        this.setTitle("Princess Save Warrior");
        this.setLayout(null);
        this.setSize(1010, 630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setLocation((screenSize.width - 1010)/2, (screenSize.height - 630)/2);

        battlePanelShow = new BattlePanel();
        this.add(battlePanelShow);
        GameModel gm = GameModel.getInstance();
        gm.setObserverAndInvoke(battlePanelShow);
        gm.addLevelChangeObserver(this);

        skillPanel = new SkillPanel(battlePanelShow);
        this.add(skillPanel);

        LogPanel logPanel = new LogPanel(new JEditorPane());
        this.add(logPanel);
        GlobalLogger.bindLogPanel(logPanel);

        MapChangePanel mapChangePanel = new MapChangePanel(this);
        this.add(mapChangePanel);

        PlayerPanel playerPanel = new PlayerPanel();
        this.add(playerPanel);
        gm.addReloadDataObservers(playerPanel);

        // 设置界面可见
        this.setVisible(true);
    }

    @Override
    public void goToLevel(int level) {
        if(storePanel != null){
            this.remove(storePanel);
            storePanel = null;
        }else{
            this.remove(skillPanel);
            this.remove(battlePanelShow);
        }
        if(level < 0){//go to store
            storePanel = new StorePanel();
            this.add(storePanel);
            storePanel.reloadData();
            GameModel gm = GameModel.getInstance();
            gm.addReloadDataObservers(storePanel);
            GlobalLogger.log("离开当前战斗，您已进入商店");
        }else{//go to level
            GameModel.changeLevel(level);
            battlePanelShow = new BattlePanel();
            skillPanel = new SkillPanel(battlePanelShow);
            this.add(skillPanel);
            this.add(battlePanelShow);
            GameModel gm = GameModel.getInstance();
            gm.setObserverAndInvoke(battlePanelShow);
            GlobalLogger.log("您进入第"+level+"层");
        }
        this.repaint();
    }
}