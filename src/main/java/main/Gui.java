package main;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class Gui extends JPanel
{
    private static final int DEFAULT_SCREEN_WIDTH = 960;
    private static final int DEFAULT_SCREEN_HEIGHT = 720;
    private static Gui instance = null;

    public static Gui getInstance()
    {
        if (instance == null) { instance = new Gui(); }
        return instance;
    }

    private Gui()
    {
        this.setPreferredSize(new Dimension(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT));
        this.setBackground(Color.white);
    }
}
