/*  
 *  Main.java
 *  
 *  Description: Sets up application.
 * 
 *  Running Project: java -jar target/Hero-Tales-1.0-SNAPSHOT.jar (DEPRECATED)
 *
*/

package main;
import javax.swing.JFrame;

public class Main 
{
    public static void main( String[] args )
    {
        // Setup window properties
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Java Database Interface");

        // Integrate Gui into the window
        Gui gui = Gui.getInstance();
        window.add(gui);
        window.pack();

        // Center and display window
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
