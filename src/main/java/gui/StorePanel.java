package gui;

import gameboard.Buyable;
import gameboard.Store;
import observation.ReloadDataObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StorePanel extends JPanel implements ReloadDataObserver {

    protected final static int SQUARE_WIDTH = 190;
    protected final static int SQUARE_HEIGHT = 230;

    public StorePanel() {
        this.setBounds(200,10,800,480);
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    @Override
    public void reloadData(){
        this.removeAll();
        this.repaint();

        Store store = Store.getInstance();
        List<Buyable> products = store.getProducts();
        for(Buyable product : products){
            JButton productButton = new JButton(product.productName);
            productButton.setPreferredSize(new Dimension(SQUARE_WIDTH, SQUARE_HEIGHT));
            productButton.addActionListener((e) -> product.buy());
            productButton.setLayout(new BorderLayout());
            Font f=new Font("宋体",Font.BOLD,17);//根据指定字体名称、样式和磅值大小，创建一个新 Font。
            productButton.setFont(f);
            productButton.setEnabled(product.canBuy());

            JLabel moneyLabel = new JLabel("所需金币："+product.money,JLabel.CENTER);
            moneyLabel.setForeground(new Color(205, 114, 29));
            productButton.add(BorderLayout.NORTH,moneyLabel);

            JLabel descriptionLabel = new JLabel("<html>"+product.description+"</html>",JLabel.CENTER);
            productButton.add(BorderLayout.SOUTH,descriptionLabel);
            this.add(productButton);
        }
    }

}
