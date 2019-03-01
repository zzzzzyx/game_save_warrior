package gui;

import factory.MapFactory;
import unit.Monster;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BattlePanel extends JPanel {

    private List<JLabel> hurtLabel;
    private final static int MONSTER_WIDTH = 180;
    private final static int MONSTER_HEIGHT = 175;

    List<Monster> monsters;

    BattlePanel() {
        this.setBounds(200,100,800,370);
        this.setBorder(BorderFactory.createLineBorder(Color.red));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        monsters = MapFactory.getMockMap();
        initBattlePanel();
    }

    private void initBattlePanel() {

        hurtLabel = new ArrayList<>();

        for(Monster monster : monsters) {
            JButton loginButton = new JButton(monster.name);
            loginButton.setPreferredSize(new Dimension(MONSTER_WIDTH, MONSTER_HEIGHT));
            loginButton.setEnabled(false);

            loginButton.setLayout(new BorderLayout());
            JLabel l = new JLabel("",JLabel.CENTER);
            l.setForeground(Color.white);
            l.setFont(new Font (Font.SANS_SERIF, Font.BOLD, 22));
            l.setVisible(false);
            hurtLabel.add(l);
            loginButton.add(BorderLayout.NORTH, l);

            loginButton.add(BorderLayout.SOUTH,new JLabel("血量：3000"));
            loginButton.add(BorderLayout.SOUTH,new JLabel("血量：3000"));

            this.add(loginButton);
        }
    }

    void testHurtLabel(){
        var num = 2367;
        for(JLabel x : hurtLabel){
            num += 1;
            HurtShower.showLabel(x,num);
        }
    }
}
