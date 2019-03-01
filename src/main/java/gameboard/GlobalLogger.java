package gameboard;

import gui.LogPanel;

public class GlobalLogger {
    private static LogPanel logPanel;

    public static void initLogPanel(LogPanel logPanel){
        GlobalLogger.logPanel = logPanel;
    }
    public static void log(String s){
        logPanel.append(s);
    }
}
