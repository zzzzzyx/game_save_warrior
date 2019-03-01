package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillPanel extends JPanel {
    private BattlePanel battlePanel;
    public SkillPanel(BattlePanel battlePanel) {
        this.setBounds(200,480,800,100);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.battlePanel = battlePanel;
        initSkillPanel();
    }

    private void initSkillPanel() {
        this.setLayout(new GridLayout(1,3));

        JButton loginButton = new JButton("平砍");
        loginButton.addActionListener(new panelChangeListener());
        this.add(loginButton);
        JButton login2Button = new JButton("平砍");
        this.add(login2Button);
        JButton login3Button = new JButton("平砍");
        this.add(login3Button);
    }

    class panelChangeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            battlePanel.testHurtLabel();
        }
    }
}
