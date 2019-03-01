package gui;

import javax.swing.*;
import java.awt.*;


public class MainFrame {

    private BattlePanel battlePanel;
    private SkillPanel skillPanel;

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.start();
    }

    private void start(){
        JFrame frame = new JFrame("Princess Save Warrior");

        frame.setLayout(null);
        frame.setSize(1200, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        frame.setLocation((screenSize.width - 1200)/2, (screenSize.height - 750)/2);

        battlePanel = new BattlePanel();
        frame.add(battlePanel);

        skillPanel = new SkillPanel(battlePanel);
        frame.add(skillPanel);

        // 设置界面可见
        frame.setVisible(true);
    }

}