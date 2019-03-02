package gui;

import observation.LevelChangeObserver;

import javax.swing.*;
import java.awt.*;

public class MapChangePanel extends JPanel {


    public MapChangePanel(LevelChangeObserver levelChangeObserver) {

        this.setBounds(35,10,130,70);
        this.setLayout(new GridLayout(2,1));

        JButton storeButton = new JButton("前往商店");
        storeButton.addActionListener((e -> levelChangeObserver.goToLevel(-1)));
        this.add(storeButton);

        JPanel comboPanel = new JPanel(new FlowLayout());
        comboPanel.add(new JLabel("前往楼层:"));
        JComboBox<Integer> comboBox=new JComboBox<>();
        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addActionListener((e) -> levelChangeObserver.goToLevel(1+comboBox.getSelectedIndex()));
        comboPanel.add(comboBox);
        this.add(comboPanel);
    }

}
