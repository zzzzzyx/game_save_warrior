package gameboard;

import gui.LogPanel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalLogger {
    private static LogPanel logPanel;

    public static void bindLogPanel(LogPanel logPanel){
        GlobalLogger.logPanel = logPanel;
    }
    public static void log(String info){
        SimpleDateFormat df = new SimpleDateFormat("[HH:mm:ss]: ");
        logPanel.append(df.format(new Date()) + info);
    }
}
