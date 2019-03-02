package gui;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

public class LogPanel extends JScrollPane {

    JEditorPane logTextPanel;
    public LogPanel(JEditorPane logTextPanel) {
        super(logTextPanel);
        this.logTextPanel = logTextPanel;
        logTextPanel.setEditable(false);
        this.setBounds(200,500,800,100);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

    public void append(String str) {
        try {
            Document doc = logTextPanel.getDocument();
            doc.insertString(doc.getLength(), "\n"+str, null);
            this.getVerticalScrollBar().setValue(this.getVerticalScrollBar().getMaximum());
        } catch(BadLocationException exc) {
            exc.printStackTrace();
        }
    }
}
