package gui;

import javax.swing.JLabel;
import java.awt.*;

class HurtShower {
    static void showLabel(JLabel x, int num){
        x.setText("- " + num);
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
        });
        t.start();
    }
}
