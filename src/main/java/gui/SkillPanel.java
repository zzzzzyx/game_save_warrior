package gui;

import gameboard.SkillModel;
import skill.AbstractSkill;
import skill.NonTargetSkill;
import skill.TargetedSkill;
import skill.W_NormalAttack_L1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillPanel extends JPanel {
    private BattlePanel battlePanelShow;
    public SkillPanel(BattlePanel battlePanelShow) {
        this.setBounds(200,480,800,100);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridLayout(1,3));
        this.battlePanelShow = battlePanelShow;
        initSkillPanel();
    }

    private void initSkillPanel() {
        SkillModel sm = SkillModel.getInstance();

        for(AbstractSkill abstractSkill : sm.getSkills()){
            JButton loginButton = new JButton(abstractSkill.getName());
            if (abstractSkill instanceof TargetedSkill){
                TargetedSkill ts = (TargetedSkill) abstractSkill;
                loginButton.addActionListener((e -> battlePanelShow.activateForChooseTarget(ts)));
            }else{
                NonTargetSkill nts = (NonTargetSkill) abstractSkill;
                loginButton.addActionListener((e -> nts.trigger()));
            }
            this.add(loginButton);
        }

    }

    class panelChangeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            battlePanelShow.testHurtLabel();
            battlePanelShow.activateForChooseTarget(new W_NormalAttack_L1());

        }
    }
}
