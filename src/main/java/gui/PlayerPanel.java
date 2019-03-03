package gui;

import gameboard.GameModel;
import info.clearthought.layout.TableLayout;
import observation.ReloadDataObserver;
import unit.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel implements ReloadDataObserver {

    public PlayerPanel() {
        this.setBounds(10,90,180,510);
        this.setBorder(BorderFactory.createLineBorder(Color.magenta));
        reloadData();
    }

    @Override
    public void reloadData(){
        synchronized (this){

            this.removeAll();
            Player player = GameModel.getInstance().getPlayer();
            double border = 10;
            double size[][] =
                    {{border, 0.45,0.55, border},  // Columns
                            {TableLayout.FILL,TableLayout.FILL,TableLayout.FILL,
                                    TableLayout.FILL,TableLayout.FILL,TableLayout.FILL,
                                    TableLayout.FILL,TableLayout.FILL,TableLayout.FILL,
                                    TableLayout.FILL,TableLayout.FILL,TableLayout.FILL,
                                    TableLayout.FILL,TableLayout.FILL,TableLayout.FILL,
                                    TableLayout.FILL,TableLayout.FILL,TableLayout.FILL}}; // Rows

            JPanel tablePanel = new JPanel(new TableLayout(size));

            tablePanel.add (new JLabel("职业："), "1, 1, ");
            tablePanel.add (new JLabel(player.name), "2, 1");
            tablePanel.add (new JLabel("攻击："), "1, 2");
            tablePanel.add (new JLabel(player.damage+""), "2, 2");
            tablePanel.add (new JLabel("防御："), "1, 3");
            tablePanel.add (new JLabel(player.defense+""), "2, 3");

            tablePanel.add (new JLabel("武器："), "1, 5");
            tablePanel.add (new JLabel(player.weapon.name), "2, 5");
            tablePanel.add (new JLabel("武器等级:"), "1, 6");
            tablePanel.add (new JLabel(player.weapon.level+""), "2, 6");
            tablePanel.add (new JLabel("攻击加成:"), "1, 7");
            tablePanel.add (new JLabel(player.weapon.damage+""), "2, 7");
            tablePanel.add (new JLabel("防御加成:"), "1, 8");
            tablePanel.add (new JLabel(player.weapon.defense+""), "2, 8");

            tablePanel.add (new JLabel("金钱："), "1, 10");
            tablePanel.add (new JLabel(player.money+""), "2, 10");
            tablePanel.add (new JLabel("等级："), "1, 11");
            tablePanel.add (new JLabel(player.level+""), "2, 11");
            tablePanel.add (new JLabel("经验："), "1, 12");
            tablePanel.add (new JLabel(player.exp+"/"+player.levelExp), "2, 12");

            JProgressBar exp = new JProgressBar(0,player.levelExp);
            exp.setValue(player.exp);
            tablePanel.add(exp,"1,13,2,13");

            tablePanel.add (new JLabel("血量："), "1, 15");
            tablePanel.add (new JLabel(player.current_blood+"/"+player.blood), "2, 15");

            JProgressBar blood = new JProgressBar(0,player.blood);
            blood.setValue(player.current_blood);
            tablePanel.add(blood,"1,16,2,16");

            this.add(tablePanel);
        }
    }
}
