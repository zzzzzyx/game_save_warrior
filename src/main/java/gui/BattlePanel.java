package gui;

import observation.MonstersRenewObserver;
import skill.TargetedSkill;
import unit.Monster;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class BattlePanel extends JPanel implements MonstersRenewObserver {

    protected final static int MONSTER_WIDTH = 180;
    protected final static int MONSTER_HEIGHT = 175;
    TargetedSkill targetedSkill;

    public BattlePanel() {
        this.setBounds(200,10,800,370);
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    protected JButton generateMonsterButton(Monster monster){
        JButton monsterButton = new JButton(monster.name);
        monsterButton.setPreferredSize(new Dimension(MONSTER_WIDTH, MONSTER_HEIGHT));
        monsterButton.setEnabled(false);
        monsterButton.setForeground(Color.BLUE);
        monsterButton.setFont(new Font (Font.SANS_SERIF, Font.BOLD, 15));
        monsterButton.setLayout(null);

        JLabel damage = new JLabel("攻击力："+monster.damage,JLabel.CENTER);
        damage.setBounds(0,125,MONSTER_WIDTH,15);
        monsterButton.add(damage);

        JLabel defense = new JLabel("防御力："+monster.defense,JLabel.CENTER);
        defense.setBounds(0,145,MONSTER_WIDTH,15);
        monsterButton.add(defense);

        JProgressBar blood = new JProgressBar(0,monster.blood);
        blood.setValue(monster.current_blood);
        blood.setBounds(2,MONSTER_HEIGHT-9,MONSTER_WIDTH-4,5);
        monsterButton.add(blood);

        return monsterButton;
    }

    @Override
    public void monstersRenew(List<Monster> monsters) {

        for (Component c: this.getComponents()){
            if(c instanceof JButton){
                this.remove(c);
            }
        }

        HashMap<Monster,JLabel> hurtLabelMap = new HashMap<>();

        for(Monster monster : monsters) {
            var monsterButton = generateMonsterButton(monster);

            JLabel l = new JLabel("",JLabel.CENTER);
            l.setForeground(Color.white);
            l.setFont(new Font (Font.SANS_SERIF, Font.BOLD, 23));
            l.setVisible(false);
            l.setSize(MONSTER_WIDTH,50);

            hurtLabelMap.put(monster,l);
            monsterButton.add(l);

            monsterButton.addActionListener((e)->targetedSkill.trigger(monster));

            this.add(monsterButton);
        }
        HurtShower.updateHurtLabelMap(hurtLabelMap);
        this.repaint();
    }

    public void activateForChooseTarget(TargetedSkill targetedSkill) {
        this.setBorder(BorderFactory.createLineBorder(Color.red));
        this.targetedSkill = targetedSkill;
        for (Component c: this.getComponents()){
            if(c instanceof JButton){
                JButton btn = (JButton)c;
                btn.setEnabled(true);
            }
        }
    }

}
