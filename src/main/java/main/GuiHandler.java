/*  
 *  GuiHandler.java
 *  
 *  Description: Handles all GUI functions.
 *
*/

package main;
import java.awt.Dimension;
import javax.swing.JFrame;

import gui.*;

public class GuiHandler
{
    private static final int DEFAULT_SCREEN_WIDTH = 960;
    private static final int DEFAULT_SCREEN_HEIGHT = 720;
    private static GuiHandler instance = null;
    private JFrame window = new JFrame();
    private NavigationBar navBar = new NavigationBar();

    public static GuiHandler getInstance()
    {
        if (instance == null) { instance = new GuiHandler(); }
        return instance;
    }

    private GuiHandler()
    {
        createWindow();
        window.setJMenuBar(navBar);
    }

    private void createWindow()
    {
        window.setSize(new Dimension(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Java Database Interface");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
