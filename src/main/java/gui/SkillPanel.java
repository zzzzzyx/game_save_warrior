package gui;

import gameboard.GameModel;
import observation.SkillUseObserver;
import skill.AbstractSkill;
import skill.NonTargetSkill;
import skill.TargetedSkill;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkillPanel extends JPanel implements SkillUseObserver {
    private BattlePanel battlePanelShow;
    private List<JButton> skillButtons = new ArrayList<>();
    public SkillPanel(BattlePanel battlePanelShow) {
        this.setBounds(200,390,800,100);
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.setLayout(new GridLayout(1,3));
        this.battlePanelShow = battlePanelShow;
        initSkillPanel();
        GameModel.getInstance().setObserverAndInvoke(this);
    }

    private void initSkillPanel() {
        GameModel sm = GameModel.getInstance();

        for(AbstractSkill abstractSkill : sm.getSkills()){
            JButton skillButton = new JButton(abstractSkill.getName());
            if (abstractSkill instanceof TargetedSkill){
                TargetedSkill ts = (TargetedSkill) abstractSkill;
                skillButton.addActionListener((e -> battlePanelShow.activateForChooseTarget(ts)));
            }else{
                NonTargetSkill nts = (NonTargetSkill) abstractSkill;
                skillButton.addActionListener((e -> nts.trigger()));
            }
            skillButton.setFont(new Font("宋体",Font.BOLD,21));
            skillButton.setLayout(new BorderLayout());
            JLabel descriptionLabel = new JLabel("<html>"+abstractSkill.getDescription()+"</html>",JLabel.CENTER);
            skillButton.add(BorderLayout.SOUTH,descriptionLabel);
            this.add(skillButton);
            skillButtons.add(skillButton);
        }

    }

    @Override
    public void invoke(boolean playerUseSkill) {
        for(JButton b : skillButtons){
            b.setEnabled(!playerUseSkill);
        }
        this.repaint();
    }
}
