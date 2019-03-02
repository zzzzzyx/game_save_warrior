package gameboard;

import gui.LogPanel;

public class GlobalLogger {
    private static LogPanel logPanel;

    public static void bindLogPanel(LogPanel logPanel){
        GlobalLogger.logPanel = logPanel;
    }
    public static void log(String info){
        logPanel.append(info);
    }
}
