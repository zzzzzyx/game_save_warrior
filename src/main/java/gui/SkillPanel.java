package gui;

import gameboard.GameModel;
import skill.AbstractSkill;
import skill.NonTargetSkill;
import skill.TargetedSkill;

import javax.swing.*;
import java.awt.*;

public class SkillPanel extends JPanel {
    private BattlePanel battlePanelShow;
    public SkillPanel(BattlePanel battlePanelShow) {
        this.setBounds(200,390,800,100);
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.setLayout(new GridLayout(1,3));
        this.battlePanelShow = battlePanelShow;
        initSkillPanel();
    }

    private void initSkillPanel() {
        GameModel sm = GameModel.getInstance();

        for(AbstractSkill abstractSkill : sm.getSkills()){
            JButton loginButton = new JButton(abstractSkill.getName());
            if (abstractSkill instanceof TargetedSkill){
                TargetedSkill ts = (TargetedSkill) abstractSkill;
                loginButton.addActionListener((e -> battlePanelShow.activateForChooseTarget(ts)));
            }else{
                NonTargetSkill nts = (NonTargetSkill) abstractSkill;
                loginButton.addActionListener((e -> nts.trigger()));
            }
            loginButton.setFont(new Font("宋体",Font.BOLD,21));
            loginButton.setLayout(new BorderLayout());
            JLabel descriptionLabel = new JLabel("<html>"+abstractSkill.getDescription()+"</html>",JLabel.CENTER);
            loginButton.add(BorderLayout.SOUTH,descriptionLabel);
            this.add(loginButton);
        }

    }
}
