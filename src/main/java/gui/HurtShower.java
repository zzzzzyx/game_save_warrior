package gui;

import gameboard.GameModel;
import unit.Monster;

import javax.swing.JLabel;
import java.awt.*;
import java.util.HashMap;

public class HurtShower {
    private static HashMap<Monster,JLabel> hurtLabelMap;

    public static boolean showMonsterHurt(Monster m, int num){
        if(hurtLabelMap.keySet().contains(m)) {
            showLabel(hurtLabelMap.get(m), "- " + num);
            return true;
        }else
            return false;

    }

    public static void showMonstersAttack(Monster m, int attack){
        if(hurtLabelMap.keySet().contains(m))
            showMonsterAttackLabel(hurtLabelMap.get(m),"对你造成了" + attack+"伤害");
    }

    private static void showLabel(JLabel x, String text){
        x.setFont(new Font (Font.SANS_SERIF, Font.BOLD, 23));
        x.setText(text);
        Thread t = new Thread(() -> {
            x.setVisible(true);
            //(255,255,255) to (255,0,0) white to red
            for (int color_x = 200;color_x>=0;color_x-=5) {
                x.setForeground(new Color(255,color_x,color_x));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            try {
                Thread.sleep(350);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            //(255,0,0) to (255,255,255) red to white
            for (int color_x =0;color_x<=200;color_x+=5) {
                x.setForeground(new Color(255,color_x,color_x));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            x.setVisible(false);
            GameModel.playerRoundEnd();
        });
        t.start();
    }

    private static void showMonsterAttackLabel(JLabel x, String text){
        x.setFont(new Font (Font.SANS_SERIF, Font.BOLD, 15));
        x.setText(text);
        Thread t = new Thread(() -> {
            x.setVisible(true);
            //(255,255,255) to (255,0,0) white to red
            for (int color_x = 200;color_x>=0;color_x-=3) {
                x.setForeground(new Color(255,color_x,color_x));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            try {
                Thread.sleep(350);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            //(255,0,0) to (255,255,255) red to white
            for (int color_x =0;color_x<=200;color_x+=3) {
                x.setForeground(new Color(255,color_x,color_x));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            x.setVisible(false);
        });
        t.start();
    }

    public static void updateHurtLabelMap(HashMap<Monster, JLabel> hurtLabelMap) {
        HurtShower.hurtLabelMap = hurtLabelMap;
    }
}
