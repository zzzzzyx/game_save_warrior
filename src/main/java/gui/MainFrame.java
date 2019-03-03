package gui;

import gameboard.GameModel;
import gameboard.GlobalLogger;
import observation.LevelChangeObserver;
import unit.Mage;
import unit.Player;
import unit.Warrior;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements LevelChangeObserver {

    private BattlePanel battlePanelShow;
    private SkillPanel skillPanel;
    private StorePanel storePanel;
    private PlayerPanel playerPanel;
    private JPanel choosePanel;
    private MapChangePanel mapChangePanel;

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.initProfessionChoose();
    }

    private void initProfessionChoose(){
        this.setTitle("Princess Save Warrior");
        this.setSize(1010, 630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setLocation((screenSize.width - 1010)/2, (screenSize.height - 630)/2);

        choosePanel = new JPanel();

        choosePanel.setLayout(new GridLayout(1,2));

        JButton warrior = new JButton("战士");
        warrior.addActionListener((e)->initLevel(new Warrior()));
        choosePanel.add(warrior);

        JButton mage = new JButton("法师");
        mage.addActionListener((e)->initLevel(new Mage()));
        choosePanel.add(mage);
        this.add(choosePanel);

        this.setVisible(true);
    }

    private void initLevel(Player player){
        this.remove(choosePanel);
        this.repaint();
        this.setLayout(null);
        GameModel gm = GameModel.getInstance();
        gm.initPlayer(player);
        gm.addMainFrame(this);

        battlePanelShow = new BattlePanel();
        this.add(battlePanelShow);
        gm.setObserverAndInvoke(battlePanelShow);
        gm.addLevelChangeObserver(this);

        skillPanel = new SkillPanel(battlePanelShow);
        this.add(skillPanel);

        LogPanel logPanel = new LogPanel(new JEditorPane());
        this.add(logPanel);
        GlobalLogger.bindLogPanel(logPanel);

        mapChangePanel = new MapChangePanel(this);
        this.add(mapChangePanel);

        playerPanel = new PlayerPanel();
        this.add(playerPanel);
        gm.addReloadDataObservers(playerPanel);
        // 设置界面可见
        this.setVisible(true);
        this.revalidate();
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

    public void gameOver() {
        this.remove(skillPanel);
        this.remove(battlePanelShow);
        this.remove(mapChangePanel);
        this.repaint();
    }
}